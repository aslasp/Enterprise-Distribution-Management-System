package businesslogic.salesbl;

public class MockCommodity extends Commodity{
	private String id;
	private String name;
	private String type;
	private int num;
	private double price;
	private double total;
	public MockCommodity(String id, String name, String type, int num,
			double price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.num = num;
		this.price = price;
		this.total=price*num;
		
	}
	
	
	public void Add(int number){
		this.num+=number;
		this.total+=(number*(this.price));
	}
	
	public void Delete(int number){
		if(number<=this.num){
			this.num-=number;
			this.total-=(number*(this.price));
		}
	}
	
	public double getTotalPrice(){
		return this.total;
	}
}
