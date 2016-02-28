package vo;

public class TransferItemVO {
	String account;
	double money;
	String info;
	
	
	public TransferItemVO(){
		this(null,0,null);
	}
	
	public TransferItemVO(String a,double b,String c){
		account=a;
		money=b;
		info=c;
	}
	
	public String getAccount(){
		return account;
	}
	
	public double getMoney(){
		return money;
	}
	
	public String getInfo(){
		return info;
	}
}
