package Stock;

import java.util.ArrayList;

import junit.framework.TestCase;
import vo.GoodsClassVO;
import vo.GoodsVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

public class showStockTest extends TestCase {
	GoodsClassVO gcVO;
	GoodsVO gVO1;
	GoodsVO gVO2;
	StockGoodsClassBLService gcService;
	StockGoodsBLService gService;
	StockControlBLService controller;

	public void setUp() {
		gcService = new GoodsClassController();
		gService = new GoodsController();
		controller = new StockControlController();
		gcVO = new GoodsClassVO("飞利浦", "灯具");
		gVO1 = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 0, 100.0, 150.0,
				0.0, 0.0, "飞利浦", "", 30);
		gVO2 = new GoodsVO("0001-SR02-0001", "飞利浦日光灯", "SR02", 0, 100.0, 150.0,
				0.0, 0.0, "飞利浦", "", 30);
	}

	// TUS1
	public void test_1() {
		System.out.println("test_1");
		int result = -1;

		// TUS1-1
		gcService.addGoodsClass(gcVO);
		gService.addGoods(gVO1);
		gService.addGoods(gVO2);
		String beginDate = "20141215";
		String endDate = "20141215";
		ArrayList<ArrayList<String>> list = controller.showStock(beginDate,
				endDate);

		// 至于查看出入库数量啥的，此处忽略了==
		result = list.size();
		assertEquals(2, result);
		System.out.println("库存查看测试结束！");

		// 还原文件
		gService.deleteGoods(gVO2);
		gService.deleteGoods(gVO1);
		gcService.deleteGoodsClass(gcVO);
	}

	// TUS2--界面测试
	public void test_2() {

	}
}
