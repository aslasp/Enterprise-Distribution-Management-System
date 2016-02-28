package businesslogic.salesbl;

import java.util.ArrayList;

public class MockSale extends Sale{
	private String id;
	private ArrayList<MockSaleItem> goodsList;
	private double totalValue;
	private double couponIncome;
	private double salesPrimeCost;
	private double totalPurchaseValue;
	
	public MockSale(String id) throws Exception{
		super();
		this.id =id;
		this.goodsList=new ArrayList<MockSaleItem>();
		this.totalValue=0;
		this.couponIncome=600;
		this.salesPrimeCost=200;
		this.totalPurchaseValue=100;
	}
	
	public void AddGoods(MockSaleItem item){
		goodsList.add(item);
		this.totalValue+=(item.getTotal());
	}
	
	
	public void DeleteGoods(MockSaleItem item){
		goodsList.remove(item);
		this.totalValue-=(item.getTotal());
	}
	
	public double getTotalValue(){
		return this.totalValue;
	}
	
	public double getCouponIncome(){
		return this.couponIncome;
	}

	public double getSalesPrimeCost() {
		return salesPrimeCost;
	}

	public double getTotalPurchaseValue() {
		return totalPurchaseValue;
	}
}
