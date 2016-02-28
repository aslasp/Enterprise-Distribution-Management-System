package Stock;

//注：此测试运行后无法还原文件
//库存赠送文件、商品文件、商品分类文件无法删除
//需要手动删除

import java.rmi.RemoteException;
import java.util.ArrayList;

import junit.framework.TestCase;
import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import vo.GiftVO;
import vo.GoodsClassVO;
import vo.GoodsVO;
import businesslogic.stockbl.gift.GiftController;
import businesslogic.stockbl.gift.GiftReceipt;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogicservice.stockblservice.giftblservice.GiftBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

public class GiftTest extends TestCase {
	GoodsClassVO gcVO;
	GoodsVO gVO;
	GiftVO giftVO1;
	GiftReceipt giftReceipt1;
	GiftVO giftVO2;
	GiftReceipt giftReceipt2;

	StockGoodsClassBLService gcService;
	StockGoodsBLService gService;
	GiftBLService giftService;

	public void setUp() {
		gcService = new GoodsClassController();
		gService = new GoodsController();
		try {
			giftService = new GiftController();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		gcVO = new GoodsClassVO("飞利浦", "灯具");
		gVO = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 100, 100.0,
				150.0, 0.0, 0.0, "飞利浦", "", 30);
		CommodityVO cVo1 = new CommodityVO("0001-SR01-0000", "飞利浦日光灯", "SR01",
				100.0, 150.0, 1, 150.0, 100.0, "");
		ArrayList<CommodityVO> giftList1 = new ArrayList<CommodityVO>();
		giftList1.add(cVo1);
		giftVO1 = new GiftVO("KCZSD-20141215", "金大大", "XSS-0000002", "宽宽", 0,
				0, "", giftList1);
		giftReceipt1 = new GiftReceipt("KCZSD-20141215", "XSS-0000002", "金大大",
				"宽宽", ReceiptType.GIFT, 0, 0, "", giftList1);

		CommodityVO cVo2 = new CommodityVO("0001-SR01-0000", "飞利浦日光灯", "SR01",
				100.0, 150.0, 99, 150.0, 100.0, "");
		ArrayList<CommodityVO> giftList2 = new ArrayList<CommodityVO>();
		giftList2.add(cVo2);
		giftVO2 = new GiftVO("KCZSD-20141215", "金大大", "XSS-0000002", "宽宽", 0,
				0, "", giftList2);
		giftReceipt2 = new GiftReceipt("KCZSD-20141215", "XSS-0000002", "金大大",
				"宽宽", ReceiptType.GIFT, 0, 0, "", giftList2);

		gcService.addGoodsClass(gcVO);
		gService.addGoods(gVO);
	}

	// TUS1
	public void test_1() {
		System.out.println("test_1");
		int result = -1;

		// TUS1-1
		result = giftService.addGift(giftVO1);
		assertEquals(0, result);
		System.out.println("添加库存赠送单测试结束！");

		// TUS1-2
		result = giftReceipt1.excute(giftVO1);
		assertEquals(0, result);
		try {
			int num = gService.findByID("0001-SR01-0000").getNumInStock();
			assertEquals(99, num);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("执行库存赠送单测试结束！");
	}

	// TUS2
	public void test_2() {
		System.out.println("test_2");
		int result = -1;

		// TUS2-1
		result = giftService.addGift(giftVO2);
		assertEquals(0, result);
		System.out.println("添加库存赠送单测试结束！");

		// TUS2-2
		result = giftReceipt2.excute(giftVO2);
		assertEquals(0, result);
		try {
			int num = gService.findByID("0001-SR01-0000").getNumInStock();
			assertEquals(0, num);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 库存报警界面提示
		System.out.println("执行库存赠送单测试结束！");

	}

	// TUS3
	public void test_3() {
		System.out.println("test_3");
		int result = -1;

		// TUS3-1
		// 审批不通过交由总经理统一测试
		System.out.println("添加库存赠送单测试结束！");

		// TUS3-2
		result = giftReceipt2.excute(giftVO2);
		assertEquals(7, result);
		System.out.println("执行库存赠送单测试结束！");
	}
}
