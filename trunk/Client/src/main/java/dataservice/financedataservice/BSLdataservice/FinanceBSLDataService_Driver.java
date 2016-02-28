package dataservice.financedataservice.BSLdataservice;

import po.BSLPO;


public class FinanceBSLDataService_Driver {
	public void drive(FinanceBSLDataService fds){
	  	  fds.viewBSL();
	      fds.export(new BSLPO());
	    }
		
	    public static void main(String[] args){
	  	  FinanceBSLDataService fds=new FinanceBSLDataService_stub();
	  	  try {
	  			new FinanceBSLDataService_Driver().drive(fds);
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		}
	    }
}
