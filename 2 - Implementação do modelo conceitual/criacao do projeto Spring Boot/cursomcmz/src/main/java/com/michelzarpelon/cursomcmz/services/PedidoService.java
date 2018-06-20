package com.michelzarpelon.cursomcmz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michelzarpelon.cursomcmz.domain.Pedido;
import com.michelzarpelon.cursomcmz.repositories.PedidoRepository;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public Pedido find(Integer id) {
		Pedido pedido = pedidoRepository.findOne(id);
		if(pedido==null) {
			throw new ObjectNotFoundException("Objeto não encontrado: "+id+", Tipo do objeto: "+Pedido.class.getName());
		}
		return pedido;
	}
	
	
	
}
