package Data.memberdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Data.serutility.JXCFile;
import po.MemberPO;
import po.MemberPO.MemberType;
import dataservice.memberdataservice.MemberDataService;

public class Member extends UnicastRemoteObject implements MemberDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JXCFile file;

	public Member() throws RemoteException {
		super();
		file=new JXCFile("member.ser");
	}

	public int add(MemberPO po) {
		file=new JXCFile("member.ser");
		file.write(po);
		return 0;
	}

	public int delete(String id) {
		file=new JXCFile("member.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		for(i=0;i<a.size();i++){
			MemberPO b=(MemberPO)a.get(i);
			if(b.getMemberID().equals(id)){
				a.remove(i);
				break;
			}
		}
		
		
		file.writeM(a);
		return 0;
	}
	
	public int changeToReceive(String id,double m)throws RemoteException{
		file=new JXCFile("member.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		for(i=0;i<a.size();i++){
			MemberPO b=(MemberPO)a.get(i);
			if(b.getMemberID().equals(id)){
				b.changeToReceive(m);
				if(b.getToReceive()>b.getMaxOwe())
					return 1;
				break;
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.writeM(a);
		return 0;
	}
	
	public int changeToPay(String id,double m)throws RemoteException{
		file=new JXCFile("member.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		for(i=0;i<a.size();i++){
			MemberPO b=(MemberPO)a.get(i);
			if(b.getMemberID().equals(id)){
				b.changeToPay(m);
				break;
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.writeM(a);
		return 0;
	}

	public int modify(MemberPO po) {
		file=new JXCFile("member.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		for(i=0;i<a.size();i++){
			MemberPO b=(MemberPO)a.get(i);
			if(b.getMemberID().equals(po.getMemberID())){
				b.setAddress(po.getAddress());
				b.setDefaultClerk(po.getDefaultClerk());
				b.setEMail(po.getEMail());
				b.setMaxOwe(po.getMaxOwe());
				b.setmType(po.getmType());
				b.setmLevel(po.getmLevel());
				b.setName(po.getName());
				b.setPoints(po.getPoints());
				b.setPostcode(po.getPostcode());
				b.setTel(po.getTel());
				b.setToPay(po.getToPay());
				b.setToReceive(po.getToReceive());
				break;
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.writeM(a);
		return 0;
	}
	
	public MemberPO findByID(String ID){
		file=new JXCFile("member.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		for(Object b:a){
			MemberPO c=(MemberPO)b;
			if(c.getMemberID().equals(ID))
				return c;
		}
		
		return null;
		
	}

	public ArrayList<MemberPO> find(String message) {
		file=new JXCFile("member.ser");
		ArrayList<MemberPO> buffer=new ArrayList<MemberPO>();
		ArrayList<Object> a=file.read();
		String s1="进货商";
		String s2="销售商";
		if(a==null)
			return null;
		for(Object b:a){
			MemberPO c=(MemberPO)b;
			if(c.getMemberID().contains(message))
				buffer.add(c);
			else if(s1.contains(message)){
				if(c.getmType()==MemberType.JHS)
				  buffer.add(c);
			}
			else if(s2.contains(message)){
				if(c.getmType()==MemberType.XSS)
					buffer.add(c);
			}
			else if(c.getName().contains(message)){
				buffer.add(c);
			}
			else if(c.getAddress().contains(message)){
				buffer.add(c);
			}
			else if(c.getTel().contains(message)){
				buffer.add(c);
			}
			else if(c.getEMail().contains(message)){
				buffer.add(c);
			}
			else if(c.getPostcode().contains(message)){
				buffer.add(c);
			}
			else if(c.getDefaultClerk().contains(message)){
				buffer.add(c);
			}
		}
		if(buffer.size()==0)
		    return null;
		return buffer;
	}

	public ArrayList<MemberPO> showAll() throws RemoteException {
		file=new JXCFile("member.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<MemberPO> buffer=new ArrayList<MemberPO>();
		for(Object b:a){
			MemberPO c=(MemberPO)b;
			buffer.add(c);
		}
		
		  return buffer;
	}
	
	public ArrayList<MemberPO> show(MemberType type)throws RemoteException{
		file=new JXCFile("member.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<MemberPO> buffer=new ArrayList<MemberPO>();
		for(Object b:a){
			MemberPO c=(MemberPO)b;
			buffer.add(c);
		}
		
		ArrayList<MemberPO> result=new ArrayList<MemberPO>();
			for(MemberPO po:buffer){
				if(po.getmType()==type)
					result.add(po);					
			}
		
		if(result.size()==0)
			return null;
		
		  return result;
	}
	
	public int getNum(MemberType type){
		file=new JXCFile("member.ser");
		ArrayList<Object> a=file.read();
		int num=0;
		
		if(a==null)
			return 0;
		else{
			for(Object b:a){
				MemberPO c=(MemberPO)b;
				if(c.getmType()==type){
					num++;
				}
			}
		}
        return num;
	}
	
	public static void main(String[] args){

		Member m;
		try {
			m = new Member();
			m.changeToPay("XSS-0000001",100);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
	/*	try {
			Member m=new Member();
			MemberPO po=new MemberPO("JHS-0000001", MemberType.JHS, MemberLevel.ONE,"马建国","23333","深圳市蛇口区","210046","a@qq.com","一米五小公主", 100, 0,0,0);
			m.add(po);
			MemberPO po2=new MemberPO("XSS-0000001", MemberType.XSS, MemberLevel.ONE,"妞妞","23333","深圳市蛇口区","210046","a@qq.com","端午", 100, 0,0,0);
			m.add(po2);
			ArrayList<MemberPO> buffer=m.showAll();
			for(MemberPO a:buffer){
			     System.out.println(a.getName());
			}
			System.out.println("------------------");
			m.delete(po2.getMemberID());
			buffer=m.showAll();
			for(MemberPO a:buffer){
			     System.out.println(a.getName());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	
	

}
