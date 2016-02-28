package dataservice.financedataservice.accountdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.AccountPO;

public interface FinanceAccountDataService  extends Remote{
	public int addAccount(AccountPO po) throws RemoteException;
    public int deleteAccount(AccountPO po) throws RemoteException;
    public int modifyAccount(AccountPO po,String name) throws RemoteException;
    public ArrayList<AccountPO> findAccount(String s) throws RemoteException;
    public ArrayList<AccountPO> showAll() throws RemoteException;
    public AccountPO findByName(String s) throws RemoteException; 
    public int addMoney(String name,double m) throws RemoteException; 
    public int delMoney(String name,double m) throws RemoteException;
}
