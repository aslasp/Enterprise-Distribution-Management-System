package Stock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;
import vo.GoodsVO;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService_stub;

public class StockGoodsBLService_DriverTest extends TestCase {
	private StockGoodsBLService stockGoodsBLService;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream bytes = null;

	public void setUp() {
		StockGoodsBLService stockGoodsbl_stub = new StockGoodsBLService_stub();
		stockGoodsBLService = stockGoodsbl_stub;
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	public void tearDown() {
		System.setOut(console);
	}

	public void testStockGoodsBLDrive() {
		GoodsVO vo = new GoodsVO(null, null, null, 0, 0, 0, 0, 0, null, null, 0);
		int resultAdd = stockGoodsBLService.addGoods(vo);
		int resultMod = stockGoodsBLService.modifyGoods(vo);
		int resultDel = stockGoodsBLService.deleteGoods(vo);
		stockGoodsBLService.findGoods("00001");
		
		stockGoodsBLService.showGoods();
		

		assertEquals(0, resultAdd);
		assertEquals(0, resultMod);
		assertEquals(0, resultDel);
		
		assertEquals("add goods succeed!" + line
				+ "modify goods succeed!"+ line 
				+ "delete goods succeed!" + line
				+ "find goods succeed!"+ line 
				+ "show goods succeed!" + line, bytes.toString());
	}
}
