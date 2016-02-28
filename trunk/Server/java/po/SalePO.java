package po;

import java.io.Serializable;
import java.util.Date;

import Data.receiptdata.ReceiptType;


public class SalePO extends ReceiptPO implements Serializable{
	String clerk;

	double totalYuanChu, totalZheHou, totalKeHuYingFu;
	double ZheRang, couponPrice;
	public Object member;
	public Object stockid;
	public SalePO(){
		
		
	}
	public SalePO(String clerk,  String id,
			MemberPO member, UserPO user, Date createDate, int status,
			int hurry,String info, String stockid, double totalYuanChu,
			double totalZheHou, double totalKeHuYingFu, double ZheRang,
			double couponPrice) {
		super();
		this.clerk = clerk;
		//this.salesList = salesList;
		this.totalYuanChu=totalYuanChu;
		this.totalKeHuYingFu=totalKeHuYingFu;
		this.totalZheHou=totalZheHou;
		this.ZheRang=ZheRang;
		this.couponPrice=couponPrice;
	}

	public String getClerk() {
		return clerk;
	}

	//public CommodityList getSalesList() {
		//return salesList;
	//}

	public double getTotalYuanChu() {
		return totalYuanChu;
	}

	public double getTotalZheHou() {
		return totalZheHou;
	}

	public double getTotalKeHuYingFu() {
		return totalKeHuYingFu;
	}

	public double getZheRang() {
		return ZheRang;
	}

	public double getCouponPrice() {
		return couponPrice;
	}
	

}
