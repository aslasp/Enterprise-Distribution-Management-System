package Data.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.PaymentPO;
import po.TransferItemPO;
import dataservice.financedataservice.listdataservice.PaymentDataService;
import Data.serutility.JXCFile;

public class Payment extends UnicastRemoteObject implements PaymentDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;
	static int num=0;
	public Payment() throws RemoteException{
		super();
		file=new JXCFile("payment.ser");
	}

	public int createPayment(PaymentPO po) throws RemoteException {
		file=new JXCFile("payment.ser");
		file.write(po);
		num++;
		return 0;
	}
	
	public int modify(PaymentPO po){
		file=new JXCFile("payment.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	 
		int i;
		for(i=0;i<a.size();i++){
			PaymentPO b=(PaymentPO)a.get(i);
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
	
	public int setStatus(String id,int st){
		file=new JXCFile("payment.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	 
		int i;
		for(i=0;i<a.size();i++){
			PaymentPO b=(PaymentPO)a.get(i);
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
	
	public ArrayList<PaymentPO> getPayment() throws RemoteException{
		file=new JXCFile("payment.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<PaymentPO> buffer=new ArrayList<PaymentPO>();
		for(Object b:a){
			PaymentPO po=(PaymentPO)b;
			buffer.add(po);
		}
		
		return buffer;
		
	}
	
	public PaymentPO findByID(String id){
		file=new JXCFile("payment.ser");
		try {
			ArrayList<PaymentPO> po=getPayment();
			if(po==null)
				return null;
			for(PaymentPO p:po){
				if(p.getId().equals(id))
					return p;
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public ArrayList<PaymentPO> findByMember(String s){
		file=new JXCFile("payment.ser");
		ArrayList<PaymentPO> al=new ArrayList<PaymentPO>();
		try {
			ArrayList<PaymentPO> po=getPayment();
			if(po==null)
				return null;
			for(PaymentPO p:po){
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
		file=new JXCFile("payment.ser");
		return num;
	}

	public static void main(String[] args){
		Payment a;
		try {
			a = new Payment();
			ArrayList<TransferItemPO> al=new ArrayList<TransferItemPO>();
			TransferItemPO item =new TransferItemPO("WYT",100,"He");
			al.add(item);
			a.createPayment(new PaymentPO("FKD-20141129-000001","王雅婷","王雅婷","Lucy",al,100,1,1));	
			System.out.println("Success!");

    		ArrayList<PaymentPO> al2=a.getPayment();
			System.out.println(al2.size());
			for(PaymentPO b:al2){
				ArrayList<TransferItemPO> c=b.getTransferlist();
				System.out.println(b.getId()+b.getMemberID()+b.getMemberName()+b.getUserID()+c.get(0).getMoney()+b.getTotalMoney());				
			}
	
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}
