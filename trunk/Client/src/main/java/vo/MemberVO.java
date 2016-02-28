package vo;

import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import businesslogic.memberbl.MemAccountInfo;
import businesslogic.memberbl.MemBaseInfo;
import businesslogic.memberbl.MemContactInfo;


public class MemberVO {
	 MemBaseInfo bInfo;
	 MemAccountInfo aInfo;
	 private MemContactInfo cInfo;

	public MemberVO(MemBaseInfo bInfo,MemAccountInfo aInfo,MemContactInfo cInfo) {
		this.bInfo=bInfo;
		this.aInfo=aInfo;
		this.cInfo=cInfo;	
	}

	public String getMemberID() {
		return bInfo.memberID;
	}
	
	public void setMemberID(String s){
		bInfo.memberID=s;
	}

	public MemberType getmType() {
		return bInfo.mType;
	}

	public MemberLevel getmLevel() {
		return bInfo.mLevel;
	}

	public String getName() {
		return bInfo.name;
	}

	public String getTel() {
		return cInfo.getTel();
	}

	public String getAddress() {
		return cInfo.getAddress();
	}

	public String getPostcode() {
		return cInfo.getPostcode();
	}

	public String getEMail() {
		return cInfo.getEMail();
	}

	public String getDefaultClerk() {
		return bInfo.defaultClerk;
	}

	public double getMaxOwe() {
		return aInfo.getMaxOwe();
	}

	public double getToReceive() {
		return aInfo.getToReceive();
	}

	public double getToPay() {
		return aInfo.getToPay();
	}
	public double getPoints() {
		return bInfo.points;
	}
}


