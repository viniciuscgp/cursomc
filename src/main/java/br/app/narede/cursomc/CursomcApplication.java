package br.app.narede.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.app.narede.cursomc.domain.Categoria;
import br.app.narede.cursomc.domain.Cidade;
import br.app.narede.cursomc.domain.Cliente;
import br.app.narede.cursomc.domain.Endereco;
import br.app.narede.cursomc.domain.Estado;
import br.app.narede.cursomc.domain.Produto;
import br.app.narede.cursomc.domain.enums.TipoCliente;
import br.app.narede.cursomc.repositories.CategoriaRepository;
import br.app.narede.cursomc.repositories.CidadeRepository;
import br.app.narede.cursomc.repositories.ClienteRepository;
import br.app.narede.cursomc.repositories.EnderecoRepository;
import br.app.narede.cursomc.repositories.EstadoRepository;
import br.app.narede.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		categoriaRepository.flush();
		produtoRepository.flush();
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//--------------------------------------------------------------
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		estadoRepository.saveAllAndFlush(Arrays.asList(est1, est2));
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		cidadeRepository.saveAllAndFlush(Arrays.asList(c1, c2, c3));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAllAndFlush(Arrays.asList(est1, est2));
		cidadeRepository.saveAllAndFlush(Arrays.asList(c1, c2, c3));
		
		//--------------------------------------------------------------
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "3332222", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		

	}
}
