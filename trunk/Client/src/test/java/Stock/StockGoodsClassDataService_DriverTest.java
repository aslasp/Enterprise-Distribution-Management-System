package Stock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import junit.framework.TestCase;
import po.GoodsClassPO;
import dataservice.stockdataservice.goodsclassdataservice.StockGoodsClassDataService;
import dataservice.stockdataservice.goodsclassdataservice.StockGoodsClassDataService_stub;

public class StockGoodsClassDataService_DriverTest extends TestCase {
	private StockGoodsClassDataService stockGoodsClassDataService;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream bytes = null;

	public void setUp() {
		StockGoodsClassDataService stockGoodsClassDataService_stub = new StockGoodsClassDataService_stub();
		stockGoodsClassDataService = stockGoodsClassDataService_stub;
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	public void tearDown() {
		System.setOut(console);
	}

	public void testStockGoodsClassDataDrive() throws RemoteException {
		GoodsClassPO po = new GoodsClassPO(null, null, null);
		int resultAdd = stockGoodsClassDataService.addGoodsClass(po);
		int resultMod = stockGoodsClassDataService.modifyGoodsClass(po);
		int resultDel = stockGoodsClassDataService.deleteGoodsClass(po);

		assertEquals(0, resultAdd);
		assertEquals(0, resultMod);
		assertEquals(0, resultDel);
		assertEquals("add goodsClass in file succeed!" + line
				+ "modify goodsClass in file succeed!" + line
				+ "delete goodsClass in file succeed!" + line, bytes.toString());
	}
}
