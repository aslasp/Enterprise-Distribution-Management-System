package Presentation.userui.usermanage;

import po.UserPO.UserJob;

public class getJobChange {
	public static  UserJob getJobType(String s){
		if(s.equals("进货销售人员"))
			return UserJob.SALE;
		else if(s.equals("库存人员"))
			return UserJob.STOCK;
		else if(s.equals("总经理"))
			return UserJob.MANAGER;
		else if(s.equals("财务人员"))
			return UserJob.FINANCE;
		else if(s.equals("系统管理员"))
			return UserJob.ADMINSTRATOR;
		else if(s.equals("销售经理"))
			return UserJob.SALEMANAGER;
		else{
			return UserJob.FINANCEMANAGER;
		}
		
	}
	
	public static String getJobString(UserJob job){
		switch(job){
		case MANAGER:
			return "总经理";
		case SALEMANAGER:
			return "销售经理";
		case FINANCE:
			return "财务人员";
		case FINANCEMANAGER:
			return "财务经理";
		case ADMINSTRATOR:
			return "系统管理员";
		case SALE:
			return "进货销售人员";
			default:
				return "库存人员";
		
			
		}
	}

}
