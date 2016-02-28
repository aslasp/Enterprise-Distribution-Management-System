package dataservice.stockdataservice.giftdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GiftPO;

public interface GiftDataService extends Remote {
	public int addGift(GiftPO po) throws RemoteException;

	public int dealGift(GiftPO po) throws RemoteException;

	// 获取所有库存赠送清单
	public ArrayList<GiftPO> getGiftList() throws RemoteException;

	// 获取某段时间内的商品赠送清单
	public ArrayList<GiftPO> getGiftList(String beginDate, String endDate)
			throws RemoteException;

	// 获取现在记录中最大的赠送单编号
	public String getMaxID() throws RemoteException;

	public GiftPO findByID(String id) throws RemoteException;

	public int modify(GiftPO po) throws RemoteException;
}
