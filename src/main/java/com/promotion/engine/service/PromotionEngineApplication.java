package com.promotion.engine.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.promotion.engine.dto.Order;

@SpringBootApplication
public class PromotionEngineApplication {


	public static void main(String[] args) {
		SpringApplication.run(PromotionEngineApplication.class, args);
		calculateOrder();
		
	}

	private static String calculateOrder() {
		CalculateOrderValue calculateOrderValue = new CalculateOrderValue();
		Order order = new Order();
		String orderPrice = calculateOrderValue.getOrderValue(order, null);
		return orderPrice;
	}
	


}
