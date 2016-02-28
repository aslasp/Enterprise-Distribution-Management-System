package dataservice.stockdataservice.goodsdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;

public interface StockGoodsDataService extends Remote {
	public int addGoods(GoodsPO po) throws RemoteException;

	public int deleteGoods(GoodsPO po) throws RemoteException;

	public int modifyGoods(GoodsPO po) throws RemoteException;

	public ArrayList<GoodsPO> findGoods(String message) throws RemoteException;

	public ArrayList<GoodsPO> showStock(String beginDate, String endDate)
			throws RemoteException;

	public ArrayList<GoodsPO> checkStock() throws RemoteException;

	public String getMaxID() throws RemoteException;
	
	public ArrayList<GoodsPO> showGoods() throws RemoteException;
}
