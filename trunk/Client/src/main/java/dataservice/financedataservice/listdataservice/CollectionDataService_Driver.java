package dataservice.financedataservice.listdataservice;

import po.CollectionPO;

public class CollectionDataService_Driver {
	public void drive(CollectionDataService fds){
	  	  fds.createCollection(new CollectionPO(null, null, null, null, null, 0, 0, 0));
	    }
		
	    public static void main(String[] args){
	  	  CollectionDataService fds=new CollectionDataService_stub();
	  	  try {
	  			new CollectionDataService_Driver().drive(fds);
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		}
	    }
}
