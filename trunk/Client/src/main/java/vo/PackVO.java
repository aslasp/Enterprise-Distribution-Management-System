package vo;

import java.util.ArrayList;



public class PackVO {
	private double totalValue;
	private double packValue;
	private ArrayList<CommodityVO> combine;
	public PackVO(double totalValue, double packValue,
		 ArrayList<CommodityVO> combine) {
	
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
	public ArrayList<CommodityVO> getCombine() {
		return combine;
	}
}
