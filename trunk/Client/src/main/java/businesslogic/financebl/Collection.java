package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;

import po.CollectionPO;
import po.ReceiptPO;
import po.TransferItemPO;
import vo.CollectionVO;
import vo.ReceiptVO;
import vo.TransferItemVO;
import businesslogic.memberbl.Member;
import businesslogic.receiptbl.Receipt;
import businesslogic.utilitybl.getDate;
import businesslogic.utilitybl.getServer;
import businesslogicservice.financeblservice.listblservice.CollectionBLService;
import dataservice.financedataservice.listdataservice.CollectionDataService;


public class Collection extends Receipt implements CollectionBLService{

	 String ID;
     String supplier;
     String seller;
     String user;
     ArrayList<TransferItemVO> transferlist=new ArrayList<TransferItemVO>();
     CollectionDataService service;
     double totalMoney;
     public Collection()throws Exception{
 //   	System.setSecurityManager(new SecurityManager());
    	String host=getServer.getServer();
 		String url="rmi://"+host+"/collectionService";
 	
 		service=(CollectionDataService)Naming.lookup(url);
     }
    

	
     public String getNewID() {
    	 String id=null;
 		ArrayList<CollectionPO> po=service.getCollection();
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
			else id="00001";
 			
 		}
 		return "SKD-"+getDate.getdate()+"-"+id;
 	}
     
    public int excute(ReceiptVO v) {
    	CollectionVO vo=(CollectionVO)v;
        try {
			Member m;
			
				m = new Member();
				m.changeToPay(vo.getMemberID(), (-1)*vo.getTotalMoney());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Account a;
			try {
				a = new Account();
				ArrayList<TransferItemVO> ts=vo.getTransferlist();
				for(TransferItemVO vv:ts){
					a.addMoney(vv.getAccount(),vv.getMoney());
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("执行成功！");
		

    	
    	setStatus(v.getId(),3);
    	return 0;

	
    	
    }
    
  
	public int createCollection(CollectionVO vo) {
		
		try {
			Member m=new Member();
			if(m.isToPay(vo.getMemberID(), vo.getTotalMoney())==1){
				return 2;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		CollectionPO po=voToPo(vo);
		Send(vo.getId());
		
		return service.createCollection(po);
//		System.out.println(vo.getID()+" "+vo.getSupplier()+" "+vo.getSeller()+" "+vo.getUser()+" "+b.get(0).getAccount()+" "+vo.getTotalMoney());		
	}
	
	public ArrayList<CollectionVO> getCollection(){
		ArrayList<CollectionPO>	po = service.getCollection();
		if(po==null) return null;
		
		else return poToVo(po);
		
	}
	
	public CollectionVO findByID(String s){
		return poToVo(service.findByID(s));
	}
	
	public CollectionPO voToPo(CollectionVO vo){
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
	
		CollectionPO po=new CollectionPO(vo.getId(),vo.getMemberID(),vo.getMemberName(),vo.getUser(),b,vo.getTotalMoney(),vo.getStatus(),vo.getHurry());
		return po;
	}

	
	public static  CollectionVO poToVo(CollectionPO po){
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
	
		CollectionVO vo=new CollectionVO(po.getId(),po.getMemberID(),po.getMemberName(),po.getUserID(),b,po.getTotalMoney(),po.getStatus(),po.getHurry());
		return vo;
	}
	
	public ArrayList<CollectionPO> voToPo(ArrayList<CollectionVO> vo){
		if(vo==null)
			return null;
		ArrayList<CollectionPO> po=new ArrayList<CollectionPO>();
		for(CollectionVO v:vo){
			CollectionPO p=voToPo(v);
			po.add(p);
		}
		return po;
	}

	public ArrayList<CollectionVO> poToVo(ArrayList<CollectionPO> po){
		if(po==null)
			return null;
		ArrayList<CollectionVO> vo=new ArrayList<CollectionVO>();
		for(CollectionPO p:po){
			CollectionVO v=poToVo(p);
			vo.add(v);
		}
		return vo;
	}

	@Override
	public int modify(CollectionVO vo) {
		try {
			return service.modify(voToPo(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public ReceiptPO getRedReceipt(ReceiptPO po){
		CollectionPO collection=(CollectionPO)po;
		ArrayList<TransferItemPO> list=collection.getTransferlist();
		for(int i=0;i<list.size();i++){
			double m=list.get(i).getMoney();
			list.get(i).setMoney(-m);
		}
	
		CollectionPO redCollection=new CollectionPO(collection.getId(),collection.getMemberID(),
				collection.getMemberName(),collection.getUserID(),list,collection.getTotalMoney()*(-1),
				collection.getStatus(),collection.getHurry());
	    	
		service.createCollection(redCollection);
		return redCollection;
		
	}
}
