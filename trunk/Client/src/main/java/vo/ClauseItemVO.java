package vo;

public class ClauseItemVO {
	 String name;
	 double money;
	 String info;
	 
	 public ClauseItemVO(String iname,double imoney,String iinfo){
		 name=iname;
		 money=imoney;
		 info=iinfo;
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
}
