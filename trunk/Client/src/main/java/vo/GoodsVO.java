package vo;

public class GoodsVO {
	String GoodsID;
	String name;
	String size;
	int numInStock;
	double purchasePrice;
	double price;
	double lastPurchasePrice;
	double lastPrice;
	String goodsClass;
	String manufactureDate;
	int minNumInstock;

	public GoodsVO(String goodsID, String name, String size, int numInStock,
			double purchasePrice, double price, double lastPurchasePrice,
			double lastPrice, String goodsClass, String manufactureDate,int minNumInStock) {
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
		this.minNumInstock=minNumInStock;
	}

	public String getGoodsID() {
		return GoodsID;
	}

	public String getName() {
		return name;
	}

	public String getSize() {
		return size;
	}

	public int getNumInStock() {
		return numInStock;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public double getPrice() {
		return price;
	}

	public double getLastPurchasePrice() {
		return lastPurchasePrice;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setNumInStock(int numInStock) {
		this.numInStock = numInStock;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setLastPurchasePrice(double lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public void setGoodsClass(String goodsClass) {
		this.goodsClass = goodsClass;
	}

	public String getGoodsClass() {
		return goodsClass;
	}

	public String getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public int getMinNumInstock() {
		return minNumInstock;
	}

	public void setMinNumInstock(int minNumInstock) {
		this.minNumInstock = minNumInstock;
	}
}
