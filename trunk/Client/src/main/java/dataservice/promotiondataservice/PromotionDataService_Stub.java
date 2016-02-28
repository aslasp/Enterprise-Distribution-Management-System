package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PromotionPO;
import po.PromotionPO.PromotionType;

public class PromotionDataService_Stub implements PromotionDataService{

	public int Add(PromotionPO po) {
		// TODO Auto-generated method stub
		System.out.println("Add Promotion Success!");
		return 0;
	}

	public int Modify(PromotionPO po) {
		// TODO Auto-generated method stub
		System.out.println("Modify Promotion Success!");
		return 0;
	}

	public PromotionPO find(String id) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("find Promotion (id="+id+") Success!");
		return null;
	}

	public void init() throws RemoteException {
		System.out.println("init Promotion Success!");
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public PromotionPO find(String id, PromotionType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Delete(String id, PromotionType type) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public ArrayList<PromotionPO> showAll() {
		// TODO Auto-generated method stub
		System.out.println("Show Promotion Success!");
		return null;
	}

	@Override
	public ArrayList<PromotionPO> show(PromotionType type) {
		// TODO Auto-generated method stub
		System.out.println("find Promotion"+type.toString()+" Success!");
		
		return null;
	}

}
