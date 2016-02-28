package Data.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import po.CashlistPO;
import po.CollectionPO;
import po.PaymentPO;
import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;
import dataservice.financedataservice.listdataservice.RedExtrusionDataService;

public class RedExtrusion extends UnicastRemoteObject implements RedExtrusionDataService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RedExtrusion() throws RemoteException{
		super();
	}

	public ReceiptPO createRedExtrusion(ReceiptPO po) throws RemoteException {

		ReceiptType type=po.getType();
		if(type==ReceiptType.COLLECTION){
			Collection c=new Collection();
			CollectionPO p=(CollectionPO)po;
			CollectionPO p2=new CollectionPO(p.getId(),p.getMemberID(),p.getMemberName(),p.getUserID(),p.getTransferlist(),p.getTotalMoney()*(-1),p.getStatus(),p.getHurry());
			c.createCollection(p2);	
			return p2;
		}
		else if(type==ReceiptType.PAYMENT){
			Payment p=new Payment();
			PaymentPO pp=(PaymentPO)po;
			PaymentPO pp2=new PaymentPO(pp.getId(),pp.getMemberID(),pp.getMemberName(),pp.getUserID(),pp.getTransferlist(),pp.getTotalMoney()*(-1),pp.getStatus(),pp.getHurry());
            p.createPayment(pp2);
            return pp2;
		}
		else if(type==ReceiptType.CASHLIST){
			Cashlist c=new Cashlist();
			CashlistPO p=(CashlistPO)po;
			CashlistPO p2=new CashlistPO(p.getId(),p.getUserID(),p.getAccount(),p.getClauselist(),p.getTotalMoney()*(-1),p.getStatus(),p.getHurry());
            c.createCashlist(p2);
            return p2;
		}

		return null;

	}
	public ReceiptPO createRedExtrusionAndCopy(ReceiptPO po) throws RemoteException {
		ReceiptType type=po.getType();
		if(type==ReceiptType.COLLECTION){
			Collection c=new Collection();
			CollectionPO p=(CollectionPO)po;
			CollectionPO p2=new CollectionPO(p.getId(),p.getMemberID(),p.getMemberName(),p.getUserID(),p.getTransferlist(),p.getTotalMoney()*(-1),p.getStatus(),p.getHurry());
			c.createCollection(p2);	
			return p;
		}
		else if(type==ReceiptType.PAYMENT){
			Payment p=new Payment();
			PaymentPO pp=(PaymentPO)po;
			PaymentPO pp2=new PaymentPO(pp.getId(),pp.getMemberID(),pp.getMemberName(),pp.getUserID(),pp.getTransferlist(),pp.getTotalMoney()*(-1),pp.getStatus(),pp.getHurry());
            p.createPayment(pp2);
            return pp;
		}
		else if(type==ReceiptType.CASHLIST){
			Cashlist c=new Cashlist();
			CashlistPO p=(CashlistPO)po;
			CashlistPO p2=new CashlistPO(p.getId(),p.getUserID(),p.getAccount(),p.getClauselist(),p.getTotalMoney()*(-1),p.getStatus(),p.getHurry());
            c.createCashlist(p2);
            return p;
		}
		return null;
	}
	
}
