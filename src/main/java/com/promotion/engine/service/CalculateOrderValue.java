package com.promotion.engine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promotion.engine.dto.LineItem;
import com.promotion.engine.dto.Order;
import com.promotion.engine.dto.Promotion;
import com.promotion.engine.dto.SkuCombination;

@Service
public class CalculateOrderValue {



	public String getOrderValue(Order order,List<Promotion> promoList) {
		// TODO Auto-generated method stub
		checkForPromotionPrice(order, promoList);
		return null;
	}



	private double checkForPromotionPrice(Order order, List<Promotion> promoList) {
		double price = 0;
		
		promoList.stream().forEach(a -> {
			double promoPrice;
			if (a.getType().equals("1")) {
				promoPrice = getPriceForType1(order, a.getSkuCombinationList(), a.getPromoPrice());
			} else {
				promoPrice = getPriceForType2(order, a.getSkuCombinationList(), a.getPromoPrice());
			}

		});
		
		return price;
	}

	private double getPriceForType2(Order order, List<SkuCombination> skuCombinationList, double promoPrice) {
		// TODO Auto-generated method stub
		return 0;
	}

	private double getPriceForType1(Order order, List<SkuCombination> skuCombinationList, double promoPrice) {
		double price = 0;
		for (SkuCombination combi : skuCombinationList) {
			for (LineItem item : order.getItems()) {
				double totalcombiPrice = 0;
				double remainingItemPrice = 0;
				if (item.getSkuId().contains(combi.getSkuId())) {
					int itemCountInOrder = item.getItemCount();
					int itemCountInCombi = combi.getCountOfItems();
					if ((itemCountInOrder > itemCountInCombi)
							&& (itemCountInOrder % itemCountInCombi < itemCountInCombi)) {

						totalcombiPrice = (itemCountInOrder / itemCountInCombi) * promoPrice;
						int remainigItems = item.getItemCount() - combi.getCountOfItems();
						remainingItemPrice = remainigItems * (item.getPrice());
					}

				}
				price = totalcombiPrice+remainingItemPrice;
			}
		}
		
		return price;
	}

}
