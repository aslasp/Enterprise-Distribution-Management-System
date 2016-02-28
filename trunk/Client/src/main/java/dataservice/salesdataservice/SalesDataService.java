package dataservice.salesdataservice;
import java.rmi.Remote;
import java.util.ArrayList;

import po.PurchasePO;
import po.PurchaseReturnPO;
import po.ReceiptPO;
import po.SalePO;
import po.SaleReturnPO;


public interface SalesDataService extends Remote{
	public int createPurchase(PurchasePO po);
	public int createPurchaseReturn(PurchaseReturnPO po);
	public int createSale(SalePO po);
	public int createSaleReturn(SaleReturnPO po);
	//
	public int updatePurchase(PurchasePO po);
	public int updatePurchaseReturn(PurchaseReturnPO po);
	public int updateSale(SalePO po);
	public int updateSaleReturn(SaleReturnPO po);
	//
	public ArrayList<PurchasePO> showPurchase();
	public ArrayList<PurchaseReturnPO> showPurchaseReturn();
	public ArrayList<SalePO> showSale();
	public ArrayList<SaleReturnPO> showSaleReturn();
	//
	public ArrayList<PurchasePO> findPurchase(String message,String type);
	public ArrayList<PurchaseReturnPO> findPurchaseReturn(String message,String type);
	public ArrayList<SalePO> findSale(String message,String type);
	public ArrayList<SaleReturnPO> findSaleReturn(String message,String type);
	
	public ArrayList<ReceiptPO> getAllPurchase();
	public ArrayList<ReceiptPO> getAllSale();
	public ReceiptPO findReceiptByID(String ID);
}
