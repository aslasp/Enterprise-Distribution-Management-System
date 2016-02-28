package Data.salesdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;

import Data.datafactory.SequenceOfReceiptPO;
import Data.serutility.JXCFile;
import po.CommodityPO;
import po.PurchasePO;
import po.PurchaseReturnPO;
import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;
import po.SalePO;
import po.SaleReturnPO;

import dataservice.salesdataservice.SalesDataService;

public class Sales extends UnicastRemoteObject implements SalesDataService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;
	public Sales() throws RemoteException{
		super();
	}
	
	public int setStatus(String id,int st){
		String[] buffer=id.split("-");
        String s=buffer[0];
        if(s.equals("JHD")){
        	file=new JXCFile("purchase.ser");
        }
        else if(s.equals("JHTHD")){
        	file=new JXCFile("purchasereturn.ser");
        }
        else if(s.equals("XSD")){
        	file=new JXCFile("sale.ser");
        }
        else{
        	file=new JXCFile("salereturn.ser");
        }
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	 
		int i;
		for(i=0;i<a.size();i++){
			ReceiptPO b=(ReceiptPO)a.get(i);
			if(b.getId().equals(id)){
				b.setStatus(st);;
				break;
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.writeM(a);
		return 0;
	}
	
	public int modify(ReceiptPO po){
		if(po.getType()==ReceiptType.PURCHASE){
        	file=new JXCFile("purchase.ser");
        }
        else if(po.getType()==ReceiptType.PURCHASERETURN){
        	file=new JXCFile("purchasereturn.ser");
        }
        else if(po.getType()==ReceiptType.SALE){
        	file=new JXCFile("sale.ser");
        }
        else{
        	file=new JXCFile("salereturn.ser");
        }
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	 
		int i;
		for(i=0;i<a.size();i++){
			ReceiptPO b=(ReceiptPO)a.get(i);
			if(b.getId().equals(po.getId())){
				b.setInfo(po.getInfo());
				break;
			}
		}
		
		if(i==a.size())      //不存在该用户
			return 1;
		
		file.writeM(a);
		return 0;
	}
	
	
	public int createPurchase(PurchasePO po) throws RemoteException{
		file=new JXCFile("purchase.ser");
		file.write(po);
		return 0;
	}
	
	public ArrayList<PurchasePO> showPurchase() throws RemoteException{
		file=new JXCFile("purchase.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<PurchasePO> buffer=new ArrayList<PurchasePO>();
		for(Object b:a){
			PurchasePO po=(PurchasePO)b;
			buffer.add(po);
		}
		
		return buffer;
	}

	

	public int createPurchaseReturn(PurchaseReturnPO po) throws RemoteException{
		file=new JXCFile("purchasereturn.ser");
		file.write(po);
		return 0;
	}
	
	public ArrayList<PurchaseReturnPO> showPurchaseReturn()throws RemoteException{
		file=new JXCFile("purchasereturn.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<PurchaseReturnPO> buffer=new ArrayList<PurchaseReturnPO>();
		for(Object b:a){
			PurchaseReturnPO po=(PurchaseReturnPO)b;
			buffer.add(po);
		}
		
		return buffer;
	}

	public int createSale(SalePO po) throws RemoteException{
		file=new JXCFile("sale.ser");
		file.write(po);
		return 0;
	}
	
	public ArrayList<SalePO> showSale() throws RemoteException{
		file=new JXCFile("sale.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<SalePO> buffer=new ArrayList<SalePO>();
		for(Object b:a){
			SalePO po=(SalePO)b;
			buffer.add(po);
		}
		
		return buffer;
	}

	public int createSaleReturn(SaleReturnPO po) throws RemoteException{
		file=new JXCFile("salereturn.ser");
		file.write(po);
		return 0;
	}
	
	public ArrayList<SaleReturnPO> showSaleReturn() throws RemoteException{
		file=new JXCFile("salereturn.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<SaleReturnPO> buffer=new ArrayList<SaleReturnPO>();
		for(Object b:a){
			SaleReturnPO po=(SaleReturnPO)b;
			buffer.add(po);
		}
		
		return buffer;
	}
	
	public int updatePurchase(PurchasePO po) throws RemoteException{
		file=new JXCFile("purchase.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		boolean tag=false;
		for(i=0;i<a.size();i++){
			PurchasePO p=(PurchasePO)a.get(i);
			if(p.getId().equals(po.getId())){
				tag=true;
				p.setInfo(po.getInfo());
				p.setPurchaseList(po.getPurchaseList());
				p.setStatus(po.getStatus());
				p.setTotalInAll(po.getTotalInAll());
				p.setAdjustCost(po.getAdjustCost());
				break;
			}
		}
		if(!tag)      //不存在该收款单
			return 1;
		
		file.writeM(a);
		return 0;
	}
	public int updatePurchaseReturn(PurchaseReturnPO po) throws RemoteException {
		file=new JXCFile("purchasereturn.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		boolean tag=false;
		for(i=0;i<a.size();i++){
			PurchaseReturnPO p=(PurchaseReturnPO)a.get(i);
			if(p.getId().equals(po.getId())){
				tag=true;
				p.setHurry(po.getHurry());
				p.setInfo(po.getInfo());
				p.setStatus(po.getStatus());
				p.setTotalInAll(po.getTotalInAll());
				break;
			}
		}
		if(!tag)      //不存在该收款单
			return 1;
		
		file.writeM(a);
		return 0;
	}
	public int updateSale(SalePO po) throws RemoteException {
		file=new JXCFile("sale.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		boolean tag=false;
		for(i=0;i<a.size();i++){
			SalePO p=(SalePO)a.get(i);
			if(p.getId().equals(po.getId())){
				tag=true;
				p.setHurry(po.getHurry());
				p.setInfo(po.getInfo());
				p.setCost(po.getCost());
				p.setCouponPrice(po.getCouponPrice());
				p.setDiscount(po.getDiscountValue());
				p.setMoneyDiscount(po.getMoneyDiscount());
				p.setSalesList(po.getSalesList());
				p.setToPay(po.getToPay());
				p.setStatus(po.getStatus());
				p.setTotal(po.getTotalValue());
				p.setTotalOrigin(po.getTotalOrigin());
				break;
			}
		}
		if(!tag)      //不存在该收款单
			return 1;
		
		file.writeM(a);
		return 0;
	}
	public int updateSaleReturn(SaleReturnPO po) throws RemoteException {
		file=new JXCFile("salereturn.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return 1;  	  //不存在该用户	
		int i;
		boolean tag=false;
		for(i=0;i<a.size();i++){
			SaleReturnPO p=(SaleReturnPO)a.get(i);
			if(p.getId().equals(po.getId())){
				tag=true;
				p.setHurry(po.getHurry());
				p.setInfo(po.getInfo());
				p.setCost(po.getCost());
				p.setCouponPrice(po.getCouponPrice());
				p.setDiscount(po.getDiscountValue());
				p.setMoneyDiscount(po.getMoneyDiscount());
				p.setSalesreturnList(po.getSalesreturnList());
				p.setToPay(po.getToPay());
				p.setStatus(po.getStatus());
				p.setTotal(po.getTotalValue());
				p.setTotalOrigin(po.getTotalOrigin());
				break;
			}
		}
		if(!tag)      //不存在该收款单
			return 1;
		
		file.writeM(a);
		return 0;
	}
	
	public ArrayList<PurchasePO> findPurchase(String message,String type) throws RemoteException {
		ArrayList<PurchasePO> po=showPurchase();
		ArrayList<PurchasePO> result=new ArrayList<PurchasePO>();
		if(po==null)
			return null;
		if(type.equals("时间区间")){
			String start=message.substring(0,8);
			String end=message.substring(8,16);
			for(PurchasePO p:po){
				if(start.compareTo(p.myGetDate())<=0&&p.myGetDate().compareTo(end)<=0)
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("商品名")){
			for(PurchasePO p:po){
				ArrayList<CommodityPO> c=p.getPurchaseList();
				  for(CommodityPO cp:c){
					  if(cp.getName().contains(message)){
					      result.add(p);
					      break;
					  }
				  }
					
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("客户")){
			for(PurchasePO p:po){
				if(p.getMemberName().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("仓库")){
			for(PurchasePO p:po){
				if(p.getStockID().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else{
			return null;
		}
		return result;
	}
	
	public ArrayList<PurchaseReturnPO> findPurchaseReturn(String message,String type) throws RemoteException {
		ArrayList<PurchaseReturnPO> po=showPurchaseReturn();
		ArrayList<PurchaseReturnPO> result=new ArrayList<PurchaseReturnPO>();
		if(po==null)
			return null;
		if(type.equals("时间区间")){
			String start=message.substring(0,8);
			String end=message.substring(8,16);
			for(PurchaseReturnPO p:po){
				if(start.compareTo(p.myGetDate())<=0&&p.myGetDate().compareTo(end)<=0)
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("商品名")){
			for(PurchaseReturnPO p:po){
				ArrayList<CommodityPO> c=p.getPurchaseReturnList();
				  for(CommodityPO cp:c){
					  if(cp.getName().contains(message)){
					      result.add(p);
					      break;
					  }
				  }
					
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("客户")){
			for(PurchaseReturnPO p:po){
				if(p.getMemberName().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("仓库")){
			for(PurchaseReturnPO p:po){
				if(p.getStockID().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else{
			return null;
		}
		return result;
	}
	public ArrayList<SalePO> findSale(String message,String type) throws RemoteException {
		ArrayList<SalePO> po=showSale();
		if(po==null)
			return null;
		ArrayList<SalePO> result=new ArrayList<SalePO>();
		if(type.equals("时间区间")){
			String start=message.substring(0,8);
			String end=message.substring(8,16);
			for(SalePO p:po){
				if(start.compareTo(p.myGetDate())<=0&&p.myGetDate().compareTo(end)<=0)
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("商品名")){
			for(SalePO p:po){
				ArrayList<CommodityPO> c=p.getSalesList();
				  for(CommodityPO cp:c){
					  if(cp.getName().contains(message)){
					      result.add(p);
					      break;
					  }
				  }
					
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("客户")){
			for(SalePO p:po){
				if(p.getMemberName().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("业务员")){
			for(SalePO p:po){
				if(p.getClerk().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("仓库")){
			for(SalePO p:po){
				if(p.getStockID().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else{
			return null;
		}
		return result;
	}
	
	public ArrayList<SaleReturnPO> findSaleReturn(String message,String type) throws RemoteException {
		ArrayList<SaleReturnPO> po=showSaleReturn();
		ArrayList<SaleReturnPO> result=new ArrayList<SaleReturnPO>();
		if(po==null)
			return null;
		if(type.equals("时间区间")){
			String start=message.substring(0,8);
			String end=message.substring(8,16);
			for(SaleReturnPO p:po){
				if(start.compareTo(p.myGetDate())<=0&&p.myGetDate().compareTo(end)<=0)
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("商品名")){
			for(SaleReturnPO p:po){
				ArrayList<CommodityPO> c=p.getSalesreturnList();
				  for(CommodityPO cp:c){
					  if(cp.getName().contains(message)){
					      result.add(p);
					      break;
					  }
				  }					
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("客户")){
			for(SaleReturnPO p:po){
				if(p.getMemberName().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("业务员")){
			for(SaleReturnPO p:po){
				if(p.getClerk().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else if(type.equals("仓库")){
			for(SaleReturnPO p:po){
				if(p.getStockID().contains(message))
					result.add(p);
			}
			if(result.size()==0)
				return null;
		}
		else{
			return null;
		}
		return result;
	}
	
	public ArrayList<ReceiptPO> getAllSale() throws RemoteException{
		ArrayList<ReceiptPO> all=new ArrayList<ReceiptPO>();
		ArrayList<SalePO> a1=showSale();
		if(a1!=null){
		   for(SalePO po:a1){
			all.add(po);
		   }
		}
		ArrayList<SaleReturnPO> a2=showSaleReturn();
		if(a2!=null){
		   for(SaleReturnPO po:a2){
			  all.add(po);
		   }
		}
		
		if(all.size()==0)
			return null;
		if(all!=null)
		   Collections.sort(all,new SequenceOfReceiptPO());
		  
		 return all;

	}
	public ArrayList<ReceiptPO> getAllPurchase() throws RemoteException{
		ArrayList<ReceiptPO> all=new ArrayList<ReceiptPO>();
		ArrayList<PurchasePO> a1=showPurchase();
		if(a1!=null){
		  for(PurchasePO po:a1){
		    	all.add(po);
		  }
		}
		ArrayList<PurchaseReturnPO> a2=showPurchaseReturn();
		if(a2!=null){
		  for(PurchaseReturnPO po:a2){
			  all.add(po);
		  }
		}
		
		if(all.size()==0)
			return null;
		 if(all!=null)
		   Collections.sort(all,new SequenceOfReceiptPO());
		  
		  return all;

	}
	
	public ReceiptPO findReceiptByID(String ID) throws RemoteException{
		ArrayList<PurchasePO> a1=showPurchase();
		if(a1!=null){
		  for(ReceiptPO po:a1){
		    	if(po.getId().equals(ID)){
		    		return po;
			    }
		  }
		}
		
		ArrayList<PurchaseReturnPO> a2=showPurchaseReturn();
		
		if(a2!=null){		
		    for(PurchaseReturnPO po:a2){
			   if(po.getId().equals(ID)){
			    	return po;
		    	}
		    }
		}
		
		ArrayList<SalePO> a3=showSale();
		if(a3!=null){
		    for(SalePO po:a3){
			  if(po.getId().equals(ID)){
			    	return po;
			   }
		    }
		}
		
		ArrayList<SaleReturnPO> a4=showSaleReturn();
		if(a4!=null){
		   for(SaleReturnPO po:a4){
			  if(po.getId().equals(ID)){
				  return po;
			  }
		   }
		}
		  
		  return null;
	}
	
	public ArrayList<ReceiptPO> findByGoodsName(String name) throws RemoteException{
		ArrayList<ReceiptPO> sale=getAllSale();
		if(sale==null) return null;
		else{
			ArrayList<ReceiptPO> result=new ArrayList<ReceiptPO>();
			ArrayList<CommodityPO> com;
			for(ReceiptPO po:sale){
				if(po.getType()==ReceiptType.SALE){
					com=((SalePO)po).getSalesList();
				}else{
					com=((SaleReturnPO)po).getSalesreturnList();
				}
				if(com!=null){
					for(CommodityPO cpo:com){
						if(cpo.getName().equals(name))
						{
							result.add(po);break;
						}
					}
				}			
			}
			if(result.size()==0) return null;
			else return result;
		}
		
	}
	
	public static void main(String[] args) throws RemoteException{
	Sales a;
		
			a = new Sales();
			
			ArrayList<PurchaseReturnPO> p=a.showPurchaseReturn();
			for(PurchaseReturnPO pp:p){
				System.out.println(pp.getpurid());
			}
//			ArrayList<CommodityPO> commodity=new ArrayList<CommodityPO>();
//			CommodityPO com=new CommodityPO("0001-SR01-0001","蓝之恋吊灯","SR01",1000,800,1,1000,800
//					,"在做测试");
//			commodity.add(com);
//			double[] total=new double[]{800,1000,1000,0,1000};
//			double[] discount=new double[]{0,0,0,0};
//			//创建销售单
//				
//		SalePO sale=new SalePO("金金",commodity,"XSD-20141205-00001","马建国","XSS-00001",
//					"XS-00001",0,0,"","1","","",discount,total);
//			
//		a.createSale(sale);
//			sale.setStatus(2);
//			a.updateSale(sale);
//			a.updateSale(sale);
			//System.out.println(a.findReceiptByID("XSD-20141205-00001").getStatus());
//			a.showPurchase();
////			ArrayList<CommodityPO> al=new ArrayList<CommodityPO>();
////			CommodityPO item =new CommodityPO("0001-001-0001","飞利浦日光灯","SRO1",100,158,100,198,98,"这是个灯");
////			al.add(item);
//		//	a.createPurchase();
////			a.updatePurchase(new PurchasePO("JHD-20141226-00001","JHS-0000001","马建国","02","XS-00001",al,"这是个进货单", 1000,0,1, 0));
////			double discount[]=new double[]{1,1,1,1};
////			double total[]=new double[]{2,2,2,2,2};
////			a.createSale(new SalePO("金大大",al,"XSD-20141202-00001","JHS-0000001","马建国","XS-00001",1,1,"这是个销售单","02",discount,total));	
//			System.out.println("Success!");
//			ArrayList<PurchasePO> pl=a.showPurchase();
//			for(PurchasePO po:pl){
//				System.out.println(po.getId()+" "+po.getDate()+" "+po.getPurchaseList().get(0).getName());
//			}
//
			//ArrayList<SalePO> pl1=a.showSale();
			//for(SalePO po:pl1){
				//System.out.println(po.getHurry());
			//}
		
			/*System.out.println("-------------------------");
		try {
			Sales b=new Sales();
//			ArrayList<ReceiptPO> pp=b.getAllSale();
			ArrayList<SalePO> po=b.findSale("2014120120141227", "时间区间");
			if(po!=null)
				for(ReceiptPO p:po)
					System.out.println(p.getId());
		//	for(ReceiptPO po:pp)
				//System.out.println(po.getId()+" "+po.getDate());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	

}
