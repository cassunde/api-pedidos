package br.com.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.domain.Categoria;
import br.com.curso.repositories.CategoriaRepository;
import br.com.curso.services.exceptions.ObjectNotFoundExceptions;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository rep;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> categoria = rep.findById(id);
		
		return categoria.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado"));
	}
	
}
