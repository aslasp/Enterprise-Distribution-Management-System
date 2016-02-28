package Presentation.promotionui;

import po.PromotionPO.PromotionType;

public class getChangeProType {
	
	public static String getProTypeString(PromotionType type){
		if(type==PromotionType.GIFTCOUPON)
			return "代金券赠送";
		else if(type==PromotionType.GIFTGOODS)
			return "商品赠送";
		else if(type==PromotionType.DISCOUNT)
			return "商品折扣";
		else 
			return "特价包";
	}
	
	public static PromotionType getProType(String type){
		if(type.equals("特价包"))
			return PromotionType.PACK;
		else if(type.equals("商品折扣"))
			return PromotionType.DISCOUNT;
		else if(type.equals("商品赠送"))
			return PromotionType.GIFTGOODS;
		else return PromotionType.GIFTCOUPON;
	}
	

}
