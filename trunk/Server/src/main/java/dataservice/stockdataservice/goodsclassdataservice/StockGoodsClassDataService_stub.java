package dataservice.stockdataservice.goodsclassdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsClassPO;

public class StockGoodsClassDataService_stub implements
		StockGoodsClassDataService {
	public int addGoodsClass(GoodsClassPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("add goodsClass in file succeed!");
		return 0;
	}

	public int deleteGoodsClass(GoodsClassPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("delete goodsClass in file succeed!");
		return 0;
	}

	public int modifyGoodsClass(GoodsClassPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("modify goodsClass in file succeed!");
		return 0;
	}

	public ArrayList<GoodsClassPO> show() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("show goodsClass in file succeed!");
		return null;
	}

	public String getMaxID() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("get max goodsClass id in file succeed!");
		return null;
	}

	public GoodsClassPO showGoodsClassInfo(String name) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("show goodsClass info in file succeed!");
		return null;
	}
}
