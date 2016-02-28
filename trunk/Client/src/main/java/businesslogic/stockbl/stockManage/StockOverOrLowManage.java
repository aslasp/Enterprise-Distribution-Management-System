package businesslogic.stockbl.stockManage;

import java.util.ArrayList;

import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import vo.StockOverOrLowVO;

public class StockOverOrLowManage {
	String goodName;
	String size;
	int num;
	int exactNum;
	double gap;

	public StockOverOrLowManage() {

	}

	public StockOverOrLowManage(String goodsName, String size, int num,
			int exactNum) {
		this.goodName = goodsName;
		this.size = size;
		this.num = num;
		this.exactNum = exactNum;
		this.gap = num - exactNum;
	}

	public double getGap() {
		return gap;
	}

	// 库存报溢单
	public int addStockOverReceipt(String id, String userID, int hurry,
			String info) {
		StockOverReceipt receipt = new StockOverReceipt("", "", "", userID,
				hurry, info, goodName, size, num, exactNum);
		return receipt.add();
	}

	// 库存报损单
	public int addStockLowReceipt(String id, String memberName,
			String memberID, String userID, int hurry, String info) {
		StockLowReceipt receipt = new StockLowReceipt("", memberName, memberID,
				userID, hurry, info, goodName, size, num, exactNum);
		return receipt.add();
	}

	// 根据ID查找相应的单据
	public StockOverOrLowVO find(String id) {
		StockOverOrLowVO result = null;
		StockControlBLService controller = new StockControlController();
		ArrayList<StockOverOrLowVO> list = controller.showStockOverReceipt();
		for (StockOverOrLowVO vo : list) {
			if (vo.getId().equals(id)) {
				result = vo;
				break;
			}
		}
		if (result == null) {
			list = controller.showStockLowReceipt();
			for (StockOverOrLowVO vo : list) {
				if (vo.getId().equals(id)) {
					result = vo;
					break;
				}
			}
		}
		return result;
	}

	// 库存报警
	public int addStockErrorReceipt(String goodsName, String size) {
		StockErrorReceipt receipt = new StockErrorReceipt(goodsName, size);
		receipt.add();
		return 0;
	}
}
