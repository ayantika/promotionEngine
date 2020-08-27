package com.promotion.engine.dto;

import java.util.List;

public class Order {


	List<LineItem> items;

	public List<LineItem> getItems() {
		return items;
	}

	public void setItems(List<LineItem> items) {
		this.items = items;
	}



}
