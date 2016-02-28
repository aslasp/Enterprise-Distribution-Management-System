package vo;

import po.ReceiptPO.ReceiptType;

public class ReceiptVO {
	String id;
	String memberName="";
	String memberID="";
	String user;
	ReceiptType type;
	int status;
	int hurry;
	String info="";

	public ReceiptVO(String id,ReceiptType type,String user,int status,int hurry){
		this.id=id;
		this.type=type;
		this.user=user;
		this.status=status;
		this.hurry=hurry;
	}
	
	public ReceiptVO(String id,String memberID, String memberName,ReceiptType type,String user,int status,int hurry){
		this.id=id;
		this.memberID=memberID;
		this.memberName=memberName;
		this.type=type;
		this.user=user;
		this.status=status;
		this.hurry=hurry;
	}
	

	public ReceiptVO(String id, String memberName, String memberID,
			String user, ReceiptType type, int status, int hurry, String info) {

		this.id = id;
		this.memberName = memberName;
		this.memberID = memberID;
		this.user = user;
		this.type = type;
		this.status = status;
		this.hurry = hurry;
		this.info = info;

	}

	public String getId() {
		return id;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMemberID() {
		return memberID;
	}

	public String getUser() {
		return user;
	}

	public ReceiptType getType() {
		return type;
	}

	public int getStatus() {
		return status;
	}

	public int getHurry() {
		return hurry;
	}

	public String getInfo() {
		return info;
	}

	public String getDate() {
		String[] buffer = id.split("-");
		String s = buffer[1];
		String year = s.substring(0, 4);
		String month = s.substring(4, 6);
		String day = s.substring(6, 8);
		return year + "/" + month + "/" + day;
	}
	
	

}