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
		System.out.println("get goods over income in file succeed!");
		return 0;
	}

	public double goodsLowCost() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("get goods low cost in file succeed!");
		return 0;
	}

	public ArrayList<GoodsPO> showStock(String beginDate, String endDate)
			throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("show stock in file succeed!");
		return null;
	}

	public ArrayList<GoodsPO> checkStock() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("check stock in file succeed!");
		return null;
	}

	public boolean isEnough(String goodsID, int num) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("judege isEnough in file succeed!");
		return false;
	}

	public double PrimeCostIncome() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("prime cost income in file succeed!");
		return 0;
	}

	public ArrayList<StockOverOrLowPO> getStockOverOrLowPO()
			throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("get stock overOrLowPO in file succeed!");
		return null;
	}

	public int recordPrimeCostIncome(String primeCostIncome)
			throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("add stockError receipt in file succeed!");
		return 0;
	}

	public String getMaxID() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("get max id in file succeed!");
		return null;
	}

	public ArrayList<StockErrorPO> getStockErrorPO() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("get stock errorPO in file succeed!");
		return null;
	}

	public String getErrorMaxID() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("get error max id in file succeed!");
		return null;
	}

	public StockOverOrLowPO findByID(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("find by id in file succeed!");
		return null;
	}

	public int excute(StockOverOrLowPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("excute in file succeed!");
		return 0;
	}

	public int modify(StockOverOrLowPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

}
