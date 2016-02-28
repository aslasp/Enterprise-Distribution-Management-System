package businesslogicservice.financeblservice.listblservice;

import vo.ReceiptVO;

public class RedExtrusionBLService_Driver {
	public void drive(RedExtrusionBLService fbs){
		fbs.createRedExtrusion(new ReceiptVO(null, null, null, 0, 0));
		fbs.createRedExtrusionAndCopy(new ReceiptVO(null, null, null, 0, 0));
	    }
		
	    public static void main(String args[]){
	    	 RedExtrusionBLService fbs=new RedExtrusionBLService_stub();
	    	 RedExtrusionBLService_Driver driver=new RedExtrusionBLService_Driver();
		   	 driver.drive(fbs);
	    }
}
