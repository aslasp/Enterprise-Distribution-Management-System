package dataservice.stockdataservice.goodsclassdataservice;

//服务器端

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsClassPO;

public interface StockGoodsClassDataService extends Remote {
	public int addGoodsClass(GoodsClassPO po) throws RemoteException;

	public int deleteGoodsClass(GoodsClassPO po) throws RemoteException;

	public int modifyGoodsClass(GoodsClassPO po) throws RemoteException;

	public ArrayList<GoodsClassPO> show() throws RemoteException;

	public String getMaxID() throws RemoteException;
	
	public GoodsClassPO showGoodsClassInfo(String name) throws RemoteException;
}
