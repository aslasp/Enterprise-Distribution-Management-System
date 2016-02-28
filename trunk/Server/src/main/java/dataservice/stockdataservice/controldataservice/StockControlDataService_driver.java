package dataservice.stockdataservice.controldataservice;

import java.rmi.RemoteException;
import po.StockErrorPO;
import po.StockOverOrLowPO;

public class StockControlDataService_driver {

	public void drive(StockControlDataService stockErrorDataService)
			throws RemoteException {
		StockOverOrLowPO stockOverOrLowPO = new StockOverOrLowPO("飞利浦日光灯",
				"SR01", null, null, null, 100, 99, null, null, null, -1,
				0);
		StockErrorPO stockErrorPO = new StockErrorPO("飞利浦日光灯", "SR01", null, null);
		stockErrorDataService.addStockOverOrLow(stockOverOrLowPO);
		stockErrorDataService.addStockError(stockErrorPO);
	}

	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		StockControlDataService_stub stockErrorDataService = new StockControlDataService_stub();
		StockControlDataService_driver driver = new StockControlDataService_driver();
		driver.drive(stockErrorDataService);
	}

}
