package po;

/*
 * 特价包促销
 */
import java.io.Serializable;
import java.util.ArrayList;

import po.MemberPO.MemberLevel;


public class PackProPO extends PromotionPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PackPO pack;
	private double totalValue;
	private double packValue;

	public PackProPO(String id,String startDate,String endDate,MemberLevel l,
			 PackPO pack)
	{
		super(id,startDate,endDate,PromotionType.PACK,l);
		this.pack=pack;
		totalValue=pack.getTotalValue();
		packValue=pack.getPackValue();
	}

	public PackPO getPackList() {
		return pack;
	}
	
	public void setPackList(PackPO list){
		this.pack=list;
	}
	
	public void totalValue(double value)
	{
		this.totalValue=value;
	}

	public double getTotalValue() {
		return totalValue;
	}
	
	public double getPackValue(){
		return packValue;
	}
	
	public void setPackValue(double value){
		this.packValue=value;
	}

}
