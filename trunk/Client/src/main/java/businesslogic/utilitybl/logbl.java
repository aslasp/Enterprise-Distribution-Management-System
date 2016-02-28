package businesslogic.utilitybl;


import java.rmi.Naming;
import java.util.ArrayList;

import dataservice.userdataservice.LogService;
import po.LogPO;
import businesslogicservice.userblservice.LogBLService;
import vo.LogVO;
//依赖userDataService??
public class logbl implements LogBLService{
	LogService service;
	public logbl() throws Exception{
		String host=getServer.getServer();;
		String url="rmi://"+host+"/logService";
	
		service=(LogService)Naming.lookup(url);
	}
	
	public void AddLog(LogVO vo){
		
		service.AddLog(voToPo(vo));
	}
	
	
	public ArrayList<LogVO> showLog(){
		ArrayList<LogPO> po=service.showAll();
		if(po==null)return null;
		else 
			{
			ArrayList<LogVO> vo=new ArrayList<LogVO>();
			for(int i=0;i<po.size();i++)
				vo.add(poToVo(po.get(i)));
			
				return vo;
			}
		
		
	}
	
	
	public ArrayList<LogVO> find(String startDate, String endDate) {
		// TODO Auto-generated method stub
		ArrayList<LogPO> po=service.find(startDate, endDate);
		if(po==null)return null;
		else 
			{
			ArrayList<LogVO> vo=new ArrayList<LogVO>();
			for(int i=0;i<po.size();i++)
				vo.add(poToVo(po.get(i)));
			
				return vo;
			}
	}
	public LogVO poToVo(LogPO po){
		LogVO vo=new LogVO(po.getDate(),po.getUserID(),po.getUserName(),
				po.getInfo(),po.getAddGrades());
		return vo;
		
	}
	
	public LogPO voToPo(LogVO vo){
		LogPO po=new LogPO(vo.getDate(),vo.getUserID(),vo.getUserName()
				,vo.getInfo(),vo.getAddGrades());
		return po;
	}

	

	
}
