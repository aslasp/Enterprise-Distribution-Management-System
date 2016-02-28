package dataservice.memberdataservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MemberPO;
import po.MemberPO.MemberType;

public interface MemberDataService extends Remote{
	public int add(MemberPO po) throws RemoteException;
	public int delete(String id)throws RemoteException;
	public int modify(MemberPO po)throws RemoteException;
	public ArrayList<MemberPO> find(String message)throws RemoteException;
	public ArrayList<MemberPO> showAll() throws RemoteException; 
	public MemberPO findByID(String ID) throws RemoteException;
	public int getNum(MemberType type) throws RemoteException;
	public ArrayList<MemberPO> show(MemberType type)throws RemoteException;
	public int changeToReceive(String id,double m)throws RemoteException;
	public int changeToPay(String id,double m)throws RemoteException;
}
