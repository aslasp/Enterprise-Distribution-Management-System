package Finance;

import java.rmi.RemoteException;
import java.text.ParseException;

import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import junit.framework.TestCase;
import businesslogic.financebl.BSL;
import businesslogic.memberbl.MockMember;
import businesslogic.promotionbl.MockCoupon;
import businesslogic.promotionbl.giftCouponPro;
import businesslogic.salesbl.MockCommodityList;
import businesslogic.salesbl.MockPurchaseReturnList;
import businesslogic.salesbl.MockSale;
import businesslogic.salesbl.MockSaleItem;
import businesslogic.salesbl.MockSaleList;
import businesslogic.stockbl.gift.GiftReceipt;
import businesslogic.stockbl.goods.MockGoods;
import businesslogic.stockbl.stockManage.MockStockControl;
import businesslogic.stockbl.stockManage.StockOverOrLowManage;

public class BSLTest extends TestCase {
	private BSL bsList;
	private MockSale sale1,sale2;
	private MockSaleItem item1,item2;
	private MockGoods good, good1, good2, good3;
	private MockCoupon coupon;
	private StockOverOrLowManage stockOver;
	private MockStockControl stockControl;
	private MockCommodityList list;
	private StockOverOrLowManage stockLow;
	private MockMember member;
	private GiftReceipt gift;
	private MockSaleList saleList1,saleList2;
	private MockPurchaseReturnList purchaseReturnList;
	public void setUp() throws ParseException {
		try {
		good = new MockGoods("01010001", "飞利浦日光灯", "SR01", 10, 100, 200);
		good1 = new MockGoods("01010001", "飞利浦日光灯", "SR02", 10, 100, 200);
		good2 = new MockGoods("01010001", "飞利浦日光灯", "SR02", 10, 85, 200);
		sale1 = new MockSale("00001");
		sale2 = new MockSale("00002");
		stockOver = new StockOverOrLowManage("飞利浦日光灯", "SR01", 100, 90);
		stockControl = new MockStockControl();
		list = new 	MockCommodityList();
		coupon = new MockCoupon("20141015-0001", 1000);
		member = new MockMember("01100", MemberType.XSS, MemberLevel.ONE,
				"Jim", 10000);
		
		gift = new GiftReceipt();
		
		good3 = new MockGoods("01010001", "飞利浦日光灯", "SR03", 10, 100, 200);
		saleList1=new MockSaleList();
		saleList2=new MockSaleList();
		purchaseReturnList=new MockPurchaseReturnList(850.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void testBSL() throws RemoteException {
		// 销售收入，两个销售单
		item1 = new MockSaleItem(good, 1);
		sale1.AddGoods(item1);
		saleList1.AddSale(sale1);
		item2 = new MockSaleItem(good, 1);
		sale2.AddGoods(item2);
		saleList1.AddSale(sale2);
		double salesIncome = saleList1.getSaleInCome();
		assertEquals(400.0, salesIncome);
		
		// 库存报溢收入
		try {
			stockControl.addStockOver(stockOver);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double goodsOverIncome = stockControl.getGoodsOverIncome();
		assertEquals(2000.0, goodsOverIncome);
		
		// 库存成本调价收入
		stockControl.changePrime(good1, good2);
		double primeCostIncome = stockControl.getPrimeCostIncome();
		assertEquals(150.0, primeCostIncome);
		
		// 进货退货收入
		list.add("01010001", "飞利浦日光灯", "SR02", 10, 85, null);	
		double importReturnIncome = purchaseReturnList.getTotalValue();
		assertEquals(850.0, importReturnIncome);
		
		// 代金券与实际收款差额收入
		giftCouponPro g=null;
		try {
			g = new giftCouponPro();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		g.useCoupon(coupon.getID(),true);
		saleList2.AddSale(sale1);
		double couponIncome = saleList2.getCouponIncome();
		assertEquals(600.0, couponIncome);
		
		// 销售成本支出
		double salesPrimeCost = saleList1.getSalesPrimeCost();
		assertEquals(200.0, salesPrimeCost);
		
		// 库存报损支出
		stockLow = new StockOverOrLowManage("飞利浦日光灯", "SR01", 95, 100);
		try {
			stockControl.addStockLow(stockLow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double goodsLowCost = stockControl.getGoodsLowCost();
		assertEquals(1000.0, goodsLowCost);
		
		// 库存赠送支出
		gift.add();
		stockControl.addGift(gift);
		double goodsGiftCost = stockControl.getGiftCost();
		assertEquals(200.0, goodsGiftCost);
		bsList=new BSL(salesIncome,goodsOverIncome,primeCostIncome,importReturnIncome
				,couponIncome,salesPrimeCost,goodsLowCost,goodsGiftCost);
		
		// 总收入 总支出   盈利
		assertEquals(4000.0, bsList.getTotalIncome());
		assertEquals(1400.0, bsList.getTotalExpense());
		assertEquals(2600.0, bsList.getProfit());
	}
}
