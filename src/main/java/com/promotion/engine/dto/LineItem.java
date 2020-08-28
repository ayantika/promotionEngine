package com.promotion.engine.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LineItem {
	String skuId;
	int itemCount;
	double price;

}
