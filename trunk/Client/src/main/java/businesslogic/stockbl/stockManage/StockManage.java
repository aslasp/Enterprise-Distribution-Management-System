package businesslogic.stockbl.stockManage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import businesslogic.utilitybl.getServer;
import po.CommodityPO;
import po.GiftPO;
import po.GoodsPO;
import po.PurchasePO;
import po.PurchaseReturnPO;
import po.ReceiptPO.ReceiptType;
import po.SalePO;
import po.SaleReturnPO;
import po.StockOverOrLowPO;
import vo.CommodityVO;
import vo.StockOverOrLowVO;
import dataservice.salesdataservice.SalesDataService;
import dataservice.stockdataservice.controldataservice.StockControlDataService;
import dataservice.stockdataservice.giftdataservice.GiftDataService;
import dataservice.stockdataservice.goodsdataservice.StockGoodsDataService;

public class StockManage {
	private StockControlDataService service;
	private StockGoodsDataService goodsService;
	private GiftDataService giftService;
	private SalesDataService saleService;
	private String host;
	private String url;
	private String url2;
	private String url3;
	private String url4;

	public StockManage() {
		try {
			host = getServer.getServer();
			url = "rmi://" + host + "/stockManageService";
			url2 = "rmi://" + host + "/goodsService";
			url3 = "rmi://" + host + "/giftService";
			url4 = "rmi://" + host + "/salesService";
			service = (StockControlDataService) Naming.lookup(url);
			goodsService = (StockGoodsDataService) Naming.lookup(url2);
			giftService = (GiftDataService) Naming.lookup(url3);
			saleService = (SalesDataService) Naming.lookup(url4);
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

	// 显示库存报溢单
	public ArrayList<StockOverOrLowVO> showStockOverReceipt() {
		ArrayList<StockOverOrLowPO> list = new ArrayList<StockOverOrLowPO>();
		ArrayList<StockOverOrLowPO> OverLsit = new ArrayList<StockOverOrLowPO>();
		ArrayList<StockOverOrLowVO> result = new ArrayList<StockOverOrLowVO>();
		list = service.getStockOverOrLowPO();
		for (StockOverOrLowPO po : list) {
			if (po.getType().equals(ReceiptType.STOCKOVER)) {
				OverLsit.add(po);
			}
		}
		result = POToVO(OverLsit);
		return result;
	}

	// 显示库存报损单
	public ArrayList<StockOverOrLowVO> showStockLowReceipt() {
		ArrayList<StockOverOrLowPO> list = new ArrayList<StockOverOrLowPO>();
		ArrayList<StockOverOrLowPO> lowList = new ArrayList<StockOverOrLowPO>();
		ArrayList<StockOverOrLowVO> result = new ArrayList<StockOverOrLowVO>();
		list = service.getStockOverOrLowPO();
		for (StockOverOrLowPO po : list) {
			if (po.getType().equals(ReceiptType.STOCKLOW)) {
				lowList.add(po);
			}
		}
		result = POToVO(lowList);
		return result;
	}

	// 库存查看
	public ArrayList<ArrayList<String>> showStock(String beginDate,
			String endDate) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		// 出库由销售、进货退货、赠送组成
		// 入库由销售退货、进货组成
		// 金额的话，相应的部分有对应的金额，
		// 注意单据要在执行完毕后才可以计算
		ArrayList<GoodsPO> goodsList = new ArrayList<GoodsPO>();
		ArrayList<SalePO> saleList = new ArrayList<SalePO>();
		ArrayList<SaleReturnPO> saleReturnList = new ArrayList<SaleReturnPO>();
		ArrayList<PurchasePO> purchaseList = new ArrayList<PurchasePO>();
		ArrayList<PurchaseReturnPO> purchaseReturnList = new ArrayList<PurchaseReturnPO>();
		ArrayList<GiftPO> giftList = new ArrayList<GiftPO>();
		try {
			goodsList = goodsService.showGoods();
			saleList = saleService.showSale();
			saleReturnList = saleService.showSaleReturn();
			purchaseList = saleService.showPurchase();
			purchaseReturnList = saleService.showPurchaseReturn();
			giftList = giftService.getGiftList(beginDate, endDate);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		for (int i = 0; i < goodsList.size(); i++) {
			ArrayList<String> temp = new ArrayList<String>();
			GoodsPO po = goodsList.get(i);

			int numIn = 0;// 入库数量
			double moneyIn = 0;// 入库金额
			int numOut = 0;// 出库数量
			double moneyOut = 0;// 出库金额
			int saleNum = 0;// 销售数量
			double saleMoney = 0;// 销售金额
			int purchaseNum = 0;// 进货数量
			double purchaseMoney = 0;// 进货金额
			// 进货
			if (purchaseList != null) {
				for (int j = 0; j < purchaseList.size(); j++) {
					PurchasePO purchasePo = purchaseList.get(j);
					String date = purchasePo.getDate().replace("/", "");
					if (beginDate.compareTo(date) <= 0
							&& endDate.compareTo(date) >= 0) {// 在当前日期内
						if (purchasePo.getStatus() == 3||purchasePo.getStatus()==2) {// 执行完毕
							ArrayList<CommodityPO> cList = purchasePo
									.getPurchaseList();
							for (int k = 0; k < cList.size(); k++) {
								if (cList.get(k).getID()
										.equals(po.getGoodsID())) {// 属于当前商品
									numIn += cList.get(k).getNum();
									purchaseNum += cList.get(k).getNum();
									moneyIn += cList.get(k).getTotal();
									purchaseMoney += cList.get(k).getTotal();
								}
							}
						}
					}
				}
			}

			// 销售退货
			if (saleReturnList != null) {
				for (int j = 0; j < saleReturnList.size(); j++) {
					SaleReturnPO saleReturnPO = saleReturnList.get(j);
					String date = saleReturnPO.getDate().replace("/", "");
					if (beginDate.compareTo(date) <= 0
							&& endDate.compareTo(date) >= 0) {// 在当前日期内
						if (saleReturnPO.getStatus() == 3||saleReturnPO.getStatus() == 2) {// 执行完毕
							//销售退货不给设置3，只给设置2
							ArrayList<CommodityPO> cList = saleReturnPO
									.getSalesreturnList();
							for (int k = 0; k < cList.size(); k++) {
								if (cList.get(k).getID()
										.equals(po.getGoodsID())) {// 属于当前商品
									numIn += cList.get(k).getNum();
									moneyIn += cList.get(k).getTotal();
								}
							}
						}
					}
				}
			}

			// 销售
			if (saleList != null) {
				for (int j = 0; j < saleList.size(); j++) {
					SalePO salePO = saleList.get(j);
					String date = salePO.getDate().replace("/", "");
					if (beginDate.compareTo(date) <= 0
							&& endDate.compareTo(date) >= 0) {// 在当前日期内
						if (salePO.getStatus() == 3||salePO.getStatus() == 2) {// 执行完毕
							//销售不给设置3，只给设置2
							ArrayList<CommodityPO> cList = salePO
									.getSalesList();
							for (int k = 0; k < cList.size(); k++) {
								if (cList.get(k).getID()
										.equals(po.getGoodsID())) {// 属于当前商品
									numOut += cList.get(k).getNum();
									moneyOut += cList.get(k).getTotal();
									saleNum += cList.get(k).getNum();
									saleMoney += cList.get(k).getTotal();
								}
							}
						}
					}
				}
			}

			// 进货退货
			if (purchaseReturnList != null) {
				for (int j = 0; j < purchaseReturnList.size(); j++) {
					PurchaseReturnPO purchaseReturnPO = purchaseReturnList
							.get(j);
					String date = purchaseReturnPO.getDate().replace("/", "");
					if (beginDate.compareTo(date) <= 0
							&& endDate.compareTo(date) >= 0) {// 在当前日期内
						if (purchaseReturnPO.getStatus() == 3||purchaseReturnPO.getStatus() == 2) {// 执行完毕
							//进货退货不给设置3，只能判断2了
							ArrayList<CommodityPO> cList = purchaseReturnPO
									.getPurchaseReturnList();
							for (int k = 0; k < cList.size(); k++) {
								if (cList.get(k).getID()
										.equals(po.getGoodsID())) {// 属于当前商品
									numOut += cList.get(k).getNum();
									moneyOut += cList.get(k).getTotal();
								}
							}
						}
					}
				}
			}

			// 赠送
			if (giftList != null) {

				for (int j = 0; j < giftList.size(); j++) {
					GiftPO giftPO = giftList.get(j);
					// 获得的库存赠送单即为这段时间内的单据，不要判断时间是否符合了
					if (giftPO.getStatus() == 3) {// 执行完毕
						ArrayList<CommodityPO> cList = giftPO.getGiftList();
						for (int k = 0; k < cList.size(); k++) {
							if (cList.get(k).getID().equals(po.getGoodsID())) {// 属于当前商品
								numOut += cList.get(k).getNum();
							}
						}
					}
				}
			}

			temp.add(po.getGoodsID());// 商品编号
			temp.add(po.getName());// 商品名称
			temp.add(po.getSize());// 商品型号
			temp.add(String.valueOf(numOut));// 出库数量
			temp.add(String.valueOf(moneyOut));// 出库金额
			temp.add(String.valueOf(numIn));// 入库数量
			temp.add(String.valueOf(moneyIn));// 入库金额
			temp.add(String.valueOf(saleNum));// 销售数量
			temp.add(String.valueOf(saleMoney));// 销售金额
			temp.add(String.valueOf(purchaseNum));// 进货数量
			temp.add(String.valueOf(purchaseMoney));// 进货金额

			result.add(temp);

		}

		return result;
	}

	// 库存盘点(库存均价==)
	public ArrayList<ArrayList<String>> checkStock() {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

		ArrayList<GoodsPO> goodsList = goodsService.showGoods();
		if (goodsList != null) {
			int i = 0;
			for (GoodsPO po : goodsList) {
				ArrayList<String> tp = new ArrayList<String>();
				// 包括当天的各种商品的名称，型号，库存数量，库存均价，批次，[盘点时，相当于设置了一个截止点，这个点就是批次（日期）批号（序号），这是系统根据当前盘点时间产生的。]批号，出厂日期，并且显示行号
				// 行号、商品名称、型号、库存数量、库存均价、批次、批号、出厂日期
				NumberFormat nf = new DecimalFormat("00000");
				String hang = nf.format(i);
				tp.add(po.getName());// 名称
				tp.add(po.getSize());// 型号
				tp.add(String.valueOf(po.getNumInStock()));// 库存数量
				tp.add(String.valueOf(po.getPurchasePrice()));// 库存均价
				tp.add(getDate());// 批次
				tp.add(hang);// 批号
				tp.add(po.getManufactureDate());// 出厂日期
				result.add(tp);
				i++;
			}
		}

		return result;
	}

	// 检查库存是否充足满足销售需求
	public boolean isEnough(String id, int num) {
		ArrayList<GoodsPO> list = goodsService.findGoods(id);
		if (list.size() != 0) {
			if (list.get(0).getNumInStock() < num) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	// 获得每个进货单的库存调价收入
	public double getPrimeCostIncome(ArrayList<CommodityVO> commodityList) {
		double result = 0;

		if (commodityList != null) {
			if (commodityList.size() != 0) {
				for (CommodityVO vo : commodityList) {
					String goodID = vo.getID();
					try {
						GoodsPO good = goodsService.findByID(goodID);
						// 调价收入跟默认进价比了==
						double purchasePrice=0;
						if(good!=null)
							purchasePrice = good.getPurchasePrice();
						double exactPurchasePrice = vo.getPrice();
						result += Math.abs(purchasePrice - exactPurchasePrice)
								* vo.getNum();
					} catch (RemoteException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	// 获得库存报溢收入
	public double getGoodsOverIncome(String beginDate, String endDate) {
		double goodsOverIncome = 0;
		ArrayList<StockOverOrLowPO> list = service.getStockOverOrLowPO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getType().equals(ReceiptType.STOCKOVER)) {
				String n = list.get(i).getGoodsName();
				String date = list.get(i).getDate().replace("/", "");
				if (date.compareTo(beginDate) >= 0
						&& date.compareTo(endDate) <= 0) {
					GoodsPO po = goodsService.findGoods(n).get(0);
					goodsOverIncome += (po.getPrice() * (0 - list.get(i)
							.getGap()));
				}
			}
		}
//		System.out.println("StockManage.getGoodsOverIncome()报溢收入："+goodsOverIncome);
		return goodsOverIncome;
	}

	// 获得库存报损支出
	public double getGoodsLowCost(String beginDate, String endDate) {
		double goodsLowCost = 0;
		ArrayList<StockOverOrLowPO> list = service.getStockOverOrLowPO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getType().equals(ReceiptType.STOCKLOW)) {
				String n = list.get(i).getGoodsName();
				String date = list.get(i).getDate().replace("/", "");
				if (date.compareTo(beginDate) >= 0
						&& date.compareTo(endDate) <= 0) {
					GoodsPO po = goodsService.findGoods(n).get(0);
					goodsLowCost += (po.getPrice() * list.get(i).getGap());
//					System.out.println("StockManage.getGoodsLowCost()list.get(i).getGap():"+list.get(i).getGap());
				}
			}
		}
//		System.out.println("StockManage.getGoodsLowCost()报损支出："+goodsLowCost);
		return goodsLowCost;
	}

	// 获得商品赠送支出
	public double getGiftCost(String beginDate, String endDate) {
		double giftCost = 0;
		try {
			ArrayList<GiftPO> list = giftService.getGiftList();
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

	// 库存报警检查
	public boolean stockNumCheck(String GoodsID) {
		// TODO 自动生成的方法存根
		boolean result = false;
		try {
			GoodsPO good = goodsService.findByID(GoodsID);
			int minNumInStock = good.getMinNumInStock();
			int numInStock = good.getNumInStock();
			if (minNumInStock >= numInStock) {
				result = true;
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<StockOverOrLowVO> POToVO(
			ArrayList<StockOverOrLowPO> list) {
		ArrayList<StockOverOrLowVO> result = new ArrayList<StockOverOrLowVO>();
		for (StockOverOrLowPO po : list) {
			StockOverOrLowVO vo = new StockOverOrLowVO(po.getId(),
					po.getUserID(), po.getType(), po.getStatus(),
					po.getHurry(), po.getInfo(), po.getGoodsName(),
					po.getSize(), po.getNum(), po.getExactNum());
			result.add(vo);
		}
		return result;
	}

	private String getDate() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String sysDatetime = fmt.format(rightNow.getTime());

		return sysDatetime;
	}
}
