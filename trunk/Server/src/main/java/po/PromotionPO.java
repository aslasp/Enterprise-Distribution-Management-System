package po;

import java.io.Serializable;

import po.MemberPO.MemberLevel;

public class PromotionPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String startDate,endDate;
	PromotionType type;
	MemberLevel level;
	boolean isMatch=false;
	
	public PromotionPO(String id,String startDate, String endDate, PromotionType type,
			MemberLevel level) {		
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.level = level;
		this.id=id;
	}
	
	public void setIsMatch(boolean match){
		this.isMatch=match;
		
	}
	
	public boolean IsMatch(){
		return this.isMatch;
	}

	public String getStartDate() {
		return startDate;
	}



	public String getEndDate() {
		return endDate;
	}
	
	public String getID(){
		return id;
	}

	public String getDate(){
		String[] buffer=id.split("-");
		String s=buffer[1];
		return s;
	}


	public PromotionType getType() {
		return type;
	}



	public MemberLevel getLevel() {
		return level;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public void setLevel(MemberLevel level) {
		this.level = level;
	}






	public enum PromotionType  implements Serializable{
		GIFTGOODS,GIFTCOUPON,PACK,DISCOUNT
	}
}