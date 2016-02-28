package po;

import java.io.Serializable;
import java.util.ArrayList;

import po.ReceiptPO;

public class CollectionPO extends ReceiptPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
     ArrayList<TransferItemPO> transferlist=new ArrayList<TransferItemPO>();
     double totalMoney;
     BSLPO vo;
        
     
     public CollectionPO(String id,String memberID,String memberName,String user,ArrayList<TransferItemPO> d,double e,int istatus,int ihurry){
    	 super(id,memberID,memberName,ReceiptType.COLLECTION,user,istatus,ihurry);
    	 transferlist=d;
    	 totalMoney=e;
     }
     
     

     public ArrayList<TransferItemPO> getTransferlist(){
    	 return transferlist;
     }
     
     public double getTotalMoney(){
    	 return totalMoney;
     }
     
     public void setTransferlist(ArrayList<TransferItemPO> po){
    	 transferlist=po;
     }
  
}
