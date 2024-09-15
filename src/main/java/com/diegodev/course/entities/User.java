package com.diegodev.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//interface serializable quando vc quer transformar seu objeto em cadeia de bytes, para que seu objeto trafegue na rede
//para que possa ser gravado em arquivo, persistencia de dados etc...

@Entity
@Table(name = "tb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	//@JsonIgnoreé útil para evitar problemas de referências circulares e controlar quais dados são retornados.
	//quando eu transformo esse objeto em json, os outros valores não serão incluidos e vice versa
    @JsonIgnore //essa anotação faz parte da biblioteca jackson, serve para serialização (quando o objeto é convertido em JSON) ou desserialização (quando JSON é convertido em um objeto Java).
	
    //Quando voce tem uma associassão para muitos o jpa não carrega o objeto para muitos, para não estourar a memoria de trafego no computador
    @OneToMany(mappedBy = "client") //informo eu o meu user é uma relação de um para muitos e informo que ele esta mapeado na classe order pelo atributo client
	private List<Order> orders = new ArrayList<>();
	
	//como estou usando fremeword sou obrigado a usar o constructor vazio
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
