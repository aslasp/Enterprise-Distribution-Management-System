package businesslogicservice.financeblservice.listblservice;

import vo.PaymentVO;

public class PaymentBLService_Driver {
	public void drive(PaymentBLService fbs){
	     fbs.createPayment(new PaymentVO(null, null, null, null, null, 0, 0, 0));
	    }
		
	    public static void main(String args[]){
	   	 PaymentBLService fbs=new PaymentBLService_stub();
	   	 PaymentBLService_Driver driver=new PaymentBLService_Driver();
	   	 driver.drive(fbs);
	    }
}
