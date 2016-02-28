package vo;

public class CommodityVO {
	private String id,name,type;
	private double price,last_bid;//售价,最后一次进价,销售成本不显示
	private int num;//数量
	private double total,cost;
	private String tip;
	public CommodityVO(String id, String name, String type, double price,
			double last_bid, int num, double total, double cost, String tip) {
	
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.last_bid = last_bid;
		this.num = num;
		this.total = total;
		this.cost = cost;
		this.tip = tip;
	}
	public double getPrice() {
		return price;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	public int getNum() {
		return num;
	}
	public double getTotal() {
		return total;
	}
	public String getTip() {
		return tip;
	}
	
	public String getID(){
		return id;
	}
	/*public void setPrice(double price) {
		this.price = price;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}*/
	
	public double getLast_bid() {
		return last_bid;
	}
	public double getCost() {
		return cost;
	}
	
	
	
}
