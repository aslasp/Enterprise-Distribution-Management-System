package vo;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;



public class PurchaseVO extends ReceiptVO {
	private ArrayList<CommodityVO> purchaseList;
	private double totalInAll,adjustCost=0;//成本调价
	private String stockid;
	public PurchaseVO( String id,String memberName,String memberID, String stockid, 
			String user,ArrayList<CommodityVO> purchaseList,String info,
			double totalInAll,int status, int hurry) {
		super(id, memberName,memberID, user,ReceiptType.PURCHASE, status,hurry,info);
		this.purchaseList = purchaseList;
		this.totalInAll=totalInAll;
		this.stockid=stockid;
	}
	
	public void setAdjustCost(double cost){
		this.adjustCost=cost;
	}
	public double getAdjustCost(){
		return this.adjustCost;
	}

	public ArrayList<CommodityVO> getPurchaseList() {
		return purchaseList;
	}

	public double getTotalInAll() {
		return totalInAll;
	}
	public String getStockid() {
		return stockid;
	}
	
	public void setPurchaseList(ArrayList<CommodityVO> list){
		this.purchaseList=list;
	}
	public void setTotalInAll(double value){
		this.totalInAll=value;
	}
	
}
