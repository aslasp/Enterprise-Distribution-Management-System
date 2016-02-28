package po;

import java.io.Serializable;

public class GoodsPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String GoodsID;
	String name;
	String size;
	int numInStock;
	int virtualnumInStock;
	double purchasePrice;
	double price;
	double lastPurchasePrice;
	double lastPrice;
	String goodsClass;

	public GoodsPO(String goodsID, String name, String size, int numInStock,
			int virtualnumInStock, double purchasePrice, double price,
			double lastPurchasePrice, double lastPrice, String goodsClass) {
		GoodsID = goodsID;
		this.name = name;
		this.size = size;
		this.numInStock = numInStock;
		this.virtualnumInStock = virtualnumInStock;
		this.purchasePrice = purchasePrice;
		this.price = price;
		this.lastPurchasePrice = lastPurchasePrice;
		this.lastPrice = lastPrice;
		this.goodsClass = goodsClass;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getNumInStock() {
		return numInStock;
	}

	public void setNumInStock(int numInStock) {
		this.numInStock = numInStock;
	}

	public int getVirtualNumInStock() {
		return virtualnumInStock;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getLastPurchasePrice() {
		return lastPurchasePrice;
	}

	public void setLastPurchasePrice(int lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(int lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(String goodsClass) {
		this.goodsClass = goodsClass;
	}
}
