package dataservice.receiptdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ReceiptMessagePO;
import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;

public interface ReceiptDataService extends Remote {
	public ReceiptPO Add(ReceiptPO po)throws RemoteException;
	public ReceiptPO addAndCopy(ReceiptPO po) throws RemoteException;
	public ReceiptPO findById(String id)throws RemoteException;
	public int Modify(ReceiptPO po)throws RemoteException;
	public ArrayList<ReceiptPO> showAll()throws RemoteException;
//	public double getNum(ReceiptType type,String date)throws RemoteException;
	public ArrayList<ReceiptPO> show(ReceiptType type)throws RemoteException;

	//批量审批  所有id数组里的单据 status都设置为 参数status
	public int Batch(String[] id,int status)throws RemoteException;
	//审批  同上  改变一个单据staus
	public int Approve(String id,int status)throws RemoteException;
	
	//单据精确查找  筛选；每一个message元素 均为一个限定条件 返回符合上所有条件的所有单据
	public ArrayList<ReceiptPO> AccurateFind(String[] message)throws RemoteException;
	public int setStatus(String id,int st) throws RemoteException;
	
	public ArrayList<ReceiptMessagePO> showAllMessage()throws RemoteException;
	public void addReceiptMessage(ReceiptMessagePO po)throws RemoteException;
	public void deleteReceiptMessage(ReceiptMessagePO po)throws RemoteException;
}
