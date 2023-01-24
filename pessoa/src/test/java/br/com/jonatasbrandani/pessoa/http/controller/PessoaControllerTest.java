package br.com.jonatasbrandani.pessoa.http.controller;
	

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.jonatasbrandani.pessoa.models.Endereco;
import br.com.jonatasbrandani.pessoa.models.Pessoa;
import br.com.jonatasbrandani.pessoa.repository.EnderecoRepository;
import br.com.jonatasbrandani.pessoa.repository.PessoaRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest

	public class PessoaControllerTest {

		@Autowired
		private PessoaController pessoaController;

		@Autowired
		private PessoaRepository pessoaRepository;

		@Autowired
		private EnderecoRepository enderecoRepository;

		@Test
		public void createPessoaTest() {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setDataNascimento(new Date());

			Pessoa result = pessoaController.createPessoa(pessoa);

			assertNotNull(result);
			assertEquals(pessoa.getNome(), result.getNome());
			assertEquals(pessoa.getDataNascimento(), result.getDataNascimento());
		}

		@Test
		public void updatePessoaTest() {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setDataNascimento(new Date());

			Pessoa savedPessoa = pessoaRepository.save(pessoa);
			savedPessoa.setNome("Teste Atualizado");

			Pessoa result = pessoaController.updatePessoa(savedPessoa.getId(), savedPessoa);
			assertNotNull(result);
			assertEquals(pessoa.getNome(), result.getNome());

		}

		@Test
		public void getPessoaTest() {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setDataNascimento(new Date());

			Pessoa savedPessoa = pessoaRepository.save(pessoa);

			Pessoa result = pessoaController.getPessoa(savedPessoa.getId());

			assertNotNull(result);
			assertEquals(savedPessoa.getId(), result.getId());
			assertEquals(savedPessoa.getNome(), result.getNome());
		}

		@Test
		public void getPessoasTest() {
			Pessoa pessoa1 = new Pessoa();
			pessoa1.setNome("Teste1");
			pessoa1.setDataNascimento(new Date());

			Pessoa pessoa2 = new Pessoa();
			pessoa2.setNome("Teste2");
			pessoa2.setDataNascimento(new Date());

			pessoaRepository.save(pessoa1);
			pessoaRepository.save(pessoa2);

			List<Pessoa> result = pessoaController.getPessoas();

			assertNotNull(result);
			assertEquals(2, result.size());
		}

		@Test
		public void deletePessoaTest() {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setDataNascimento(new Date());

			Pessoa savedPessoa = pessoaRepository.save(pessoa);

			pessoaController.deletePessoa(savedPessoa.getId());

			Pessoa result = pessoaController.getPessoa(savedPessoa.getId());

			assertNull(result);
		}

		@Test
		public void createEnderecoTest() {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setDataNascimento(new Date());

			Pessoa savedPessoa = pessoaRepository.save(pessoa);

			Endereco endereco = new Endereco();
			endereco.setLogradouro("Rua Teste");
			endereco.setCep("12345678");
			endereco.setNumero(123);
			endereco.setCidade("Cidade Teste");

			Endereco result = pessoaController.createEndereco(savedPessoa.getId(), endereco);

			assertNotNull(result);
			assertEquals(endereco.getLogradouro(), result.getLogradouro());
			assertEquals(endereco.getCep(), result.getCep());
			assertEquals(endereco.getNumero(), result.getNumero());
			assertEquals(endereco.getCidade(), result.getCidade());
		}

		@Test
		public void updateEnderecoTest() {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setDataNascimento(new Date());

			pessoaRepository.save(pessoa);

			Endereco endereco = new Endereco();
			endereco.setLogradouro("Rua Teste");
			endereco.setCep("12345678");
			endereco.setNumero(123);
			endereco.setCidade("Cidade Teste");

			Endereco savedEndereco = enderecoRepository.save(endereco);
			savedEndereco.setLogradouro("Rua Teste Atualizado");

			Endereco result = pessoaController.updateEndereco(savedEndereco.getId(), savedEndereco);

			assertNotNull(result);
			assertEquals(savedEndereco.getLogradouro(), result.getLogradouro());

		}

		@Test
		public void getEnderecosTest() {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setDataNascimento(new Date());

			Pessoa savedPessoa = pessoaRepository.save(pessoa);

			Endereco endereco1 = new Endereco();
			endereco1.setLogradouro("Rua Teste1");
			endereco1.setCep("12345678");
			endereco1.setNumero(123);
			endereco1.setCidade("Cidade Teste1");
			endereco1.setPessoa(savedPessoa);

			Endereco endereco2 = new Endereco();
			endereco2.setLogradouro("Rua Teste2");
			endereco2.setCep("87654321");
			endereco2.setNumero(456);
			endereco2.setCidade("Cidade Teste1");
			endereco2.setPessoa(savedPessoa);

			enderecoRepository.save(endereco1);
			enderecoRepository.save(endereco2);

			List<Endereco> result = pessoaController.getEnderecos(savedPessoa.getId());

			assertNotNull(result);
			assertEquals(2, result.size());
		}

		@Test
		public void deleteEnderecoTeste() {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setDataNascimento(new Date());

			pessoaRepository.save(pessoa);

			Endereco endereco = new Endereco();

			endereco.setLogradouro("Rua Teste");
			endereco.setCep("12345678");
			endereco.setNumero(123);
			endereco.setCidade("Cidade Teste");

			Endereco savedEndereco = enderecoRepository.save(endereco);

			pessoaController.deleteEndereco(savedEndereco.getId());

			Endereco result = enderecoRepository.findById(savedEndereco.getId()).orElse(null);
			assertNull(result);

		}

	}
