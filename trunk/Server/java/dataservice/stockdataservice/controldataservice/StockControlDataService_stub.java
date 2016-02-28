package dataservice.stockdataservice.controldataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;
import po.StockErrorPO;
import po.StockOverOrLowPO;

public class StockControlDataService_stub implements StockControlDataService {
	public int addStockOverOrLow(StockOverOrLowPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("add stockOverOrLow receipt in file succeed!");
		return 0;
	}

	public int addStockError(StockErrorPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("add stockError receipt in file succeed!");
		return 0;
	}

	public double goodsOverIncome() throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

	public double goodsLowCost() throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

	public ArrayList<GoodsPO> showStock(String beginDate, String endDate)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<GoodsPO> checkStock() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	public boolean isEnough(String goodsID, int num) throws RemoteException {
		// TODO 自动生成的方法存根
		return false;
	}

	public double PrimeCostIncome() throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

	public ArrayList<StockOverOrLowPO> getStockOverOrLowPO()
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

}
