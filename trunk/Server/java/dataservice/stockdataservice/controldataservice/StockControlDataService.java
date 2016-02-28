package dataservice.stockdataservice.controldataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;
import po.StockErrorPO;
import po.StockOverOrLowPO;

public interface StockControlDataService extends Remote {

	public int addStockOverOrLow(StockOverOrLowPO po) throws RemoteException;

	public int addStockError(StockErrorPO po) throws RemoteException;

	// 商品报溢收入
	public double goodsOverIncome() throws RemoteException;

	// 商品报损支出
	public double goodsLowCost() throws RemoteException;

	public ArrayList<GoodsPO> showStock(String beginDate, String endDate) throws RemoteException;

	public ArrayList<GoodsPO> checkStock() throws RemoteException;

	// 库存充足检查
	public boolean isEnough(String goodsID, int num) throws RemoteException;

	// 商品调价收入
	public double PrimeCostIncome() throws RemoteException;

	// 获得所有库存报溢报损单
	public ArrayList<StockOverOrLowPO> getStockOverOrLowPO() throws RemoteException;
}