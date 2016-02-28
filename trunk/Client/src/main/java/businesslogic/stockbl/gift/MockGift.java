package businesslogic.stockbl.gift;

import businesslogic.memberbl.Member;
import businesslogic.stockbl.goods.Goods;

public class MockGift extends GiftController {
	Member member;
	Goods goods;

	public MockGift(Member member, Goods goods) throws Exception {
		this.member = member;
		this.goods = goods;
	}

	public MockGift() throws Exception {

	}

	public double getGiftCost(double total) {
		return total;
	}
}
