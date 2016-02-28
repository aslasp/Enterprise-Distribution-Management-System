package dataservice.stockdataservice.goodsclassdataservice;

import java.rmi.RemoteException;

import po.GoodsClassPO;

public class StockGoodsClassDataService_driver {
	public void drive(StockGoodsClassDataService_stub stockGoodsClassDataService)
			throws RemoteException {
		GoodsClassPO goodsClassPO = new GoodsClassPO("","日光灯", "");
		stockGoodsClassDataService.addGoodsClass(goodsClassPO);
		stockGoodsClassDataService.deleteGoodsClass(goodsClassPO);
		stockGoodsClassDataService.modifyGoodsClass(goodsClassPO);
	}

	public static void main(String[] args) throws RemoteException {
		StockGoodsClassDataService_stub stockGoodsClassDataService = new StockGoodsClassDataService_stub();
		StockGoodsClassDataService_driver driver = new StockGoodsClassDataService_driver();
		driver.drive(stockGoodsClassDataService);

	}
}
