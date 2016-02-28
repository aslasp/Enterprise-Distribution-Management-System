package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CouponPO;

public interface CouponDataService extends Remote{
	public ArrayList<CouponPO> getAllCoupon();
	public void addCoupon(ArrayList<CouponPO> po);
	public void ModifyCoupon(String id,boolean status,String useDate);
	

}
