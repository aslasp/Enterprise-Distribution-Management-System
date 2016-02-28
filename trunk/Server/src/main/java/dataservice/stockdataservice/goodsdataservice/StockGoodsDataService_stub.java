package dataservice.stockdataservice.goodsdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;

public class StockGoodsDataService_stub implements StockGoodsDataService {
	public int addGoods(GoodsPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("add goods in file succeed!");
		return 0;
	}

	public int deleteGoods(GoodsPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("delete goods in file succeed!");
		return 0;
	}

	public int modifyGoods(GoodsPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("modify goods in file succeed!");
		return 0;
	}

	public ArrayList<GoodsPO> findGoods(String message) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("find goods in file succeed!");
		return null;
	}

	public ArrayList<GoodsPO> showStock(String beginDate, String endDate)
			throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("show stock in file succeed!");
		return new ArrayList<GoodsPO>();
	}

	public ArrayList<GoodsPO> checkStock() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("check stock in file succeed!");
		return new ArrayList<GoodsPO>();
	}

	public String getMaxID() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("get max ID in file succeed!");
		return null;
	}

	public ArrayList<GoodsPO> showGoods() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("show goods in file succeed!");
		return null;
	}

	public GoodsPO findByID(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("find by id in file succeed!");
		return null;
	}
}
