package businesslogicservice.stockblservice.controlblservice;

import java.util.ArrayList;

import vo.CommodityVO;
import vo.StockErrorVO;
import vo.StockOverOrLowVO;

public interface StockControlBLService {
	public int addStockOverOrLow(StockOverOrLowVO vo);

	public int addStockError(StockErrorVO vo);

	// 显示库存报溢单
	public ArrayList<StockOverOrLowVO> showStockOverReceipt();

	// 显示库存报损溢单
	public ArrayList<StockOverOrLowVO> showStockLowReceipt();

	// 商品报溢收入
	public double getGoodsOverIncome(String beginDate, String endDate);

	// 商品报损支出
	public double getGoodsLowCost(String beginDate, String endDate);

	public ArrayList<ArrayList<String>> showStock(String beginDate,
			String endDate);

	public ArrayList<ArrayList<String>> checkStock();

	public boolean isEnough(String ID, int num);

	// 商品调价收入
	public double getPrimeCostIncome(ArrayList<CommodityVO> commodityList);

	// 获得商品赠送支出
	public double getGiftCost(String beginDate, String endDate);

	// 检查商品库存报警
	public boolean stockNumCheck(String goodsID);

	// 修改报溢报损单
	public int modify(StockOverOrLowVO vo);

}
