package com.diegodev.course.entities.enums;

public enum OrderStatus {
	
	//o tipo enumerado é representado como um valor do tipo inteiro, porem se futuramente eu precisar adicionar mais um tipo
	//no meu banco os valores ira quebrar, então para que isso não aconteça eu defino um valor para cada um deles

	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	//como eu defini um valor para cada tipo preciso criar um construtor para atribuir 
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static OrderStatus valueOf(int code) {
		
		for(OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
