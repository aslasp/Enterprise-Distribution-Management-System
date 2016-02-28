package po;
import java.io.Serializable;
import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;


public class PurchaseReturnPO extends ReceiptPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<CommodityPO> purchaseReturnList;
	double totalInAll;
	private String stockid;
	
	private String purid;
	
	public PurchaseReturnPO(String id,String memberID,String name, String stockid,String purid,String user,ArrayList<CommodityPO> purchasereturnList,String info, double totalInAll,int status, int hurry) {
		super(id,memberID,name,user,ReceiptType.PURCHASERETURN,info,status, hurry);
		this.stockid=stockid;
		this.purchaseReturnList = purchasereturnList;
		this.totalInAll=totalInAll;
		this.purid=purid;
	}
	
	public String getpurid(){
		return purid;
	}
	

	
	public String getStockID(){
		return stockid;
	}
	
	public ArrayList<CommodityPO> getPurchaseReturnList() {
		return purchaseReturnList;
	}
	public double getTotalInAll() {
		return totalInAll;
	}
	
	public void setTotalInAll(double totalInAll) {
		this.totalInAll = totalInAll;
	}
	
	
}
