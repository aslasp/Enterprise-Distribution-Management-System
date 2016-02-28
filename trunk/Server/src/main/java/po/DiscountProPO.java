package po;
/*
 * 折让促销
 */
import java.io.Serializable;
import java.util.ArrayList;

import po.MemberPO.MemberLevel;

public class DiscountProPO extends PromotionPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Double> countList;
	private ArrayList<CommodityPO>  goodsList;
	
	public DiscountProPO(String id,String startDate,String endDate,MemberLevel l,
			ArrayList<Double> count,
			ArrayList<CommodityPO>  goods)
	{
		super(id,startDate,endDate,PromotionType.DISCOUNT,l);
		goodsList=goods;
		countList=count;
	}

	public ArrayList<Double> getCountList() {
		return countList;
	}

	public ArrayList<CommodityPO> getGoodsList() {
		return goodsList;
	}

	
	public void setDiscount(ArrayList<Double> count,
	ArrayList<CommodityPO>  goods){
		this.countList=count;
		this.goodsList=goods;
	}


	
}
