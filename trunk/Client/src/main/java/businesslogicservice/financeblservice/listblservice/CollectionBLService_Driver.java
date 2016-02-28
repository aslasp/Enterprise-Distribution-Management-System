package businesslogicservice.financeblservice.listblservice;

import vo.CollectionVO;


public class CollectionBLService_Driver {
	public void drive(CollectionBLService fbs){
	     fbs.createCollection(new CollectionVO(null, null, null, null, null, 0, 0, 0));
	    }
		
	    public static void main(String args[]){
	   	 CollectionBLService fbs=new CollectionBLService_stub();
	   	 CollectionBLService_Driver driver=new CollectionBLService_Driver();
	   	 driver.drive(fbs);
	    }
}
