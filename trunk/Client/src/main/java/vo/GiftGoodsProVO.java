package vo;

import java.util.ArrayList;

import po.GoodsPO;
import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import po.PromotionPO.PromotionType;

public class GiftGoodsProVO  extends PromotionVO{
	private ArrayList<CommodityVO> giftList;
	private double totalValue;
	public GiftGoodsProVO(String id,String startDate,String endDate,MemberLevel l,
			ArrayList<CommodityVO> list,double value)
	{
		super(id,startDate,endDate,PromotionType.GIFTGOODS,l);
		this.giftList=list;
		this.totalValue=value;
		
	}
	public ArrayList<CommodityVO> getGiftList() {
		return giftList;
	}
	public double getTotalValue() {
		return totalValue;
	}
}
