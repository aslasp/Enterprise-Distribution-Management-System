package dataservice.financedataservice.listdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionPO;

public interface CollectionDataService extends Remote{
	public int createCollection (CollectionPO po) throws RemoteException;
	public ArrayList<CollectionPO> getCollection() throws RemoteException;
	public int getNum() throws RemoteException;
	public CollectionPO findByID(String id) throws RemoteException;
	public int modify(CollectionPO po) throws RemoteException;
}
