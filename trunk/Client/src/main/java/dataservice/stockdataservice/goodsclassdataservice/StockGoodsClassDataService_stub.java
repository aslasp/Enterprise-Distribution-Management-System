package dataservice.stockdataservice.goodsclassdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsClassPO;

public class StockGoodsClassDataService_stub implements
		StockGoodsClassDataService {
	public int addGoodsClass(GoodsClassPO po) {
		// TODO 自动生成的方法存根
		System.out.println("add goodsClass in file succeed!");
		return 0;
	}

	public int deleteGoodsClass(GoodsClassPO po) {
		// TODO 自动生成的方法存根
		System.out.println("delete goodsClass in file succeed!");
		return 0;
	}

	public int modifyGoodsClass(GoodsClassPO po) {
		// TODO 自动生成的方法存根
		System.out.println("modify goodsClass in file succeed!");
		return 0;
	}

	public ArrayList<GoodsClassPO> show() {
		// TODO 自动生成的方法存根
		return null;
	}

	public String getMaxID() {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<GoodsClassPO> showAllPO() {
		// TODO 自动生成的方法存根
		return null;
	}

	public GoodsClassPO showGoodsClassInfo(String name) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}
}
