package vo;

public class AccountVO {
      String name;
      double money;
      
      public AccountVO(){
    	  this(null,0);
      }
      
      public AccountVO(String a,double b){
    	  name=a;
    	  money=b;
      }
      
      public String getName(){
    	  return name;
      }
      
      public double getMoney(){
    	  return money;
      }
}
