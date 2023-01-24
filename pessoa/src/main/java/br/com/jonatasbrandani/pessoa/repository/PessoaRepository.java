package br.com.jonatasbrandani.pessoa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonatasbrandani.pessoa.models.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	
	


}
