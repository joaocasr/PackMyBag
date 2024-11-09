package com.example.cartService.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.cartService.dto.CartItemChangeQuantityDTO;
import com.example.cartService.dto.CartItemDTO;
import com.example.cartService.dto.CartItemInsertDTO;
import com.example.cartService.dto.CartItemRemoveDTO;
import com.example.cartService.dto.CartPaymentDTO;
import com.example.cartService.dto.CartPaymentStatusChangeDTO;
import com.example.cartService.dto.ClientCartDTO;
import com.example.cartService.dto.PagamentoDTO;
import com.example.cartService.exceptions.NoCartException;
import com.example.cartService.exceptions.NoClientException;
import com.example.cartService.exceptions.NoItemException;
import com.example.cartService.exceptions.NoPaymentException;
import com.example.cartService.mappers.CartItemMapper;
import com.example.cartService.mappers.ClientCartMapper;
import com.example.cartService.mappers.ClientPagamentoMapper;
import com.example.cartService.model.Cart;
import com.example.cartService.model.Cliente;
import com.example.cartService.model.Item;
import com.example.cartService.model.ItemEncomenda;
import com.example.cartService.model.Pagamento;
import com.example.cartService.repositories.CartRepository;
import com.example.cartService.repositories.ClientCartRepository;
import com.example.cartService.repositories.PagamentoRepository;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
/*
 * Most things here are a work in progress
 * take it with a grain of salt
 */

@Service
public class CartService {
    
    private final ClientCartRepository clientCartRepository;
    private final CartRepository cartRepository;
    private final ClientCartMapper clientCartMapper;
    private final PagamentoRepository pagamentoRepository;
    private final CartItemMapper cartItemMapper;

    private final PayPalHttpClient payPalClient;
    
    @Value("${paypal.client.id}")
    private String paypalClientId;
    
    @Value("${paypal.client.secret}")
    private String paypalClientSecret;

    public CartService(ClientCartRepository clientCartRepository,PagamentoRepository pagamentoRepository ,CartRepository cartRepository, ClientCartMapper clientCartMapper, CartItemMapper cartItemMapper) {
        this.clientCartRepository = clientCartRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.cartRepository = cartRepository;
        this.clientCartMapper = clientCartMapper;
        this.cartItemMapper = cartItemMapper;

        PayPalEnvironment environment = new PayPalEnvironment.Sandbox(paypalClientId, paypalClientSecret);
        this.payPalClient = new PayPalHttpClient(environment);
    }

