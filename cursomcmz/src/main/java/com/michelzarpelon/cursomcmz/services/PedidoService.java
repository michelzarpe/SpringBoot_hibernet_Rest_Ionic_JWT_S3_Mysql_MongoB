package com.michelzarpelon.cursomcmz.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelzarpelon.cursomcmz.domain.ItemPedido;
import com.michelzarpelon.cursomcmz.domain.PagamentoComCartao;
import com.michelzarpelon.cursomcmz.domain.PatamentoComBoleto;
import com.michelzarpelon.cursomcmz.domain.Pedido;
import com.michelzarpelon.cursomcmz.domain.enums.EstadoPagamento;
import com.michelzarpelon.cursomcmz.repositories.ItemPedidoRepository;
import com.michelzarpelon.cursomcmz.repositories.PagamentoRepository;
import com.michelzarpelon.cursomcmz.repositories.PedidoRepository;
import com.michelzarpelon.cursomcmz.repositories.ProdutoRepository;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorioObj;
	
	@Autowired
	private PagamentoRepository repositorioPag;
	
	@Autowired
	private ProdutoRepository repositorioProd;
	
	@Autowired
	private ItemPedidoRepository repositorioItemPedido;
	
	public Pedido find(Integer id) {
		Pedido pedido = repositorioObj.findOne(id);
		if(pedido==null) {
			throw new ObjectNotFoundException("Objeto não encontrado: "+id+", Tipo do objeto: "+Pedido.class.getName());
		}
		return pedido;
	}


	public Pedido insert(Pedido _obj) {
		_obj.setId(null);
		_obj.setInstante(new Date());
		_obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		_obj.getPagamento().setPedido(_obj);
		
		//suposicao pra calcular as datas
		if (_obj.getPagamento() instanceof PatamentoComBoleto) {
			PatamentoComBoleto pagamento = (PatamentoComBoleto) _obj.getPagamento();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(_obj.getInstante());
			cal.add(Calendar.DAY_OF_MONTH, 7);
			pagamento.setDataVencimento(cal.getTime());
		}
		
		_obj = repositorioObj.save(_obj);
		repositorioPag.save(_obj.getPagamento());
		//pegando os precos do produtos que estao nos itens de pedido do pedido
		for(ItemPedido ip: _obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(repositorioProd.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(_obj);
		}
		repositorioItemPedido.save(_obj.getItens());
		
		return _obj;
	}
	
	
	
}
