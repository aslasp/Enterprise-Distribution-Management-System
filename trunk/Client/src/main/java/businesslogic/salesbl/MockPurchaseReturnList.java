package businesslogic.salesbl;

import java.util.ArrayList;

public class MockPurchaseReturnList {
	private double totalValue;
	private ArrayList<PurchaseReturn> list;
	
	public MockPurchaseReturnList(double value){
		this.totalValue=value;
	}
	
	public void addPurchaseReturn(PurchaseReturn purchaseReturn){
		this.list.add(purchaseReturn);
		this.totalValue+=purchaseReturn.getTotal();
		
	}

	public double getTotalValue() {
		return totalValue;
	}
	
	
}
