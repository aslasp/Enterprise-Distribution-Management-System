package businesslogic.stockbl.goodsClass;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import po.GoodsClassPO;
import po.GoodsPO;
import vo.GoodsClassVO;
import vo.GoodsVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.utilitybl.getServer;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import dataservice.stockdataservice.goodsclassdataservice.StockGoodsClassDataService;
import dataservice.stockdataservice.goodsdataservice.StockGoodsDataService;

public class GoodsClass {
	String classID;
	String name;
	String upClassName;
	StockGoodsClassDataService service;
	StockGoodsDataService goodsService;

	public GoodsClass() {
		// System.setSecurityManager(new SecurityManager());
		try {
			String host = getServer.getServer();
			String url1 = "rmi://" + host + "/goodsClassService";
			String url2 = "rmi://" + host + "/goodsService";
			service = (StockGoodsClassDataService) Naming.lookup(url1);
			goodsService = (StockGoodsDataService) Naming.lookup(url2);
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public GoodsClass(String name, String upClassName) {
		this();
		this.name = name;
		this.upClassName = upClassName;
	}

	public int addGoodsClass() throws RemoteException {
		GoodsClassManage manage = new GoodsClassManage();
		boolean isExist = false;
		ArrayList<GoodsClassVO> list = new ArrayList<GoodsClassVO>();
		list = manage.show();

		for (int i = 0; i < list.size(); i++) {
			if (name.equals(list.get(i).getName())) {
				isExist = true;
			}
		}
		if (!isExist) {
			GoodsClassPO upClass = manage.find(upClassName);
			ArrayList<GoodsPO> goodsList = goodsService.showGoods();
			if (upClass != null) {
				// 查找上级分类下是否有商品，如果有则不可以在其下加子分类
				boolean isAble = true;
				for (int i = 0; i < goodsList.size(); i++) {
					if (upClass.getName().equals(
							goodsList.get(i).getGoodsClassName())) {
						isAble = false;
						break;
					}
				}

				if (isAble == true) {
					// 商品分类编号+1
					NumberFormat nf = new DecimalFormat("0000");
					String maxID = service.getMaxID();
					if (maxID != null) {
						int tp = Integer.parseInt(maxID);
						classID = nf.format((tp + 1));
					} else {
						classID = nf.format(1);
					}
					GoodsClassPO po = new GoodsClassPO(classID, name,
							upClassName);
					return service.addGoodsClass(po);
				} else {
					return 1;// 上级分类下有商品，无法添加
				}
			} else {
				return 2;// 上级分类不存在
			}
		} else {
			return 5;// 当前分类已存在
		}
	}

	public int deleteGoodsClass() {
		ArrayList<GoodsPO> goodsList = goodsService.showGoods();
		// 查找当前分类下是否有商品，如果有则不可以删除该分类
		// 当前分类下的分类有商品，也不可以删除！！
		boolean isAble = true;
		// 算法==
		for (int i = 0; i < goodsList.size(); i++) {
			String cn = goodsList.get(i).getGoodsClassName();
			if (name.equals(cn)) {
				isAble = false;
				break;
			} else {
				GoodsClassManage manage = new GoodsClassManage();
				GoodsClassPO currClass = manage.find(cn);
				cn = currClass.getUpClass();
				while (!cn.equals("灯具")) {
					if (name.equals(cn)) {
						isAble = false;
						break;
					} else {
						currClass = manage.find(cn);
						cn = currClass.getUpClass();
					}
				}

			}

		}

		if (isAble) {
			GoodsClassPO po = new GoodsClassPO(classID, name, upClassName);
			int result = -1;
			try {
				result = service.deleteGoodsClass(po);
				// 如果该分类不是叶节点，则将其下的叶节点也要删除
				GoodsClassController controller = new GoodsClassController();
				ArrayList<GoodsClassVO> list = new ArrayList<GoodsClassVO>();
				list = controller.show();

				for (int i = 0; i < list.size(); i++) {
					GoodsClassVO vo = list.get(i);
					if (vo.getUpClassName().equals(name)) {
						GoodsClassPO tp = new GoodsClassPO("", vo.getName(),
								vo.getUpClassName());
						result = service.deleteGoodsClass(tp);
					}
				}

			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return result;
		} else {
			return 3;
		}
	}

	public int modifyGoodsClass(GoodsClassVO oldVO) {
		int result = -1;
		try {
			GoodsClassPO oldPO = service.showGoodsClassInfo(oldVO.getName());
			GoodsClassPO newPO = new GoodsClassPO(oldPO.getID(), name,
					upClassName);
			if (service.showGoodsClassInfo(name) == null) {
				result = service.modifyGoodsClass(newPO);

				// 把该分类的下级分类也要修改了
				ArrayList<GoodsClassPO> classList = service.show();
				if (classList != null) {
					for (GoodsClassPO po : classList) {
						if (po.getUpClass().equals(oldVO.getName())) {
							po.setUpClass(oldVO.getName());
							service.modifyGoodsClass(po);
						}
					}
				}

				// 把该分类下的商品分类也要修改了
				StockGoodsBLService controller = new GoodsController();
				ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
				list = controller.showGoods();
				for (int i = 0; i < list.size(); i++) {
					GoodsVO vo = list.get(i);
					if (vo.getGoodsClass().equals(oldVO.getName())) {
						vo.setGoodsClass(name);
						controller.modifyGoods(vo);
					}
				}
			} else {
				result = 6;
			}

		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public String getClassID() {
		return classID;
	}

	public String getName() {
		return name;
	}

	public String getUpClassName() {
		return upClassName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUpClassName(String upClassName) {
		this.upClassName = upClassName;
	}

}
