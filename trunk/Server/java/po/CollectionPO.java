package po;

import java.io.Serializable;
import java.util.ArrayList;


public class CollectionPO extends ReceiptPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 String ID;
     MemberPO member;
     UserPO user;
     ArrayList<TransferItemPO> transferlist=new ArrayList<TransferItemPO>();
     double totalMoney;
     BSLPO vo;
        
     public CollectionPO(){
    	 this(null,null,null,null,0);
     }
     
     public CollectionPO(String a,MemberPO b,UserPO c,ArrayList<TransferItemPO> d,double e){
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
     
     public ArrayList<TransferItemPO> getTransferlist(){
    	 return transferlist;
     }
     
     public double getTotalMoney(){
    	 return totalMoney;
     }
     
     
  
}
