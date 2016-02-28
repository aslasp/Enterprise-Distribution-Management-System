package dataservice.stockdataservice.controldataservice;

import java.rmi.RemoteException;

import po.StockErrorPO;
import po.StockOverOrLowPO;

public class StockControlDataService_driver {

	public void drive(StockControlDataService stockControlDataService)
			throws RemoteException {
		StockOverOrLowPO stockOverOrLowPO = new StockOverOrLowPO("飞利浦日光灯",
				"SR01", null, null, null, 100, 99, null, null, null, 0, 0);
		StockErrorPO stockErrorPO = new StockErrorPO("飞利浦日光灯", "SR01", null, null);
		stockControlDataService.addStockOverOrLow(stockOverOrLowPO);
		stockControlDataService.addStockError(stockErrorPO);
	}

	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		StockControlDataService_stub stockControlDataService = new StockControlDataService_stub();
		StockControlDataService_driver driver = new StockControlDataService_driver();
		driver.drive(stockControlDataService);
	}

}
