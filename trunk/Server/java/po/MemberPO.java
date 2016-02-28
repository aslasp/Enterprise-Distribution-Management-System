package po;




public class MemberPO {
	String memberID;
	MemberType mType;
	MemberLevel mLevel;
	String name, tel, address, postcode, EMail, defaultClerk;
	double MaxOwe;
	double toReceive, toPay;
	int points;

	public MemberPO(String memberID, MemberType mType, MemberLevel mLevel,
			String name, String tel, String address, String postcode,
			String EMail, String defaultClerk, double MaxOwe, double toReceive,
			double toPay) {
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
		this.points=0;
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
	public enum MemberLevel {
		ONE,TWO,THREE,FOUR,FIVE;
	}
	public enum MemberType {
		JHS,XSS
	}

}
