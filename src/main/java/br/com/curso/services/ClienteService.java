package br.com.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.domain.Cliente;
import br.com.curso.repositories.ClienteRepository;
import br.com.curso.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository rep;
	
	public Cliente buscar(Integer id) {
		
		Optional<Cliente> cliente = rep.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado"));
	}
	
}
