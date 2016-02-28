package businesslogicservice.salesblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.ReceiptVO;

public interface SaleListBLService {
	
	public ArrayList<ReceiptVO> getAllPurchase() ;
	public ArrayList<ReceiptVO> getAllSale() ;
	//public ReceiptVO findReceiptByID(String ID) throws RemoteException;
	public double getSaleIncome(String startDate,String endDate);//销售收入
	public double getSaleCost(String startDate,String endDate);//销售成本
	public double getAdjustCost(String startDate,String endDate);//成本调价收入
	public double getPurchaseReturnProfitCalc(String startDate,String endDate);//进货退货差价
	public double getCouponProfitCalc(String startDate,String endDate);//代金券与实际收款差额收入
	public double getDiscountMoney(String startDate,String endDate);//折让金额
	public double getGiftCouponUseCost(String startDate,String endDate);//代金券使用支出
	//public double AllIncome(String startDate,String endDate);//总收入
	//public double AllCost(String startDate,String endDate);//总支出
	//public double allProfit(String startDate,String endDate);//利润
}
