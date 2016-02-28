package dataservice.financedataservice.listdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CashlistPO;

public interface CashlistDataService extends Remote{
	 public int createCashlist (CashlistPO po) throws RemoteException;
	 public ArrayList<CashlistPO> getCashlist() throws RemoteException;
	 public int getNum() throws RemoteException;
	 public CashlistPO findByID(String id) throws RemoteException;
	 public int modify(CashlistPO po) throws RemoteException;
}
