package dataservice.financedataservice.listdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentPO;

public interface PaymentDataService extends Remote{
	public int createPayment(PaymentPO po) throws RemoteException;
	 public ArrayList<PaymentPO> getPayment() throws RemoteException;
	 public int getNum() throws RemoteException;
	 public PaymentPO findByID(String id) throws RemoteException;
	 public int modify(PaymentPO po) throws RemoteException;
}
