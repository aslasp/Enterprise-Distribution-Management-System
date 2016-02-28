

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
		file=new JXCFile("src/main/java/user.ser");
	}
	
	public int add(UserPO po) throws RemoteException {
		if(showUserInfo(po.getID())==null){
			file.write(po);
		    return 0;		
		}
		else 
			return 1;
	    
	}
	
	public void addGrades(String id,double grades) throws RemoteException{
		ArrayList<Object> a=file.read();
		int i;
		for(i=0;i<a.size();i++){
			UserPO b=(UserPO)a.get(i);
			if(b.getID().equals(id)){
				b.setGrades(grades);
				break;
			}
		}
		
		
		file.writeM(a);
	
	}

	public int delete(String ID) throws RemoteException {
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		for(i=0;i<a.size();i++){
			UserPO b=(UserPO)a.get(i);
			if(b.getID().equals(ID)){
				a.remove(i);
				break;
			}
		}
		
		
		file.writeM(a);
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
				b.setGrades(po.getGrades());
				break;
			}
		}
		

		file.writeM(a);
		return 0;
	}

	public UserPO showUserInfo(String ID) throws RemoteException {
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		for(Object b:a){
			UserPO c=(UserPO)b;
			if(c.getID().equals(ID))
				return c;
		}
	//	UserPO po=new UserPO("jfje","22","123456",UserJob.SALE,0);
					
		return null; //不存在该用户
	}
	
	
	public ArrayList<UserPO> showAll() throws RemoteException{
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<UserPO> buffer=new ArrayList<UserPO>();
		for(Object b:a){
			UserPO c=(UserPO)b;
			buffer.add(c);
		}
		
		return buffer;
	}
	
	
	public int getNum(UserJob job){
		ArrayList<Object> a=file.read();
		int num=0;
		
		if(a==null)
			return 0;
		else{
			for(Object b:a){
				UserPO c=(UserPO)b;
				if(c.getJob()==job){
					num++;
				}
			}
		}
        return num;

	}
	
	public static void main(String[] args){
		User a;
		try {
			a=new User();
	//		UserPO b=new UserPO("刘钦","GL-00001","123456",UserJob.ADMINSTRATOR,0);
	//		a.add(b);
/*			UserPO b = new UserPO("Lucy", "CW-00001","123456",UserJob.FINANCE,100);
			System.out.println(a.getNum(UserJob.STOCK));
			System.out.println(a.getNum(UserJob.SALE));
			System.out.println(a.getNum(UserJob.FINANCE));
			System.out.println(a.getNum(UserJob.MANAGER));
			System.out.println(a.add(b)+"增加Lucy结果");
			UserPO c=a.showUserInfo("JL-00001");
			UserPO d=a.showUserInfo("CW-00001");
			if(c==null)
				System.out.println("ID不存在");
			else {
			    System.out.println(c.getName()+c.getPassword()+c.getJob());
			    System.out.println(d.getName()+d.getPassword()+d.getJob());
			}
			System.out.println(a.add(b)+"增加Lucy结果");
			System.out.println(a.delete(b)+"去掉Lucy结果");
			UserPO e=a.showUserInfo("CW-00001");
			if(e==null){
				System.out.println("ID不存在");
			    System.out.println(c.getName()+c.getPassword()+c.getJob());
			}
			else {
			    System.out.println(e.getName()+e.getPassword()+e.getJob());
			   
			}
			
			UserPO f = new UserPO("Lucy", "CW-00001","123456",UserJob.FINANCE,100);
			System.out.println(a.add(f)+"增加Lucy结果");
			UserPO g = new UserPO("Lucy", "CW-00001","123456",UserJob.FINANCE,100);
			System.out.println(a.modify(g)+"修改Lucy结果");
			UserPO h=a.showUserInfo("CW-00001");
			if(h==null){
				System.out.println("ID不存在");
			    System.out.println(c.getName()+c.getPassword()+c.getJob());
			}
			else {
			    System.out.println(h.getName()+h.getPassword()+h.getJob());
			   
			}
			
			UserPO i = new UserPO("Lucy", "CW-00001","123456",UserJob.FINANCE,100);
			a.modify(i);
*/			
			UserPO j = new UserPO("王宁宁", "XS-00001","123456",UserJob.SALE,0);
			a.add(j);
			UserPO k = new UserPO("宽宽", "KC-00001","123456",UserJob.STOCK,0);
			a.add(k);
			UserPO k1 = new UserPO("Lucy", "CW-00001","123456",UserJob.FINANCE,100);
			a.add(k1);
			UserPO k2 = new UserPO("金大大", "JL-00001","123456",UserJob.MANAGER,0);
			a.add(k2);
			UserPO k3 = new UserPO("刘钦", "GL-00001","123456",UserJob.ADMINSTRATOR,0);
			a.add(k3);

			ArrayList<UserPO> buffer=a.showAll();	
			System.out.println("-----------------我是萌萌哒的分隔线---------------------");
			for(UserPO po:buffer){
				System.out.println("ID:"+po.getID()+" name:"+po.getName()+" password:"+po.getPassword()+" job:"+po.getJob()+" grades:"+po.getGrades());
			}

			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	

}

