package dataservice.financedataservice.initdataservice;

import po.BeginInfoPO;

public class FinanceInitDataService_Driver {
	public void drive(FinanceInitDataService fds){
	  	  fds.initInfo(null, new BeginInfoPO());
	  	  fds.getInfo(null);
	    }
		
	    public static void main(String[] args){
	  	  FinanceInitDataService fds=new FinanceInitDataService_stub();
	  	  try {
	  			new FinanceInitDataService_Driver().drive(fds);
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		}
	    }
}
