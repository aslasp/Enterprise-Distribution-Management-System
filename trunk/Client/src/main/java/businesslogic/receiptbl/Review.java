package businesslogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionPO;
import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;
import dataservice.receiptdataservice.ReceiptDataService;
import businesslogic.financebl.CashList;
import businesslogic.financebl.Collection;
import businesslogic.financebl.MockAccount;
import businesslogic.financebl.MockCashList;
import businesslogic.financebl.Payment;
import businesslogic.salesbl.Purchase;
import businesslogic.salesbl.PurchaseReturn;
import businesslogic.salesbl.Sale;
import businesslogic.salesbl.SaleReturn;
import businesslogic.stockbl.gift.GiftReceipt;
import businesslogic.stockbl.stockManage.StockErrorReceipt;
import businesslogic.stockbl.stockManage.StockLowReceipt;
import businesslogic.stockbl.stockManage.StockOverReceipt;
import businesslogic.utilitybl.getServer;
import vo.ReceiptVO;
import vo.SaleVO;
//Reply 和Send 使用观察者模式，Reply之后单据类自己做善后处理
public class Review {
	ReceiptDataService service;
	public Review() throws Exception{
		String host=getServer.getServer();
		String url="rmi://"+host+"/receiptService";
	
		service=(ReceiptDataService)Naming.lookup(url);
	}
	

	

	public ReceiptPO View(String id){
		return service.findById(id);
		
	
	}
	
	
	//除报警单外执行外  其他七种需审批， 自动库存赠送单需执行
	public  int Excute(ReceiptVO vo,int tag) throws Exception{
		Receipt receipt=null;int i=0;
		receipt=getCorrectInstance(vo.getType());
		int result=0;
		if(vo.getType()==ReceiptType.SALE){
			SaleVO saleReceipt=(SaleVO)vo;
			boolean RedORNormal=tag==0||(tag==1&&saleReceipt.getCouponid().equals(""));
			if(!RedORNormal)
			{	Sale sale=(Sale)receipt;
				result=sale.excute(saleReceipt,false);				
			}
			else
				result=receipt.excute(vo);	//正常执行   部分单据的红冲执行需要分开 仅出现在销售
		}else
			 result=receipt.excute(vo);	
			 receipt.Reply(vo.getId(), vo.getType(), result);
	
		return result;//0成功  1不成功  
	}
	
	
	//每个单据都可被红冲 每个单据继承父类的 红冲方法  除库存报警
	public ReceiptPO Red(ReceiptPO po){
		Receipt receipt=null;
		try {
			receipt=getCorrectInstance(po.getType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		po=receipt.getRedReceipt(po);
		return po;
		
	}
	
	
	public Receipt getCorrectInstance(ReceiptType type) throws Exception{
		Receipt receipt=null;
		switch(type){
		case PURCHASE:
			receipt=new Purchase();break;
		case SALE:
			receipt=new Sale();break;
		case PURCHASERETURN:
			receipt=new PurchaseReturn();break;
		case SALERETURN:
			receipt=new SaleReturn();break;
		case CASHLIST:
			receipt=new CashList();break;
		case COLLECTION:
			receipt=new Collection();break;
		case GIFT:
			receipt=new GiftReceipt();break;
		case PAYMENT:
			receipt=new Payment();break;
		case STOCKOVER:
			receipt=new StockOverReceipt();break;
		case STOCKLOW:
			receipt=new StockLowReceipt();break;
		
	}
		
		return receipt;
	}

	

	public int Approve(String id,int status){
	//	int i=service.Approve(id,status);
		int result=0;
		if(status==1){
			service.setStatus(id, status);
			return 0;
		}
		else{
		
			ReceiptVO vo=ReceiptController.poToVo(service.findById(id));
			try {
				 result=Excute(vo,0);
				 if(result==0){
					 service.setStatus(id, status);
				 }else{
					 service.setStatus(id, 1);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return result;
	}
	
}
