package businesslogicservice.financeblservice.initblservice;

import vo.BeginInfoVO;

public class FinanceInitBLService_Driver {
	public void drive(FinanceInitBLService fbs){
		 fbs.initInfo(null, new BeginInfoVO());
         fbs.getInfo(null);
	    }
		
	    public static void main(String args[]){
	   	 FinanceInitBLService fbs=new FinanceInitBLService_stub();
	   	 FinanceInitBLService_Driver driver=new FinanceInitBLService_Driver();
	   	 driver.drive(fbs);
	    }
}
