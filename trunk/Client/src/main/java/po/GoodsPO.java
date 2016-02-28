package po;

import java.io.Serializable;

public class GoodsPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String GoodsID;
	private String name;
	private String size;
	private int numInStock;
	private double purchasePrice;
	private double price;
	private double lastPurchasePrice;
	private double lastPrice;
	private String goodsClass;
	private String manufactureDate;
	private int minNumInStock;

	public GoodsPO() {

	}

	public GoodsPO(String goodsID, String name, String size, int numInStock,
			double purchasePrice, double price, double lastPurchasePrice,
			double lastPrice, String goodsClass, String manufactureDate,
			int minNumInStock) {
		GoodsID = goodsID;
		this.name = name;
		this.size = size;
		this.numInStock = numInStock;
		this.purchasePrice = purchasePrice;
		this.price = price;
		this.lastPurchasePrice = lastPurchasePrice;
		this.lastPrice = lastPrice;
		this.goodsClass = goodsClass;
		this.manufactureDate = manufactureDate;
		this.minNumInStock = minNumInStock;
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

	public String getGoodsClassName() {
		return goodsClass;
	}

	public void setGoodsClassName(String goodsClass) {
		this.goodsClass = goodsClass;
	}

	public String getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public int getMinNumInStock() {
		return minNumInStock;
	}

	public void setMinNumInStock(int minNumInStock) {
		this.minNumInStock = minNumInStock;
	}
}
