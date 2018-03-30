package br.com.curso;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.curso.domain.Categoria;
import br.com.curso.domain.Cidade;
import br.com.curso.domain.Cliente;
import br.com.curso.domain.Endereco;
import br.com.curso.domain.Estado;
import br.com.curso.domain.ItemPedido;
import br.com.curso.domain.Pagamento;
import br.com.curso.domain.PagamentoComBoleto;
import br.com.curso.domain.PagamentoComCartao;
import br.com.curso.domain.Pedido;
import br.com.curso.domain.Produto;
import br.com.curso.domain.enums.EstadoPagamento;
import br.com.curso.domain.enums.TipoCliente;
import br.com.curso.repositories.CategoriaRepository;
import br.com.curso.repositories.CidadeRepository;
import br.com.curso.repositories.ClienteRepository;
import br.com.curso.repositories.EnderecoRepository;
import br.com.curso.repositories.EstadoRepository;
import br.com.curso.repositories.ItemPedidoRepository;
import br.com.curso.repositories.PagamentoRepository;
import br.com.curso.repositories.PedidoRepository;
import br.com.curso.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoApiApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRespository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRep;
	
	@Autowired
	private PagamentoRepository pagamentoRep;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRep;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll( Arrays.asList(produto1, produto2, produto3) );
		cat2.getProdutos().add( produto2 );
		
		produto1.getCategorias().add(cat1);
		produto2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		produto3.getCategorias().add(cat1);
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll( Arrays.asList(produto1,produto2, produto3) );
		
		Estado minas = new Estado(null, "Minas Gerais");
		Estado sao = new Estado(null, "São Paulo");
		
		Cidade uber = new Cidade(null, "Uberlândia", minas);
		Cidade saoPaulo = new Cidade(null, "São Paulo", sao);
		Cidade campinas = new Cidade(null, "Campinas", sao);
		
		minas.getCidades().add(uber);
		sao.getCidades().addAll(Arrays.asList(saoPaulo, campinas));
		
		estadoRepository.saveAll(Arrays.asList( minas, sao ));
		cidadeRepository.saveAll( Arrays.asList( uber, saoPaulo, campinas ) );
		
		
		Cliente maria = new Cliente(null, "Maria do Rosário", "maria.rosa@gmail.com", "02596325877", TipoCliente.PESSOAFISICA);
		maria.getTelefones().addAll(Arrays.asList("85749965","85965898"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "apt 213", "Jardim", "646552258", maria, uber);
		Endereco end2 = new Endereco(null, "AV Gomes de matos", "300", "apt 213", "Jardim", "646552258", maria, saoPaulo);
		
		maria.getEnderecos().addAll( Arrays.asList(end1, end2) );
		
		clienteRespository.save(maria);
		enderecoRepository.saveAll( Arrays.asList( end1, end2 ) );
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), maria, end1);
		
		Pagamento pgaCartao = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgaCartao);
		
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), maria, end2);
		Pagamento pgaBoleto = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgaBoleto);
		
		maria.getPedidos().addAll( Arrays.asList( ped1, ped2 ) );
		
		pedidoRep.saveAll( Arrays.asList( ped1, ped2 ) );
		pagamentoRep.saveAll( Arrays.asList( pgaBoleto, pgaCartao ) );
		
		ItemPedido item1 = new ItemPedido(ped1, produto1, 0.00, 1, 2000.00);
		ItemPedido item2 = new ItemPedido(ped1, produto3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(ped2, produto2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(item1, item2));
		ped2.getItens().add(item3);
		
		produto1.getItens().addAll( Arrays.asList(item1) );
		produto2.getItens().addAll( Arrays.asList(item3) );
		produto3.getItens().addAll( Arrays.asList(item2) );
		

		itemPedidoRep.saveAll( Arrays.asList( item1,item2, item3) );
		
	}
}
