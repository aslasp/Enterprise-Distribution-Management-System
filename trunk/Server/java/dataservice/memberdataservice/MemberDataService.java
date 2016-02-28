package dataservice.memberdataservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MemberPO;

public interface MemberDataService extends Remote{
	public int add(MemberPO po) throws RemoteException;
	public int delete(String id)throws RemoteException;
	public int modify(MemberPO po)throws RemoteException;
	public MemberPO find(String message)throws RemoteException;
	public ArrayList<MemberPO> showAll() throws RemoteException; 
}
