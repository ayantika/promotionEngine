package com.promotion.engine.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Promotion {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(mappedBy="promotion")
	List<SkuCombination> skuCombinationList ;
	double promoPrice;
	String type;
	
	public Promotion(List<SkuCombination> skuCombinationList,double promoPrice, String type){
		super();
		this.skuCombinationList = skuCombinationList;
		this.promoPrice = promoPrice;
		this.type = type;
		
	}

}
