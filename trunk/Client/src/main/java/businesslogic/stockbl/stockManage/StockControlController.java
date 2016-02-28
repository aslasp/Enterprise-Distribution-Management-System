package businesslogic.stockbl.stockManage;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import vo.GoodsVO;
import vo.StockErrorVO;
import vo.StockOverOrLowVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;

public class StockControlController implements StockControlBLService {

	public int addStockOverOrLow(StockOverOrLowVO vo) {
		// TODO 自动生成的方法存根
		StockOverOrLowManage manage = new StockOverOrLowManage(
				vo.getGoodsName(), vo.getSize(), vo.getNum(), vo.getExactNum());
		if (vo.getType().equals(ReceiptType.STOCKOVER)) {
			return manage.addStockOverReceipt(vo.getId(), vo.getUser(),
					vo.getHurry(), vo.getInfo());
		} else {
			return manage
					.addStockLowReceipt(vo.getId(), vo.getMemberName(),
							vo.getMemberID(), vo.getUser(), vo.getHurry(),
							vo.getInfo());
		}
	}

	// 库存报警
	public int addStockError(StockErrorVO vo) {
		// TODO 自动生成的方法存根
		StockOverOrLowManage manage = new StockOverOrLowManage();
		return manage
				.addStockErrorReceipt(vo.getGoodsName(), vo.getGoodsSize());
	}

	// 获得库存报溢收入
	public double getGoodsOverIncome(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.getGoodsOverIncome(beginDate, endDate);
	}

	// 获得库存报损支出
	public double getGoodsLowCost(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.getGoodsLowCost(beginDate, endDate);
	}

	// 库存查看
	public ArrayList<ArrayList<String>> showStock(String beginDate,
			String endDate) {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.showStock(beginDate, endDate);

	}

	// 库存盘点(库存均价)
	public ArrayList<ArrayList<String>> checkStock() {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.checkStock();
	}

	// 销售检查库存是否充足
	public boolean isEnough(String ID, int num) {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.isEnough(ID, num);
	}

	// 商品调价收入
	public double getPrimeCostIncome(ArrayList<CommodityVO> commodityList) {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.getPrimeCostIncome(commodityList);
	}

	// 获得商品赠送支出
	public double getGiftCost(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.getGiftCost(beginDate, endDate);
	}

	// 显示库存报溢单
	public ArrayList<StockOverOrLowVO> showStockOverReceipt() {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.showStockOverReceipt();
	}

	// 显示库存报损单
	public ArrayList<StockOverOrLowVO> showStockLowReceipt() {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		return manage.showStockLowReceipt();
	}

	// 库存报警检查
	public boolean stockNumCheck(String goodsID) {
		// TODO 自动生成的方法存根
		StockManage manage = new StockManage();
		boolean result = manage.stockNumCheck(goodsID);

		if (result) {
			StockOverOrLowManage stockManage = new StockOverOrLowManage();
			StockGoodsBLService goodController = new GoodsController();
			GoodsVO vo = null;
			try {
				vo = goodController.findByID(goodsID);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			stockManage.addStockErrorReceipt(vo.getName(), vo.getSize());
		}

		return result;
	}

	//修改
	public int modify(StockOverOrLowVO newVo) {
		// TODO 自动生成的方法存根
		if (newVo.getType().equals(ReceiptType.STOCKOVER)) {
			StockOverReceipt receipt = new StockOverReceipt(newVo.getId(),
					newVo.getMemberName(), newVo.getMemberID(), newVo.getUser(),
					newVo.getHurry(), newVo.getInfo(), newVo.getGoodsName(),
					newVo.getSize(), newVo.getNum(), newVo.getExactNum());
			return receipt.Modify(newVo.getId());
		} else {
			StockLowReceipt receipt = new StockLowReceipt(newVo.getId(),
					newVo.getMemberName(), newVo.getMemberID(), newVo.getUser(),
					newVo.getHurry(), newVo.getInfo(), newVo.getGoodsName(),
					newVo.getSize(), newVo.getNum(), newVo.getExactNum());
			return receipt.Modify(newVo.getId());
		}
	}
}
