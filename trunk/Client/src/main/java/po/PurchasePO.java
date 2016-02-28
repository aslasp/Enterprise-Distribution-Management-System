package po;
import java.io.Serializable;
import java.util.ArrayList;

public class PurchasePO extends ReceiptPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CommodityPO> purchaseList;
	private double totalInAll,adjustCost;
	private String stockid;
	
	public PurchasePO(String id,String name, String memberID,String stockid,String user,ArrayList<CommodityPO> purchaseList,String info,
			double totalInAll,double cost,int status, int hurry) {
		super(id,memberID,name,user,ReceiptType.PURCHASE,info,status, hurry);
		this.stockid=stockid;
		this.purchaseList = purchaseList;
		this.totalInAll=totalInAll;
		this.adjustCost=cost;
	}
	
	public double getAdjustCost() {
		return adjustCost;
	}

	
	public void setAdjustCost(double adjustCost) {
		this.adjustCost = adjustCost;
	}

	public String getStockID(){
		return stockid;
	}
	
	public ArrayList<CommodityPO> getPurchaseList() {
		return purchaseList;
	}
	public double getTotalInAll() {
		return totalInAll;
	}
	public void setPurchaseList(ArrayList<CommodityPO> purchaseList) {
		this.purchaseList = purchaseList;
	}
	public void setTotalInAll(double totalInAll) {
		this.totalInAll = totalInAll;
	}
	
	
}
