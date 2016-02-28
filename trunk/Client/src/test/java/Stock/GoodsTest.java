package Stock;

import java.rmi.RemoteException;
import java.util.ArrayList;

import junit.framework.TestCase;
import vo.CommodityVO;
import vo.GiftVO;
import vo.GoodsClassVO;
import vo.GoodsVO;
import businesslogic.stockbl.gift.GiftController;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogicservice.stockblservice.giftblservice.GiftBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

//注：此测试运行后无法还原文件
//因为删除是失败的，无法删除商品及其分类
//另外，导入的库存赠送文件、商品调价收入文件无法删除
//需要手动删除

public class GoodsTest extends TestCase {
	GoodsClassVO gcVO;
	GoodsVO gVO;
	StockGoodsClassBLService gcService;
	StockGoodsBLService gService;

	public void setUp() {
		gcService = new GoodsClassController();
		gService = new GoodsController();
		gcVO = new GoodsClassVO("飞利浦", "灯具");
		gVO = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 0, 100.0, 150.0,
				0.0, 0.0, "飞利浦", "", 30);

		gcService.addGoodsClass(gcVO);
	}

	// TUS1
	public void test_1() {
		System.out.println("test_1");
		int result = -1;

		// TUS1-1
		result = gService.addGoods(gVO);
		assertEquals(0, result);
		System.out.println("添加商品测试结束！");

		// TUS1-2
		// 商品修改只可以改默认进售价
		GoodsVO newVO = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 0,
				150.0, 200.0, 0.0, 0.0, "飞利浦", "", 30);
		result = gService.modifyGoods(newVO);
		assertEquals(0, result);
		System.out.println("修改商品测试结束！");

		// TUS1-4
		GoodsVO vo = null;
		try {
			vo = gService.findByID("0001-SR01-0000");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 注：这儿如果是直接判断两个对象是不是相等的话，不行诶，因为断言相当于用的==来判断的？？？
		assertEquals(newVO.getGoodsID(), vo.getGoodsID());
		assertEquals(newVO.getName(), vo.getName());
		assertEquals(newVO.getSize(), vo.getSize());
		assertEquals(newVO.getGoodsClass(), vo.getGoodsClass());
		System.out.println("查找商品测试结束！");

		// TUS1-3
		result = gService.deleteGoods(newVO);
		assertEquals(0, result);
		System.out.println("删除商品测试结束！");
	}

	// TUS2
	public void test_2() {
		System.out.println("test_2");
		int result = -1;

		// TUS1-1
		gService.addGoods(gVO);
		result = gService.addGoods(gVO);
		assertEquals(4, result);
		System.out.println("添加商品测试结束！");

		// 商品修改只可以改默认进售价,此处不需测试修改后商品记录存在的情况

		// TUS1-3
		GoodsVO newVO = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 0,
				100.0, 150.0, 0.0, 0.0, "飞利浦", "", 30);

		GoodsVO vo = null;
		try {
			vo = gService.findByID("0001-SR01-0000");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 注：这儿如果是直接判断两个对象是不是相等的话，不行诶，因为断言相当于用的==来判断的？？？
		assertEquals(newVO.getGoodsID(), vo.getGoodsID());
		assertEquals(newVO.getName(), vo.getName());
		assertEquals(newVO.getSize(), vo.getSize());
		assertEquals(newVO.getGoodsClass(), vo.getGoodsClass());
		System.out.println("查找商品测试结束！");

		// TUS1-2
		result = gService.deleteGoods(newVO);
		assertEquals(0, result);
		System.out.println("删除商品测试结束！");

	}

	// TUS3
	public void test_3() {
		System.out.println("test_3");
		int result = -1;

		// TUS1-1
		result = gService.addGoods(gVO);
		assertEquals(0, result);
		System.out.println("添加商品测试结束！");

		// TUS1-2
		// 商品修改只可以改默认进售价
		GoodsVO newVO = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 0,
				150.0, 200.0, 0.0, 0.0, "飞利浦", "", 30);
		result = gService.modifyGoods(newVO);
		assertEquals(0, result);
		System.out.println("修改商品测试结束！");

		// TUS1-4
		GoodsVO vo = null;
		try {
			vo = gService.findByID("0001-SR01-0000");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 注：这儿如果是直接判断两个对象是不是相等的话，不行诶，因为断言相当于用的==来判断的？？？
		assertEquals(newVO.getGoodsID(), vo.getGoodsID());
		assertEquals(newVO.getName(), vo.getName());
		assertEquals(newVO.getSize(), vo.getSize());
		assertEquals(newVO.getGoodsClass(), vo.getGoodsClass());
		System.out.println("查找商品测试结束！");

		// TUS1-3
		try {
			GiftBLService giftController = new GiftController();
			ArrayList<CommodityVO> giftList = new ArrayList<CommodityVO>();
			CommodityVO cVo = new CommodityVO("0001-SR01-0000", "飞利浦日光灯",
					"SR01", 100.0, 150.0, 1, 150.0, 100.0, "");
			giftList.add(cVo);
			GiftVO giftVO = new GiftVO("KCZSD-20141213-00001", "金大大",
					"XSS-0000002", "宽宽", 3, 0, "", giftList);
			giftController.addGift(giftVO);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		result = gService.deleteGoods(newVO);
		boolean b = true;
		if (result == 91 || result == 92 || result == 93) {
			b = false;
		}
		assertEquals(false, b);
		System.out.println("删除商品测试结束！");
	}
}
