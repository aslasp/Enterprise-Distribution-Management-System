package Data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.LogPO;
import Data.serutility.JXCFile;
import dataservice.userdataservice.LogService;

public class Log extends UnicastRemoteObject implements LogService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;

	public Log() throws RemoteException {
		super();
		file=new JXCFile("log.ser");
	}

	public void AddLog(LogPO po) throws RemoteException {
		file=new JXCFile("log.ser");
		file.write(po);
		User user=new User();
		user.addGrades(po.getUserID(),po.getAddGrades());
	}

	public ArrayList<LogPO> showAll() throws RemoteException {
		file=new JXCFile("log.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<LogPO> buffer=new ArrayList<LogPO>();
		for(Object b:a){
			LogPO c=(LogPO)b;
			buffer.add(c);
		}
		
		return buffer;
	}
	
	public ArrayList<LogPO> find(String startDate, String endDate) throws RemoteException {
		file=new JXCFile("log.ser");
		ArrayList<LogPO> log=showAll();
		if(log==null)
			return null;
		ArrayList<LogPO> result=new ArrayList<LogPO>();
		for(LogPO po:log){
			if(startDate.compareTo(po.getDate())<=0&&po.getDate().compareTo(endDate)<=0)
				result.add(po);
		}
		if(result.size()==0)
			return null;
		
		return result;		
	}
	
	public static void main(String[] args){
		Log a;
		try {
			a = new Log();
			User b=new User();
			System.out.println(b.showUserInfo("CW-00001").getGrades());
			LogPO pp=new LogPO("2014/12/08","CW-00001","Lucy","创建一个收款单",100);
			a.AddLog(pp);
			ArrayList<LogPO> buffer=a.showAll();	
			System.out.println("-----------------我是萌萌哒的分隔线---------------------");
			for(LogPO po:buffer){
				System.out.println("Date:"+po.getDate()+" user:"+po.getUserName()+" info:"+po.getInfo()+" addGrades:"+po.getAddGrades());
			}
			System.out.println(b.showUserInfo("CW-00001").getGrades());
			ArrayList<LogPO> buffer1=a.find("2014/12/08","2014/12/08");
			System.out.println("-----------------我是萌萌哒的分隔线---------------------");
			for(LogPO po:buffer1){
				System.out.println("Date:"+po.getDate()+" user:"+po.getUserName()+" info:"+po.getInfo()+" addGrades:"+po.getAddGrades());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
