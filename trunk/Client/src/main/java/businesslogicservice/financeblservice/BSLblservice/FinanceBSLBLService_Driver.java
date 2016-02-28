package businesslogicservice.financeblservice.BSLblservice;

import vo.BSLVO;


public class FinanceBSLBLService_Driver {
	public void drive(FinanceBSLBLService fbs){
		 fbs.viewBSL();
         fbs.export(new BSLVO());
	    }
		
	    public static void main(String args[]){
	   	 FinanceBSLBLService fbs=new FinanceBSLBLService_stub();
	   	 FinanceBSLBLService_Driver driver=new FinanceBSLBLService_Driver();
	   	 driver.drive(fbs);
	    }
}
