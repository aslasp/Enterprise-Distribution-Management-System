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

import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;
import po.StockOverOrLowPO;
import vo.GoodsVO;
import vo.ReceiptVO;
import vo.StockOverOrLowVO;
import businesslogic.receiptbl.Receipt;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.utilitybl.getServer;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import dataservice.stockdataservice.controldataservice.StockControlDataService;

public class StockLowReceipt extends Receipt {
	private String goodName;
	private String size;
	private int num;
	private int exactNum;
	private int gap;
	private StockControlDataService service;
	private String host;
	private String url;

	public StockLowReceipt() throws Exception {
		try {
			host = getServer.getServer();
			url = "rmi://" + host + "/stockManageService";
			service = (StockControlDataService) Naming.lookup(url);
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public StockLowReceipt(String id, String memberName, String memberID,
			String userID, int hurry, String info, String goodName,
			String size, int num, int exactNum) {
		super(id, memberName, memberID, userID, ReceiptType.STOCKLOW, hurry, 3,
				info);
		try {
			host = getServer.getServer();
			url = "rmi://" + host + "/stockManageService";
			service = (StockControlDataService) Naming.lookup(url);
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

		this.goodName = goodName;
		this.size = size;
		this.num = num;
		this.exactNum = exactNum;
		this.gap = num - exactNum;
	}

	public int add() {
		// 生成编号
		String exactID = "KCBSD-" + getDate() + "-";
		String maxID = service.getMaxID();

		if (maxID == null) {
			exactID += "00001";
		} else {
			NumberFormat nf = new DecimalFormat("00000");
			int tp = Integer.parseInt(maxID);
			exactID += nf.format(tp + 1);
		}
		StockOverOrLowPO po = new StockOverOrLowPO(exactID,
				super.getmemberName(), super.getMemberID(), super.getUserID(),
				ReceiptType.STOCKLOW, 0, super.getHurry(), super.getInfo(),
				goodName, size, num, exactNum);
		Send(po.getId());
		return service.addStockOverOrLow(po);
	}

	public int excute(ReceiptVO v) {
		StockOverOrLowVO vo = (StockOverOrLowVO) v;
		// 向系统库存中添加商品
		StockGoodsBLService goodsController = new GoodsController();
		ArrayList<GoodsVO> list = goodsController.findGoods(vo.getGoodsName()
				+ vo.getSize());
		if (list != null) {
			if (list.size() == 0) {
				return 10;
			}
			GoodsVO goodvo = list.get(0);
			goodvo.setNumInStock(vo.getExactNum());
			goodsController.modifyGoods(goodvo);
			StockOverOrLowPO po = voToPo(vo);
			service.excute(po);
			return 0;
		} else {
			return 10;
		}
	}

	// 红冲
	public ReceiptPO getRedReceipt(ReceiptPO po) {
		StockOverOrLowPO sopo = (StockOverOrLowPO) po;
		int newExactNum = sopo.getNum() + sopo.getGap();
		StockOverOrLowPO newPO = new StockOverOrLowPO(sopo.getId(),
				sopo.getMemberName(), sopo.getMemberID(), sopo.getUserID(),
				sopo.getType(), sopo.getStatus(), sopo.getHurry(),
				sopo.getInfo(), sopo.getGoodsName(), sopo.getSize(),
				sopo.getNum(), newExactNum);
		service.addStockOverOrLow(newPO);
		return newPO;
	}

	// 修改
	public int Modify(String id) {
		String statusString = super.getStatus();
		int status = -1;
		switch (statusString) {
		case "待审批":
			status = 0;
			break;
		case "审批不通过":
			status = 1;
			break;
		case "审批通过":
			status = 2;
			break;
		case "执行完毕":
			status = 3;
			break;
		default:
			break;
		}
		StockOverOrLowPO po = new StockOverOrLowPO(id, super.getmemberName(),
				super.getMemberID(), super.getUserID(), ReceiptType.STOCKLOW,
				status, super.getHurry(), super.getInfo(), goodName, size, num,
				exactNum);
		return service.modify(po);
	}

	public String getGoodName() {
		return goodName;
	}

	public String getSize() {
		return size;
	}

	public int getNum() {
		return num;
	}

	public int getExactNum() {
		return exactNum;
	}

	public int getGap() {
		return gap;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setExactNum(int exactNum) {
		this.exactNum = exactNum;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	private String getDate() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String sysDatetime = fmt.format(rightNow.getTime());

		return sysDatetime;
	}

	private StockOverOrLowPO voToPo(StockOverOrLowVO vo) {
		return new StockOverOrLowPO(vo.getId(), vo.getMemberName(),
				vo.getMemberID(), vo.getUser(), ReceiptType.STOCKLOW,
				vo.getStatus(), vo.getHurry(), vo.getInfo(), vo.getGoodsName(),
				vo.getSize(), vo.getNum(), vo.getExactNum());
	}
}
