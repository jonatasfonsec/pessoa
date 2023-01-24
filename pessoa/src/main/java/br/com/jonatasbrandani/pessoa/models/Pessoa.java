package br.com.jonatasbrandani.pessoa.models;

import java.util.Date;
import java.util.List;

import org.hibernate.engine.internal.Cascade;
import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
public class Pessoa{
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_principal_id",referencedColumnName = "id")
	private Endereco enderecoPrincipal;
	
	@OneToMany(mappedBy = "pessoa",cascade = CascadeType.ALL)
	private List<Endereco> enderecos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	public void setEnderecoPrincipal(Endereco enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	
	
}