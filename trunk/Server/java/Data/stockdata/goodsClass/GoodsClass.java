package Data.stockdata.goodsClass;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

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
		file = new JXCFile(
				"C:/Users/Administrator/Desktop/invoicingsystem/trunk/Server/src/main/java/goodsClass.ser");
	}

	public int addGoodsClass(GoodsClassPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		if (showGoodsClassInfo(po.getName()) == null) {
			return 1;
		}
		file.write(po);
		return 0;
	}

	public int deleteGoodsClass(GoodsClassPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<Object> a = file.read();

		if (a != null) {
			int i;
			for (i = 0; i < a.size(); i++) {
				GoodsClassPO b = (GoodsClassPO) a.get(i);
				if (b.getName().equals(po.getName())) {
					a.remove(i);
				}
			}
			file.write(a);
			return 0;
		} else {
			return 0;
		}
	}

	//修改是要删除原来的所有的数据重新写入，调用writeM方法——yan
	public int modifyGoodsClass(GoodsClassPO po) throws RemoteException {
		// TODO 自动生成的方法存根
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
		ArrayList<GoodsClassPO> result = new ArrayList<GoodsClassPO>();
		ArrayList<Object> a = file.read();

		if (a == null) {

		} else {
			int i;
			for (i = 0; i < a.size(); i++) {
				GoodsClassPO b = (GoodsClassPO) a.get(i);
				result.add(b);
			}
		}
		return result;
	}

	// //显示所有商品分类信息，建树并返回给界面
	// public JTree show() throws RemoteException {
	// // TODO 自动生成的方法存根
	// ArrayList<Object> a = file.read();
	// String[][] data = null;
	// // { { "体育", "足球", "篮球", "乒乓球" },
	// // { "娱乐", "唱歌", "跳舞", "相声", "小品" }, { "电视", "长虹", "海尔", "创维" },
	// // { "戏剧", "京剧", "川剧", "越剧" }, { "国家", "中国", "越南", "朝鲜", "德国" },
	// // { "武器", "飞机", "大炮", "坦克" } }
	// // 遍历获得所有分类信息，按分类将其拆分到二维数组里去——也不一定是二维啊，如果我有三级分类呢、、、、
	// int j = 0;
	// for (Object b : a) {
	// GoodsClassPO c = (GoodsClassPO) b;
	// if (c.getUpClass().equals("灯具")) {
	// data[0][j] = c.getName();
	// j++;
	// }
	// }
	//
	// DefaultMutableTreeNode root;
	// /* DefaultMutableTreeNode是树数据结构中的通用节点 */
	// DefaultMutableTreeNode child;
	// DefaultMutableTreeNode chosen;
	// JTree tree;
	// DefaultTreeModel model; // 使用 TreeNodes 的简单树数据模型.
	//
	// root = new DefaultMutableTreeNode("根");
	// tree = new JTree(root); // 建立以root为根的树
	//
	// model = (DefaultTreeModel) tree.getModel();
	// for (int i = 0; i < data.length; i++) {
	// child = new Branch(data[i++]).node();
	// chosen = (DefaultMutableTreeNode) tree
	// .getLastSelectedPathComponent();
	// if (chosen == null) {
	// chosen = root;
	// }
	// model.insertNodeInto(child, chosen, 0);
	// }
	//
	// return tree;
	// }

	public String getMaxID() throws RemoteException {
		// TODO 自动生成的方法存根
		String maxID = "";
		ArrayList<Object> a = file.read();
		//有可能是系统初始化，所以读出的MaxID可能为空!
		if (a != null) {
			maxID = ((GoodsClassPO) a.get(0)).getID();

			for (Object b : a) {
				GoodsClassPO c = (GoodsClassPO) b;
				if (Integer.parseInt(c.getID()) > Integer.parseInt(maxID)) {
					maxID = c.getID();
				}
			}
		} else {
			maxID = "0000";
		}
		return maxID;
	}

	public GoodsClassPO showGoodsClassInfo(String name) throws RemoteException {
		/*
		 * ArrayList<Object> a=file.read(); for(Object b:a){ GoodsClassPO
		 * c=(GoodsClassPO)b; if(c.getName()==name) return c; }
		 */
		GoodsClassPO po = new GoodsClassPO("0001", "飞利浦", "日光灯");

		return po;
	}

	class Branch {
		DefaultMutableTreeNode r;

		public Branch(String[] data) {
			r = new DefaultMutableTreeNode(data[0]);
			for (int i = 1; i < data.length; i++) {
				r.add(new DefaultMutableTreeNode(data[i]));
			}
		}

		public DefaultMutableTreeNode node() {
			return r;
		}
	}

	public ArrayList<GoodsClassPO> showAllPO() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<GoodsClassPO> result = new ArrayList<GoodsClassPO>();
		// ArrayList<Object> a = file.read();
		// if (a == null) {
		// return result;
		// } else {
		// for (Object b : a) {
		// GoodsClassPO c = (GoodsClassPO) b;
		// result.add(c);
		// }
		//
		// return result;
		// }

		GoodsClassPO po = new GoodsClassPO("00001", "飞利浦", "灯具");
		result.add(po);
		return result;
	}

}
