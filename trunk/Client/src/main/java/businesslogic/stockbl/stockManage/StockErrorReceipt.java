package businesslogic.stockbl.stockManage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import po.StockErrorPO;
import vo.ReceiptMessageVO;
import businesslogic.receiptbl.ReceiptMessage;
import businesslogic.utilitybl.getDate;
import businesslogic.utilitybl.getServer;
import dataservice.stockdataservice.controldataservice.StockControlDataService;

public class StockErrorReceipt {
	private String goodName;
	private String size;
	private String date;
	private StockControlDataService service;
	private String host;
	private String url;

	public StockErrorReceipt() {
		try {
			host = getServer.getServer();
			url = "rmi://" + host + "/stockManageService";
			service = (StockControlDataService) Naming.lookup(url);
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public StockErrorReceipt(String goodName, String size) {
		try {
			host = getServer.getServer();
			url = "rmi://" + host + "/stockManageService";
			service = (StockControlDataService) Naming.lookup(url);
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		this.goodName = goodName;
		this.size = size;
		this.date = getDate();
	}

	public int add() {
		// 生成编号
		String exactID = "KCBJD-" + getDate() + "-";
		String maxID = service.getMaxID();

		if (maxID == null) {
			exactID += "00001";
		} else {
			NumberFormat nf = new DecimalFormat("00000");
			int tp = Integer.parseInt(maxID);
			exactID += nf.format(tp + 1);
		}
		StockErrorPO po = new StockErrorPO(exactID, goodName, size, date);
		Send();
		return service.addStockError(po);
	}

	// 发送库存报警信息给库存人员
	public void Send() {
		ReceiptMessage message;
		try {
			message = new ReceiptMessage();
			message.addMessage(new ReceiptMessageVO(3, getDate.getAllDate()
					+ ":" + goodName + "-" + size + "库存报警！"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getDate() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String sysDatetime = fmt.format(rightNow.getTime());

		return sysDatetime;
	}
}
