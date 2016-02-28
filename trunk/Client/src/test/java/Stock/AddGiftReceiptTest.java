package Stock;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import junit.framework.TestCase;
import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import businesslogic.stockbl.gift.GiftReceipt;

public class AddGiftReceiptTest extends TestCase {
	private GiftReceipt giftReceipt;
	private CommodityVO c1;
	private ArrayList<CommodityVO> giftList;

	public void setUp() throws ParseException {
		try {
			c1 = new CommodityVO("0001-SR01-0000", "飞利浦日光灯", "SR01", 100.0,
					150.0, 1, 150.0, 100.0, "");
			giftList = new ArrayList<CommodityVO>();
			giftList.add(c1);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public void testAddGiftReceipt() throws RemoteException {
		giftReceipt = new GiftReceipt("id", "00001", "小赵", "KC-00001",
				ReceiptType.GIFT, 0, 0, "", giftList);
		assertEquals(giftList, giftReceipt.getGiftVOList());
		giftReceipt.add();
	}
}
