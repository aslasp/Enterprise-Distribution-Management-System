package Data.receiptdata;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

import Data.datafactory.SequenceOfReceiptPO;
import Data.financedata.Cashlist;
import Data.financedata.Collection;
import Data.financedata.Payment;
import Data.financedata.RedExtrusion;
import Data.salesdata.Sales;
import Data.serutility.JXCFile;
import Data.stockdata.gift.Gift;
import Data.stockdata.stockManage.StockControl;
import Data.userdata.User;
import po.CashlistPO;
import po.CollectionPO;
import po.GiftPO;
import po.PaymentPO;
import po.PurchasePO;
import po.PurchaseReturnPO;
import po.ReceiptMessagePO;
import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;
import po.SalePO;
import po.SaleReturnPO;
import po.StockErrorPO;
import dataservice.receiptdataservice.ReceiptDataService;
import po.StockOverOrLowPO;



public class Receipt extends UnicastRemoteObject implements ReceiptDataService{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Receipt() throws RemoteException {
		super();
	}
	
	public ReceiptPO Add(ReceiptPO po) throws RemoteException {
       RedExtrusion r=new RedExtrusion();
       r.createRedExtrusion(po);
       return null;
	}

	public ReceiptPO addAndCopy(ReceiptPO po) throws RemoteException{
		RedExtrusion r=new RedExtrusion();
		return r.createRedExtrusionAndCopy(po);
	}
	
	public ReceiptPO findById(String id) throws RemoteException {

            String[] buffer=id.split("-");
            String s=buffer[0];
	        if(s.equals("SKD")){
	        	Collection c=new Collection();
	        	return c.findByID(id);
	        }
	        else if(s.equals("FKD")){
	        	Payment p=new Payment();
	        	return p.findByID(id);
	        }
	        else if(s.equals("XJFYD")){
	        	Cashlist c=new Cashlist();
	        	return c.findByID(id);
	        }
	        else if(s.equals("JHD")||s.equals("JHTHD")||s.equals("XSD")||s.equals("XSTHD")){
	        	Sales sale=new Sales();
	        		return sale.findReceiptByID(id);
	        }
	        else if(s.equals("KCZSD")){
	        	Gift gift=new Gift();
	        	return gift.findByID(id);
	        	
	        }
	        else if(s.equals("KCBYD")||s.equals("KCBSD")){
	        	StockControl sc=new StockControl();
	        	return sc.findByID(id);	        	
	        }	       
	        else{
	        	return null;
	        }
	        
	}

	public int Modify(ReceiptPO po) throws RemoteException {
		if(po.getType()==ReceiptType.COLLECTION){
			Collection c=new Collection();
			return c.modify((CollectionPO)po);
		}
		else if(po.getType()==ReceiptType.PAYMENT){
			Payment p=new Payment();
			return p.modify((PaymentPO)po);
		}
		else if(po.getType()==ReceiptType.CASHLIST){
			Cashlist c=new Cashlist();
			return c.modify((CashlistPO)po);
		}
		else if(po.getType()==ReceiptType.PURCHASE||po.getType()==ReceiptType.PURCHASERETURN||po.getType()==ReceiptType.SALE||po.getType()==ReceiptType.SALERETURN){
			Sales s=new Sales();
			return s.modify(po);
		}
		else if(po.getType()==ReceiptType.GIFT){
			Gift g=new Gift();
			return g.modify(po);
		}
		else{
			return 1;
		}
	}
	
	public int setStatus(String id,int st) throws RemoteException{
		   String[] buffer=id.split("-");
           String s=buffer[0];
	        if(s.equals("SKD")){
	        	Collection c=new Collection();
	        	return c.setStatus(id, st);
	        }
	        else if(s.equals("FKD")){
	        	Payment p=new Payment();
	        	return p.setStatus(id, st);
	        }
	        else if(s.equals("XJFYD")){
	        	Cashlist c=new Cashlist();
	        	return c.setStatus(id, st);
	        }
	        else if(s.equals("JHD")||s.equals("JHTHD")||s.equals("XSD")||s.equals("XSTHD")){
	        	Sales sale=new Sales();
	        		return sale.setStatus(id, st);
	        }
	       
	        else{
	        	return 1;
	        }
	}

