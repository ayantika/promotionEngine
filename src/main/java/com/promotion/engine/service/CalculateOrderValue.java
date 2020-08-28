package com.promotion.engine.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.promotion.engine.dto.LineItem;
import com.promotion.engine.dto.Order;
import com.promotion.engine.dto.Promotion;
import com.promotion.engine.dto.SkuCombination;

@Service
public class CalculateOrderValue {

	/**
	 * @param order
	 * @param promoList
	 * @return
	 */
	public double getOrderValue(Order order, List<Promotion> promoList) {
		// TODO Auto-generated method stub
		return calculateOrderPrice(order, promoList);
	}

	/**
	 * @param order
	 * @param promoList
	 * @return
	 */
	private double calculateOrderPrice(Order order, List<Promotion> promoList) {

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
		return promoPrice1 + promoPrice2 + promoPrice3;
	}

	/**
	 * @param order
	 * @param skuCombinationList
	 * @param promoPrice
	 * @return
	 */
	private double getPriceForType3(Order order, List<SkuCombination> skuCombinationList, double promoPrice) {
		Set<String> promoItemSet = new HashSet();
		Map<String, Integer> itemsMap = new HashMap();
		double price = 0;
		skuCombinationList.stream().forEach(combi -> promoItemSet.add(combi.getSkuId()));

		order.getItems().forEach(item -> {
			itemsMap.put(item.getSkuId(), item.getItemCount());
		});
		if (isPromotionApplicable(itemsMap, promoItemSet)) {
			price = calculatePriceWithOffer(itemsMap, order.getItems(), promoPrice, promoItemSet);
		} else {
			price = calculatePriceWithoutOffer(order.getItems(), promoItemSet);
		}
		return price;
	}

	/**
	 * @param itemsMap
	 * @param items
	 * @param promoPrice
	 * @param promoItemSet
	 * @return
	 */
	private double calculatePriceWithOffer(Map<String, Integer> itemsMap, List<LineItem> items, double promoPrice,
			Set<String> promoItemSet) {
		Integer offerAppliedforCount = itemsMap.values().stream().min(Integer::compare).get();
		double promoItemsPrice = offerAppliedforCount * promoPrice;
		double remaingItemsPrice = 0;
		for (Entry<String, Integer> entry : itemsMap.entrySet()) {
			if ((promoItemSet.contains(entry.getKey()) && !entry.getValue().equals(offerAppliedforCount))) {
				remaingItemsPrice = (entry.getValue() - offerAppliedforCount) * getPriceForitem(items, entry.getKey());
			}
		}

		return promoItemsPrice + remaingItemsPrice;

	}

	/**
	 * @param items
	 * @param key
	 * @return
	 */
	private double getPriceForitem(List<LineItem> items, String key) {
		items.stream().filter(item -> item.getSkuId().equals(key)).mapToDouble(item -> item.getPrice()).sum();

		return 0;
	}

	/**
	 * @param items
	 * @param promoItemSet
	 * @return
	 */
	private double calculatePriceWithoutOffer(List<LineItem> items, Set<String> promoItemSet) {
		double price = 0;
		price = price + items.stream().filter(item -> promoItemSet.contains(item.getSkuId()))
				.mapToDouble(item -> item.getPrice()).sum();
		return price;
	}

	/**
	 * @param itemsMap
	 * @param promoItemSet
	 * @return
	 */
	private boolean isPromotionApplicable(Map<String, Integer> itemsMap, Set<String> promoItemSet) {
		return itemsMap.keySet().containsAll(promoItemSet);

	}

	/**
	 * @param order
	 * @param skuCombinationList
	 * @param promoPrice
	 * @return
	 */
	private double getPriceForType2(Order order, List<SkuCombination> skuCombinationList, double promoPrice) {
		return getPriceForType1(order, skuCombinationList, promoPrice);
	}

	/**
	 * @param order
	 * @param skuCombinationList
	 * @param promoPrice
	 * @return
	 */
	private double getPriceForType1(Order order, List<SkuCombination> skuCombinationList, double promoPrice) {
		double price = 0;
		for (SkuCombination combi : skuCombinationList) {
			for (LineItem item : order.getItems()) {
				double totalcombiPrice = 0;
				double remainingItemPrice = 0;
				if (item.getSkuId().contains(combi.getSkuId())) {
					int itemCountInOrder = item.getItemCount();
					int itemCountInCombi = combi.getCountOfItems();
					if ((itemCountInOrder >= itemCountInCombi)
							&& (itemCountInOrder % itemCountInCombi < itemCountInCombi)) {

						totalcombiPrice = (itemCountInOrder / itemCountInCombi) * promoPrice;
						int itemsInOffer = (itemCountInOrder / itemCountInCombi) * combi.getCountOfItems();
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
