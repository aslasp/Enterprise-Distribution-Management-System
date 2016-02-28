package businesslogic.salesbl;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;

import po.CommodityPO;
import po.PurchasePO;
import po.ReceiptPO;
import vo.CommodityVO;
import vo.GoodsVO;
import vo.PurchaseVO;
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

public class Purchase extends Receipt {
	private static Commodity com;
	private SalesDataService service;

	public Purchase() throws Exception {
		String host = getServer.getServer();
		String url = "rmi://" + host + "/salesService";

		service = (SalesDataService) Naming.lookup(url);

	}

	public int AddPurchase(PurchaseVO vo) {
		Send(vo.getId());
		return service.createPurchase(voToPo(vo));
	}

	public int excute(ReceiptVO v) {
		PurchaseVO vo = (PurchaseVO) v;
		Member m;
		try {
			m = new Member();
			m.changeToPay(vo.getMemberID(), vo.getTotalInAll());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 修改库存数量、最近进价及出厂日期
		StockGoodsBLService goodsController = new GoodsController();
		ArrayList<CommodityVO> list = vo.getPurchaseList();
		for (CommodityVO cvo : list) {
			try {
				GoodsVO goodsVO = goodsController.findByID(cvo.getID());
				if(goodsVO!=null){
				goodsVO.setNumInStock(goodsVO.getNumInStock() + cvo.getNum());
				goodsVO.setLastPurchasePrice(cvo.getPrice());
				goodsVO.setManufactureDate(vo.getDate());// 修改出厂日期
				goodsController.modifyGoods(goodsVO);}
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}
		
		//设置本单据的库存调价收入
		StockControlBLService stockControlController=new StockControlController();
		double adjustIncome=stockControlController.getPrimeCostIncome(vo.getPurchaseList());
		vo.setAdjustCost(adjustIncome);
		
		
		ModifyPurchase(vo);
		return 0;
	}

	public ArrayList<PurchaseVO> find(String message, String type) {

		ArrayList<PurchasePO> po = service.findPurchase(message, type);
		if (po == null)
			return null;
		else {
			ArrayList<PurchaseVO> vo = new ArrayList<PurchaseVO>();
			for (int i = 0; i < po.size(); i++)
				vo.add(poToVo(po.get(i)));
			return vo;
		}
	}

	public PurchaseVO find(String id) {
		ReceiptPO po = service.findReceiptByID(id);
		if (po == null)
			return null;
		else {
			PurchasePO ppo = (PurchasePO) po;
			return poToVo(ppo);
		}

	}

	public int ModifyPurchase(PurchaseVO vo) {
		return service.updatePurchase(voToPo(vo));

	}

	public ArrayList<PurchaseVO> show() {
		ArrayList<PurchasePO> po = service.showPurchase();
		if (po == null)
			return null;
		else {
			ArrayList<PurchaseVO> vo = new ArrayList<PurchaseVO>();
			for (int i = 0; i < po.size(); i++) {
				vo.add(poToVo(po.get(i)));
			}

			return vo;
		}
	}

	public static PurchasePO voToPo(PurchaseVO vo) {
		ArrayList<CommodityPO> puList;
		ArrayList<CommodityVO> List = vo.getPurchaseList();

		puList = com.voTPo(List);
		PurchasePO po = new PurchasePO(vo.getId(), vo.getMemberName(),
				vo.getMemberID(), vo.getStockid(), vo.getUser(), puList,
				vo.getInfo(), vo.getTotalInAll(),vo.getAdjustCost(), vo.getStatus(), vo.getHurry());
		return po;
	}

	public static PurchaseVO poToVo(PurchasePO po) {
		ArrayList<CommodityVO> puList;
		ArrayList<CommodityPO> List = po.getPurchaseList();

		puList = com.poTVo(List);
		PurchaseVO vo = new PurchaseVO(po.getId(), po.getMemberName(),
				po.getMemberID(), po.getStockID(), po.getUserID(), puList,
				po.getInfo(), po.getTotalInAll(), po.getStatus(), po.getHurry());
		vo.setAdjustCost(po.getAdjustCost());
		return vo;

	}

	public String getNewID() {
		// TODO Auto-generated method stub
		String id = null;
		ArrayList<PurchasePO> po = service.showPurchase();
		if (po == null)
			id = "00001";
		else {
			int i = po.size();
			String date = po.get(i - 1).getId().substring(4, 12);
			if (date.equals(getDate.getdate())) {
				Double d = Double.parseDouble(po.get(i - 1).getId()
						.substring(13)) + 1;
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMinimumIntegerDigits(5);
				nf.setGroupingUsed(false);
				id = nf.format(d);
			} else
				id = "00001";

		}
		return id;
	}
	
	public ReceiptPO getRedReceipt(ReceiptPO po){
		PurchasePO purchase=(PurchasePO)po;
		ArrayList<CommodityPO> list=com.getRedList(purchase.getPurchaseList());
		PurchasePO redPurchase=new PurchasePO(po.getId(),
				po.getMemberName(),po.getMemberID(),purchase.getStockID(),po.getUserID(),
				list,po.getInfo(),-purchase.getTotalInAll(),-purchase.getAdjustCost(),
				po.getStatus(),po.getHurry());
		service.createPurchase(redPurchase);
		return redPurchase;
		
	}

}
