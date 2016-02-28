package businesslogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import businesslogic.utilitybl.MyComparator;
import businesslogic.utilitybl.MySort;
import businesslogic.utilitybl.getServer;
import po.CollectionPO;
import po.ReceiptPO;
import po.ReceiptPO.ReceiptType;
import dataservice.receiptdataservice.ReceiptDataService;
import vo.ReceiptVO;

public class ReceiptList {
	ReceiptDataService service;
	public ReceiptList() throws Exception{
		String host=getServer.getServer();
		String url="rmi://"+host+"/receiptService";
	
		service=(ReceiptDataService)Naming.lookup(url);
	}
	
	
	public ArrayList<ReceiptPO> showAllReceipt(){
		
			ArrayList<ReceiptPO> list=service.showAll();
			if(list==null) return null;
			else return MySort.sort(list);
		
	}
	public ArrayList<ReceiptPO>  AccurateFind(String message[])
	{
		return service.AccurateFind(message);
	}
	
	public ArrayList<ReceiptPO>  showApproved(){
		ArrayList<ReceiptPO> po=showAllReceipt();
		if(po==null) return null;
		ArrayList<ReceiptPO> approved=new ArrayList<ReceiptPO>();
		for(int i=0;i<po.size();i++){
			ReceiptPO p=po.get(i);
			if(p.getStatus()!=0)
				approved.add(p);
			
		}
		if(approved.size()==0) return null;
		else return MySort.sort(approved);
	}
	
	public ArrayList<ReceiptPO> showToApprove(){
		ArrayList<ReceiptPO> po=showAllReceipt();
		if(po==null) return null;
		ArrayList<ReceiptPO> toApprove=new ArrayList<ReceiptPO>();
		for(int i=0;i<po.size();i++){
			ReceiptPO p=po.get(i);
			if(p.getStatus()==0)
				toApprove.add(p);
			
		}
		if(toApprove.size()==0) return null;
		else return MySort.sort(toApprove);
	}
	
	public ArrayList<ReceiptPO> showToApprove(ReceiptType type){
		ArrayList<ReceiptPO> toApprove=showToApprove();
		if(toApprove==null) return null;
		//已排好序
		else{
			ArrayList<ReceiptPO> toApproveByType=new ArrayList<ReceiptPO>();
			for(int i=0;i<toApprove.size();i++)
				if(toApprove.get(i).getType()==type)
					toApproveByType.add(toApprove.get(i));
			if(toApproveByType.size()==0) return null;
			else
				return toApproveByType;
		}
		
		
	}
	
	
	public ArrayList<ReceiptPO> showApproved(ReceiptType type){
		ArrayList<ReceiptPO> Approve=showApproved();
		if(Approve==null) return null;
		//已排好序
		else{
			ArrayList<ReceiptPO> ApproveByType=new ArrayList<ReceiptPO>();
			for(int i=0;i<Approve.size();i++)
				if(Approve.get(i).getType()==type)
					ApproveByType.add(Approve.get(i));
			if(ApproveByType.size()==0) return null;
			else
				return ApproveByType;
		}
		
		
	}


	
}
