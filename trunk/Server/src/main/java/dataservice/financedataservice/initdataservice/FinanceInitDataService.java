package dataservice.financedataservice.initdataservice;

import po.BeginInfoPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FinanceInitDataService extends Remote{
	public int initInfo(String time,BeginInfoPO po) throws RemoteException;
	public BeginInfoPO getInfo(String time) throws RemoteException;
	public String getCurrentTime() throws RemoteException;
	public ArrayList<BeginInfoPO> showAll() throws RemoteException;
	public void setTime(String s) throws RemoteException;
	public void reset() throws RemoteException;
}
