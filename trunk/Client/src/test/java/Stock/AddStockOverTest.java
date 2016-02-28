package Stock;

import java.rmi.RemoteException;
import java.text.ParseException;

import junit.framework.TestCase;
import vo.GoodsVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.stockManage.StockOverOrLowManage;

public class AddStockOverTest extends TestCase {
	String goodsID;
	private String goodsName;
	String size;
	int num;
	int exactNum;
	int gap;
	GoodsVO goodVO;
	GoodsController gController;

	public void setUp() throws ParseException {
		goodsID = "0004-SR01-0000";
		goodsName = "飞利浦日光灯";
		size = "SR01";
		num = 90;
		exactNum = 100;
		gap = num - exactNum;
		gController = new GoodsController();
		try {
			goodVO = new GoodsVO("0004-SR01-0000", "飞利浦日光灯", "SR01", 0, 150.0,
					200.0, 0.0, 0.0, "飞利浦", "", 30);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void testAddStockOver() throws RemoteException {
		StockOverOrLowManage stockOverOrLowReceipt = new StockOverOrLowManage(
				goodsName, size, num, exactNum);
		double total = 0;
		try {
			total = gController.findByID(goodsID).getPrice() * (num - exactNum);
			assertEquals(total, stockOverOrLowReceipt.getGap()
					* gController.findByID(goodsID).getPrice());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
