package com.michelzarpelon.cursomcmz.domain.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.michelzarpelon.cursomcmz.services.validation.ClienteInsert;

@ClienteInsert  /*classe de validacao criada*/
public class NovoClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Favor preencher o campo!")
	@Length(max = 100, min = 10, message = "Favor Preencher campo com no 5 a 80 ctrs!")
	private String nome;
	@NotEmpty(message = "Favor preencher o campo!")
	@Email(message = "E-mail inválido")
	private String email;
	@NotEmpty(message = "Favor preencher o campo!")
	private String cpfOuCnpj;
	private Integer tipo;
	@NotEmpty(message = "Favor preencher o campo!")
	private String logradouro;
	@NotEmpty(message = "Favor preencher o campo!")
	private String numero;
	private String complemento;
	private String bairro;
	@NotEmpty(message = "Favor preencher o campo!")
	private String cep;
	@NotEmpty(message = "Favor preencher o campo!")
	private String telefone1;
	private String telefone2;
	private String telefone3;

	private Integer cidadeId;

	public NovoClienteDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
