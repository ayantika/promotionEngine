package com.promotion.engine.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
	List<LineItem> items;
}
