package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LogPO;

public interface LogService extends Remote{

	public void AddLog(LogPO po) throws RemoteException;
	public ArrayList<LogPO>  showAll() throws RemoteException;
	public ArrayList<LogPO>  find(String startDate,String endDate) throws RemoteException;
}
