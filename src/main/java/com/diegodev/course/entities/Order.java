package com.diegodev.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.diegodev.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//formatando da data no padrão iso 8601
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	//o tipo enum agora interno ele é do tipo inteiro
	private Integer orderStatus;
	
	//como eu defino que a minha classe é uma entidade do banco, como user e order estão associados 
	@ManyToOne //defino que order e user tem uma assiciação de muitos para um
	@JoinColumn(name = "client_id") //e defino um nome para a minha chave estrangeira
	private User client;
	
	
	@OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)
	private Set<OrderItem> items = new HashSet<>();
	
	//um Order não precisa obrigatoriamente ter um payment
	//a classe order é a classe independente ou principal
	//digo q esse atributo esta mapeado pelo atributo order da classe payment
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	//mapeando as duas entidades para ter o mesmo id, se o pedido ter o codigo 5 o pagamento desse pedido tambem deve ser o codigo 5
	//o cascading permite que as operações realizadas na entidade "pai" sejam automaticamente aplicadas às entidades "filhas" ou relacionadas.
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDate() {
		return moment;
	}

	public void setDate(Instant moment) {
		this.moment = moment;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
		
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}
