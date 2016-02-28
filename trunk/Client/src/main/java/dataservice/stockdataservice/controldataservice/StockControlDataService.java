package dataservice.stockdataservice.controldataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.StockErrorPO;
import po.StockOverOrLowPO;

public interface StockControlDataService extends Remote {

	public int addStockOverOrLow(StockOverOrLowPO po);

	public int addStockError(StockErrorPO po);

	// 获得所有库存报溢报损单
	public ArrayList<StockOverOrLowPO> getStockOverOrLowPO();

	// 获得所有库存报警单
	public ArrayList<StockErrorPO> getStockErrorPO();

	public String getMaxID();

	public String getErrorMaxID();

	public StockOverOrLowPO findByID(String id);

	public int excute(StockOverOrLowPO po);

	public int modify(StockOverOrLowPO po);
}
