package Stock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;
import vo.GoodsClassVO;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService_stub;

public class StockGoodsClassBLService_DriverTest extends TestCase {
	private StockGoodsClassBLService stockGoodsClassBLService;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream bytes = null;

	public void setUp() {
		StockGoodsClassBLService stockGoodsClassbl_stub = new StockGoodsClassBLService_stub();
		stockGoodsClassBLService = stockGoodsClassbl_stub;
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	public void tearDown() {
		System.setOut(console);
	}

	public void testStockGoodsClassBLDrive() {
		GoodsClassVO vo = new GoodsClassVO(null, null);
		int resultAdd = stockGoodsClassBLService.addGoodsClass(vo);
		int resultMod = stockGoodsClassBLService.modifyGoodsClass(vo, vo);
		int resultDel = stockGoodsClassBLService.deleteGoodsClass(vo);
		if (resultAdd == 1)
			System.out.println("该商品分类无法添加！");
		if (resultMod == 2)
			System.out.println("该商品分类无法修改！");
		if (resultDel == 3)
			System.out.println("该商品分类无法删除！");

		assertEquals(0, resultAdd);
		assertEquals(0, resultMod);
		assertEquals(0, resultDel);
		assertEquals("add goodsClass succeed!" + line
				+ "modify goodsClass succeed!" + line
				+ "delete goodsClass succeed!" + line, bytes.toString());
	}
}
