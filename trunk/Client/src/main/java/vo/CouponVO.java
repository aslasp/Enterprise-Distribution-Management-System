package vo;

import java.util.Date;

public class CouponVO {
	String id;
	double money;//�ɵ��ý��
	boolean isUse;
	String useDate="";
	public CouponVO(String id,  double money,boolean isUse) {
		this.id = id;
		this.money = money;
		this.isUse=isUse;
		//this.createDate=
	}
	public void setUseDate(String date){
		this.useDate=date;
	}
	
	public String getUseDate(){
		return this.useDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}

	public double getValue() {
		return money;
	}

	public boolean getIsUse() {
		return isUse;
	}
	

}
