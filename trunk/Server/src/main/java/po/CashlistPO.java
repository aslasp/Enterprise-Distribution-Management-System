package po;

import java.io.Serializable;
import java.util.ArrayList;

public class CashlistPO extends ReceiptPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
     String account;
     ArrayList<ClauseItemPO> clauselist=new ArrayList<ClauseItemPO>();
     double totalMoney;
     
     public CashlistPO(String id,String user,String c,ArrayList<ClauseItemPO> d,double e,int status,int hurry){
    	 super(id,ReceiptType.CASHLIST,user,status,hurry);
    	 account=c;
    	 clauselist=d;
    	 totalMoney=e;
     }
     
     public String getAccount(){
    	 return account;
     }
     
     public ArrayList<ClauseItemPO> getClauselist(){
    	 return clauselist;
     }
     
     public double getTotalMoney(){
    	 return totalMoney;
     }
     
     public void setClauselist(ArrayList<ClauseItemPO> po){
    	 clauselist=po;
     }

}
