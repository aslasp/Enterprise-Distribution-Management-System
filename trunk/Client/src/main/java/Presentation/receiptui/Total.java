package Presentation.receiptui;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;
import vo.CashlistVO;
import vo.CollectionVO;
import vo.CommodityVO;
import vo.GiftVO;
import vo.PaymentVO;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import vo.ReceiptVO;
import vo.SaleReturnVO;
import vo.SaleVO;

public class Total {
	
	public static String getTotal(ReceiptVO vo){
		switch(vo.getType()){
		case SALE:
			SaleVO v=(SaleVO)vo;return v.getTotalValue()+"元";
		case SALERETURN:
			return ((SaleReturnVO)vo).getTotal()[2]+"元";
		case PURCHASE:
			return ((PurchaseVO)vo).getTotalInAll()+"元";
		case PURCHASERETURN:
			return ((PurchaseReturnVO)vo).getTotalInAll()+"元";
		case CASHLIST:
			return ((CashlistVO)vo).getTotalMoney()+"元";
		case PAYMENT:
			return ((PaymentVO)vo).getTotalMoney()+"元";
		case GIFT:
			return cal(((GiftVO)vo).getGiftList())+"元";
		case COLLECTION:
			return ((CollectionVO)vo).getTotalMoney()+"元";
		default:
				
			return "";
			
			
		}
		
	}
	
	public static String getType(ReceiptType type){
		switch(type){
		case SALE:
			return "销售单";
		case SALERETURN:
			return "销售退货单";
		case PURCHASE:
			return "进货单";
		case PURCHASERETURN:
			return "进货退货单";
		case CASHLIST:
			return "现金费用单";
		case PAYMENT:
			return "付款单";
		case GIFT:
			return "库存赠送单";
		case COLLECTION:
			return "收款单";
		case STOCKOVER:
			return "库存报溢单";
		default:
			return "库存报损单";
			
			
		}
		
	}
	
	
	public static ReceiptType getsType(String type){
		switch(type){
		case "销售单":
			return ReceiptType.SALE;
		case "销售退货单":
			return ReceiptType.SALERETURN;
		case "进货单":
			return ReceiptType.PURCHASE;
		case "进货退货单":
			return ReceiptType.PURCHASERETURN;
		case "现金费用单":
			return ReceiptType.CASHLIST;
		case "付款单":
			return ReceiptType.PAYMENT;
		case "库存赠送单":
			return ReceiptType.GIFT;
		case "收款单":
			return ReceiptType.COLLECTION;
		case "库存报溢单":
			return ReceiptType.STOCKOVER;
		case "库存报损单":
			return ReceiptType.STOCKLOW;
		default:
			return ReceiptType.STOCKERROR;
			
			
		}
		
	}
	
	
	public static String getStatus(int i){
		if(i==0)
			return "待审批";
		else if(i==1)
			return "审批不通过";
		else if(i==2)
			return "审批通过";
		else return "执行完毕";
	}
	
	
	
	
	public static double cal(ArrayList<CommodityVO> v){
		double t=0;
		for(int i=0;i<v.size();i++){
			t+=v.get(i).getTotal();		
		}
		return t;
	}
	
	
	public static String getFailReason(ReceiptType type,int i){
		String  reason="";
		switch(type){
		case SALE:
		   if(i==1) reason="超过客户应收额度,";
		   else if(i==2) reason="库存不足，无法出库,";
		   break;
		case GIFT:
			if(i==1) reason="库存不足,无法赠送,";
			break;
		case COLLECTION:
			if(i==2) reason="客户应付小于0,";
			else if(i==3)  reason="账户余额小于0,";
		    break;
		case PAYMENT:
			if(i==2) reason="客户应收小于0,";
			else if(i==3)  reason="账户余额小于0,";
		    break;
		case CASHLIST:
			if(i==3)  reason="账户余额小于0,";break;
		case PURCHASERETURN:
			if(i==1)  reason="库存不足,无法退货,";break;
		}
		
		return reason;
	}

}
