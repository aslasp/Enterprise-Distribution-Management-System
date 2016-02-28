package Data.promotiondata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CouponPO;
import po.PackProPO;
import Data.serutility.JXCFile;
import dataservice.promotiondataservice.CouponDataService;

public class Coupon extends UnicastRemoteObject implements CouponDataService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;
	public Coupon() throws RemoteException {
		super();		
	}
	
	public ArrayList<CouponPO> getAllCoupon() throws RemoteException {
		file=new JXCFile("coupon.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<CouponPO> buffer=new ArrayList<CouponPO>();
		for(Object b:a){
			CouponPO po=(CouponPO)b;
			buffer.add(po);
		}
		
		return buffer;
	}
	
	public void addCoupon(ArrayList<CouponPO> po) throws RemoteException {
		file=new JXCFile("coupon.ser");
		for(int i=0;i<po.size();i++){
		  CouponPO pp=po.get(i);
		  file.write(pp);
		}
	}

	public void ModifyCoupon(String id, boolean status,String date) throws RemoteException {
		file=new JXCFile("coupon.ser");
		ArrayList<Object> al=file.read();
		if(al!=null){
			for(int i=0;i<al.size();i++){
				CouponPO p=(CouponPO)al.get(i);
				if(id.equals(p.getId())){
					p.setUse(status);
					p.setUseDate(date);
					break;
				}
				file=new JXCFile("coupon.ser");
				file.writeM(al);
		
			}
		}
	}
	
	
}
