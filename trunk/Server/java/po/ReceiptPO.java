package po;

import java.util.Date;

import Data.receiptdata.ReceiptType;


public class ReceiptPO {
	String id;
	MemberPO member;
	UserPO user;
	ReceiptType type;
	Date createDate;
	int status;
	int hurry;
	String info;
	String stockid;
	public  ReceiptPO(){

	}
	public ReceiptPO(String id, MemberPO member, UserPO user, ReceiptType type,
			Date createDate, int status, int hurry,String info, String stockid) {
	
		this.id = id;
		this.member = member;
		this.user = user;
		this.type = type;
		this.createDate = createDate;
		this.status = status;
		this.hurry=hurry;
		this.info = info;
		this.stockid = stockid;
	}
}
