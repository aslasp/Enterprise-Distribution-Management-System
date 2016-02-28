package Data.financedata;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Data.serutility.JXCFile;
import dataservice.financedataservice.accountdataservice.FinanceAccountDataService;
import po.AccountPO;

public class Account extends UnicastRemoteObject implements FinanceAccountDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file; 

	public Account() throws RemoteException {
		super();
		file=new JXCFile("account.ser");
	}
	

	public int addAccount(AccountPO po) {
		file=new JXCFile("account.ser");
	    if(myFindAccount(po.getName())==null){
	    	  file.write(po);
	          return 0;
	    }
	    else
	    	return 1;
	}

	public int deleteAccount(AccountPO po) {
		file=new JXCFile("account.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		for(i=0;i<a.size();i++){
			AccountPO b=(AccountPO)a.get(i);
			if(b.getName().equals(po.getName())){
				a.remove(b);
				break;
			}
		}
		
		
		file.writeM(a);
		return 0;
	}

	public int modifyAccount(AccountPO po,String name) {
		file=new JXCFile("account.ser");
		if(myFindAccount(name)!=null){
			return 1;
		}
       ArrayList<Object> a=file.read();
		
		if(a==null)
			return 1;  	  //不存在该用户	
		
		int i;
		for(i=0;i<a.size();i++){
			AccountPO b=(AccountPO)a.get(i);
			if(b.getName().equals(po.getName())){
				b.setName(name);
				break;
			}
		}
		
		file.writeM(a);
		return 0;
	}
	
	public int addMoney(String s,double m) throws RemoteException{
		file=new JXCFile("account.ser");
		 ArrayList<Object> a=file.read();
			
			if(a==null)
				return 1;  	  //不存在该用户	
			
			int i;
			for(i=0;i<a.size();i++){
				AccountPO b=(AccountPO)a.get(i);
				if(b.getName().equals(s)){
					b.addMoney(m);
					break;
				}
			}
			
			file.writeM(a);
			return 0;
	}
	
	public int delMoney(String s,double m) throws RemoteException{
		file=new JXCFile("account.ser");
		 ArrayList<Object> a=file.read();
			
			if(a==null)
				return 1;  	  //不存在该用户	
			
			int i;
			for(i=0;i<a.size();i++){
				AccountPO b=(AccountPO)a.get(i);
				if(b.getName().equals(s)){
					b.delMoney(m);
					break;
				}
			}
			
			file.writeM(a);
			return 0;
	}

	private AccountPO myFindAccount(String s){
		file=new JXCFile("account.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		for(Object b:a){
			AccountPO c=(AccountPO)b;
			if(c.getName().equals(s))
				return c;
		}
					
		return null; //不存在该用户
	}
	
	public AccountPO findByName(String s) throws RemoteException {
		file=new JXCFile("account.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		for(Object b:a){
			AccountPO c=(AccountPO)b;
			if(c.getName().equals(s))
				return c;
		}
					
		return null; //不存在该用户
	}
	
	public ArrayList<AccountPO> findAccount(String s) {
		file=new JXCFile("account.ser");
		ArrayList<Object> a=file.read();
		ArrayList<AccountPO> buffer=new ArrayList<AccountPO>();
		if(a==null)
			return null;
		for(Object b:a){
			AccountPO po=(AccountPO)b;
			if(po.getName().contains(s)){
				buffer.add(po);
			}
		}
		return buffer;
	}
	
	public ArrayList<AccountPO> showAll(){
		file=new JXCFile("account.ser");
		ArrayList<Object> a=file.read();
		ArrayList<AccountPO> buffer=new ArrayList<AccountPO>();
		if(a==null)
			return null;
		for(Object b:a){
			AccountPO po=(AccountPO)b;
			buffer.add(po);
		}
		return buffer;
	}

	public static void main(String[] args) {
		Account a;
		try {
			a=new Account();
/*			AccountPO b=new AccountPO("刘钦",0);
			a.addAccount(b);
			AccountPO c = new AccountPO("Lucy18", 10000);
			a.addAccount(c);
			ArrayList<AccountPO> buffer=a.showAll();	
			System.out.println("-----------------我是萌萌哒的分隔线---------------------");
			for(AccountPO po:buffer){
				System.out.println("name:"+po.getName()+" money:"+po.getMoney());
			}
			
			AccountPO i = new AccountPO("Lucy18", 10000);
			System.out.println(a.deleteAccount(i));
			AccountPO j = new AccountPO("刘钦", 1);
			System.out.println(a.deleteAccount(j));
			buffer=a.showAll();	
			System.out.println("-----------------我是萌萌哒的分隔线---------------------");
*/			
			ArrayList<AccountPO> buffer=a.showAll();
			for(AccountPO po:buffer){
				System.out.println("name:"+po.getName()+" money:"+po.getMoney());
			}
		
			AccountPO k = new AccountPO("妞妞", 10000);
			System.out.println(a.modifyAccount(k, "端午"));
			buffer=a.showAll();	
			System.out.println("-----------------我是萌萌哒的分隔线---------------------");
			for(AccountPO po:buffer){
				System.out.println("name:"+po.getName()+" money:"+po.getMoney());
			}
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	
}

