package dataservice.receiptdataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.ReceiptMessagePO;
import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;

public interface ReceiptDataService extends Remote{
	//红冲
	public ReceiptPO Add(ReceiptPO po);
	//红冲并复制
	public ReceiptPO addAndCopy(ReceiptPO po) ;
	public ReceiptPO findById(String id);
	public int Modify(ReceiptPO po);
	//按时间顺序返回所有单据
	public ArrayList<ReceiptPO> showAll();
	
	//每个单据自己有getNew ID 方法
	//public double getNum(ReceiptType type,String date)throws RemoteException;
	public ArrayList<ReceiptPO> show(ReceiptType type);
	//批量审批  所有id数组里的单据 status都设置为 参数status
	public int Batch(String[] id,int status);
	//审批  同上  改变一个单据staus
	public int Approve(String id,int status);
	
	//单据精确查找  筛选；每一个message元素 均为一个限定条件 返回符合上所有条件的所有单据
	public ArrayList<ReceiptPO> AccurateFind(String[] message);
	public int setStatus(String id,int st);
	
	public ArrayList<ReceiptMessagePO> showAllMessage();
	public void addReceiptMessage(ReceiptMessagePO vo);
	public void deleteReceiptMessage(ReceiptMessagePO vo);
	
	
}
