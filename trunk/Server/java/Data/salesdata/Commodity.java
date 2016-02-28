package Data.salesdata;



public class Commodity {
	String goodID,goodName,goodSize;
	int num;
	double price;
	double totalPrice;
	String memo;
	public Commodity(String goodID, String goodName, String goodSize,
			int num, double price, double totalPrice, String memo) {
		this.goodID = goodID;
		this.goodName = goodName;
		this.goodSize = goodSize;
		this.num = num;
		this.price = price;
		this.totalPrice = totalPrice;
		this.memo = memo;
	}
	
}
