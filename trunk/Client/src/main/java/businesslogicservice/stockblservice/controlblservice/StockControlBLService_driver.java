package businesslogicservice.stockblservice.controlblservice;

import vo.StockErrorVO;
import vo.StockOverOrLowVO;

public class StockControlBLService_driver {
	public void drive(StockControlBLService_stub stockControlBLService) {
		StockOverOrLowVO stockOverOrLowVO = new StockOverOrLowVO(null, null,
				null, 0, 0, null, null, null, 0, 0);

		StockErrorVO stockErrorVO = new StockErrorVO("", "", "", "", "");
		stockControlBLService.addStockOverOrLow(stockOverOrLowVO);
		stockControlBLService.addStockError(stockErrorVO);
		stockControlBLService.getGoodsOverIncome("", "");
		stockControlBLService.getGoodsLowCost("", "");
		stockControlBLService.showStock("", "");
		stockControlBLService.checkStock();
		stockControlBLService.isEnough("00001", 10);
	}

	public static void main(String[] args) {
		StockControlBLService_stub stockControlBLService = new StockControlBLService_stub();
		StockControlBLService_driver driver = new StockControlBLService_driver();
		driver.drive(stockControlBLService);
	}
}
