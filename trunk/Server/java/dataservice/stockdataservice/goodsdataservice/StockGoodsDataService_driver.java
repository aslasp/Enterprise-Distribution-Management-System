package dataservice.stockdataservice.goodsdataservice;

import java.rmi.RemoteException;

import po.GoodsPO;

public class StockGoodsDataService_driver {
	public void drive(StockGoodsDataService_stub stockGoodsDataService)
			throws RemoteException {
		GoodsPO goodsPO = new GoodsPO("00001", "飞利浦日光灯", "SR01", 10, 10, 100,
				150, 100, 150,null);
		stockGoodsDataService.addGoods(goodsPO);
		stockGoodsDataService.deleteGoods(goodsPO);
		stockGoodsDataService.modifyGoods(goodsPO);
		stockGoodsDataService.findGoods("00001");
		stockGoodsDataService.showStock("2014年10月1日", "2014年10月17日");

		stockGoodsDataService.checkStock();
	}

	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		StockGoodsDataService_stub stockGoodsDataService = new StockGoodsDataService_stub();
		StockGoodsDataService_driver driver = new StockGoodsDataService_driver();
		driver.drive(stockGoodsDataService);
	}

}
