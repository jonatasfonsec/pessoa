package br.com.jonatasbrandani.pessoa.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonatasbrandani.pessoa.models.Endereco;
import br.com.jonatasbrandani.pessoa.models.Pessoa;
import br.com.jonatasbrandani.pessoa.repository.EnderecoRepository;
import br.com.jonatasbrandani.pessoa.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")

public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@PostMapping
	public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@PutMapping("/{id}")
	public Pessoa updatePessoa(@PathVariable Long id,@RequestBody Pessoa pessoa) {
		pessoa.setId(id);
		return pessoaRepository.save(pessoa);
	}
	
	@GetMapping("/{id}")
	public Pessoa getPessoa(@PathVariable Long id) {
		return pessoaRepository.findById(id).orElse(null);
	}
	
	@GetMapping
	public List<Pessoa> getPessoas(){
		return pessoaRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void deletePessoa(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}
	
	@PostMapping("/{id}/enderecos")
	public Endereco createEndereco(@PathVariable Long id, @RequestBody Endereco endereco){
		Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
		endereco.setPessoa(pessoa);
		return enderecoRepository.save(endereco);
	}
	
	@PostMapping("/{id}/enderecos")
	public Endereco updateEndereco(@PathVariable Long id,@RequestBody Endereco endereco) {
		endereco.setId(id);
		return enderecoRepository.save(endereco);
	}
	
	@GetMapping("/{id}/enderecos")
	public List<Endereco> getEnderecos(@PathVariable Long id){
		Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
		return enderecoRepository.findByPessoa(pessoa);
	}
	
	@DeleteMapping("/enderecos/{id}")
	public void deleteEndereco(@PathVariable Long id) {
		enderecoRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	

}
