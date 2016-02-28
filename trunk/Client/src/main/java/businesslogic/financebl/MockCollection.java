package businesslogic.financebl;

import java.util.ArrayList;

/*
 * 收款单Mock  
 */
public class MockCollection extends Collection{
	private String id;
	private ArrayList<String> memberID;
	private ArrayList<Double> money;
	private double totalMoney;
	public MockCollection(String id) throws Exception{
		this.id=id;
		memberID=new ArrayList<String>();
		money=new ArrayList<Double>();
		totalMoney=0;
	
	}
	
	
	public void AddCollectItem(String memberId,double Money){
		money.add(Money);
		memberID.add(memberId);
		this.totalMoney+=Money;
	}
	
	public void DeleteCollectItem(String ID){
		int i=memberID.indexOf(ID);
		memberID.remove(i);
		this.totalMoney-=money.get(i);
		money.remove(i);
		
	}
	
	public String getMemberIDByOrder(int i){
		return memberID.get(i);
	}
	
	public double getMoneyByOrder(int i){
		return money.get(i);
	}
	
	public double getTotalMoney(){
		return this.totalMoney;
	}
	
	
	
	

	

	
	
}
