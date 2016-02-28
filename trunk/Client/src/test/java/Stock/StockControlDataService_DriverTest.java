package Stock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import junit.framework.TestCase;
import po.StockErrorPO;
import po.StockOverOrLowPO;
import dataservice.stockdataservice.controldataservice.StockControlDataService;
import dataservice.stockdataservice.controldataservice.StockControlDataService_stub;

public class StockControlDataService_DriverTest extends TestCase {
	private StockControlDataService stockControlDataService;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream bytes = null;

	public void setUp() {
		StockControlDataService stockControlDataService_stub = new StockControlDataService_stub();
		stockControlDataService = stockControlDataService_stub;
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	public void tearDown() {
		System.setOut(console);
	}

	public void testStockGoodsDataDrive() throws RemoteException {
		StockErrorPO stockErrorPO = new StockErrorPO(null, null, null, null);
		StockOverOrLowPO stockOverOrLowPO = new po.StockOverOrLowPO(null, null,
				null, null, null, 0, 0, null, null, null, 0, 0);
		int resultAddError = stockControlDataService
				.addStockError(stockErrorPO);
		int resultAddOverOrLow = stockControlDataService
				.addStockOverOrLow(stockOverOrLowPO);

		assertEquals(0, resultAddError);
		assertEquals(0, resultAddOverOrLow);
		assertEquals("add stockError receipt in file succeed!" + line
				+ "add stockOverOrLow receipt in file succeed!" + line,
				bytes.toString());
	}
}
