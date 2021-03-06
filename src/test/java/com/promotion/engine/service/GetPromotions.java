package com.promotion.engine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.promotion.engine.dto.Promotion;
import com.promotion.engine.dto.SkuCombination;

@Service
public class GetPromotions {

	public Promotion getPromo1() {

		SkuCombination combi1 = new SkuCombination();
		combi1.setSkuId("A");
		combi1.setCountOfItems(3);
		List<SkuCombination> combList = new ArrayList();
		combList.add(combi1);
		Promotion promo1 = new Promotion(combList, 130 , "1");
		return promo1;

	}

	public Promotion getPromo2() {
		SkuCombination combi1 = new SkuCombination();
		combi1.setSkuId("B");
		combi1.setCountOfItems(2);
		List<SkuCombination> combList = new ArrayList();
		combList.add(combi1);
		Promotion promo2 = new Promotion(combList, 45 , "2");
		return promo2;
	}
	
	public Promotion getPromo3() {
		SkuCombination combi1 = new SkuCombination();
		combi1.setSkuId("C");
		combi1.setCountOfItems(1);
		SkuCombination combi2 = new SkuCombination();
		combi2.setSkuId("D");
		combi2.setCountOfItems(1);
		List<SkuCombination> combList = new ArrayList();
		combList.add(combi1);
		combList.add(combi2);
		Promotion promo2 = new Promotion(combList, 30 , "3");
		return promo2;
	}
	
	public List<Promotion> getPromotionList() {
		List<Promotion> promoList = new ArrayList();
		promoList.add(getPromo1());
		promoList.add(getPromo2());
		promoList.add(getPromo3());
		return promoList;
	}

}
