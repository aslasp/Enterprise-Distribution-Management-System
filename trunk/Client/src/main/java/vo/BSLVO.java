package vo;

public class BSLVO {
    
	double salesIncome;
    double goodsOverIncome;
    double primeCostIncome;
    double importReturnIncome;
    double couponIncome;
    double totalIncome;
    
    double salesPrimeCost;
    double goodsLowCost;
    double goodsGiftCost;
	double totalExpense;
	
	double profit;
        
	public BSLVO(){
		this(0,0,0,0,0,0,0,0);
	}
	
    public BSLVO(double a,double b,double c,double d,double e,double f,double g,double h){
    	salesIncome=a;
    	goodsOverIncome=b;
    	primeCostIncome=c;
    	importReturnIncome=d;
    	couponIncome=e;
    	totalIncome=a+b+c+d+e;
    	salesPrimeCost=f;
    	goodsLowCost=g;
	    goodsGiftCost=h;
	    totalExpense=f+g+h;
	    profit=totalIncome-totalExpense;
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
	
	public double getSalesPrimeCost(){
		return salesPrimeCost;
	}
	public double getTotalExpense(){
		return totalExpense;
	}		
	 
	 public double getProfit(){
		 return profit;
	 }


	
}
