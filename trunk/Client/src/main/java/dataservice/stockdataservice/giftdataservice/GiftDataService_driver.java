package dataservice.stockdataservice.giftdataservice;

import java.rmi.RemoteException;

import po.GiftPO;


public class GiftDataService_driver {
	public void drive(GiftDataService_stub giftDataService_stub) {
		GiftPO po =new GiftPO();
	
		try {
			giftDataService_stub.addGift(po);
			giftDataService_stub.dealGift(po);
			giftDataService_stub.getAccount(po);
			giftDataService_stub.giftCost();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		GiftDataService_stub giftDataService = new GiftDataService_stub();
		GiftDataService_driver driver = new GiftDataService_driver();
		driver.drive(giftDataService);
	}

}
