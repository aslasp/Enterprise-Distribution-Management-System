package dataservice.financedataservice.listdataservice;

import java.util.ArrayList;

import po.CollectionPO;

public class CollectionDataService_stub implements CollectionDataService{
	public int createCollection(CollectionPO po) {
		System.out.println("Create collection success!");
		return 0;
	}

	public ArrayList<CollectionPO> getCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNum(){
		// TODO Auto-generated method stub
		return 0;
	}

	public CollectionPO findByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(CollectionPO po) {
		// TODO Auto-generated method stub
		return 0;
	}
}
