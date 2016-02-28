package Data.stockdata.stockManage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import po.StockErrorPO;
import po.StockOverOrLowPO;
import Data.serutility.JXCFile;
import dataservice.stockdataservice.controldataservice.StockControlDataService;

public class StockControl extends UnicastRemoteObject implements
		StockControlDataService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile overOrLowFile;
	JXCFile errorFile;
	JXCFile file;// 记录库存调价收入

	public StockControl() throws RemoteException {
		overOrLowFile = new JXCFile("overOrLowReceipt.ser");
		errorFile = new JXCFile("errorReceipt.ser");
		file = new JXCFile("primeChangeRecord.ser");
	}

	public int addStockOverOrLow(StockOverOrLowPO po) throws RemoteException {
		overOrLowFile = new JXCFile("overOrLowReceipt.ser");
		overOrLowFile.write(po);
		return 0;
	}

	public int addStockError(StockErrorPO po) throws RemoteException {
		errorFile = new JXCFile("errorReceipt.ser");
		errorFile.write(po);
		return 0;
	}

	public ArrayList<StockOverOrLowPO> getStockOverOrLowPO()
			throws RemoteException {
		overOrLowFile = new JXCFile("overOrLowReceipt.ser");
		ArrayList<StockOverOrLowPO> result = new ArrayList<StockOverOrLowPO>();
		ArrayList<Object> list = overOrLowFile.read();

		if (list != null) {
			for (Object po : list) {
				result.add((StockOverOrLowPO) po);
			}
		}
		return result;
	}

	public ArrayList<StockErrorPO> getStockErrorPO() throws RemoteException {
		errorFile = new JXCFile("errorReceipt.ser");
		ArrayList<StockErrorPO> result = new ArrayList<StockErrorPO>();
		ArrayList<Object> list = errorFile.read();

		if (list != null) {
			for (Object po : list) {
				result.add((StockErrorPO) po);
			}
		}
		return result;
	}

