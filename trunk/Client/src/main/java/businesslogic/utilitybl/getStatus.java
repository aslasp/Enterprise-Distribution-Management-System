package businesslogic.utilitybl;

public class getStatus {
   public static String getstatus(int i){
	   if(i==0)
		   return "待审批";
	   if(i==1)
	       return "审批未通过";
	   if(i==2)
		   return "审批通过";
	   if(i==3)
		   return "执行完毕";
	   
	   return "error";
   }
}
