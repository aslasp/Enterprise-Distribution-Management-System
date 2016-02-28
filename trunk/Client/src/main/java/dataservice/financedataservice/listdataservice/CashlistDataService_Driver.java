package dataservice.financedataservice.listdataservice;

import po.CashlistPO;


public class CashlistDataService_Driver {
	public void drive(CashlistDataService fds){
	  	  fds.createCashlist(new CashlistPO(null, null, null, null, 0, 0, 0));
	    }
		
	    public static void main(String[] args){
	  	  CashlistDataService fds=new CashlistDataService_stub();
	  	  try {
	  			new CashlistDataService_Driver().drive(fds);
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		}
	    }
}
