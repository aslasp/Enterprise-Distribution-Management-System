package po;

import java.io.Serializable;
import java.util.ArrayList;


public class PaymentPO extends ReceiptPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ID;
    MemberPO member;
    UserPO user;
    ArrayList<TransferItem> transferlist=new ArrayList<TransferItem>();
    double totalMoney;
       
    public PaymentPO(){
   	 this(null,null,null,null,0);
    }
    
    public PaymentPO(String a,MemberPO b,UserPO c,ArrayList<TransferItem> d,double e){
   	 ID=a;
   	 member=b;
   	 user=c;
   	 transferlist=d;
   	 totalMoney=e;
    }
    
    public String getID(){
   	 return ID;
    }
    
    public MemberPO getMember(){
   	 return member;
    }
    
    public UserPO getUser(){
   	 return user;
    }
    
    public ArrayList<TransferItem> getTransferlist(){
   	 return transferlist;
    }
    
    public double getTotalMoney(){
   	 return totalMoney;
    }
    
    
  class TransferItem{
	AccountPO account;
	double money;
	String info;
 }
 
}
