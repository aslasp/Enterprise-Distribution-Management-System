package Data.financedata;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Data.serutility.JXCFile;
import po.CashlistPO;
import po.ClauseItemPO;
import dataservice.financedataservice.listdataservice.CashlistDataService;

public class Cashlist extends UnicastRemoteObject implements CashlistDataService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;
	static int num=0;
	public Cashlist() throws RemoteException{
		super();
		file=new JXCFile("cashlist.ser");
	}
	public int createCashlist(CashlistPO po) throws RemoteException {
		file=new JXCFile("cashlist.ser");
		file.write(po);
		num++;
		return 0;
	}
	
	public int setStatus(String id,int st){
		file=new JXCFile("cashlist.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	 
		int i;
		for(i=0;i<a.size();i++){
			CashlistPO b=(CashlistPO)a.get(i);
			if(b.getId().equals(id)){
				b.setStatus(st);;
				break;
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.writeM(a);
		return 0;
	}
	
	public int modify(CashlistPO po){
		file=new JXCFile("cashlist.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	 
		int i;
		for(i=0;i<a.size();i++){
			CashlistPO b=(CashlistPO)a.get(i);
			if(b.getId().equals(po.getId())){
				b.setClauselist(po.getClauselist());;
				break;
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.writeM(a);
		return 0;
	}
	
	public ArrayList<CashlistPO> getCashlist() throws RemoteException{
		file=new JXCFile("cashlist.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<CashlistPO> buffer=new ArrayList<CashlistPO>();
		for(Object b:a){
			CashlistPO po=(CashlistPO)b;
			buffer.add(po);
		}
		
		return buffer;
		
	}
	
	public CashlistPO findByID(String id){
		file=new JXCFile("cashlist.ser");
		try {
			ArrayList<CashlistPO> po=getCashlist();
			if(po==null)
				return null;
			for(CashlistPO p:po){
				if(p.getId().equals(id))
					return p;
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public int getNum() throws RemoteException{
		file=new JXCFile("cashlist.ser");
		return num;
	}

	public static void main(String[] args){
		Cashlist a;
		try {
			a = new Cashlist();
			ArrayList<ClauseItemPO> al=new ArrayList<ClauseItemPO>();
			ClauseItemPO item =new ClauseItemPO("王雅婷拿去吃饭的",100,"He");
			al.add(item);
			a.createCashlist(new CashlistPO("XJFYD-20141129-000001","CW-00001","王雅婷",al,100,1,1));	
			System.out.println("Success!");

    		ArrayList<CashlistPO> al2=a.getCashlist();
			System.out.println(al2.size());
			for(CashlistPO b:al2){
				ArrayList<ClauseItemPO> c=b.getClauselist();
				System.out.println(b.getId()+b.getUserID()+b.getAccount()+c.get(0).getMoney()+b.getTotalMoney());				
			}
	
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}
