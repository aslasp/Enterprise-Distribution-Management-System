package po;

import java.io.Serializable;

public class AccountPO implements Serializable{
	      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		  String name;
	      double money;
	      
	      public AccountPO(){
	    	  this(null,0);
	      }
	      
	      public AccountPO(String a){
	    	  this(a,0);
	      }
	      
	      public AccountPO(String a,double b){
	    	  name=a;
	    	  money=b;
	      }
	      
	      public String getName(){
	    	  return name;
	      }
	      
	      public double getMoney(){
	    	  return money;
	      }
	      
	      public void setName(String s){
	    	  name=s;
	      }
	      
	      public void addMoney(double m){
	    	  money+=m;
	      }
	      
	      public void delMoney(double m){
	    	  money-=m;
	      }
	

}
