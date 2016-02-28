package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;

import dataservice.financedataservice.listdataservice.CashlistDataService;
import po.CashlistPO;
import po.ClauseItemPO;
import po.ReceiptPO;
import vo.CashlistVO;
import vo.ClauseItemVO;
import vo.ReceiptVO;
import businesslogic.receiptbl.Receipt;
import businesslogic.utilitybl.getDate;
import businesslogic.utilitybl.getServer;
import businesslogicservice.financeblservice.listblservice.CashlistBLService;

public class CashList extends Receipt implements CashlistBLService{
	 CashlistDataService service;
	 
	 public CashList()throws Exception{
		 //   	System.setSecurityManager(new SecurityManager());
		        String host=getServer.getServer();
		 		String url="rmi://"+host+"/cashlistService";
		 		
		 		service=(CashlistDataService)Naming.lookup(url);
		     }
	 
	public String getNewID() {
	   	 String id=null;
			ArrayList<CashlistPO> po=service.getCashlist();
			if(po==null) id="00001";
			else{
				int i=po.size();
				String date=po.get(i-1).getId().substring(6, 14);
				if(date.equals(getDate.getdate())){
				Double d=Double.parseDouble(po.get(i-1).getId().substring(15))+1;
				 NumberFormat nf = NumberFormat.getInstance();
			     nf.setMinimumIntegerDigits(5); 
			     nf.setGroupingUsed(false);
			     id=nf.format(d);}
				else id="00001";
				
			}
			return "XJFYD-"+getDate.getdate()+"-"+id;
		}

	public int createCashlist(CashlistVO vo) {
		
		
		try {
			Account a=new Account();
			if(a.isMoney(vo.getAccount(), (-1)*vo.getTotalMoney())==1){
				return 3;
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
		CashlistPO po=voToPo(vo);
			return service.createCashlist(po);
	}
	
	
	
	public int excute(ReceiptVO v){
		CashlistVO vo=(CashlistVO)v;
		try {
			Account a=new Account();
			a.delMoney(vo.getAccount(),vo.getTotalMoney());
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
		setStatus(v.getId(),3);
		return 0;
	}

	public ArrayList<CashlistVO> getCashlist() {
		ArrayList<CashlistPO> po;
	    po = service.getCashlist();
	    
		if(po==null) return null;
		else return poToVo(po);
		
	}
	
	public CashlistPO voToPo(CashlistVO vo){
		if(vo==null)
			return null;
		 ArrayList<ClauseItemVO> a=vo.getClauselist();
		 ArrayList<ClauseItemPO> b=new ArrayList<ClauseItemPO>();
		 ClauseItemPO po1;
		 for(int i=0;i<a.size();i++){
			 ClauseItemVO vo1=a.get(i);
			 po1=new ClauseItemPO(vo1.getName(),vo1.getMoney(),vo1.getInfo());
			 b.add(po1);
		 }
	
		CashlistPO po=new CashlistPO(vo.getId(),vo.getUser(),vo.getAccount(),b,vo.getTotalMoney(),vo.getStatus(),vo.getHurry());
		return po;
	}

	
	public static CashlistVO poToVo(CashlistPO po){
		if(po==null)
			return null;
		 ArrayList<ClauseItemPO> a=po.getClauselist();
		 ArrayList<ClauseItemVO> b=new ArrayList<ClauseItemVO>();
		 ClauseItemVO vo1;
		 for(int i=0;i<a.size();i++){
			 ClauseItemPO po1=a.get(i);
			 vo1=new ClauseItemVO(po1.getName(),po1.getMoney(),po1.getInfo());
			 b.add(vo1);
		 }
	
		 CashlistVO vo=new CashlistVO(po.getId(),po.getUserID(),po.getAccount(),b,po.getTotalMoney(),po.getStatus(),po.getHurry());
		 return vo;
	}
	
	public ArrayList<CashlistPO> voToPo(ArrayList<CashlistVO> vo){
		if(vo==null)
			return null;
		ArrayList<CashlistPO> po=new ArrayList<CashlistPO>();
		for(CashlistVO v:vo){
			CashlistPO p=voToPo(v);
			po.add(p);
		}
		return po;
	}

	public ArrayList<CashlistVO> poToVo(ArrayList<CashlistPO> po){
		if(po==null)
			return null;
		ArrayList<CashlistVO> vo=new ArrayList<CashlistVO>();
		for(CashlistPO p:po){
			CashlistVO v=poToVo(p);
			vo.add(v);
		}
		return vo;
	}

	@Override
	public CashlistVO findByID(String s) {
		return poToVo(service.findByID(s));
	}

	@Override
	public int modify(CashlistVO vo) {
		return service.modify(voToPo(vo));
	}
	
	public ReceiptPO getRedReceipt(ReceiptPO po){
		CashlistPO cashlist=(CashlistPO)po;
		ArrayList<ClauseItemPO> list=cashlist.getClauselist();
		for(int i=0;i<list.size();i++){
			double m=list.get(i).getMoney();
			list.get(i).setMoney(-m);
		}
	
		CashlistPO redCashlist=new CashlistPO(cashlist.getId(),cashlist.getUserID(),
				cashlist.getAccount(),list,cashlist.getTotalMoney()*(-1),
				cashlist.getStatus(),cashlist.getHurry());
	    	
		service.createCashlist(redCashlist);
		return redCashlist;
		
	}
}

