package com.jordan.cursomc.services;

import com.jordan.cursomc.domain.*;
import com.jordan.cursomc.domain.enums.EstadoPagamento;
import com.jordan.cursomc.domain.enums.Perfil;
import com.jordan.cursomc.domain.enums.TipoCliente;
import com.jordan.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired 
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritòrio");
		Categoria cat3 = new Categoria(null, "Perfumaria");
		Categoria cat4 = new Categoria(null, "Cama mesa e banho");
		Categoria cat5 = new Categoria(null, "Decoração");
		Categoria cat6 = new Categoria(null, "Jardim");
		Categoria cat7 = new Categoria(null, "Carros");
		Categoria cat8 = new Categoria(null, "Motos");
		Categoria cat9 = new Categoria(null, "Eletronicos");
		Categoria cat10 = new Categoria(null, "Celulares");
		Categoria cat11 = new Categoria(null, "Perfumaria");
		Categoria cat12 = new Categoria(null, "Audio");
		Categoria cat13 = new Categoria(null, "Video");
		Categoria cat14 = new Categoria(null, "Moveis");
		Categoria cat15 = new Categoria(null, "Automação Residencial");
		Categoria cat16 = new Categoria(null, "Iluminação");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "jordannegreirossantos@hotmail.com", "363789912377", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Cliente cli2 = new Cliente(null, "Jordan", "jordannegreirossantos@gmail.com", "16419125707", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		cli2.addPerfil(Perfil.ADMIN);


		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim", "3822034", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua Amarildo", "375", null, "Centro", "25570760", cli2, c2);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6,cat7, cat8, cat9, cat10, cat11, cat12, cat13, cat14, cat15, cat16));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
