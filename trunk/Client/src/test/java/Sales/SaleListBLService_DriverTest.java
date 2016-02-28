package Sales;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

import org.junit.Test;

import businesslogic.salesbl.SaleList;
import businesslogicservice.salesblservice.SaleListBLService;

public class SaleListBLService_DriverTest extends TestCase{
	private SaleListBLService s;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream beos = null;

	// --------------------------------------------
	public void setUp() throws Exception {
		s = new SaleList();
		beos = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(beos));
	}

	public void tearDown() {
		System.setOut(console);
	}

	@Test
	public void test() {
		String startDate="20141201";
		String endDate="20141209";
		s.getAllSale();
		s.getAllPurchase();
		s.getCouponProfitCalc(startDate, endDate);
		s.getPurchaseReturnProfitCalc(startDate, endDate);
		s.getSaleIncome(startDate, endDate);
		s.getSaleCost(startDate, endDate);
		//注 替换后 工程已不输出
		assertEquals("showSaleList!"+ line
				+"showPurchaseList!"+ line
				+"couponProfitCalc"+ line
				+"purchaseReturnProfitCalc"+line
				+"totalMoneyWeGot"+ line
				+"totalMoneyWePaid"+ line,beos.toString());
		
	}

}