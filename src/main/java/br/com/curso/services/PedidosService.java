package br.com.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.domain.Pedido;
import br.com.curso.repositories.PedidoRepository;
import br.com.curso.services.exceptions.ObjectNotFoundExceptions;

@Service
public class PedidosService {

	@Autowired
	private PedidoRepository rep;
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> pedido = rep.findById(id);
		
		return pedido.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado"));
	}
	
}
