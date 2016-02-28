package businesslogic.salesbl;

import java.rmi.Naming;
import java.text.NumberFormat;
import java.util.ArrayList;

import po.CommodityPO;
import po.MemberPO.MemberLevel;
import po.ReceiptPO;
import po.SalePO;
import vo.CommodityVO;
import vo.GoodsVO;
import vo.ReceiptVO;
import vo.SaleVO;
import businesslogic.memberbl.Member;
import businesslogic.promotionbl.giftCouponPro;
import businesslogic.promotionbl.promotionController;
import businesslogic.receiptbl.Receipt;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogic.utilitybl.getDate;
import businesslogic.utilitybl.getServer;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import dataservice.salesdataservice.SalesDataService;

public class Sale extends Receipt { 

	private SalesDataService service;
	private static Commodity com;

	public Sale() throws Exception {
		String host = getServer.getServer();
		String url = "rmi://" + host + "/salesService";
		service = (SalesDataService) Naming.lookup(url);

	}

	public int Add(SaleVO vo) {
		Send(vo.getId());//发送待审批单据提示消息
		return service.createSale(voToPo(vo));
	}

	// 先find获取原item的cost,total,原位置，修改后,存回list
	public int Modify(SaleVO vo) {
		return service.updateSale(voToPo(vo));
	}

	public ArrayList<SaleVO> find(String message, String type) {
		ArrayList<SalePO> po = service.findSale(message, type);
		if (po == null)
			return null;
		else {
			ArrayList<SaleVO> vo = new ArrayList<SaleVO>();
			for (int i = 0; i < po.size(); i++)
				vo.add(poToVo(po.get(i)));
			return vo;
		}

	}
	
	
	public SaleVO find(String id) {
		ReceiptPO po = service.findReceiptByID(id);
		if (po == null)
			return null;
		else {
			SalePO ppo = (SalePO) po;
			return poToVo(ppo);
		}

	}

	public ArrayList<SaleVO> show() {
		ArrayList<SalePO> po = service.showSale();
		if (po == null)
			return null;
		else {
			ArrayList<SaleVO> vo = new ArrayList<SaleVO>();
			for (int i = 0; i < po.size(); i++)
				vo.add(poToVo(po.get(i)));
			return vo;
		}
	}

	// 计算会员优惠
	public double getPrivilege(String MemberID) throws Exception {
		Member member = new Member();
		MemberLevel level = member.findById(MemberID).getmLevel();
		double dis;
		switch (level) {
		case FIVE:
			dis = 0.88;
			break;
		case FOUR:
			dis = 0.9;
			break;
		case THREE:
			dis = 0.95;
			break;
		case TWO:
			dis = 0.98;
			break;
		default:
			dis = 1;
		}
		return dis;

	}

	//正常执行
	public int excute(ReceiptVO v) {
		
		return excute(v, true);
	}

	//参数二  若红冲销售单 ，则代金券要设为未使用过
	public int excute(ReceiptVO v, boolean tag) {
		// 修改库存
		boolean status = tag;
		SaleVO vo = (SaleVO) v;
		try {

			Member m = new Member();
			int i = m.checkToReceive(vo.getMemberID(), vo.getToPay());
			if (i == 0) {
				//库存和客户应收均无问题 再执行
				StockGoodsBLService goodsController = new GoodsController();
				ArrayList<CommodityVO> list = vo.getSalesList();
				//存储待更新商品
				ArrayList<GoodsVO> saleGoods=new ArrayList<GoodsVO>();
				for (CommodityVO cvo : list) {
					GoodsVO goodsVO = goodsController.findByID(cvo.getID());
					// 检查是否可以销售
					StockControlBLService contronller = new StockControlController();
					if (contronller.isEnough(cvo.getID(), cvo.getNum())) {
						// 修改库存数量
						goodsVO.setNumInStock(goodsVO.getNumInStock()
								- cvo.getNum());
						//修改最近售价
						goodsVO.setLastPrice(cvo.getPrice());
						saleGoods.add(goodsVO);						
					} else {
						return 2;// 库存数量不满足销售
					}
				}
				//check均无问题 执行
				for(GoodsVO goodsVO:saleGoods){
					goodsController.modifyGoods(goodsVO);
					// 库存报警检查
					StockControlBLService stockController = new StockControlController();
					stockController.stockNumCheck(goodsVO.getGoodsID());
					
				}
				m.changeToReceive(vo.getMemberID(),vo.getTotalValue());
				m.updatePoints(vo.getMemberID(), vo.getTotalValue());
				//执行促销策略
				if (!vo.getProid().equals(""))
					promotionController.Excute(vo.getProid(), vo);
				//代金券使用
				if (!vo.getCouponid().equals("")) {
					giftCouponPro gp = new giftCouponPro();
					gp.useCoupon(vo.getCouponid(), status);
				}
			}else{
				return 1;// 不成功 超过额度
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return 0;
	}


	public String getNewID() {
		// TODO Auto-generated method stub
		String id = null;
		ArrayList<SalePO> po = service.showSale();
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

	//获取红冲单据
	public ReceiptPO getRedReceipt(ReceiptPO po) {
		SalePO sale = (SalePO) po;
		ArrayList<CommodityPO> list = com.getRedList(sale.getSalesList());
		double total[] = new double[sale.getTotal().length];
		double discount[] = new double[sale.getDiscount().length];
		for (int i = 0; i < total.length; i++)
			total[i] = -sale.getTotal()[i];
		for (int j = 0; j < discount.length; j++)
			discount[j] = -sale.getDiscount()[j];
		SalePO redSale = new SalePO(sale.getClerk(), list, po.getId(),
				po.getMemberID(), po.getMemberName(), po.getUserID(),
				po.getStatus(), po.getHurry(), po.getInfo(), sale.getStockID(),
				sale.getProid(), sale.getCouponid(), discount, total);
		service.createSale(redSale);//入库
		return redSale;

	}
	
	
	public static SalePO voToPo(SaleVO vo) {
		ArrayList<CommodityPO> saList;
		ArrayList<CommodityVO> List = vo.getSalesList();

		saList = com.voTPo(List);
		SalePO po = new SalePO(vo.getClerk(), saList, vo.getId(),
				vo.getMemberID(), vo.getMemberName(), vo.getUser(),
				vo.getStatus(), vo.getHurry(), vo.getInfo(), vo.getStockid(),
				vo.getProid(), vo.getProid(), vo.getDiscount(), vo.getTotal());
		return po;
	}

	public static SaleVO poToVo(SalePO po) {
		ArrayList<CommodityVO> saList;
		ArrayList<CommodityPO> List = po.getSalesList();

		saList = com.poTVo(List);
		SaleVO vo = new SaleVO(po.getClerk(), saList, po.getId(),
				po.getMemberName(), po.getMemberID(), po.getUserID(),
				po.getStatus(), po.getHurry(), po.getInfo(), po.getStockID(),
				po.getProid(), po.getCouponid(), po.getTotal(),
				po.getDiscount());
		return vo;
	}

}
