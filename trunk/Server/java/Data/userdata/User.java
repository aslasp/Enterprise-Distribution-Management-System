

package Data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.UserPO;
import po.UserPO.UserJob;
import dataservice.userdataservice.UserDataService;
import Data.serutility.JXCFile;
//11-17 By jin  添加showAll 和Find 方法删除 login 判断留在bl层
public class User extends UnicastRemoteObject implements UserDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	JXCFile file;
	public User() throws RemoteException {
		super();
		file=new JXCFile("user.ser");
		// TODO 自动生成的构造函数存根
	}
	
	public int add(UserPO po) throws RemoteException {
		if(showUserInfo(po.getID())!=null){
			return 1;
		}
	    file.write(po);
	    return 0;
	}

	public int delete(UserPO po) throws RemoteException {
		ArrayList<Object> a=file.read();
		  		
		int i;
		for(i=0;i<a.size();i++){
			UserPO b=(UserPO)a.get(i);
			if(b.getID().equals(po.getID())){
				a.remove(i);
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.write(a);
		return 0;
	}

	public int modify(UserPO po) throws RemoteException {
		ArrayList<Object> a=file.read();
  		
		int i;
		for(i=0;i<a.size();i++){
			UserPO b=(UserPO)a.get(i);
			if(b.getID().equals(po.getID())){
				b.setJob(po.getJob());
				b.setName(po.getName());
				b.setPassword(po.getPassword());
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.write(a);
		return 0;
	}

	public UserPO showUserInfo(String ID) throws RemoteException {
		/*ArrayList<Object> a=file.read();
		for(Object b:a){
			UserPO c=(UserPO)b;
			if(c.getID()==ID)
				return c;
		}*/
		UserPO po=new UserPO("jfje","22","123456",UserJob.SALE,0);
					
		return po; //不存在该用户
	}
	

}

