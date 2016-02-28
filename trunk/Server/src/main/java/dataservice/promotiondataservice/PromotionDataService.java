package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CouponPO;
import po.PromotionPO;
import po.PromotionPO.PromotionType;

public interface PromotionDataService extends Remote{
	public int Add(PromotionPO po)throws RemoteException;
	public PromotionPO find(String id,PromotionType type)throws RemoteException;
	public int Delete(String id,PromotionType type)throws RemoteException;;
	public int Modify(PromotionPO po)throws RemoteException;
	public ArrayList<PromotionPO>showAll()throws RemoteException;
	public ArrayList<PromotionPO> show(PromotionType type) throws RemoteException;

}
