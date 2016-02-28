package vo;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;

public class CashlistVO extends ReceiptVO{

     String account;
     ArrayList<ClauseItemVO> clauselist=new ArrayList<ClauseItemVO>();
     double totalMoney;
 
     public CashlistVO(String id,String user,String iaccount,ArrayList<ClauseItemVO> cla,double money,int status,int hurry){
    	 super(id,ReceiptType.CASHLIST,user,status,hurry);
    	 account=iaccount;
    	 clauselist=cla;
    	 totalMoney=money;
     }
     

     
     public String getAccount(){
    	 return account;
     }
     
     public ArrayList<ClauseItemVO> getClauselist(){
    	 return clauselist;
     }
     
     public double getTotalMoney(){
    	 return totalMoney;
     }
     

}
