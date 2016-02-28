package dataservice.salesdataservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PurchasePO;
import po.PurchaseReturnPO;
import po.ReceiptPO;
import po.SalePO;
import po.SaleReturnPO;


public interface SalesDataService extends Remote{
	
	public int createPurchase(PurchasePO po) throws RemoteException;
	public int createPurchaseReturn(PurchaseReturnPO po) throws RemoteException;
	public int createSale(SalePO po) throws RemoteException;
	public int createSaleReturn(SaleReturnPO po) throws RemoteException;
	//
	public int updatePurchase(PurchasePO po) throws RemoteException;
	public int updatePurchaseReturn(PurchaseReturnPO po) throws RemoteException;
	public int updateSale(SalePO po) throws RemoteException;
	public int updateSaleReturn(SaleReturnPO po) throws RemoteException;
	//
	public ArrayList<PurchasePO> showPurchase() throws RemoteException;
	public ArrayList<PurchaseReturnPO> showPurchaseReturn() throws RemoteException;
	public ArrayList<SalePO> showSale() throws RemoteException;
	public ArrayList<SaleReturnPO> showSaleReturn() throws RemoteException;
	//
	public ArrayList<PurchasePO> findPurchase(String message,String type) throws RemoteException;
	public ArrayList<PurchaseReturnPO> findPurchaseReturn(String message,String type) throws RemoteException;
	public ArrayList<SalePO> findSale(String message,String type) throws RemoteException;
	public ArrayList<SaleReturnPO> findSaleReturn(String message,String type) throws RemoteException;
	
	public ArrayList<ReceiptPO> getAllPurchase() throws RemoteException;
	public ArrayList<ReceiptPO> getAllSale() throws RemoteException;
	public ReceiptPO findReceiptByID(String ID) throws RemoteException;
	
}
