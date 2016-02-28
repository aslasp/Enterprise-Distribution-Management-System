package dataservice.receiptdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ReceiptPO;

public interface ReceiptDataservice extends Remote {
	public int Add(ReceiptPO po)throws RemoteException;
	public ReceiptPO find(String id)throws RemoteException;
	public int Modify(ReceiptPO po)throws RemoteException;
	public ArrayList<ReceiptPO> show()throws RemoteException;
	public void init() throws RemoteException;
	
}
