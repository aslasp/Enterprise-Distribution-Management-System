package Presentation.mainui;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import businesslogic.utilitybl.logbl;
import businesslogicservice.userblservice.LogBLService;
import vo.LogVO;

/*
 *Author:jin 
 *lastModify:12-06 by jin
 *description:add the system log when user do operation 
 *s
 * */
public class log {
	public  static  void  addLog(LogVO vo){
		try {
			LogBLService service=new logbl();
			service.AddLog(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static String getdate(){
		 Calendar rightNow = Calendar.getInstance();
	        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	        String sysDatetime = fmt.format(rightNow.getTime());   
	return sysDatetime;
	}

}
