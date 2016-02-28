package po;
/*
 * 代金券赠送促销
 */
import java.io.Serializable;
import java.util.ArrayList;

import po.MemberPO.MemberLevel;

public class GiftCouponProPO extends PromotionPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CouponPO> couponList;
	private double totalValue; //代金券总值
	public GiftCouponProPO(String id,String startDate,String endDate,MemberLevel l,
			ArrayList<CouponPO> coupon,double value)
	{
		super(id,startDate,endDate,PromotionType.GIFTCOUPON,l);
		couponList=coupon;
		totalValue=value;
	}
	public ArrayList<CouponPO> getCouponList() {
		return couponList;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public void setCouponList(ArrayList<CouponPO> couponList) {
		this.couponList = couponList;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	
	
}
