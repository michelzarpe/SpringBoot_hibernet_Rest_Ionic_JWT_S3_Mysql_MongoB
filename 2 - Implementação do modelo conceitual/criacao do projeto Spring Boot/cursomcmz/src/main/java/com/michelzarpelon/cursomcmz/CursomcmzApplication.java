package com.michelzarpelon.cursomcmz;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.print.attribute.standard.PDLOverrideSupported;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.michelzarpelon.cursomcmz.domain.Categoria;
import com.michelzarpelon.cursomcmz.domain.Cidade;
import com.michelzarpelon.cursomcmz.domain.Cliente;
import com.michelzarpelon.cursomcmz.domain.Endereco;
import com.michelzarpelon.cursomcmz.domain.Estado;
import com.michelzarpelon.cursomcmz.domain.ItemPedido;
import com.michelzarpelon.cursomcmz.domain.Pagamento;
import com.michelzarpelon.cursomcmz.domain.PagamentoComCartao;
import com.michelzarpelon.cursomcmz.domain.PatamentoComBoleto;
import com.michelzarpelon.cursomcmz.domain.Pedido;
import com.michelzarpelon.cursomcmz.domain.Produto;
import com.michelzarpelon.cursomcmz.domain.enums.EstadoPagamento;
import com.michelzarpelon.cursomcmz.domain.enums.TipoCliente;
import com.michelzarpelon.cursomcmz.repositories.CategoriaRepository;
import com.michelzarpelon.cursomcmz.repositories.CidadeRepository;
import com.michelzarpelon.cursomcmz.repositories.ClienteRepository;
import com.michelzarpelon.cursomcmz.repositories.EnderecoRepository;
import com.michelzarpelon.cursomcmz.repositories.EstadoRepository;
import com.michelzarpelon.cursomcmz.repositories.ItemPedidoRepository;
import com.michelzarpelon.cursomcmz.repositories.PagamentoRepository;
import com.michelzarpelon.cursomcmz.repositories.PedidoRepository;
import com.michelzarpelon.cursomcmz.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcmzApplication implements CommandLineRunner{

	
	@Autowired
	private CategoriaRepository repositorioCategoria;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcmzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p1));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
	
		repositorioCategoria.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefone().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardins", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));		
		clienteRepository.save(cli1);
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Pagamento pagt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);	
		ped1.setPagamento(pagt1);
		Pagamento pagt2 = new PatamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf1.parse("20/10/2017"), null);
		ped2.setPagamento(pagt2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagt1,pagt2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
	
		
	}
	
	
}

