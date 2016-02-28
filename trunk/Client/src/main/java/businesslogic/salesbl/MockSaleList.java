package businesslogic.salesbl;

import java.util.ArrayList;

public class MockSaleList extends SaleList{
	private ArrayList<MockSale> saleList;
	private double saleInCome;
	private double couponIncome;
	private double salesPrimeCost;
	
	public MockSaleList() throws Exception{
		super();
		this.saleList=new ArrayList<MockSale>();
		this.saleInCome=0;
		this.couponIncome=0;
		this.salesPrimeCost=0;
	}
	
	public void AddSale(MockSale sale){
		saleList.add(sale);
		this.saleInCome+=sale.getTotalValue();
		this.couponIncome+=sale.getCouponIncome();
		this.salesPrimeCost+=sale.getTotalPurchaseValue();
	}
	

	public double getSaleInCome(){
		return this.saleInCome;
	}
	
	public double getCouponIncome(){
		return this.couponIncome;
	}

	public double getSalesPrimeCost(){
		return this.salesPrimeCost;
	}
}
