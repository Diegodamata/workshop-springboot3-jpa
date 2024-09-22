package com.diegodev.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String descrition;
	private Double price;
	private String imgUrl;
	
	//assiciassão muitos para muitos, no banco isso não é possivel, então escolho qualquer uma das classe para fazer essa operação
	@ManyToMany(fetch = FetchType.EAGER) //define como as assiciações devem ser feitas no banco, indica que todos os dados associados a essa tabela 
	//devem ser carregadas imediatamente de forma automatica, assim não tendo o erro, de lazy loading carregamento tardio
	//EAGER JPA irá buscar as entidades associadas no mesmo momento.
	//quando temos associassão muitos para muitos precisamos criar uma nova tabela 
	//crio uma nova tabela defino um nome, com o joinColumns, eu informo que essa tabela ira conter a chave estrangeira da tabela produto 
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id")) //e faço o inverso, para que essa tabela tambem contenha a chave estrangeira da tabela category
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "id.product", fetch = FetchType.EAGER)
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {
	}

	public Product(Long id, String name, String descrition, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.descrition = descrition;
		this.price = price;
		this.imgUrl = imgUrl;
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

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Set<Category> getCategories() {
		return categories;
	}
	
	//o meu produto precisa apenas buscar o pedido associado a ele
	//então eu crio um metodo get para a minha coleção set 
	//dessa forma o meu produto com id do OrderItem com o id do Order
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for (OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
}
