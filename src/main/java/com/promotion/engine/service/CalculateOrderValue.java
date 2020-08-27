package com.promotion.engine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promotion.engine.dto.LineItem;
import com.promotion.engine.dto.Order;
import com.promotion.engine.dto.Promotion;
import com.promotion.engine.dto.SkuCombination;

@Service
public class CalculateOrderValue {

	public double getOrderValue(Order order, List<Promotion> promoList) {
		// TODO Auto-generated method stub
		return checkForPromotionPrice(order, promoList);

	}

	private double checkForPromotionPrice(Order order, List<Promotion> promoList) {
		double price = 0;
		double promoPrice1 = 0;
		double promoPrice2 = 0;
		double promoPrice3 = 0;
		for (Promotion promo : promoList) {
			if (promo.getType().equals("1")) {
				promoPrice1 = getPriceForType1(order, promo.getSkuCombinationList(), promo.getPromoPrice());
			} else if (promo.getType().equals("2")) {
				promoPrice2 = getPriceForType2(order, promo.getSkuCombinationList(), promo.getPromoPrice());
			} else if (promo.getType().equals("3")) {
				promoPrice3 = getPriceForType3(order, promo.getSkuCombinationList(), promo.getPromoPrice());
			}
		}

		price = promoPrice1 + promoPrice2 + promoPrice3;
		return price;
	}

	private double getPriceForType3(Order order, List<SkuCombination> skuCombinationList, double promoPrice) {
		Set<String> promoItemSet = new HashSet();
		boolean applyOffer;
		double price = 0;
		for (SkuCombination combi : skuCombinationList) {
			promoItemSet.add(combi.getSkuId());
		}

		Map<String, Integer> itemsMap = new HashMap();
		order.getItems().forEach(item -> {
			itemsMap.put(item.getSkuId(), item.getItemCount());
		});
		if (isPromotionApplicable(itemsMap, promoItemSet)) {
			
		} else {
			price = calculatePriceWithoutOffer(order.getItems(), promoItemSet);
		}
		return price;
	}

	private double calculatePriceWithoutOffer(List<LineItem> items,Set<String> promoItemSet) {
		double price = 0;
		for(LineItem item : items) {
			if(promoItemSet.contains(item.getSkuId())) {
				price = price + item.getPrice();
			}
		}
		return price;
	}

	private boolean isPromotionApplicable(Map<String, Integer> itemsMap, Set<String> promoItemSet) {
		return itemsMap.keySet().containsAll(promoItemSet);

	}

	private double getPriceForType2(Order order, List<SkuCombination> skuCombinationList, double promoPrice) {
		return getPriceForType1(order, skuCombinationList, promoPrice);
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
						int itemsInOffer = (itemCountInOrder / itemCountInCombi)* combi.getCountOfItems();
						int remainigItems = item.getItemCount() - itemsInOffer;
						
						remainingItemPrice = remainigItems * (item.getPrice());
					} else {
						price = itemCountInOrder * (item.getPrice());
					}

				}
				price = price + totalcombiPrice + remainingItemPrice;
			}
		}

		return price;
	}

}
