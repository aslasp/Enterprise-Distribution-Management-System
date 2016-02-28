package businesslogic.stockbl.stockManage;

import java.util.ArrayList;

import businesslogic.stockbl.gift.GiftReceipt;
import businesslogic.stockbl.goods.MockGoods;

public class MockStockControl {
	String num;
	ArrayList<StockOverOrLowManage> stockOverOrLowReceiptlist;
	ArrayList<GiftReceipt> giftReceiptlist;
	double goodsOverIncome;
	double primeCostIncome;
	double goodsLowCost;
	double giftCost;

	public MockStockControl() {
		stockOverOrLowReceiptlist = new ArrayList<StockOverOrLowManage>();
		giftReceiptlist = new ArrayList<GiftReceipt>();
		this.goodsOverIncome = 0;
		this.primeCostIncome = 0;
		this.goodsLowCost = 0;
		this.giftCost = 0;
	}

	public int addStockOver(StockOverOrLowManage receipt) throws Exception {
		MockGoods good = new MockGoods("01010001", "飞利浦日光灯", "SR01", 10, 100,
				200);
		goodsOverIncome += receipt.getGap() * good.getPrice();
		return 0;
	}

	public int addStockLow(StockOverOrLowManage receipt) throws Exception {
		MockGoods good = new MockGoods("01010001", "飞利浦日光灯", "SR01", 10, 100,
				200);
		goodsLowCost -= receipt.getGap() * good.getPrice();
		return 0;
	}

	public int changePrime(MockGoods good, MockGoods newGood) {
		primeCostIncome += (good.getPurchasePrice() - newGood
				.getPurchasePrice()) * newGood.getNumInStock();
		return 0;
	}

	public int addGift(GiftReceipt receipt) {
		giftCost += receipt.getTotal();
		return 0;
	}

	// 库存充足检查
	public boolean isEnough(String goodsID, int num) {
		boolean isEnough = false;
		isEnough = true;
		return isEnough;
	}

	// 商品调价收入
	public double getPrimeCostIncome() {
		return primeCostIncome;
	}

	// 商品报溢收入
	public double getGoodsOverIncome() {
		return goodsOverIncome;
	}

	// 商品报损支出
	public double getGoodsLowCost() {
		return goodsLowCost;
	}

	// 商品赠送支出
	public double getGiftCost() {
		return giftCost;
	}
}
