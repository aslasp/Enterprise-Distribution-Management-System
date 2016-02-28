package po;

import java.io.Serializable;

public class ClauseItemPO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	 double money;
	 String info;
	 
	 public ClauseItemPO(){
		 this(null,0,null);
	 }
	 
	 public ClauseItemPO(String a,double b,String c){
		 name=a;
		 money=b;
		 info=c;
	 }
	 
	 public String getName(){
		 return name;
	 }
	 
	 public double getMoney(){
		 return money;
	 }
	 
	 public String getInfo(){
		 return info;
	 }
	 
	 public void setMoney(double m){
		 money=m;
	 }
	 
	 public void setInfo(String s){
		 info=s;
	 }
	 
}
