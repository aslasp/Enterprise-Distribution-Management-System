package vo;

import java.util.ArrayList;


import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import po.PromotionPO.PromotionType;

public class GiftCouponProVO extends PromotionVO{
	private ArrayList<CouponVO> couponList;
	private double totalValue;
	public GiftCouponProVO(String id,String startDate,String endDate,MemberLevel l,
			ArrayList<CouponVO> coupon,double value)
	{
		super(id,startDate,endDate,PromotionType.GIFTCOUPON,l);
		couponList=coupon;
		totalValue=value;
	}
	public ArrayList<CouponVO> getCouponList() {
		return couponList;
	}
	public  void setCouponList(ArrayList<CouponVO> list) {
		this.couponList=list;
	}
	public double getTotalValue() {
		return totalValue;
	}
}
