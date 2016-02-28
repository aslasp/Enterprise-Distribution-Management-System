package dataservice.stockdataservice.giftdataservice;

import java.rmi.RemoteException;

import po.GiftPO;
import po.GoodsPO;
import po.MemberPO;


public class GiftDataService_driver {
	public void drive(GiftDataService_stub giftDataService_stub) {
		GoodsPO goodsPO = new GoodsPO("00001", "飞利浦日光灯", "SR01", 10,100,100, 150,
				100, 150,null);
		MemberPO memberPO = new MemberPO(null, null, null, null, null, null, null, null, null, 0, 0, 0);
		GiftPO po =new GiftPO(memberPO,goodsPO,1);
	
		try {
			giftDataService_stub.addGift(po);
			giftDataService_stub.dealGift(po);
			giftDataService_stub.getAccount(po);
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
