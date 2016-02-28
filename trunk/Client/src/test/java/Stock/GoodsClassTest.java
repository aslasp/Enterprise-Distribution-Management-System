package Stock;

import junit.framework.TestCase;
import vo.GoodsClassVO;
import vo.GoodsVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

public class GoodsClassTest extends TestCase {
	GoodsClassVO gcVO1;
	GoodsClassVO gcVO2;
	GoodsVO gVO;
	StockGoodsClassBLService gcService;
	StockGoodsBLService gService;

	public void setUp() {
		gcVO1 = new GoodsClassVO("飞利浦", "灯具");
		gcVO2 = new GoodsClassVO("日光灯", "飞利浦");
		gVO = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 0, 100.0, 150.0,
				0.0, 0.0, "飞利浦", "", 30);
		gcService = new GoodsClassController();
		gService = new GoodsController();
	}

	// TUS1
	public void test_1() {
		System.out.println("test_1");
		int result = -1;
		// TUS1-1
		result = gcService.addGoodsClass(gcVO1);
		assertEquals(0, result);
		System.out.println("添加商品分类测试结束！");

		// TUS1-2
		GoodsClassVO newVO = new GoodsClassVO("飞利浦12", "灯具");
		result = gcService.modifyGoodsClass(gcVO1, newVO);
		assertEquals(0, result);
		System.out.println("修改商品分类测试结束！");

		// TUS1-3
		result = gcService.deleteGoodsClass(newVO);
		assertEquals(0, result);
		System.out.println("删除商品分类测试结束！");

	}

	// TUS2
	public void test_2() {
		System.out.println("test_2");
		int result = -1;
		// TUS2-1
		gcService.addGoodsClass(gcVO1);
		gService.addGoods(gVO);

		result = gcService.addGoodsClass(gcVO2);
		assertEquals(1, result);
		System.out.println("添加商品分类测试结束！");

		// 文件数据还原
		System.out.println(gService.deleteGoods(gVO));
		System.out.println(gcService.deleteGoodsClass(gcVO2));
		System.out.println(gcService.deleteGoodsClass(gcVO1));

	}

	// TUS3
	public void test_3() {
		System.out.println("test_3");
		int result = -1;

		// TUS1-1
		result = gcService.addGoodsClass(gcVO1);
		assertEquals(0, result);
		System.out.println("添加商品分类测试结束！");

		gService.addGoods(gVO);

		// TUS1-2
		GoodsClassVO newVO = new GoodsClassVO("飞利浦12", "灯具");
		result = gcService.modifyGoodsClass(gcVO1, newVO);
		assertEquals(0, result);
		System.out.println("修改商品分类测试结束！");

		// TUS1-3
		result = gcService.deleteGoodsClass(newVO);
		assertEquals(3, result);
		System.out.println("删除商品分类测试结束！");
	}

}