	public ArrayList<ReceiptPO> showAll() throws RemoteException {
		ArrayList<ReceiptPO> al=new ArrayList<ReceiptPO>();
		Collection c=new Collection();
		if(c.getCollection()!=null)
			for(CollectionPO p:(c.getCollection())){
				al.add(p);
			}
		Payment p=new Payment();
		if(p.getPayment()!=null)
			for(PaymentPO pp:(p.getPayment())){
				al.add(pp);
			}
		Cashlist cc=new Cashlist();
		if(cc.getCashlist()!=null)
			for(CashlistPO pp:(cc.getCashlist())){
				al.add(pp);
			}
		Sales sa=new Sales();
		if(sa.getAllPurchase()!=null){
			for(ReceiptPO pp:(sa.getAllPurchase())){
				al.add(pp);
			}
		}
		if(sa.getAllSale()!=null){
			for(ReceiptPO pp:(sa.getAllSale())){
				al.add(pp);
			}
		}
		Gift g=new Gift();
		if(g.getGiftList()!=null){
			for(GiftPO pp:(g.getGiftList())){
				al.add(pp);
			}
		}
		
		StockControl sc=new StockControl();
		if(sc.getStockOverOrLowPO()!=null){
			for(StockOverOrLowPO pp:(sc.getStockOverOrLowPO())){
				al.add(pp);
			}
		}		
		
		if(al.size()==0)
			return null;
		
		if(al!=null)
		Collections.sort(al,new SequenceOfReceiptPO());
		return al;
	}


	public ArrayList<ReceiptPO> show(ReceiptType type) throws RemoteException {
		ArrayList<ReceiptPO> al=new ArrayList<ReceiptPO>();
		if(type==ReceiptType.COLLECTION){
			Collection c=new Collection();
			ArrayList<CollectionPO> cc=c.getCollection();
			if(cc==null)
				return null;
			for(CollectionPO p:cc){
				al.add(p);
			}
		}
		else if(type==ReceiptType.PAYMENT){
			Payment c=new Payment();
			ArrayList<PaymentPO> pp=c.getPayment();
			if(pp==null)
				return null;
			for(PaymentPO p:pp){
				al.add(p);
			}
		}
		else if(type==ReceiptType.CASHLIST){
			Cashlist c=new Cashlist();
			ArrayList<CashlistPO> pp=c.getCashlist();
			if(pp==null)
				return null;
			for(CashlistPO p:pp){
				al.add(p);
			}
		}
		else if(type==ReceiptType.GIFT){
			Gift c=new Gift();
			ArrayList<GiftPO> pp=c.getGiftList();
			if(pp==null)
				return null;
			for(GiftPO p:pp){
				al.add(p);
			}
		}
		else if(type==ReceiptType.PURCHASE||type==ReceiptType.PURCHASERETURN||type==ReceiptType.SALE||type==ReceiptType.SALERETURN){
			Sales s=new Sales();
			if(type==ReceiptType.PURCHASE){
				ArrayList<PurchasePO> pp=s.showPurchase();
				if(pp==null)
					return null;
				for(PurchasePO p:pp){
					al.add(p);
				}
			}
			else if(type==ReceiptType.PURCHASERETURN){
				ArrayList<PurchaseReturnPO> pp=s.showPurchaseReturn();
				if(pp==null)
					return null;
				for(PurchaseReturnPO p:pp){
					al.add(p);
				}
			}
			else if(type==ReceiptType.SALE){
				ArrayList<SalePO> pp=s.showSale();
				if(pp==null)
					return null;
				for(SalePO p:pp){
					al.add(p);
				}
			}
			else if(type==ReceiptType.SALERETURN){
				ArrayList<SaleReturnPO> pp=s.showSaleReturn();
				if(pp==null)
					return null;
				for(SaleReturnPO p:pp){
					al.add(p);
				}
			}
		}
		else if(type==ReceiptType.STOCKLOW||type==ReceiptType.STOCKOVER){
				StockControl sc=new StockControl();
				ArrayList<StockOverOrLowPO> pp=sc.getStockOverOrLowPO();
				ArrayList<StockErrorPO> error=sc.getStockErrorPO();
				if(pp==null)
					return null;
				ArrayList<StockOverOrLowPO> pp1=new ArrayList<StockOverOrLowPO>();
				ArrayList<StockOverOrLowPO> pp2=new ArrayList<StockOverOrLowPO>();
				for(StockOverOrLowPO p:pp){
					if(p.getType()==ReceiptType.STOCKLOW)
						pp1.add(p);
					else
						pp2.add(p);
				}
				if(type==ReceiptType.STOCKLOW){
					for(StockOverOrLowPO ppp:pp1){
						al.add(ppp);
					}
				}
				else if(type==ReceiptType.STOCKOVER){
					for(StockOverOrLowPO ppp:pp2){
						al.add(ppp);
					}
				}
			/*	else {
					for(StockErrorPO errorpo:error)
						al.add(errorpo);
				}*/
		}
		if(al.size()==0) return null;
		else return al;
				
	}

