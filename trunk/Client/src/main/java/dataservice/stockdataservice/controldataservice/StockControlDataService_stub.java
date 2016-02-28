package dataservice.stockdataservice.controldataservice;

import java.util.ArrayList;

import po.GoodsPO;
import po.StockErrorPO;
import po.StockOverOrLowPO;

public class StockControlDataService_stub implements StockControlDataService {
	public int addStockOverOrLow(StockOverOrLowPO po) {
		// TODO 自动生成的方法存根
		System.out.println("add stockOverOrLow receipt in file succeed!");
		return 0;
	}

	public int addStockError(StockErrorPO po) {
		// TODO 自动生成的方法存根
		System.out.println("add stockError receipt in file succeed!");
		return 0;
	}

	public double goodsOverIncome() {
		// TODO 自动生成的方法存根
		System.out.println("return goods over income in file succeed!");
		return 0;
	}

	public double goodsLowCost() {
		// TODO 自动生成的方法存根
		System.out.println("return goods low cost in file succeed!");
		return 0;
	}

	public ArrayList<GoodsPO> showStock(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		System.out.println("show stock in file succeed!");
		return new ArrayList<GoodsPO>();
	}

	public ArrayList<GoodsPO> checkStock() {
		// TODO 自动生成的方法存根
		System.out.println("check stock in file succeed!");
		return new ArrayList<GoodsPO>();
	}

	public boolean isEnough(String goodsID, int num) {
		// TODO 自动生成的方法存根
		System.out.println("check stock is enough in file succeed!");
		return true;
	}

	public double PrimeCostIncome() {
		// TODO 自动生成的方法存根
		System.out.println("return prime cost income  in file succeed!");
		return 0;
	}

	public ArrayList<StockOverOrLowPO> getStockOverOrLowPO() {
		// TODO 自动生成的方法存根
		return null;
	}

	public int recordPrimeCostIncome(String primeCostIncome) {
		// TODO 自动生成的方法存根
		return 0;
	}

	public ArrayList<String> getPrimeCostIncome() {
		// TODO 自动生成的方法存根
		return null;
	}

	public String getMaxID() {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<StockErrorPO> getStockErrorPO() {
		// TODO 自动生成的方法存根
		return null;
	}

	public String getErrorMaxID() {
		// TODO 自动生成的方法存根
		return null;
	}

	public StockOverOrLowPO findByID(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

	public int getWarnnigNum() {
		// TODO 自动生成的方法存根
		return 0;
	}

	public int setWarningNum(int num) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int excute(StockOverOrLowPO po) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int modify(StockOverOrLowPO po) {
		// TODO 自动生成的方法存根
		return 0;
	}

}
