package businesslogic.stockbl.goods;

import businesslogic.stockbl.goodsClass.MockGoodsClass;

public class MockGoods extends Goods{
	String GoodsID;
	String name;
	String type;
	int numInStock;
	double purchasePrice;// 售价
	double price;// 进价
	double lastPurchasePrice;
	double lastPrice;

	public MockGoods(String goodsID, String name, String type, int numInStock,
			double purchasePrice, double price) throws Exception{
		GoodsID = goodsID;
		this.name = name;
		this.type = type;
		this.numInStock = numInStock;
		this.purchasePrice = purchasePrice;
		this.price = price;
		this.lastPurchasePrice = 0;
		this.lastPrice = 0;
	}

	public MockGoods() throws Exception{

	}

	public MockGoods getGoods(String goodsID) throws Exception{
		MockGoods good = new MockGoods(goodsID, name, type, numInStock,
				purchasePrice, price);
		return good;
	}

	public void SaleGoods(int num) {
		numInStock -= num;
	}

	// 商品价格
	public double getPrice() {
		return price;
	}

	public double getLastPurchasePrice() {
		return lastPurchasePrice;
	}

	public void setLastPurchasePrice(double lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}

	public String getGoodsID() {
		return GoodsID;
	}

	public void setGoodsID(String goodsID) {
		GoodsID = goodsID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumInStock() {
		return numInStock;
	}

	public void setNumInStock(int numInStock) {
		this.numInStock = numInStock;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public MockGoods findByClass(MockGoodsClass goodsClass) {
		// MockGoods good=new MockGoods("00020001","飞利浦日光灯","SR01",10,200,100);
		if (this.GoodsID.substring(0, 4).equals(goodsClass.getClassID())) {
			return this;
		} else {
			return null;
		}
	}

	public int modifyGoods(MockGoods newGood) {
		this.numInStock = newGood.numInStock;

		return 0;
	}

	public int getNum() {
		// TODO 自动生成的方法存根
		return numInStock;
	}
}
