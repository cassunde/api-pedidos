package br.com.curso.domain;

import javax.persistence.Entity;

import br.com.curso.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcela;

	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDePercela) {
		super(id, estadoPagamento, pedido);
		this.numeroDeParcela = numeroDePercela;
	}

	public Integer getNumeroDeParcela() {
		return numeroDeParcela;
	}

	public void setNumeroDeParcela(Integer numeroDeParcela) {
		this.numeroDeParcela = numeroDeParcela;
	}
	
}
