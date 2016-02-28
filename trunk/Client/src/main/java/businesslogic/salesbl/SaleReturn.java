package businesslogic.salesbl;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;

import po.CommodityPO;
import po.ReceiptPO;
import po.SaleReturnPO;
import vo.CommodityVO;
import vo.GoodsVO;
import vo.ReceiptVO;
import vo.SaleReturnVO;
import businesslogic.memberbl.Member;
import businesslogic.receiptbl.Receipt;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogic.utilitybl.getDate;
import businesslogic.utilitybl.getServer;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import dataservice.salesdataservice.SalesDataService;

public class SaleReturn extends Receipt {

	private static Commodity com;
	private SalesDataService service;

	public SaleReturn() throws Exception {
		String host = getServer.getServer();
		String url = "rmi://" + host + "/salesService";

		service = (SalesDataService) Naming.lookup(url);

	}

	public int add(SaleReturnVO vo) {
		Send(vo.getId());
		return service.createSaleReturn(voToPo(vo));
	}

	public int Modify(SaleReturnVO vo) {
		return service.updateSaleReturn(voToPo(vo));
	}

	public ArrayList<SaleReturnVO> find(String message, String type) {
		ArrayList<SaleReturnPO> po = service.findSaleReturn(message, type);
		if (po == null)
			return null;
		else {
			ArrayList<SaleReturnVO> vo = new ArrayList<SaleReturnVO>();
			for (int i = 0; i < po.size(); i++)
				vo.add(poToVo(po.get(i)));
			return vo;
		}
	}

	public SaleReturnVO find(String id) {
		ReceiptPO po = service.findReceiptByID(id);
		if (po == null)
			return null;
		else {
			SaleReturnPO ppo = (SaleReturnPO) po;
			return poToVo(ppo);
		}

	}

	public String getNewID() {
		// TODO Auto-generated method stub
		String id = null;
		ArrayList<SaleReturnPO> po = service.showSaleReturn();
		if (po == null)
			id = "00001";
		else {
			int i = po.size();
			String date = po.get(i - 1).getId().substring(6, 14);
			if (date.equals(getDate.getdate())) {
				Double d = Double.parseDouble(po.get(i - 1).getId()
						.substring(15)) + 1;
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMinimumIntegerDigits(5);
				nf.setGroupingUsed(false);
				id = nf.format(d);
			} else
				id = "00001";

		}
		return id;
	}

	public int excute(ReceiptVO v) {
		// 修改库存
		SaleReturnVO vo = (SaleReturnVO) v;
		Member m;
		try {
			m = new Member();
			m.changeToReceive(vo.getMemberID(), -vo.getTotal()[4]);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 库存入库
		StockGoodsBLService goodsController = new GoodsController();
		ArrayList<CommodityVO> list = vo.getSaleReturnList();
		// 存储待更新商品
		ArrayList<GoodsVO> saleReturnGoods = new ArrayList<GoodsVO>();
		for (CommodityVO cvo : list) {
			try {
				GoodsVO goodsVO = goodsController.findByID(cvo.getID());
				// 检查是否可以销售
				StockControlBLService contronller = new StockControlController();
				if (contronller.isEnough(cvo.getID(), cvo.getNum())) {
					// 修改库存数量
					goodsVO.setNumInStock(goodsVO.getNumInStock()
							+ cvo.getNum());
					// 修改最近售价
					goodsVO.setLastPrice(cvo.getPrice());
					saleReturnGoods.add(goodsVO);
				} else {
					return 2;// 库存数量不满足销售
				}
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			// check均无问题 执行
			for (GoodsVO goodsVO : saleReturnGoods) {
				goodsController.modifyGoods(goodsVO);
				// 库存报警检查
				StockControlBLService stockController = new StockControlController();
				stockController.stockNumCheck(goodsVO.getGoodsID());
			}
		}
		Reply(vo.getId(), vo.getType(), 0);
		return 0;// 执行成功

	}

	public ArrayList<SaleReturnVO> show() {

		ArrayList<SaleReturnPO> po = service.showSaleReturn();
		if (po == null)
			return null;
		else {
			ArrayList<SaleReturnVO> vo = new ArrayList<SaleReturnVO>();
			for (int i = 0; i < po.size(); i++)
				vo.add(poToVo(po.get(i)));
			return vo;
		}
	}

	public static SaleReturnVO poToVo(SaleReturnPO po) {
		ArrayList<CommodityPO> list = po.getSalesreturnList();
		ArrayList<CommodityVO> rList = com.poTVo(list);
		SaleReturnVO vo = new SaleReturnVO(po.getId(), po.getMemberName(),
				po.getMemberID(), po.getUserID(), po.getStatus(), po.getInfo(),
				po.getHurry(), po.getTotal(), po.getDiscount(), po.getClerk(),
				rList, po.getStockID());
		return vo;

	}


	public SaleReturnPO voToPo(SaleReturnVO vo) {
		ArrayList<CommodityVO> list = vo.getSaleReturnList();
		ArrayList<CommodityPO> rList = com.voTPo(list);
		SaleReturnPO po = new SaleReturnPO(vo.getClerk(), rList, vo.getId(),
				vo.getMemberID(), vo.getMemberName(), vo.getUser(),
				vo.getStatus(), vo.getHurry(), vo.getInfo(), vo.getStockid(),
				vo.getDiscount(), vo.getTotal());
		return po;
	}

	public ReceiptPO getRedReceipt(ReceiptPO po) {
		SaleReturnPO sale = (SaleReturnPO) po;
		ArrayList<CommodityPO> list = com.getRedList(sale.getSalesreturnList());
		double total[] = new double[sale.getTotal().length];
		double discount[] = new double[sale.getDiscount().length];
		for (int i = 0; i < total.length; i++)
			total[i] = -sale.getTotal()[i];
		for (int j = 0; j < discount.length; j++)
			discount[j] = -sale.getDiscount()[j];
		SaleReturnPO redSale = new SaleReturnPO(sale.getClerk(), list,
				po.getId(), po.getMemberID(), po.getMemberName(),
				po.getUserID(), po.getStatus(), po.getHurry(), po.getInfo(),
				sale.getStockID(), discount, total);
		service.createSaleReturn(redSale);
		return redSale;

	}
}
