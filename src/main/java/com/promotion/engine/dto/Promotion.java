package com.promotion.engine.dto;

import java.util.List;

public class Promotion {
	List<SkuCombination> skuCombinationList ;
	double promoPrice;
	String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SkuCombination> getSkuCombinationList() {
		return skuCombinationList;
	}

	public void setSkuCombinationList(List<SkuCombination> skuCombinationList) {
		this.skuCombinationList = skuCombinationList;
	}

	public double getPromoPrice() {
		return promoPrice;
	}

	public void setPromoPrice(double promoPrice) {
		this.promoPrice = promoPrice;
	}

	public Promotion(List<SkuCombination> skuCombinationList,double promoPrice, String type){
		super();
		this.skuCombinationList = skuCombinationList;
		this.promoPrice = promoPrice;
		this.type = type;
		
	}

}
