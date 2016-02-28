package po;
import java.util.ArrayList;
import java.util.Date;

import Data.receiptdata.ReceiptType;


public class PurchasePO extends ReceiptPO{
	//CommodityList purchaseList;
	double totalInAll;

	public Object stockid;
	public Object member;
	public PurchasePO(ArrayList<CommodityListPO> purchaseList, String id,
			MemberPO member, UserPO user,Date createDate,
			int status, int hurry,String info, String stockid,double totalInAll) {

		//super();

		super(id, member, user, ReceiptType.PURCHASE, createDate, status, hurry, info, stockid);

		//this.purchaseList = purchaseList;
		this.totalInAll=totalInAll;
	}

	//public CommodityList getPurchaseList() {
	//	return purchaseList;
	//}
	
}
