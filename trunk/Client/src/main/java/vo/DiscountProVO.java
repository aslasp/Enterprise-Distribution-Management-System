package vo;

import java.util.ArrayList;


import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import po.PromotionPO.PromotionType;

public class DiscountProVO extends PromotionVO{
	private ArrayList<Double> countList;
	private ArrayList<CommodityVO>  goodsList;
	
	public DiscountProVO(String id,String startDate,String endDate,MemberLevel l,
			ArrayList<Double> count,
			ArrayList<CommodityVO>  goods)
	{
		super(id,startDate,endDate,PromotionType.DISCOUNT,l);
		goodsList=goods;
		countList=count;
	}

	public ArrayList<Double> getCountList() {
		return countList;
	}

	public ArrayList<CommodityVO> getGoodsList() {
		return goodsList;
	}

	
	
}
