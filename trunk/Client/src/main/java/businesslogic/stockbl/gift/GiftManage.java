package businesslogic.stockbl.gift;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityPO;
import po.GiftPO;
import vo.CommodityVO;
import vo.GiftVO;
import vo.GoodsVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogic.utilitybl.getServer;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import dataservice.stockdataservice.giftdataservice.GiftDataService;

public class GiftManage {
	private GiftDataService service;
	private String host;
	private String url1;

	public GiftManage() {
		try {
			host = getServer.getServer();
			url1 = "rmi://" + host + "/giftService";
			service = (GiftDataService) Naming.lookup(url1);
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

	// 库存赠送单的处理
	public int excute(GiftReceipt receipt) {
		StockControlBLService controller = new StockControlController();
		boolean isAble = true;// 库存是否满足
		ArrayList<CommodityVO> list = receipt.getGiftVOList();
		for (int i = 0; i < list.size(); i++) {
			CommodityVO vo = list.get(i);
			boolean isEnough = controller.isEnough(vo.getID(), vo.getNum());
			if (!isEnough) {
				isAble = false;
				break;
			}
		}
		if (isAble) {
			// 减少库存数量
			StockGoodsBLService goodsController = new GoodsController();
			for (int i = 0; i < list.size(); i++) {
				CommodityVO vo = list.get(i);
				GoodsVO good = null;
				try {
					good = goodsController.findByID(vo.getID());
				} catch (RemoteException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				good.setNumInStock(good.getNumInStock() - vo.getNum());
				goodsController.modifyGoods(good);

				// 检测库存是否报警
				StockControlBLService stockController = new StockControlController();
				stockController.stockNumCheck(good.getGoodsID());
			}

			// 调用服务器端处理库存赠送单
			GiftPO po = new GiftPO(receipt.getId(), receipt.getMemberID(),
					receipt.getmemberName(), receipt.getUserID(),
					receipt.getInfo(), 3, receipt.getHurry(), VOToPO(list));
			int result = -1;
			try {
				result = service.dealGift(po);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return result;
		} else {
			return 7;
		}

	}

	// 商品赠送支出
	public double getGiftCost(String beginDate, String endDate) {
		double giftCost = 0;
		try {
			ArrayList<GiftPO> list = service.getGiftList();
			for (int i = 0; i < list.size(); i++) {
				String date = list.get(i).getDate().replace("/", "");
				if (date.compareTo(beginDate) >= 0
						&& date.compareTo(endDate) <= 0) {
					ArrayList<CommodityPO> commodityList = list.get(i)
							.getGiftList();
					for (int j = 0; j < commodityList.size(); j++) {
						giftCost += commodityList.get(j).getCost();
					}
				}
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return giftCost;
	}

	// 获取所有库存赠送清单
	public ArrayList<GiftVO> getGiftList() {
		ArrayList<GiftVO> result = new ArrayList<GiftVO>();
		ArrayList<GiftPO> list = new ArrayList<GiftPO>();
		try {
			list = service.getGiftList();
			result = POToVO(list);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	// 获取某段时间内的库存赠送清单
	public ArrayList<GiftPO> getGiftList(String beginDate, String endDate) {
		ArrayList<GiftPO> list = new ArrayList<GiftPO>();
		try {
			list = service.getGiftList(beginDate, endDate);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return list;
	}

	// 获得某段时间内的商品赠送总量
	public int getGiftNum(String beginDate, String endDate) {
		int num = 0;
		ArrayList<GiftPO> list = new ArrayList<GiftPO>();
		try {
			list = service.getGiftList(beginDate, endDate);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for (GiftPO po : list) {
			for (CommodityPO commodity : po.getGiftList()) {
				num += commodity.getNum();
			}
		}

		return num;
	}

	// 获得某段时间内的商品赠送总额
	public double getGiftMoney(String beginDate, String endDate) {
		double money = -1;
		// ArrayList<GiftPO> list = new ArrayList<GiftPO>();
		// try {
		// list = service.getGiftList(beginDate, endDate);
		// } catch (RemoteException e) {
		// // TODO 自动生成的 catch 块
		// e.printStackTrace();
		// }
		// for (GiftPO po : list) {
		// for (CommodityPO commodity : po.getGiftList()) {
		// money += commodity.getCost();
		// }
		// }

		return money;
	}

	// 根据ID查找库存赠送单
	public GiftVO findByID(String id) {
		// TODO 自动生成的方法存根
		GiftVO vo = null;
		try {
			GiftPO po = service.findByID(id);
			vo = giftPOToVO(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vo;
	}

	// 将赠送商品列表由vo转为po
	private ArrayList<CommodityPO> VOToPO(ArrayList<CommodityVO> list) {
		ArrayList<CommodityPO> result = new ArrayList<CommodityPO>();
		for (int i = 0; i < list.size(); i++) {
			CommodityVO vo = list.get(i);
			CommodityPO po = new CommodityPO(vo.getID(), vo.getName(),
					vo.getType(), vo.getPrice(), vo.getLast_bid(), vo.getNum(),
					vo.getTotal(), vo.getCost(), vo.getTip());
			result.add(po);
		}

		return result;
	}

	// 将赠送商品列表由po转为vo
	private static ArrayList<CommodityVO> commodityPOToVO(
			ArrayList<CommodityPO> list) {
		ArrayList<CommodityVO> result = new ArrayList<CommodityVO>();
		for (CommodityPO po : list) {
			CommodityVO vo = new CommodityVO(po.getID(), po.getName(),
					po.getType(), po.getPrice(), po.getLast_bid(), po.getNum(),
					po.getTotal(), po.getCost(), po.getTip());
			result.add(vo);
		}
		return result;
	}

	// 将库存赠送列表由po转为vo
	private ArrayList<GiftVO> POToVO(ArrayList<GiftPO> list) {
		ArrayList<GiftVO> result = new ArrayList<GiftVO>();
		for (GiftPO po : list) {
			GiftVO vo = giftPOToVO(po);
			result.add(vo);
		}
		return result;
	}

	public static GiftVO giftPOToVO(GiftPO po) {

		GiftVO vo = new GiftVO(po.getId(), po.getMemberName(),
				po.getMemberID(), po.getUserID(), po.getStatus(),
				po.getHurry(), po.getInfo(), commodityPOToVO(po.getGiftList()));
		return vo;
	}
}
