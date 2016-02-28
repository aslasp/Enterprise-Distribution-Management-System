package businesslogic.financebl;

public class MockAccount extends Account{
	private String name;
	private String number;
	private double balance;
	public MockAccount(String name,String number,double balance) throws Exception{
		this.name=name;
		this.number=number;
		this.balance=balance;
	}	
	
	public void updateBalance(double money){
		balance+=money;
	}
	
	public double getBalance(){
		return balance;
	}

}
