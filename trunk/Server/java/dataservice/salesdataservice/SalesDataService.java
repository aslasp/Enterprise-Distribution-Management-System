package dataservice.salesdataservice;
import java.rmi.Remote;

import po.PurchasePO;
import po.PurchaseReturnPO;
import po.SalePO;
import po.SaleReturnPO;


public interface SalesDataService extends Remote{
	public void getSaleList();
	public int purchase(PurchasePO po);
	public int purchaseReturn(PurchaseReturnPO po);
	public int sale(SalePO po);
	public int saleReturn(SaleReturnPO po);
}
