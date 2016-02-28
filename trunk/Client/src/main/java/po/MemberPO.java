package po;

import java.io.Serializable;




public class MemberPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String memberID;
	MemberType mType;
	MemberLevel mLevel;
	String name, tel, address, postcode, EMail, defaultClerk;
	double MaxOwe;
	double toReceive, toPay;
	double points;

	public MemberPO(String memberID, MemberType mType, MemberLevel mLevel,
			String name, String tel, String address, String postcode,
			String EMail, String defaultClerk, double MaxOwe, double toReceive,
			double toPay,double points) {
		this.memberID=memberID;
		this.mType=mType;
		this.mLevel=mLevel;
		this.name=name;
		this.tel=tel;
		this.address=address;
		this.postcode=postcode;
		this.EMail=EMail;
		this.defaultClerk=defaultClerk;
		this.MaxOwe=MaxOwe;
		this.toReceive=toReceive;
		this.toPay=toPay;
		this.points=points;
	}
	
	public void setmType(MemberType mtype){
		this.mType=mtype;
	}

	public void setmLevel(MemberLevel mLevel) {
		this.mLevel = mLevel;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}



	public void setEMail(String eMail) {
		EMail = eMail;
	}



	public void setDefaultClerk(String defaultClerk) {
		this.defaultClerk = defaultClerk;
	}



	public void setMaxOwe(double maxOwe) {
		MaxOwe = maxOwe;
	}



	//public void setToReceive(double toReceive) {
	////	this.toReceive = toReceive;
	//}谁



	//public void setToPay(double toPay) {
		//this.toPay = toPay;
	//是}



	public void setPoints(double points) {
		this.points = points;
	}



	public String getMemberID() {
		return memberID;
	}

	public MemberType getmType() {
		return mType;
	}

	public MemberLevel getmLevel() {
		return mLevel;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getEMail() {
		return EMail;
	}

	public String getDefaultClerk() {
		return defaultClerk;
	}

	public double getMaxOwe() {
		return MaxOwe;
	}

	public double getToReceive() {
		return toReceive;
	}

	public double getToPay() {
		return toPay;
	}
	public double getPoints() {
		return points;
	}
	
	public void changeToReceive(double m){
		toReceive+=m;
	}
	
	public void changeToPay(double m){
		toPay+=m;
	}
	
	public enum MemberLevel implements Serializable{
		ONE,TWO,THREE,FOUR,FIVE;
	}
	public enum MemberType implements Serializable{
		JHS,XSS
	}
}
