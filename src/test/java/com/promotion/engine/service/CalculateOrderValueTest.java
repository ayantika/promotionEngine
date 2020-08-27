package com.promotion.engine.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.promotion.engine.dto.LineItem;
import com.promotion.engine.dto.Order;
import com.promotion.engine.dto.Promotion;

class CalculateOrderValueTest {
	

	@Autowired
	CalculateOrderValue calculateOrderValue;

	@Test
	void test() {
		CreatePromotion createpromo = new CreatePromotion();
		List<Promotion> promoList= createpromo.getPromotionList();
		Order order = new Order();
		LineItem item1 = new LineItem();
		LineItem item2 = new LineItem();
		LineItem item3 = new LineItem();
		item1.setSkuId("A");
		item1.setItemCount(1);
		item1.setPrice(50);
		item2.setSkuId("B");
		item2.setItemCount(1);
		item2.setPrice(30);
		item2.setSkuId("C");
		item2.setItemCount(1);
		item2.setPrice(20);
		order.getItems().add(item1);
		order.getItems().add(item2);
		order.getItems().add(item3);
		calculateOrderValue.getOrderValue(order, promoList);
		
	}

}
