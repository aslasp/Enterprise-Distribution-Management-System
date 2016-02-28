package Data.stockdata.goods;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.GoodsPO;
import Data.serutility.JXCFile;
import dataservice.stockdataservice.goodsdataservice.StockGoodsDataService;

public class Goods extends UnicastRemoteObject implements StockGoodsDataService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;

	public Goods() throws RemoteException {
		file = new JXCFile("C:/Users/Administrator/Desktop/invoicingsystem/trunk/Server/src/main/java/stock.ser");
//		GoodsPO po=new GoodsPO("00020001","飞利浦日光灯","SR01",10,10,200,100,200, 100, "飞利浦");
//		file.write(po);
	}

	public int addGoods(GoodsPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		file.write(po);
		return 0;
	}

	public int deleteGoods(GoodsPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<Object> a = file.read();

		int i;
		for (i = 0; i < a.size(); i++) {
			GoodsPO b = (GoodsPO) a.get(i);
			if (b.getGoodsID().equals(po.getGoodsID())) {
				a.remove(i);
			}
		}

		file.write(a);
		return 0;
	}

	public int modifyGoods(GoodsPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<Object> a = file.read();

		int i;
		for (i = 0; i < a.size(); i++) {
			GoodsPO b = (GoodsPO) a.get(i);
			if (b.getGoodsID().equals(po.getGoodsID())) {
				a.set(i, po);
			}
		}

		file.write(a);
		return 0;
	}

	public ArrayList<GoodsPO> findGoods(String message) throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<Object> a = file.read();
		ArrayList<GoodsPO> result = new ArrayList<GoodsPO>();
		int i;
		for (i = 0; i < a.size(); i++) {
			GoodsPO b = (GoodsPO) a.get(i);
			// 定义模糊查找的范围：编号、名称、型号(暂时)
			String tp = "" + b.getGoodsID() + "," + b.getName() + ","
					+ b.getSize();
			if (tp.contains(message)) {
				result.add(b);
			}
		}

		return result;
	}

	public ArrayList<GoodsPO> showGoods() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<GoodsPO> result = new ArrayList<GoodsPO>();
		ArrayList<Object> a = file.read();
		if (a==null) {
			return result;
		} else {
			int i;
			for (i = 0; i < a.size(); i++) {
				GoodsPO b = (GoodsPO) a.get(i);
				result.add(b);
			}

			return result;
		}
//		return result;
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

	public String getMaxID() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

}
