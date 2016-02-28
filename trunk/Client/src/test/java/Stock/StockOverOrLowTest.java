package Stock;

import java.rmi.RemoteException;

//注：此测试运行后无法完全还原文件
//库存报溢报损单文件、message文件无法删除
//需要手动删除

import junit.framework.TestCase;
import po.ReceiptPO.ReceiptType;
import vo.GoodsClassVO;
import vo.GoodsVO;
import vo.StockOverOrLowVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogic.stockbl.stockManage.StockLowReceipt;
import businesslogic.stockbl.stockManage.StockOverReceipt;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

public class StockOverOrLowTest extends TestCase {
	StockGoodsClassBLService gcService;
	StockGoodsBLService gService;
	StockControlBLService cService;
	GoodsClassVO gcVO;
	GoodsVO gVO;
	StockOverOrLowVO oVO;
	StockOverOrLowVO lVO;
	StockOverReceipt oReceipt;
	StockLowReceipt lReceipt;

	public void setUp() {
		gcService = new GoodsClassController();
		gService = new GoodsController();
		cService = new StockControlController();
		gcVO = new GoodsClassVO("飞利浦", "灯具");
		gVO = new GoodsVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 100, 100.0,
				150.0, 0.0, 0.0, "飞利浦", "", 30);
		oVO = new StockOverOrLowVO("KCBYD-20141215-00001", "宽宽",
				ReceiptType.STOCKOVER, 0, 0, "", "飞利浦日光灯", "SR01", 100, 101);
		lVO = new StockOverOrLowVO("KCBSD-20141215-00002", "宽宽",
				ReceiptType.STOCKLOW, 0, 0, "", "飞利浦日光灯", "SR01", 101, 99);
		try {
			oReceipt = new StockOverReceipt("KCBYD-20141215-00001", "", "",
					"宽宽", 0, "", "飞利浦日光灯", "SR01", 100, 101);
			lReceipt = new StockLowReceipt("KCBSD-20141215-00002", "", "",
					"宽宽", 0, "", "飞利浦日光灯", "SR01", 100, 101);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	// TUS1
	public void test_1() {
		System.out.println("test_1");
		int result = -1;

		gcService.addGoodsClass(gcVO);
		gService.addGoods(gVO);
		// TUS1-1
		// --------------------测试报溢----------------------------------
		result = cService.addStockOverOrLow(oVO);
		assertEquals(0, result);// 测试添加库存报溢单
		oReceipt.excute(oVO);
		int num = -1;
		try {
			num = gService.findByID("0001-SR01-0000").getNumInStock();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(101, num);// 测试报溢单审批后库存数量
		StockOverOrLowVO overVO = cService.showStockOverReceipt().get(0);
		assertEquals(3, overVO.getStatus());// 测试执行后单据状态

		// --------------------测试报损-----------------------------------
		result = cService.addStockOverOrLow(lVO);
		assertEquals(0, result);// 测试添加库存报损单
		lReceipt.excute(lVO);
		try {
			num = gService.findByID("0001-SR01-0000").getNumInStock();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(99, num);// 测试报损单审批后库存数量
		StockOverOrLowVO lowVO = cService.showStockLowReceipt().get(0);
		assertEquals(3, lowVO.getStatus());// 测试执行后单据状态

		System.out.println("库存报溢报损测试结束！");

		// 部分文件数据还原（商品分类及商品）--此处不涉及进销售，所以取巧可以删除商品
		gService.deleteGoods(gVO);
		gcService.deleteGoodsClass(gcVO);

	}
}
