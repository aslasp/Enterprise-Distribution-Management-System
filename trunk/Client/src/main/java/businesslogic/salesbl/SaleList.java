package businesslogic.salesbl;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PurchasePO;
import po.PurchaseReturnPO;
import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;
import po.SalePO;
import po.SaleReturnPO;
import dataservice.salesdataservice.SalesDataService;
import vo.ReceiptVO;
import businesslogic.promotionbl.giftCouponPro;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogic.utilitybl.getServer;
import businesslogicservice.salesblservice.SaleListBLService;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;

public class SaleList implements SaleListBLService{
	private SalesDataService service;

	public SaleList() throws Exception {
		String host=getServer.getServer();
		String url="rmi://"+host+"/salesService";
	
		service=(SalesDataService)Naming.lookup(url);
	
	}
	
	public double getSaleIncome(String startDate,String endDate){
		double profit=0;
		ArrayList<SalePO> spo1=service.findSale(startDate+endDate, "时间区间");
		ArrayList<SaleReturnPO> spo2=service.findSaleReturn(startDate+endDate, "时间区间");
		if(spo1!=null)
			for(int i=0;i<spo1.size();i++){
				profit+=spo1.get(i).getTotal()[2];
			}
		if(spo2!=null)
			for(int i=0;i<spo2.size();i++){
				profit-=spo2.get(i).getTotal()[2];
			}
		return profit;
	}
	
	//成本调价收入
	public double getAdjustCost(String startDate,String endDate){
		double adcost=0;
		ArrayList<PurchasePO> p=service.findPurchase(startDate+endDate, "时间区间");
		ArrayList<PurchaseReturnPO> pr=service.findPurchaseReturn(startDate+endDate, "时间区间");
		//剔除有退货的进货单据
		if(p!=null&&pr!=null){
		for(int i=0;i<p.size();i++)
			for(int j=0;j<pr.size();j++)
				if(p.get(i).getId().equals(pr.get(j).getpurid()))
				{
					p.remove(i);pr.remove(j);
				}
		for(int i=0;i<p.size();i++)
			adcost+=p.get(i).getAdjustCost();
		}
		return adcost;
		
	}
	
	public double getSaleCost(String startDate,String endDate){
		double profit=0;
		ArrayList<SalePO> spo1=service.findSale(startDate+endDate, "时间区间");
		ArrayList<SaleReturnPO> spo2=service.findSaleReturn(startDate+endDate, "时间区间");
		if(spo1!=null)
			for(int i=0;i<spo1.size();i++){
				profit+=spo1.get(i).getTotal()[0];
			}
		if(spo2!=null)
			for(int i=0;i<spo2.size();i++){
				profit-=spo2.get(i).getTotal()[0];
			}
		return profit;
	}
	
	
	
	public double getCouponProfitCalc(String startDate,String endDate){
		//计算代金券与实际收款差额收入
		double profit=0;
		ArrayList<SalePO> spo=service.findSale(startDate+endDate, "时间区间");
		if(spo!=null)
		for(int i=0;i<spo.size();i++)
			profit+=spo.get(i).getCouponPrice();
		
		return profit;
	}
	
	public double getPurchaseReturnProfitCalc(String startDate,String endDate) {
		// 计算商品进货退货差价收入  退货钱更多？
		double prprofit=0;
		ArrayList<PurchaseReturnPO> ppo=service.findPurchaseReturn(startDate+endDate, "时间区间");
		
		if(ppo!=null)
		for(int i=0;i<ppo.size();i++)
			{PurchasePO po=(PurchasePO)service.findReceiptByID(ppo.get(i).getpurid());
			if(po!=null)
				prprofit+=(ppo.get(i).getTotalInAll()-po.getTotalInAll());
			}
		return prprofit;
	}
	

	
	public double getDiscountMoney(String startDate,String endDate){
		double profit=0;
		ArrayList<SalePO> spo1=service.findSale(startDate+endDate, "时间区间");
		if(spo1!=null)
			for(int i=0;i<spo1.size();i++){
				profit+=(spo1.get(i).getTotal()[1]-spo1.get(i).getTotal()[2]);
			}
		
		return profit;
	}
	
	public double getGiftCouponUseCost(String startDate,String endDate){
		giftCouponPro gcp=null;
		try {
			gcp=new giftCouponPro();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gcp.getCouponCost(startDate, endDate);
	}

	
	
	public ArrayList<ReceiptVO> getAllPurchase() {
		
		ArrayList<ReceiptPO> po=service.getAllPurchase();
		
		ArrayList<ReceiptVO> vo=new ArrayList<ReceiptVO>();
		if(po==null) return null;
		else{
			for(int i=0;i<po.size();i++){
				ReceiptPO p=po.get(i);
				ReceiptVO v;
				if(p.getType()==ReceiptType.PURCHASE)
				{	PurchasePO pp=(PurchasePO)p;
						v=Purchase.poToVo(pp);}
				else{
					PurchaseReturnPO pp=(PurchaseReturnPO)p;
					 v=PurchaseReturn.poToVo(pp);}
				vo.add(v);
			}
			
			return vo;
		// TODO Auto-generated method stub
		}
	}

	public ArrayList<ReceiptVO> getAllSale() {
		// TODO Auto-generated method stub
		ArrayList<ReceiptPO> po=service.getAllSale();
		ArrayList<ReceiptVO> vo=new ArrayList<ReceiptVO>();
		if(po==null) return null;
		else{
			for(int i=0;i<po.size();i++){
				ReceiptPO p=po.get(i);
				ReceiptVO v;
				if(p.getType()==ReceiptType.SALE)
				{	SalePO pp=(SalePO)p;
						v=Sale.poToVo(pp);}
				else{
					SaleReturnPO pp=(SaleReturnPO)p;
					 v=SaleReturn.poToVo(pp);}
				vo.add(v);
			}
			
			return vo;
		// TODO Auto-generated method stub
		}
	
	}

/*public ReceiptVO findReceiptByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		ReceiptPO p=service.findReceiptByID(ID);
		if(p==null)return null;
		else{
			ReceiptVO v;
			if(p.getType()==ReceiptType.SALE)
			{SalePO po=(SalePO)p;
			v=Sale.poToVo(po);
			}else if(p.getType()==ReceiptType.PURCHASE){
				PurchasePO po=(PurchasePO)p;
				v=Purchase.poToVo(po);
			}else if(p.getType()==ReceiptType.SALERETURN){
				SaleReturnPO po=(SaleReturnPO)p;
				v=SaleReturn.poToVo(po);
			}else{
				PurchaseReturnPO po=(PurchaseReturnPO)p;
				v=PurchaseReturn.poToVo(po);
			}
			return v;
		}
		
		
	}*/

	

	
}
