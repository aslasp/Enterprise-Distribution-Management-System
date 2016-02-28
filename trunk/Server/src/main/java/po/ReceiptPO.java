package po;

import java.io.Serializable;


public class ReceiptPO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String memberID="";
	private String memberName="";
	private String userID;
	private ReceiptType type;
	private int status;
	private int hurry;
	private String info="";

	public ReceiptPO(String id,ReceiptType type,String user,int status,int hurry){
		this.id=id;
		this.type=type;
		this.userID=user;
		this.status=status;
		this.hurry=hurry;
	}
	
	public ReceiptPO(String id,String memberID,String memberName,ReceiptType type,String user,int status,int hurry){
		this.id=id;
		this.memberID=memberID;
		this.memberName=memberName;
		this.type=type;
		this.userID=user;
		this.status=status;
		this.hurry=hurry;
	}
	
	public ReceiptPO(String id,String memberID,String memberName,String userID, ReceiptType type,String info,int status,int hurry) {

		this.id = id;
		this.memberID = memberID;
		this.memberName=memberName;
		this.userID = userID;
		this.type = type;
		
		this.status = status;
		this.hurry = hurry;
		this.info = info;
	}

	public ReceiptPO() {

	}

	public String getId() {
		return id;
	}


	public String getMemberID() {
		return memberID;
	}

	public String getMemberName(){
		return memberName;
	}
	
	public String getUserID() {
		return userID;
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
	
	public String getDate(){
		String[] buffer=id.split("-");
		String s=buffer[1];
		String year=s.substring(0,4);
		String month=s.substring(4,6);
		String day=s.substring(6,8);
		return year+"/"+month+"/"+day;
	}
	
	public String myGetDate(){
		String[] buffer=id.split("-");
		String s=buffer[1];
		return s;
	}

	public String getInfo() {
		return info;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setHurry(int hurry) {
		this.hurry = hurry;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public enum ReceiptType implements Serializable{
		 SALE,SALERETURN,PURCHASE,PURCHASERETURN,COLLECTION,PAYMENT,CASHLIST,
		   GIFT,STOCKERROR,STOCKOVER,STOCKLOW
	}
	
}
