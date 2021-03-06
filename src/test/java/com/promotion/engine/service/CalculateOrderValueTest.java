package com.promotion.engine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.promotion.engine.dto.LineItem;
import com.promotion.engine.dto.Order;
import com.promotion.engine.dto.Promotion;

class CalculateOrderValueTest {
	

	@Test
	void testScenarioA() {
		GetPromotions getPromotions = new GetPromotions();
		List<Promotion> promoList= getPromotions.getPromotionList();
		Order order = new Order();
		List<LineItem> itemList = new ArrayList();
		LineItem item1 = new LineItem();
		LineItem item2 = new LineItem();
		LineItem item3 = new LineItem();
		item1.setSkuId("A");
		item1.setItemCount(1);
		item1.setPrice(50);
		item2.setSkuId("B");
		item2.setItemCount(1);
		item2.setPrice(30);
		item3.setSkuId("C");
		item3.setItemCount(1);
		item3.setPrice(20);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		order.setItems(itemList);
		CalculateOrderValue calculateOrderValue = new CalculateOrderValue();
		double price = calculateOrderValue.getOrderValue(order, promoList);
		assertEquals(price,100);
	}
	
	@Test
	void testScenarioB() {
		GetPromotions getPromotions = new GetPromotions();
		List<Promotion> promoList= getPromotions.getPromotionList();
		Order order = new Order();
		List<LineItem> itemList = new ArrayList();
		LineItem item1 = new LineItem();
		LineItem item2 = new LineItem();
		LineItem item3 = new LineItem();
		item1.setSkuId("A");
		item1.setItemCount(5);
		item1.setPrice(50);
		item2.setSkuId("B");
		item2.setItemCount(5);
		item2.setPrice(30);
		item3.setSkuId("C");
		item3.setItemCount(1);
		item3.setPrice(20);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		order.setItems(itemList);
		CalculateOrderValue calculateOrderValue = new CalculateOrderValue();
		double price = calculateOrderValue.getOrderValue(order, promoList);
		assertEquals(price,370);
	}	
	
	@Test
	void testScenarioC() {
		GetPromotions getPromotions = new GetPromotions();
		List<Promotion> promoList= getPromotions.getPromotionList();
		Order order = new Order();
		List<LineItem> itemList = new ArrayList();
		LineItem item1 = new LineItem();
		LineItem item2 = new LineItem();
		LineItem item3 = new LineItem();
		LineItem item4 = new LineItem();
		item1.setSkuId("A");
		item1.setItemCount(3);
		item1.setPrice(50);
		item2.setSkuId("B");
		item2.setItemCount(5);
		item2.setPrice(30);
		item3.setSkuId("C");
		item3.setItemCount(1);
		item3.setPrice(20);
		item4.setSkuId("D");
		item4.setItemCount(1);
		item4.setPrice(15);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);
		order.setItems(itemList);
		CalculateOrderValue calculateOrderValue = new CalculateOrderValue();
		double price = calculateOrderValue.getOrderValue(order, promoList);
		assertEquals(price,280);
	}	

}
