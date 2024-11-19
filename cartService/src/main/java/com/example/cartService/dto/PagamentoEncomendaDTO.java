package com.example.cartService.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.cartService.model.Pagamento;

public class PagamentoEncomendaDTO{
    	
	private double total;
	private String localEntrega;
	private String inicioAluguer;
	private String fimAluguer;
	private String codigo;
	private String modoPagamento;
	private String dataGeracao;
	private String status;
    private Set<DetailedItemDTO> itens;

	public PagamentoEncomendaDTO() {
	}

	public PagamentoEncomendaDTO(Pagamento p) {
		this.total = p.getTotal();
		this.localEntrega = p.getLocalEntrega();
		this.inicioAluguer = p.getInicioAluguer();
		this.fimAluguer = p.getFimAluguer();
		this.codigo = p.getCodigo();
		this.modoPagamento = p.getModoPagamento();
		this.dataGeracao = p.getdataGeracao();
		this.status = p.getStatus();
		this.itens = new HashSet<>();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public String getInicioAluguer() {
		return inicioAluguer;
	}

	public void setInicioAluguer(String inicioAluguer) {
		this.inicioAluguer = inicioAluguer;
	}

	public String getFimAluguer() {
		return fimAluguer;
	}

	public void setFimAluguer(String fimAluguer) {
		this.fimAluguer = fimAluguer;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getModoPagamento() {
		return modoPagamento;
	}

	public void setModoPagamento(String modoPagamento) {
		this.modoPagamento = modoPagamento;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<DetailedItemDTO> getItems() {
		return itens;
	}

	public void setItems(Set<DetailedItemDTO> itens) {
		this.itens = itens;
	}
}