package Stock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;
import vo.GiftVO;
import businesslogicservice.stockblservice.giftblservice.GiftBLService;
import businesslogicservice.stockblservice.giftblservice.GiftBLService_stub;

public class GiftBLService_DriverTest extends TestCase {
	private GiftBLService giftBLService;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream bytes = null;

	public void setUp() {
		GiftBLService giftbl_stub = new GiftBLService_stub();
		giftBLService = giftbl_stub;
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	public void tearDown() {
		System.setOut(console);
	}

	public void testStockGoodsBLDrive() {
		GiftVO vo = new GiftVO(null, null, null, null, 0, 0, null, null);
		int resultAdd = giftBLService.addGift(vo);
		int resultDeal = giftBLService.dealGift(vo);
		giftBLService.getGiftCost(null, null);

		assertEquals(0, resultAdd);
		assertEquals(0, resultDeal);

		assertEquals("add gift succeed!" + line + "deal gift succeed!" + line
				+"get gift cost succeed!"+ line, bytes.toString());

	}
}
