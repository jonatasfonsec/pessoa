package br.com.jonatasbrandani.pessoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jonatasbrandani.pessoa.models.Endereco;
import br.com.jonatasbrandani.pessoa.models.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByPessoa(Pessoa pessoa);


}
