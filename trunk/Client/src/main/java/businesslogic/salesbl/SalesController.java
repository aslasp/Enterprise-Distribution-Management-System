package businesslogic.salesbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import vo.SaleReturnVO;
import vo.SaleVO;
import businesslogic.utilitybl.getDate;
import businesslogicservice.salesblservice.PurchaseBLService;
import businesslogicservice.salesblservice.SalesBLService;
import businesslogicservice.salesblservice.viewInfoService;

public class SalesController implements SalesBLService,PurchaseBLService,viewInfoService{
	Sale sale;
	SaleReturn saleReturn;
	Purchase purchase;
	PurchaseReturn purchaseReturn;
	
	public SalesController() throws Exception{
		sale=new Sale();
		purchase=new Purchase();
		purchaseReturn=new PurchaseReturn();
		saleReturn=new SaleReturn();
	}
	
	public String getNewID(ReceiptType type){
		
			ArrayList<ReceiptPO> po;
			String id=null;
			String d=null;
			switch(type){
			case SALE:
				id="XSD-";d=sale.getNewID();break;
			case PURCHASE:
				id="JHD-";d=purchase.getNewID();break;
			case SALERETURN:
				id="XSTHD-";d=saleReturn.getNewID();break;
			default:
				id="JHTHD-";d=purchaseReturn.getNewID();			
			}	
			//获取当期日期变成String;
			String date=getDate.getdate()+"-";
			return id+date+d;	
	}
	
	public int addSale(SaleVO vo) {
		// TODO Auto-generated method stub
		return sale.Add(vo);
	}
	public int addSaleReturn(SaleReturnVO vo) {
		// TODO Auto-generated method stub
		return saleReturn.add(vo);
	}
	
	public int addPurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return purchase.AddPurchase(vo);
	}
	
	public int addPurchaseReturn(PurchaseReturnVO vo) {
		// TODO Auto-generated method stub
		return purchaseReturn.AddPurchaseReturn(vo);
	}
	
	public int modifySale(SaleVO vo) {
		// TODO Auto-generated method stub
		return sale.Modify(vo);
	}
	
	public int modifySaleReturn(SaleReturnVO vo) {
		// TODO Auto-generated method stub
		return saleReturn.Modify(vo);
	}

	public int modifyPurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return purchase.ModifyPurchase(vo);
	}
	
	public int modifyPurchaseReturn(PurchaseReturnVO vo) {
		// TODO Auto-generated method stub
		return purchaseReturn.ModifyPurchaseReturn(vo);
	}
	public ArrayList<PurchaseVO> showPurchase() {
		// TODO Auto-generated method stub
		return purchase.show();
	}

	public ArrayList<PurchaseReturnVO> showPurchaseReturn() {
		// TODO Auto-generated method stub
		return purchaseReturn.show();
	}

	public ArrayList<SaleVO> showSale() {
		// TODO Auto-generated method stub
		return sale.show();
	}

	public ArrayList<SaleReturnVO> showSaleReturn() {
		// TODO Auto-generated method stub
		return saleReturn.show();
	}

	public ArrayList<PurchaseVO> findPurchase(String message,String type) {
		// TODO Auto-generated method stub
		return purchase.find(message, type);
	}

	public ArrayList<PurchaseReturnVO> findPurchaseReturn(String message,String type) {
		// TODO Auto-generated method stub
		return purchaseReturn.find(message, type);
	}

	public ArrayList<SaleVO> findSale(String message,String type) {
		// TODO Auto-generated method stub
		return sale.find(message, type);
	}

	public ArrayList<SaleReturnVO> findSaleReturn(String message,String type) {
		// TODO Auto-generated method stub
		return saleReturn.find(message, type);
	}
	
	public PurchaseVO PFindByID(String id) {
		// TODO Auto-generated method stub
		return purchase.find(id);
	}
	
	
	public SaleVO SFindByID(String id) {
		// TODO Auto-generated method stub
		return sale.find(id);
	}

	public PurchaseReturnVO PRFindByID(String id) {
		// TODO Auto-generated method stub
		return purchaseReturn.find(id);
	}

	public SaleReturnVO SRFindByID(String id) {
		// TODO Auto-generated method stub
		return saleReturn.find(id);
	}
	

	public double getPrivilege(String MemberID)   {
		// TODO Auto-generated method stub
		double pre=0;
		try {
			 pre=sale.getPrivilege(MemberID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pre;
			
	}
	

}
