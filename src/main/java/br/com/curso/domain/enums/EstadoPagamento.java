package br.com.curso.domain.enums;

import br.com.curso.services.exceptions.ObjectNotFoundExceptions;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null)
			return null;
		
		for( EstadoPagamento estadoPagamento : EstadoPagamento.values() ) {
			if( estadoPagamento.getCod() == cod )
				return estadoPagamento;
		}
		
		throw new ObjectNotFoundExceptions("Objeto não encontrado com id "+cod);
		
	}
	
}
