package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;

import dataservice.financedataservice.listdataservice.PaymentDataService;
import po.PaymentPO;
import po.ReceiptPO;
import po.TransferItemPO;
import vo.PaymentVO;
import vo.ReceiptVO;
import vo.TransferItemVO;
import businesslogic.memberbl.Member;
import businesslogic.receiptbl.Receipt;
import businesslogic.utilitybl.getDate;
import businesslogic.utilitybl.getServer;
import businesslogicservice.financeblservice.listblservice.PaymentBLService;

public class Payment extends Receipt implements PaymentBLService{

	 String ID;
     String supplier;
     String seller;
     String user;
     ArrayList<TransferItemVO> transferlist=new ArrayList<TransferItemVO>();
     PaymentDataService service;
     double totalMoney;
     public Payment()throws Exception{
 //   	System.setSecurityManager(new SecurityManager());
    	String host=getServer.getServer();
 		String url="rmi://"+host+"/paymentService";
 		
 		service=(PaymentDataService)Naming.lookup(url);
     }
         
	public String getNewID(){

   	  String id=null;
		ArrayList<PaymentPO> po;
		try {
			po = service.getPayment();
		if(po==null) id="00001";
		else{
			int i=po.size();
			String date=po.get(i-1).getId().substring(4, 12);
			if(date.equals(getDate.getdate())){
			Double d=Double.parseDouble(po.get(i-1).getId().substring(13))+1;
			 NumberFormat nf = NumberFormat.getInstance();
		     nf.setMinimumIntegerDigits(5); 
		     nf.setGroupingUsed(false);
		     id=nf.format(d);}
			else id="00001";}
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FKD-"+getDate.getdate()+"-"+id;
	}

	public int createPayment(PaymentVO vo) {
		
		try {
			Member m=new Member();
			if(m.isToReceive(vo.getMemberID(), vo.getTotalMoney())==1){
				return 2;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Account a=new Account();
			ArrayList<TransferItemVO> ts=vo.getTransferlist();
			for(TransferItemVO vv:ts){
				if(a.isMoney(vv.getAccount(),(-1)*vv.getMoney())==1){
					return 3;
				}
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PaymentPO po=voToPo(vo);
		try {
			Send(vo.getId());
			return service.createPayment(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public int excute(ReceiptVO v){
		PaymentVO vo=(PaymentVO)v;
			Member m;
			try {
				m = new Member();
				m.changeToReceive(vo.getMemberID(), vo.getTotalMoney());
				Account a=new Account();
				ArrayList<TransferItemVO> ts=vo.getTransferlist();
				for(TransferItemVO vv:ts){
					a.delMoney(vv.getAccount(),vv.getMoney());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("执行成功！");
		
    	
    	setStatus(v.getId(),3);
    	return 0;
	}

	public ArrayList<PaymentVO> getPayment() {
		ArrayList<PaymentPO> po;
		try {
			po = service.getPayment();
			if(po==null) return null;
			
			else return poToVo(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public PaymentPO voToPo(PaymentVO vo){
		if(vo==null)
			return null;
		 ArrayList<TransferItemVO> a=vo.getTransferlist();
		 ArrayList<TransferItemPO> b=new ArrayList<TransferItemPO>();
		 TransferItemPO po1;
		 for(int i=0;i<a.size();i++){
			 TransferItemVO vo1=a.get(i);
			 po1=new TransferItemPO(vo1.getAccount(),vo1.getMoney(),vo1.getInfo());
			 b.add(po1);
		 }
	
		PaymentPO po=new PaymentPO(vo.getId(),vo.getMemberID(),vo.getMemberName(),vo.getUser(),b,vo.getTotalMoney(),vo.getStatus(),vo.getHurry());
		return po;
	}

	
	public static PaymentVO poToVo(PaymentPO po){
		if(po==null)
			return null;
		 ArrayList<TransferItemPO> a=po.getTransferlist();
		 ArrayList<TransferItemVO> b=new ArrayList<TransferItemVO>();
		 TransferItemVO vo1;
		 for(int i=0;i<a.size();i++){
			 TransferItemPO po1=a.get(i);
			 vo1=new TransferItemVO(po1.getAccount(),po1.getMoney(),po1.getInfo());
			 b.add(vo1);
		 }
	
		 PaymentVO vo=new PaymentVO(po.getId(),po.getMemberID(),po.getMemberName(),po.getUserID(),b,po.getTotalMoney(),po.getStatus(),po.getHurry());
		return vo;
	}
	
	public ArrayList<PaymentPO> voToPo(ArrayList<PaymentVO> vo){
		if(vo==null)
			return null;
		ArrayList<PaymentPO> po=new ArrayList<PaymentPO>();
		for(PaymentVO v:vo){
			PaymentPO p=voToPo(v);
			po.add(p);
		}
		return po;
	}

	public ArrayList<PaymentVO> poToVo(ArrayList<PaymentPO> po){
		if(po==null)
			return null;
		ArrayList<PaymentVO> vo=new ArrayList<PaymentVO>();
		for(PaymentPO p:po){
			PaymentVO v=poToVo(p);
			vo.add(v);
		}
		return vo;
	}

	@Override
	public PaymentVO findByID(String s) {
		try {
			return poToVo(service.findByID(s));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int modify(PaymentVO vo) {
		try {
			return service.modify(voToPo(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public ReceiptPO getRedReceipt(ReceiptPO po){
		PaymentPO payment=(PaymentPO)po;
		ArrayList<TransferItemPO> list=payment.getTransferlist();
		for(int i=0;i<list.size();i++){
			double m=list.get(i).getMoney();
			list.get(i).setMoney(-m);
		}
	
		PaymentPO redPayment=new PaymentPO(payment.getId(),payment.getMemberID(),
				payment.getMemberName(),payment.getUserID(),list,payment.getTotalMoney()*(-1),
				payment.getStatus(),payment.getHurry());
	    	
		try {
			service.createPayment(redPayment);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return redPayment;
		
	}
	
}
