package Data.stockdata.goodsClass;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.GoodsClassPO;
import Data.serutility.JXCFile;
import dataservice.stockdataservice.goodsclassdataservice.StockGoodsClassDataService;

public class GoodsClass extends UnicastRemoteObject implements
		StockGoodsClassDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;

	public GoodsClass() throws RemoteException {
		super();
		file = new JXCFile("src/main/java/goodsClass.ser");
	}

	public int addGoodsClass(GoodsClassPO po) throws RemoteException {
		file = new JXCFile("src/main/java/goodsClass.ser");
		file.write(po);
		return 0;
	}

	public int deleteGoodsClass(GoodsClassPO po) throws RemoteException {
		file = new JXCFile("src/main/java/goodsClass.ser");
		ArrayList<Object> a = file.read();

		if (a != null) {
			int i;
			for (i = 0; i < a.size(); i++) {
				GoodsClassPO b = (GoodsClassPO) a.get(i);
				if (b.getName().equals(po.getName())) {
					a.remove(i);
				}
			}
			file.writeM(a);
			return 0;
		} else {
			return 0;
		}
	}

	// 修改是要删除原来的所有的数据重新写入，调用writeM方法——yan
	public int modifyGoodsClass(GoodsClassPO po) throws RemoteException {
		file = new JXCFile("src/main/java/goodsClass.ser");
		ArrayList<Object> a = file.read();

		int i;
		for (i = 0; i < a.size(); i++) {
			GoodsClassPO b = (GoodsClassPO) a.get(i);
			if (b.getID().equals(po.getID())) {
				b.setName(po.getName());
				b.setUpClass(po.getUpClass());
			}
		}

		file.writeM(a);
		return 0;
	}

	public ArrayList<GoodsClassPO> show() throws RemoteException {
		file = new JXCFile("src/main/java/goodsClass.ser");
		ArrayList<GoodsClassPO> result = new ArrayList<GoodsClassPO>();
		ArrayList<Object> a = file.read();

		if (a == null) {
			return null;
		} else {
			for (int i = 0; i < a.size(); i++) {
				GoodsClassPO b = (GoodsClassPO) a.get(i);
				result.add(b);
			}
		}
		return result;
	}

	public String getMaxID() throws RemoteException {
		file = new JXCFile("src/main/java/goodsClass.ser");
		String maxID = null;
		ArrayList<Object> a = file.read();
		// 有可能是系统初始化，所以读出的MaxID可能为空!
		if (a != null) {
			maxID = ((GoodsClassPO) a.get(0)).getID();

			for (Object b : a) {
				GoodsClassPO c = (GoodsClassPO) b;
				if (Integer.parseInt(c.getID()) > Integer.parseInt(maxID)) {
					maxID = c.getID();
				}
			}
		} else {
			maxID = null;
		}
		return maxID;
	}

	public GoodsClassPO showGoodsClassInfo(String name) throws RemoteException {
		file = new JXCFile("src/main/java/goodsClass.ser");
		GoodsClassPO po = null;
		ArrayList<Object> a = file.read();
		if (a != null) {
			for (Object b : a) {
				GoodsClassPO c = (GoodsClassPO) b;
				if (c.getName().equals(name)) {
					po = c;
					break;
				}
			}
		}

		return po;
	}
}
