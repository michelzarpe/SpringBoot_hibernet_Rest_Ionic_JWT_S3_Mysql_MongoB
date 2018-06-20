package com.michelzarpelon.cursomcmz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michelzarpelon.cursomcmz.domain.Produto;
import com.michelzarpelon.cursomcmz.repositories.ProdutoRepository;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	private Produto find(Integer id) {
		Produto produto = produtoRepository.findOne(id);
		if(produto==null) {
			throw new ObjectNotFoundException("Objeto não encontrado: "+id+", Tipo do objeto: "+Produto.class.getName());
		}
		return produto;
	}
	

	
}
