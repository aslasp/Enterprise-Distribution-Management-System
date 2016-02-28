package Data.financedata;

import java.rmi.server.UnicastRemoteObject;

import po.CollectionPO;
import po.TransferItemPO;
import dataservice.financedataservice.listdataservice.CollectionDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Data.serutility.JXCFile;

public class Collection extends UnicastRemoteObject implements CollectionDataService{
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;
	static int num=0;
	public Collection() throws RemoteException{
		super();
		file=new JXCFile("collection.ser");
	}
	public int createCollection(CollectionPO po) throws RemoteException{
		file=new JXCFile("collection.ser");
		file.write(po);
		num++;
/*		ArrayList<Object> al2=file.read();
		System.out.println(al2.size());
		for(Object a:al2){
			CollectionPO b=(CollectionPO)a;
			ArrayList<TransferItemPO> c=b.getTransferlist();
			System.out.println(b.getID()+b.getSupplier()+b.getSeller()+b.getUser()+c.get(0).getMoney()+b.getTotalMoney());
			
		}
*/
		return 0;
	}
	
	public ArrayList<CollectionPO> getCollection() throws RemoteException{
		file=new JXCFile("collection.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<CollectionPO> buffer=new ArrayList<CollectionPO>();
		for(Object b:a){
			CollectionPO po=(CollectionPO)b;
			buffer.add(po);
		}
		
		return buffer;
		
	}
	
	public int setStatus(String id,int st){
		file=new JXCFile("collection.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	 
		int i;
		for(i=0;i<a.size();i++){
			CollectionPO b=(CollectionPO)a.get(i);
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
	
	public int modify(CollectionPO po){
		file=new JXCFile("collection.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	 
		int i;
		for(i=0;i<a.size();i++){
			CollectionPO b=(CollectionPO)a.get(i);
			if(b.getId().equals(po.getId())){
				b.setTransferlist(po.getTransferlist());
				break;
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.writeM(a);
		return 0;
	}
	
	public CollectionPO findByID(String id){
		file=new JXCFile("collection.ser");
		try {
			ArrayList<CollectionPO> po=getCollection();
			if(po==null)
				return null;
			for(CollectionPO p:po){
				if(p.getId().equals(id))
					return p;
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public ArrayList<CollectionPO> findByMember(String s){
		file=new JXCFile("collection.ser");
		ArrayList<CollectionPO> al=new ArrayList<CollectionPO>();
		try {
			ArrayList<CollectionPO> po=getCollection();
			if(po==null)
				return null;
			for(CollectionPO p:po){
				if(p.getMemberID().equals(s))
					al.add(p);
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(al.size()==0)
			return null;
		
		return al;
		
	}
	
	public int getNum() throws RemoteException{
		file=new JXCFile("collection.ser");
		return num;
	}

	public static void main(String[] args){
		Collection a;
		try {
			a = new Collection();
			ArrayList<TransferItemPO> al=new ArrayList<TransferItemPO>();
			TransferItemPO item =new TransferItemPO("马建国",100,"He");
			TransferItemPO item2 =new TransferItemPO("马建国",2,"He");
			TransferItemPO item3 =new TransferItemPO("小马甲",1,"He");
			TransferItemPO item4 =new TransferItemPO("妞",23,"He");
			TransferItemPO item5 =new TransferItemPO("端",34,"He");
			al.add(item);
			al.add(item2);
			al.add(item3);
			al.add(item4);
			al.add(item5);
			a.createCollection(new CollectionPO("SKD-20141129-001112","JHS-0000001","金大大","CW-00001",al,1,1,1));	
			System.out.println("Success!");
			CollectionPO po=a.findByID("SKD-20141129-001112");
			ArrayList<TransferItemPO> bl=po.getTransferlist();
			System.out.println(bl.get(0).getAccount()+bl.get(0).getMoney());
			System.out.println(bl.get(1).getAccount()+bl.get(1).getMoney());
			System.out.println(bl.get(2).getAccount()+bl.get(2).getMoney());
			System.out.println(bl.get(3).getAccount()+bl.get(3).getMoney());
			System.out.println(bl.get(4).getAccount()+bl.get(4).getMoney());


		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
}
