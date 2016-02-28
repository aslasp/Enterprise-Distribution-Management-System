package po;

import java.io.Serializable;


public class BSLPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Income in;
	Expense out;
	Profit pro;
	
	class Income{
		double salesIncome;
	    double goodsOverIncome;
	    double primeCostIncome;
	    double importReturnIncome;
	    double couponIncome;
	    double totalIncome;
	    
	    public Income(){
	    	this(0,0,0,0,0);
	    }
	    
	    public Income(double a,double b,double c,double d,double e){
	    	salesIncome=a;
	    	goodsOverIncome=b;
	    	primeCostIncome=c;
	    	importReturnIncome=d;
	    	couponIncome=e;
	    	totalIncome=a+b+c+d+e;
	    }
	    
	    public double getSalesIncome(){
	    	return salesIncome;
	    }
	    
	    public double getGoodsOverIncome(){
	    	return goodsOverIncome;
	    }
	    
	    public double getPrimeCostIncome(){
	    	return primeCostIncome;
	    }
	    
	    public double getImportReturnIncome(){
	    	return importReturnIncome;
	    }
	    
	    public double getCouponIncome(){
	    	return couponIncome;
	    }
	    
	    public double getTotalIncome(){
	    	return totalIncome;
	    }
	    
	}
	 
	class Expense{
		double salesPrimeCost;
		double totalExpense;
		
		
		public double getSalesPrimeCost(){
			return salesPrimeCost;
		}
		public double getTotalExpense(){
			return totalExpense;
		}
	}
	
	class Profit{
		double profit;
		
		 public Profit(){
			 Income i=new Income();
			 Expense e=new Expense();
			 profit=i.totalIncome-e.totalExpense;
		 }
		 
		 public double getProfit(){
			 return profit;
		 }
	}
	

}
