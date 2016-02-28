package po;

import java.io.Serializable;

public class TransferItemPO implements Serializable{
	String account;
	double money;
	String info;
	
	
	public TransferItemPO(){
		this(null,0,null);
	}
	
	public TransferItemPO(String a,double b,String c){
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