	public int Batch(String[] id, int status) {
        for(int i=0;i<id.length;i++){
        	try {
				ReceiptPO p=findById(id[i]);
				p.setStatus(status);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

		return 0;
	}

	public int Approve(String id, int status) {
		ReceiptPO p;
		try {
			p = findById(id);
			p.setStatus(status);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<ReceiptPO> findByTime(String s) throws RemoteException{
		ArrayList<ReceiptPO> al;
		ArrayList<ReceiptPO> result=new ArrayList<ReceiptPO>();
			al = showAll();
			if(al==null)
				return null;
			String startDate=s.substring(0,8);
			String endDate=s.substring(8,16);
			for(ReceiptPO po:al){
				if(startDate.compareTo(po.myGetDate())<=0&&po.myGetDate().compareTo(endDate)<=0)
					result.add(po);	
			}
			if(result!=null)
			Collections.sort(result,new SequenceOfReceiptPO());
		     return result;
	}
	
	public ArrayList<ReceiptPO> findByMember(String s) throws RemoteException{
		ArrayList<ReceiptPO> result=new ArrayList<ReceiptPO>();
		ArrayList<ReceiptPO> al=showAll();
		if(al==null)
			return null;
		for(ReceiptPO po:al){
			if(po.getType()==ReceiptType.CASHLIST){
				continue;
			}
			else{
				if(s.equals(po.getMemberName()))
					result.add(po);
			}
		}
		if(result.size()==0)
			return null;
		if(result!=null)
		Collections.sort(result,new SequenceOfReceiptPO());			
		return result;	
	}
	
	//肿么办我们业务员做成操作员了 只有销售单有业务员？貌似 
	public ArrayList<ReceiptPO> findByUser(String s) throws RemoteException{
		ArrayList<ReceiptPO> result=new ArrayList<ReceiptPO>();
		ArrayList<ReceiptPO> al=showAll();
		User user=new User();
		if(al==null)
			return null;
		for(ReceiptPO po:al){
			if(user.showUserInfo(po.getUserID()).getName().equals(s))
				result.add(po);
		}
		if(result.size()==0)
			return null;
		if(result!=null)
		Collections.sort(result,new SequenceOfReceiptPO());
	    return result;
	}
	
	
	public ArrayList<ReceiptPO> findByWarehouse(String s) throws RemoteException{
		ArrayList<ReceiptPO> result=new ArrayList<ReceiptPO>();
		ArrayList<ReceiptPO> al=showAll();
		if(al==null)
			return null;
		for(ReceiptPO po:al){
			if(po.getType()==ReceiptType.PURCHASE){
				if(((PurchasePO)po).getStockID().equals(s))
					result.add(po);
			}
			else if(po.getType()==ReceiptType.PURCHASERETURN){
				if(((PurchaseReturnPO)po).getStockID().equals(s))
					result.add(po);
			}
			else if(po.getType()==ReceiptType.SALE){
				if(((SalePO)po).getStockID().equals(s))
					result.add(po);
			}
			else if(po.getType()==ReceiptType.SALERETURN){
				if(((SaleReturnPO)po).getStockID().equals(s))
					result.add(po);
			}
		}
		if(al.size()==0)
			return null;
		if(result!=null)
		Collections.sort(result,new SequenceOfReceiptPO());
		return result;
	}
	
	public ArrayList<ReceiptPO> intersection(ArrayList<ReceiptPO> a1,ArrayList<ReceiptPO> a2){
		ArrayList<ReceiptPO> result=new ArrayList<ReceiptPO>();
		
			if(a1==null||a2==null)
				return null;
		for(int i=0;i<a1.size();i++)
			for(int j=0;j<a2.size();j++){
				if(a1.get(i).getId().equals(a2.get(j).getId())){
					result.add(a1.get(i));
					break;
				} 
			}
		if(result.size()==0)
			return null;
		return result;
	}
	
	public ArrayList<ReceiptPO> AccurateFind(String[] message)throws RemoteException {
		//时间区间,单据类型，客户，业务员，仓库
		
		ArrayList<ReceiptPO> a1;
		ArrayList<ReceiptPO> a2;
		ArrayList<ReceiptPO> a3;
		ArrayList<ReceiptPO> a4;
		ArrayList<ReceiptPO> a5;
		ArrayList<ReceiptPO> result;
		ArrayList<ArrayList<ReceiptPO>> recipts=new ArrayList<ArrayList<ReceiptPO>>();
		if(message[0]!=null){
			a1=findByTime(message[0]);
		}
		else
			a1=showAll();
		//查找类单据
		
		if(message[message.length-1].equals("销售明细")){
			Sales s=new Sales();
			if(message[1]!=null&&!message[1].equals("全部"))
			{
				a2=s.findByGoodsName(message[1]);
			}else{
				a2=s.getAllSale();
			}
		}
		else{
			if(message[1]!=null&&!message[1].equals("全部")){
				a2=show(ReceiptType.valueOf(message[1]));
			//recipts.add(a2);
			}
			else
				a2=showAll();
		}
		
		if(message[2]!=null&&!message[2].equals("全部")){
			a3=findByMember(message[2]);
			//recipts.add(a3);
		}
		else
			a3=showAll();
		
		if(message[3]!=null&&!message[3].equals("全部")){
			a4=findByUser(message[3]);
			//recipts.add(a4);
		}
		else
			a4=showAll();
		
		if(message[4]!=null&&!message[4].equals("")){
			a5=findByWarehouse(message[4]);
			//recipts.add(a5);
		}
		else
			a5=showAll();
		
	//	if(recipts.size()==0)
		//	return null;
		
		//result=recipts.get(0);
		//for(int i=1;i<recipts.size();i++){
		//	result=intersection(result,recipts.get(i));
		//}
		result=intersection(a1,intersection(a2,intersection(a3,intersection(a4,a5))));
	/*	if(message[message.length-1].equals("销售明细")){
			for(int i=0;i<result.size();i++){
				ReceiptPO p=result.get(i);
				boolean isSale=p.getType()==ReceiptType.SALE||p.getType()==ReceiptType.SALERETURN;
				if(!isSale)
					result.remove(i);
			}
		}*/
		
		if(result!=null)
		Collections.sort(result,new SequenceOfReceiptPO());
		return result;
			
	}

	public ArrayList<ReceiptMessagePO> showAllMessage() throws RemoteException {
		// TODO Auto-generated method stub
		JXCFile file;
		file=new JXCFile("message.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<ReceiptMessagePO> buffer=new ArrayList<ReceiptMessagePO>();
		for(Object b:a){
			ReceiptMessagePO po=(ReceiptMessagePO)b;
			buffer.add(po);
		}
		
		return buffer;
	}

	public void addReceiptMessage(ReceiptMessagePO po) throws RemoteException {
		// TODO Auto-generated method stub
		JXCFile file;
		file=new JXCFile("message.ser");
		file.write(po);
	}

	public void deleteReceiptMessage(ReceiptMessagePO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		JXCFile file;
		file=new JXCFile("message.ser");
		ArrayList<Object> a=file.read();
		if(a!=null)
			for(int i=0;i<a.size();i++)
				{ReceiptMessagePO p=(ReceiptMessagePO)a.get(i);
				if(p.getInfo().equals(po.getInfo()))
					if(p.getTag()==po.getTag())
						{a.remove(i);break;}
				}
		file.writeM(a);
		
	}
	public static void main(String[] args) throws RemoteException {
		String  m[]={"2014120120141220","SALERETURN","金大大","王宁宁","","经营历程"};
		Receipt r=new Receipt();
		ArrayList<ReceiptPO> po=r.AccurateFind(m);
		for(int i=0;i<po.size();i++)
			System.out.println(po.get(i).getId());
	
      
	}
	
}
