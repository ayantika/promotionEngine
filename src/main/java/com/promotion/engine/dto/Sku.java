package com.promotion.engine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sku {

	long id;
	String name;
	String description;
	double price;

	public Sku(long id, String name, String description, double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}


}
