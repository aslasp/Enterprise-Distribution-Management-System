package businesslogic.salesbl;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;

import po.CommodityPO;
import po.PurchaseReturnPO;
import po.ReceiptPO;
import vo.CommodityVO;
import vo.GoodsVO;
import vo.PurchaseReturnVO;
import vo.ReceiptVO;
import businesslogic.memberbl.Member;
import businesslogic.receiptbl.Receipt;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogic.utilitybl.getDate;
import businesslogic.utilitybl.getServer;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import dataservice.salesdataservice.SalesDataService;

//进货 退货单必须为逆操作，总经理只能审批通过与否
public class PurchaseReturn extends Receipt {
	private double total;

	private static Commodity com;
	private SalesDataService service;

	public PurchaseReturn() throws Exception {
		String host = getServer.getServer();
		String url = "rmi://" + host + "/salesService";

		service = (SalesDataService) Naming.lookup(url);

	}

	public int AddPurchaseReturn(PurchaseReturnVO vo) {
		return service.createPurchaseReturn(voToPo(vo));
	}

	public int excute(ReceiptVO v) {
		// 修改库存

		PurchaseReturnVO vo = (PurchaseReturnVO) v;
		Member m;
		try {
			m = new Member();
			m.changeToPay(vo.getMemberID(), -vo.getTotalInAll());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 修改库存数量
		StockGoodsBLService goodsController = new GoodsController();
		ArrayList<CommodityVO> list = vo.getPurchaseReturnList();
		// 存储待更新商品
		ArrayList<GoodsVO> purchaseReturnGoods = new ArrayList<GoodsVO>();
		for (CommodityVO cvo : list) {
			try {
				GoodsVO goodsVO = goodsController.findByID(cvo.getID());
				// 检查是否可以销售
				StockControlBLService contronller = new StockControlController();
				if (contronller.isEnough(cvo.getID(), cvo.getNum())) {
					// 修改库存数量
					goodsVO.setNumInStock(goodsVO.getNumInStock()
							- cvo.getNum());
					// 修改最近售价
					goodsVO.setLastPrice(cvo.getPrice());
					purchaseReturnGoods.add(goodsVO);
				} else {
					return 2;// 库存数量不满足进货退货
				}
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			// check均无问题 执行
			for (GoodsVO goodsVO : purchaseReturnGoods) {
				goodsController.modifyGoods(goodsVO);
				// 库存报警检查
				StockControlBLService stockController = new StockControlController();
				stockController.stockNumCheck(goodsVO.getGoodsID());
			}
		}
		return 0;
	}

	public PurchaseReturnVO find(String id) {
		ReceiptPO po = service.findReceiptByID(id);
		if (po == null)
			return null;
		else {
			PurchaseReturnPO ppo = (PurchaseReturnPO) po;
			return poToVo(ppo);
		}

	}

	public ArrayList<PurchaseReturnVO> find(String message, String type) {

		ArrayList<PurchaseReturnPO> po = service.findPurchaseReturn(message,
				type);
		if (po == null)
			return null;
		else {
			ArrayList<PurchaseReturnVO> vo = new ArrayList<PurchaseReturnVO>();
			for (int i = 0; i < po.size(); i++)
				vo.add(poToVo(po.get(i)));
			return vo;
		}
	}

	public int ModifyPurchaseReturn(PurchaseReturnVO vo) {
		return service.updatePurchaseReturn(voToPo(vo));

	}

	public ArrayList<PurchaseReturnVO> show() {
		ArrayList<PurchaseReturnPO> po = service.showPurchaseReturn();
		if (po == null)
			return null;
		else {
			ArrayList<PurchaseReturnVO> vo = new ArrayList<PurchaseReturnVO>();
			for (int i = 0; i < po.size(); i++) {
				vo.add(poToVo(po.get(i)));
			}

			return vo;
		}
	}

	private PurchaseReturnPO voToPo(PurchaseReturnVO vo) {
		ArrayList<CommodityPO> puList;
		ArrayList<CommodityVO> List = vo.getPurchaseReturnList();

		puList = com.voTPo(List);
		PurchaseReturnPO po = new PurchaseReturnPO(vo.getId(),
				vo.getMemberID(), vo.getMemberName(), vo.getStockid(),
				vo.getPurid(), vo.getUser(), puList, vo.getInfo(),
				vo.getTotalInAll(), vo.getStatus(), vo.getHurry());
		return po;
	}

	// String id,String MemName,String MemID,String user, int status,
	// String info,int hurry,ArrayList<CommodityVO> list,double total,String
	// sid) {
	public static PurchaseReturnVO poToVo(PurchaseReturnPO po) {
		ArrayList<CommodityVO> puList;
		ArrayList<CommodityPO> List = po.getPurchaseReturnList();

		puList = com.poTVo(List);
		PurchaseReturnVO vo = new PurchaseReturnVO(po.getId(),
				po.getMemberName(), po.getMemberID(), po.getUserID(),
				po.getStatus(), po.getInfo(), po.getHurry(), puList,
				po.getTotalInAll(), po.getStockID(), po.getpurid());
		return vo;

	}

	public String getNewID() {
		// TODO Auto-generated method stub
		String id = null;
		ArrayList<PurchaseReturnPO> po = service.showPurchaseReturn();
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

	// 进货退货，需检查库存！

	public double getTotal() {
		return this.total;
	}

	public ReceiptPO getRedReceipt(ReceiptPO po) {
		PurchaseReturnPO purchase = (PurchaseReturnPO) po;
		ArrayList<CommodityPO> list = com.getRedList(purchase
				.getPurchaseReturnList());
		PurchaseReturnPO redPurchase = new PurchaseReturnPO(po.getId(),
				po.getMemberID(), po.getMemberName(), purchase.getStockID(),
				purchase.getpurid(), po.getUserID(), list, po.getInfo(),
				-purchase.getTotalInAll(), po.getStatus(), po.getHurry());
		service.createPurchaseReturn(redPurchase);
		return redPurchase;

	}

}