//	public int recordPrimeCostIncome(String primeCostIncome)
//			throws RemoteException {
//		file = new JXCFile("primeChangeRecord.ser");
//		ArrayList<Object> originRecord = file.read();
//
//		String[] newData = primeCostIncome.split(";");
//		String date = newData[0];
//		double money = 0;
//		money = Double.parseDouble(newData[1]);
//		ArrayList<String> record = new ArrayList<String>();
//
//		if (originRecord != null) {
//			@SuppressWarnings("unchecked")
//			ArrayList<Object> preRecordObject = (ArrayList<Object>) originRecord
//					.get(0);
//
//			if (preRecordObject != null) {
//				for (int i = 0; i < preRecordObject.size(); i++) {
//					Object o = preRecordObject.get(i);
//					record.add((String) o);
//				}
//
//				int index = -1;
//				for (int i = 0; i < record.size(); i++) {
//					String preRecord = record.get(i);
//					String data[] = preRecord.split(";");
//					if (date.equals(data[1])) {
//						money += Double.parseDouble(data[1]);
//						index = i;
//						break;
//					}
//				}
//
//				String newRecord = date + ";" + String.valueOf(money);
//
//				if (index == -1) {
//					record.add(newRecord);
//				} else {
//					record.set(index, newRecord);
//				}
//			} else {
//				String newRecord = date + ";" + String.valueOf(money);
//				record.add(newRecord);
//			}
//
//		} else {
//			String newRecord = date + ";" + String.valueOf(money);
//			record.add(newRecord);
//		}
//		file.writeM(record);
//		return 0;
//	}

	public ArrayList<String> getPrimeCostIncome() throws RemoteException {
		file = new JXCFile("primeChangeRecord.ser");
		ArrayList<Object> preRecordObject = new ArrayList<Object>();
		preRecordObject = file.read();
		ArrayList<String> record = new ArrayList<String>();
		for (Object o : preRecordObject) {
			record.add((String) o);
		}

		return record;
	}

	public String getMaxID() throws RemoteException {
		overOrLowFile = new JXCFile("overOrLowReceipt.ser");
		errorFile = new JXCFile("errorReceipt.ser");
		file = new JXCFile("primeChangeRecord.ser");
		String result = "";
		ArrayList<StockOverOrLowPO> list = getStockOverOrLowPO();

		if (list.size() == 0) {
			return null;
		} else {
			String today = getDate();
			int index = -1;
			for (int i = list.size() - 1; i >= 0; i--) {
				String tpID = list.get(i).getId();
				String temID[] = tpID.split("-");
				if (today.equals(temID[1])) {
					index = i;
					break;
				}
			}
			if (index == -1) {
				return null;
			} else {
				String id = list.get(index).getId();
				String tempID[] = id.split("-");
				result = tempID[2];
				for (int i = 0; i < list.size(); i++) {

					String tpID = list.get(i).getId();
					String temID[] = tpID.split("-");

					if (today.equals(temID[1])
							&& result.compareTo(temID[2]) < 0) {
						result = temID[2];
					}

				}
				return result;
			}
		}
	}

	public String getErrorMaxID() throws RemoteException {
		overOrLowFile = new JXCFile("overOrLowReceipt.ser");
		errorFile = new JXCFile("errorReceipt.ser");
		file = new JXCFile("primeChangeRecord.ser");
		String result = "";
		ArrayList<StockErrorPO> list = getStockErrorPO();

		if (list.size() == 0) {
			return null;
		} else {
			String today = getDate();
			int index = -1;
			for (int i = list.size() - 1; i >= 0; i--) {
				String tpID = list.get(i).getId();
				String temID[] = tpID.split("-");
				if (today.equals(temID[1])) {
					index = i;
					break;
				}
			}
			if (index == -1) {
				return null;
			} else {
				String id = list.get(index).getId();
				String tempID[] = id.split("-");
				result = tempID[2];
				for (int i = 0; i < list.size(); i++) {

					String tpID = list.get(i).getId();
					String temID[] = tpID.split("-");

					if (today.equals(temID[1])
							&& result.compareTo(temID[2]) < 0) {
						result = temID[2];
					}

				}
				return result;
			}
		}
	}

	private String getDate() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String sysDatetime = fmt.format(rightNow.getTime());

		return sysDatetime;
	}

	public StockOverOrLowPO findByID(String id) throws RemoteException {
		overOrLowFile = new JXCFile("overOrLowReceipt.ser");
		errorFile = new JXCFile("errorReceipt.ser");
		file = new JXCFile("primeChangeRecord.ser");
		StockOverOrLowPO po = null;
		ArrayList<StockOverOrLowPO> list = getStockOverOrLowPO();
		if (list != null) {
			for (StockOverOrLowPO p : list) {
				if (p.getId().equals(id)) {
					po = p;
					break;
				}
			}
		}
		return po;
	}

	public int excute(StockOverOrLowPO po) throws RemoteException {
		overOrLowFile = new JXCFile("overOrLowReceipt.ser");
		errorFile = new JXCFile("errorReceipt.ser");
		file = new JXCFile("primeChangeRecord.ser");
		ArrayList<Object> list = new ArrayList<Object>();
		list = overOrLowFile.read();
		for (int i = 0; i < list.size(); i++) {
			StockOverOrLowPO stockOverOrLowPO = (StockOverOrLowPO) list.get(i);
			if (stockOverOrLowPO.getId().equals(po.getId())) {
				po.setStatus(3);
				list.set(i, po);
				break;
			}
		}

		overOrLowFile.writeM(list);

		return 0;
	}

	public int modify(StockOverOrLowPO po) throws RemoteException {
		overOrLowFile = new JXCFile("overOrLowReceipt.ser");
		ArrayList<Object> list = new ArrayList<Object>();
		list = overOrLowFile.read();
		for (int i = 0; i < list.size(); i++) {
			StockOverOrLowPO stockOverOrLowPO = (StockOverOrLowPO) list.get(i);
			if (stockOverOrLowPO.getId().equals(po.getId())) {
				list.set(i, po);
				break;
			}
		}
		overOrLowFile.writeM(list);
		return 0;
	}
}