package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PromotionPO;

public interface PromotionDataService extends Remote{
	public int Add(PromotionPO po)throws RemoteException;
	public PromotionPO find(String id)throws RemoteException;
	public int Modify(PromotionPO po)throws RemoteException;
	public ArrayList<PromotionPO>show()throws RemoteException;
	public void init()throws RemoteException;

}
