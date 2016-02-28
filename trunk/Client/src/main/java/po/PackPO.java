package po;

import java.io.Serializable;
/*
 * 特价包
 */
import java.util.ArrayList;



public class PackPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double totalValue;
	private double packValue;
	private ArrayList<CommodityPO> combine;
	public PackPO(double totalValue, double packValue,
			 ArrayList<CommodityPO> combine) {
	
	
		this.totalValue = totalValue;
		this.packValue = packValue;
		this.combine = combine;
	}
	
	public double getTotalValue() {
		return totalValue;
	}
	public double getPackValue() {
		return packValue;
	}
	
	public ArrayList<CommodityPO> getCombine() {
		return combine;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	public void setPackValue(double packValue) {
		this.packValue = packValue;
	}
	

	
	
	
	

}