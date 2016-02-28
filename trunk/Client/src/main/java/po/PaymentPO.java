package po;

import java.io.Serializable;
import java.util.ArrayList;


public class PaymentPO extends ReceiptPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    ArrayList<TransferItemPO> transferlist=new ArrayList<TransferItemPO>();
    double totalMoney;
       

    
    public PaymentPO(String id,String MID,String Mname,String user,ArrayList<TransferItemPO> d,double e,int istatus,int ihurry){
   	 super(id,MID,Mname,ReceiptType.PAYMENT,user,istatus,ihurry);

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