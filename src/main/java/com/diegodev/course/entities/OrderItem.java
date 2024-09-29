package com.diegodev.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.diegodev.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	//como em lugar nenhum essa classe foi instanciada, precisei instanciar, no atributo, senão comecara valendo null
	@EmbeddedId //anotação que define no banco que é o id da chave primaria composta
	private OrderItemPK id = new OrderItemPK(); //precisa ser instanciado se não vai lançar uma exception nullpointerexception
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	//para não dar uma referencia cicrica, quando acontece um loop, e uma dependencia chama a outra
	//com na minha classe OrderItem não contem o atributo order para cortar essa referencia
	//no java EE é utilizado o metodo get para cortar essa referencia
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	//dessa forma ira trazer o produto e os pedidos associados a ele, porem dessa forma não é correta
	//quando buscar os produtos eu simplesmente so que os produtos
	//@JsonIgnore
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSubTotal() {
		return price * quantity;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
