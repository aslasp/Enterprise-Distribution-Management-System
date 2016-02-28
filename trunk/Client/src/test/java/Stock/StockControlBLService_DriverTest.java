package Stock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import po.ReceiptPO.ReceiptType;
import junit.framework.TestCase;
import vo.StockErrorVO;
import vo.StockOverOrLowVO;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService_stub;

public class StockControlBLService_DriverTest extends TestCase {
	private StockControlBLService stockControlBLService;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream bytes = null;

	public void setUp() {
		StockControlBLService stockControlbl_stub = new StockControlBLService_stub();
		stockControlBLService = stockControlbl_stub;
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	public void tearDown() {
		System.setOut(console);
	}

	public void testStockGoodsBLDrive() {
		StockErrorVO stockErrorVO = new StockErrorVO(null, null, null, null,
				null);
		StockOverOrLowVO stockOverOrLowVO = new StockOverOrLowVO(null, null,
				ReceiptType.STOCKOVER, 0, 0, null, null, null, 0, 0);
		int resultAddError = stockControlBLService.addStockError(stockErrorVO);
		int resultAddOverOrLow = stockControlBLService
				.addStockOverOrLow(stockOverOrLowVO);
		stockControlBLService.getGoodsOverIncome(null, null);
		stockControlBLService.getGoodsLowCost(null, null);
		stockControlBLService.checkStock();
		stockControlBLService.showStock("2014年10月1日", "2014年10月17日");
		boolean isEnough = stockControlBLService.isEnough("00001", 10);
		stockControlBLService.getPrimeCostIncome(null);

		assertEquals(0, resultAddError);
		assertEquals(0, resultAddOverOrLow);
		assertEquals(true, isEnough);

		assertEquals("add stockError receipt succeed!" + line
				+ "add stockOverOrLow receipt succeed!" + line
				+ "return goods over income succeed!" + line
				+ "return goods low cost succeed!" + line
				+ "check stock succeed!" + line + "show stock succeed!" + line
				+ "check stock is enough succeed!" + line
				+ "return prime cost income succeed!" + line, bytes.toString());

	}
}
