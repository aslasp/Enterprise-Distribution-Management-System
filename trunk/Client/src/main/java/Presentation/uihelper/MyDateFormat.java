package Presentation.uihelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyDateFormat {
	 public static String FomatDate(String date){
	    	String year=date.substring(0, 4);
	    	String month=date.substring(4, 6);
	    	String day=date.substring(6, 8);
	    	return year+"/"+month+"/"+day;
	    }
	 
	 public static String getToday(){
	 Calendar rightNow = Calendar.getInstance();
     SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
     String sysDatetime = fmt.format(rightNow.getTime());   
     return sysDatetime;
	 }
}
