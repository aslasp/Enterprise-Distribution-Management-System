package dataservice.financedataservice.listdataservice;

import java.rmi.RemoteException;

import po.PaymentPO;


public class PaymentDataService_Driver {
	public void drive(PaymentDataService fds){
	  	  try {
			fds.createPayment(new PaymentPO(null, null, null, null, null, 0, 0, 0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		
	    public static void main(String[] args){
	  	  PaymentDataService fds=new PaymentDataService_stub();
	  	  try {
	  			new PaymentDataService_Driver().drive(fds);
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		}
	    }
}
