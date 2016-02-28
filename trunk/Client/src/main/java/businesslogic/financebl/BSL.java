package businesslogic.financebl;

public class BSL {
	private double salesIncome;
	private double goodsOverIncome;
	private double primeCostIncome;
	private double importReturnIncome;
	private double couponIncome;
	private double totalIncome;

	private double salesPrimeCost;
	private double goodsLowCost;
	private double goodsGiftCost;
	private double totalExpense;

	private double profit;

	public BSL(double salesIncome, double goodsOverIncome,
			double primeCostIncome, double importReturnIncome,
			double couponIncome, double salesPrimeCost, double goodsLowCost,
			double goodsGiftCost) {
		this.salesIncome = salesIncome;
		this.goodsOverIncome = goodsOverIncome;
		this.primeCostIncome = primeCostIncome;
		this.importReturnIncome = importReturnIncome;
		this.couponIncome = couponIncome;
		this.totalIncome = salesIncome + goodsOverIncome + primeCostIncome
				+ importReturnIncome + couponIncome;
		this.salesPrimeCost = salesPrimeCost;
		this.goodsLowCost = goodsLowCost;
		this.goodsGiftCost = goodsGiftCost;
		this.totalExpense = salesPrimeCost + goodsLowCost + goodsGiftCost;
		this.profit = totalIncome - totalExpense;
	}
	
	public double getTotalIncome(){
		return this.totalIncome;
	}
	
	
	public double getTotalExpense(){
		return this.totalExpense;
	}
	
	public double getProfit(){
		return this.profit;
	}

}
