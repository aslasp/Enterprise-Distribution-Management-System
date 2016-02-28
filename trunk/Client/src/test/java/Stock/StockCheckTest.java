package Stock;

import java.util.ArrayList;

//注：此测试运行后无法还原文件
//商品进货过，无法删除
//另外，导入的进货文件、商品分类文件、message文件无法删除
//需要手动删除

import junit.framework.TestCase;
import vo.CommodityVO;
import vo.GoodsClassVO;
import vo.GoodsVO;
import vo.PurchaseVO;
import businesslogic.salesbl.Purchase;
import businesslogic.salesbl.SalesController;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogicservice.salesblservice.SalesBLService;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

public class StockCheckTest extends TestCase {
	StockGoodsBLService gService;
	StockGoodsClassBLService gcService;
	StockControlBLService cService;
	SalesBLService sService;
	GoodsClassVO gcVO;
	GoodsVO gVO1;
	GoodsVO gVO2;
	Purchase purchase;
	PurchaseVO pvo;
	ArrayList<CommodityVO> pList;

	public void setUp() {
		gcService = new GoodsClassController();
		gService = new GoodsController();
		cService = new StockControlController();
		try {
			sService = new SalesController();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		gcVO = new GoodsClassVO("飞利浦", "灯具");
		gVO1 = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 0, 100.0, 150.0,
				0.0, 0.0, "飞利浦", "", 30);
		gVO2 = new GoodsVO("0001-SR02-0001", "飞利浦日光灯", "SR02", 0, 100.0, 150.0,
				0.0, 0.0, "飞利浦", "", 30);

		pList = new ArrayList<CommodityVO>();
		CommodityVO cVo = new CommodityVO("0001-SR01-0000", "飞利浦日光灯", "SR01",
				100.0, 150.0, 1, 150.0, 100.0, "");
		pList.add(cVo);
		pvo = new PurchaseVO("JHD-20141215-00001", "马建国", "JHS-000001", "1",
				"王宁宁", pList, "", 150.0, 3, 0);
		
		gcService.addGoodsClass(gcVO);
		gService.addGoods(gVO1);
		gService.addGoods(gVO2);
		
		try {
			purchase = new Purchase();
			purchase.AddPurchase(pvo);
			purchase.excute(pvo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	// TUS1
	public void test_1() {
		System.out.println("test_1");
		ArrayList<ArrayList<String>> result = cService.checkStock();
		assertEquals(2, result.size());
		assertEquals("1", result.get(0).get(2));//检测库存数量
		assertEquals("100.0", result.get(0).get(3));//检测库存均价
		assertEquals("2014/12/15", result.get(0).get(6));//检测出厂日期
		System.out.println("库存盘点测试结束！");
	}

	// TUS2--界面测试
	public void test_2() {
		
	}
}
