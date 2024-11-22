package com.example.cartService.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.cartService.dto.CartItemChangeQuantityDTO;
import com.example.cartService.dto.CartItemDTO;
import com.example.cartService.dto.CartItemInsertDTO;
import com.example.cartService.dto.CartItemRemoveDTO;
import com.example.cartService.dto.CartPaymentDTO;
import com.example.cartService.dto.CartPaymentStatusChangeDTO;
import com.example.cartService.dto.ClientCartDTO;
import com.example.cartService.dto.DetailedItemDTO;
import com.example.cartService.dto.FormPaymentDTO;
import com.example.cartService.dto.FreeResourcesDTO;
import com.example.cartService.dto.ItemDTO;
import com.example.cartService.dto.PagamentoDTO;
import com.example.cartService.dto.PagamentoEncomendaDTO;
import com.example.cartService.dto.RequestDTO;
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
import com.example.cartService.repositories.ItemEncomendaRepository;
import com.example.cartService.repositories.PagamentoRepository;
/*
 * Most things here are a work in progress
 * take it with a grain of salt
 */

@Service
public class CartService {
    
    private final ClientCartRepository clientCartRepository;
    private final CartRepository cartRepository;
    private final ClientCartMapper clientCartMapper;
    private final ClientPagamentoMapper clientPagamentoMapper;
    private final PagamentoRepository pagamentoRepository;
    private final ItemEncomendaRepository itemEncomendaRepository;
    private final List<Thread> paymentThreads = new ArrayList<>();
    private RestTemplate restTemplate;
    /* private final PayPalHttpClient payPalClient;
    
    @Value("${paypal.client.id}")
    private String paypalClientId;
    
    @Value("${paypal.client.secret}")
    private String paypalClientSecret;
*/
    public CartService(ClientCartRepository clientCartRepository,PagamentoRepository pagamentoRepository ,CartRepository cartRepository,ItemEncomendaRepository itemEncomendaRepository, ClientCartMapper clientCartMapper,ClientPagamentoMapper clientPagamentoMapper, RestTemplate restTemplate) {
        this.clientCartRepository = clientCartRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.cartRepository = cartRepository;
        this.clientCartMapper = clientCartMapper;
        this.itemEncomendaRepository = itemEncomendaRepository;
        this.clientPagamentoMapper = clientPagamentoMapper;
        this.restTemplate = restTemplate;

        //PayPalEnvironment environment = new PayPalEnvironment.Sandbox(paypalClientId, paypalClientSecret);
        //this.payPalClient = new PayPalHttpClient(environment);
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
        for(Item i : cart.getItens()){
            ItemEncomenda item = itemEncomendaRepository.getItemByIdCode(i.getCodigo(), i.getIdLoja());
            item.setImagem(i.getImagem());
            item.setDesignacao(i.getDesignacao());
            item.setPreco(i.getPreco());
            itemEncomendaRepository.save(item);
        }
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

    public void createFormPayment(FormPaymentDTO paymentInfo) {
        Cliente cliente = clientCartRepository.getClienteByUsername(paymentInfo.getUsername());
        if (cliente == null) {
            //throw new NoClientException("Client not found with username: " + item.getUsername());
            cliente = new Cliente("",paymentInfo.getUsername(),"");
        }

        Cart cart = cliente.getCart();
        if (cart == null) {
            // Create new cart if user doesn't have one
            cart = new Cart();
            cliente.setCart(cart);
        }
        cartRepository.save(cart);
        Pagamento payment = new Pagamento();
        payment.setCodigo(paymentInfo.getCodigo());
        payment.setLocalEntrega("");
        payment.setInicioAluguer("");
        payment.setFimAluguer("");
        payment.setModoPagamento(paymentInfo.getModoPagamento());
        payment.setTotal(paymentInfo.getTotal());
        payment.setdataGeracao(paymentInfo.getDataGeracao());
        payment.setStatus(paymentInfo.getStatus());
        cliente.addTransaction(payment);

        clientCartRepository.save(cliente);
        System.out.println("saving:"+cliente);
        this.checkPayment(payment.getCodigo());
    }


    @Transactional
    @Async
    public void createPayment(CartPaymentDTO paymentInfo) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(paymentInfo.getUsername());
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + paymentInfo.getUsername());
        }

        Cart cart = cliente.getCart();
        if (cart == null || cart.getItens().isEmpty()) {
            throw new NoCartException("Cart is empty for client: " + paymentInfo.getUsername());
        }

        
        Pagamento payment = new Pagamento();
        payment.setCodigo(paymentInfo.getCodigo());
        payment.setLocalEntrega(paymentInfo.getLocalEntrega());
        payment.setInicioAluguer(paymentInfo.getInicioAluguer());
        payment.setFimAluguer(paymentInfo.getFimAluguer());
        payment.setModoPagamento(paymentInfo.getModoPagamento());
        payment.setTotal(paymentInfo.getTotal());
        payment.setdataGeracao(paymentInfo.getDataGeracao());
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
        //clearCart(paymentInfo.getUsername());
        this.checkPayment(payment.getCodigo());
    }

    public List<PagamentoDTO> getUserTransactions(String username,int page, int number) throws NoClientException {
        int idx_inicial = page * number;
        int idx_final = idx_inicial + number;
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }
        List<PagamentoDTO> pagamentoDTOs = cliente.getTransacoes().stream()
            .map(x-> clientPagamentoMapper.toPagamentoDTO(x)).collect(Collectors.toList());
        return pagamentoDTOs.stream().sorted(new DateComparator()).collect(Collectors.toList()).subList(idx_inicial, idx_final);
        
    }

    public PagamentoEncomendaDTO getPagamentoByCode(String codigo){
        Pagamento p = null;
        p = pagamentoRepository.findByCode(codigo);
        PagamentoEncomendaDTO pagamentoEncomendaDTO = new PagamentoEncomendaDTO(p);
        Set<DetailedItemDTO> s = new HashSet<>();
        for(ItemEncomenda i : p.getitens()){
            ItemEncomenda item = itemEncomendaRepository.getItemByIdCode(i.getCodigo(),i.getIdLoja());
            s.add(new DetailedItemDTO(item.getPreco(),item.getDesignacao(),item.getImagem(),item.getIdLoja(),item.getQuantidade(),item.getCodigo()));
        }
        pagamentoEncomendaDTO.setItems(s);
        return pagamentoEncomendaDTO;
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

    @Transactional
    @Async
    public void checkPayment(String codigo) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(100000);
                System.out.println(codigo);
                Pagamento p = pagamentoRepository.findByCode(codigo);
                if(p==null) p = pagamentoRepository.findByCodeForm(codigo);
                System.out.println(p);
                if(p.getStatus().equals("PENDING") && p.getCodigo().startsWith("CART")) {
                    pagamentoRepository.deleteById(p.getORMID());
                    String gatewayUrl = "http://localhost:8888/api/catalogoService/freeItems";
                    restTemplate.postForObject(gatewayUrl, new FreeResourcesDTO(p.getitens().stream().map(x->new ItemDTO(x.getCodigo(),x.getIdLoja(),x.getQuantidade())).toList()), String.class);
                }
                if(p.getStatus().equals("PENDING") && p.getCodigo().startsWith("FORM")) {
                    pagamentoRepository.deleteById(p.getORMID());
                    String gatewayUrl = "http://localhost:8888/api/recomendacoesService/removePedido";
                    restTemplate.postForObject(gatewayUrl, new RequestDTO(p.getCodigo(),"DELETED"), String.class);
                }
                /*
                if(p.getStatus().equals("PAYED")){
                    pagamentoRepository.deleteById(p.getIDPagamento());
                }*/
                this.paymentThreads.removeIf((x)->x.getName().equals(codigo));

            } catch (Exception e) {
                System.out.println(e);
            }
            
        });
        t.setName(codigo);
        this.paymentThreads.add(t);
        t.start();
    }



    /*
    public String processPayPalPayment(CartPaymentPaypalDTO paymentInfo) throws NoClientException, IOException {
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
        HttpResponse<Order> response = payPalClient.execute(request);
        
        if (response.result().status().equals("COMPLETED")) {
            // Create payment record
            CartPaymentDTO newpaymentInfo = new CartPaymentDTO();
            newpaymentInfo.setUsername(paymentInfo.getUsername());
            newpaymentInfo.setCodigo(paymentInfo.getCodigo());
            newpaymentInfo.setModoPagamento("PAYPAL");
            newpaymentInfo.setStatus("PAID");
            
            // Use existing createPayment method to save the transaction
            createPayment(newpaymentInfo);
            
            return "Payment processed successfully";
        } else {
            throw new RuntimeException("PayPal payment failed with status: " + response.result().status());
        }
    }*/
}