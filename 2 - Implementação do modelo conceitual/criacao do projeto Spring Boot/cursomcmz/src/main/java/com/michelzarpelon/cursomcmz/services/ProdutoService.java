package com.michelzarpelon.cursomcmz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelzarpelon.cursomcmz.domain.Produto;
import com.michelzarpelon.cursomcmz.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	private Produto buscar(Integer id) {
		Produto produto = produtoRepository.findOne(id);
		return produto;
	}
	

	
}
