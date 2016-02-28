package vo;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;

public class CollectionVO extends ReceiptVO{
     ArrayList<TransferItemVO> transferlist=new ArrayList<TransferItemVO>();
     double totalMoney;
        
     
     public CollectionVO(String id,String MID,String Mname,String user,ArrayList<TransferItemVO> d,double e,int istatus,int ihurry){
    	 super(id,MID,Mname,ReceiptType.COLLECTION,user,istatus,ihurry);
    	 transferlist=d;
    	 totalMoney=e;
     }
     

     public ArrayList<TransferItemVO> getTransferlist(){
    	 return transferlist;
     }
     
     public double getTotalMoney(){
    	 return totalMoney;
     }
     
     
  
}

