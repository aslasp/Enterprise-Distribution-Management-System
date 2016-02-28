package dataservice.stockdataservice.giftdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GiftPO;

public class GiftDataService_stub implements GiftDataService {

	public int addGift(GiftPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

	public int dealGift(GiftPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

	public double getAccount(GiftPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

	public ArrayList<GiftPO> getGiftList() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<GiftPO> getGiftList(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		return null;
	}

	public String getMaxID() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	public GiftPO findByID(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	public int modify(GiftPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

}
