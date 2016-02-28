package vo;

import java.util.ArrayList;

import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import po.PackPO;
import po.PromotionPO.PromotionType;



public class PackProVO extends PromotionVO{
	private PackVO pack;
	private double totalValue;
	private double packValue;

	public PackProVO(String id,String startDate,String endDate,MemberLevel l,
			 PackVO pack)
	{
		super(id,startDate,endDate,PromotionType.PACK,l);
		this.pack=pack;
		totalValue=pack.getTotalValue();
		this.packValue=pack.getPackValue();
	}
	public PackVO getPack() {
		return pack;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public double getPackValue() {
		return packValue;
	}
	
	
}
