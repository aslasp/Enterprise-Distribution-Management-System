package dataservice.financedataservice.listdataservice;

import po.ReceiptPO;


public class RedExtrusionDataService_Driver {
	public void drive(RedExtrusionDataService fds){
	  	  fds.createRedExtrusion(new ReceiptPO());
	  	  fds.createRedExtrusionAndCopy(new ReceiptPO());
	    }
		
	    public static void main(String[] args){
	    	RedExtrusionDataService fds=new RedExtrusionDataService_stub();
	  	  try {
	  			new RedExtrusionDataService_Driver().drive(fds);
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		}
	    }
}