    public ClientCartDTO getUserCart(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }
        return clientCartMapper.toClientCartDTO(cliente);
    }

    public List<CartItemDTO> getCartItems(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            return new ArrayList<>();
        }
        return cliente.getCart().getItens().stream()
            .map(CartItemMapper::toCartItemDTO)
            .collect(Collectors.toList());
    }

    public void addToCart(CartItemInsertDTO item) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(item.getUsername());

        if (cliente == null) {
            //throw new NoClientException("Client not found with username: " + item.getUsername());
            cliente = new Cliente(item.getNome(),item.getUsername(),item.getEmail());
        }

        Cart cart = cliente.getCart();
        if (cart == null) {
            // Create new cart if user doesn't have one
            cart = new Cart();
            cliente.setCart(cart);
        }

        // Check if item already exists
        boolean itemExists = false;
        for (Item existingItem : cart.getItens()) {
            if (existingItem.getCodigo().equals(item.getCodigo()) && existingItem.getIdLoja()==item.getIdLoja()) {
                // Update quantity if item exists
                existingItem.setQuantidade(existingItem.getQuantidade() + item.getQuantidade());
                itemExists = true;
                break;
            }
        }
        cartRepository.save(cart);
        Item cartItem;
        if (!itemExists) {
            // Add new item
            cartItem = new Item();
            cartItem.setCodigo(item.getCodigo());
            cartItem.setIdLoja(item.getIdLoja());
            cartItem.setDesignacao(item.getDesignacao());
            cartItem.setImagem(item.getImagem());
            cartItem.setPreco(item.getPreco());
            cartItem.setQuantidade(item.getQuantidade());
            cart.getItens().add(cartItem);
            cartRepository.save(cart);
        }

        clientCartRepository.save(cliente);
    }
    
    /**
     * Changes the quantity of an item in the cart,
     * doesn't check if the quantity passed is valid,
     * that's done in the controller
     * @param itemChange
     * @throws NoClientException
     */
    public void changeItemQuantity(CartItemChangeQuantityDTO itemChange) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(itemChange.getUsername());
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + itemChange.getUsername());
        }

        Cart cart = cliente.getCart();
        if (cart == null) {
            throw new NoCartException("Cart not found for client: " + itemChange.getUsername());
        }

        Item existingItem = cart.getItens().stream()
            .filter(i -> i.getCodigo().equals(itemChange.getCodigo()))
            .findFirst()
            .orElse(null);

        if (existingItem == null) {
            throw new NoItemException("Item not found in cart: " + itemChange.getCodigo());
        }

        // Simply update the quantity instead of calling addToCart
        existingItem.setQuantidade(itemChange.getQuantity());
        clientCartRepository.save(cliente);
    }

    public void removeFromCart(CartItemRemoveDTO itemToRemove) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(itemToRemove.getUsername());
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + itemToRemove.getUsername());
        }

        Cart cart = cliente.getCart();
        if (cart == null) {
            throw new NoCartException("Cart not found for client: " + itemToRemove.getUsername());
        }

        cart.removeItem(itemToRemove.getCodigo(),itemToRemove.getIdLoja());
        clientCartRepository.save(cliente);
    }

    public void clearCart(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }
        
        Cart cart = cliente.getCart();
        if (cart != null) {
            cart.getItens().clear();
        } else {
            cart = new Cart();
            cart.setItens(new HashSet<>());
            cliente.setCart(cart);
        }
        
        clientCartRepository.save(cliente);
    }

    public int getCartItemsCount(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }
        return cliente.getCart().getItens().stream()
                .mapToInt(Item::getQuantidade)
                .sum();
    }

    public void createPayment(CartPaymentDTO paymentInfo) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(paymentInfo.getUsername());
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + paymentInfo.getUsername());
        }

        Cart cart = cliente.getCart();
        if (cart == null || cart.getItens().isEmpty()) {
            throw new NoCartException("Cart is empty for client: " + paymentInfo.getUsername());
        }

        // Calculate total amount
        double totalAmount = cart.getItens().stream()
                .mapToDouble(item -> item.getPreco() * item.getQuantidade())
                .sum();

        // Create new payment
        Pagamento payment = new Pagamento();
        payment.setCodigo(paymentInfo.getCodigo());
        payment.setTotal(totalAmount);
        payment.setLocalEntrega(paymentInfo.getLocalEntrega());
        payment.setInicioAluguer(paymentInfo.getInicioAluguer());
        payment.setFimAluguer(paymentInfo.getFimAluguer());
        payment.setModoPagamento(paymentInfo.getModoPagamento());
        payment.setStatus(paymentInfo.getStatus());

        paymentInfo.getItems()
                    .stream()
                    .map(x->new ItemEncomenda(x.getQuantidade(),x.getCodigo(),x.getIdloja()))
                    .forEach(
                        payment::addItemEncomenda
                    );

        System.out.println("Payment info: " + paymentInfo);
        System.out.println("Payment: " + payment);

        // Add payment to client's transactions
        cliente.addTransaction(payment);

        System.out.println("Client's transactions: " + cliente.getTransacoes());
        System.out.println("Nº of Client's transactions: " + cliente.getTransacoes().size());
        // Save changes
        clientCartRepository.save(cliente);
        //pagamentoRepository.save(payment);

        // Clear the cart after payment
        clearCart(paymentInfo.getUsername());


    }

    public Set<PagamentoDTO> getUserTransactions(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }
        return cliente.getTransacoes().stream()
            .map(ClientPagamentoMapper::toPagamentoDTO)
            .collect(Collectors.toSet());
    }

    public void changePaymentStatus(CartPaymentStatusChangeDTO paymentInfo) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(paymentInfo.getUsername());
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + paymentInfo.getUsername());
        }

        Pagamento payment = cliente.getTransacoes().stream()
                .filter(p -> p.getCodigo().equals(paymentInfo.getCodigo()))
                .findFirst()
                .orElse(null);

        if (payment == null) {
            throw new NoPaymentException("Payment not found with id: " + paymentInfo.getCodigo());
        }

        payment.setStatus(paymentInfo.getStatus());
        clientCartRepository.save(cliente);
    }

    public String processPayPalPayment(CartPaymentPaypalDTO paymentInfo) throws NoClientException {
        // Verify the client and cart
        Cliente cliente = clientCartRepository.getClienteByUsername(paymentInfo.getUsername());
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + paymentInfo.getUsername());
        }

        Cart cart = cliente.getCart();
        if (cart == null || cart.getItens().isEmpty()) {
            throw new NoCartException("Cart is empty for client: " + paymentInfo.getUsername());
        }
        
        // Capture the PayPal order
        OrdersCaptureRequest request = new OrdersCaptureRequest(paymentInfo.getPaypalOrderId());
        OrdersCapture response = payPalClient.execute(request);
        
        if (response.result().status().equals("COMPLETED")) {
            // Create payment record
            CartPaymentDTO paymentInfo = new CartPaymentDTO();
            paymentInfo.setUsername(paymentInfo.getUsername());
            paymentInfo.setCodigo(paymentInfo.getCodigo());
            paymentInfo.setModoPagamento("PAYPAL");
            paymentInfo.setStatus("PAID");
            
            // Use existing createPayment method to save the transaction
            createPayment(paymentInfo);
            
            return "Payment processed successfully";
        } else {
            throw new RuntimeException("PayPal payment failed with status: " + response.result().status());
        }
    }
}