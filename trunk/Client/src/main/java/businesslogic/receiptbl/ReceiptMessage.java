package businesslogic.receiptbl;

import java.rmi.Naming;
import java.util.ArrayList;

import po.ReceiptMessagePO;
import vo.ReceiptMessageVO;
import businesslogic.utilitybl.getServer;
import businesslogicservice.receiptblservice.ReceiptTipService;
import dataservice.receiptdataservice.ReceiptDataService;

public class ReceiptMessage implements ReceiptTipService{
	ReceiptDataService service;
	public ReceiptMessage() throws Exception{
		String host=getServer.getServer();
		String url="rmi://"+host+"/receiptService";
	
		service=(ReceiptDataService)Naming.lookup(url);
	}

	public ArrayList<ReceiptMessageVO> getToApprove() {
		// TODO Auto-generated method stub
		ArrayList<ReceiptMessagePO> po=service.showAllMessage();
		if(po==null) return null;
		else{
			ArrayList<ReceiptMessageVO> vo=new ArrayList<ReceiptMessageVO>();
			for(int i=0;i<po.size();i++)
				if(po.get(i).getTag()==0)
					vo.add(poToVo(po.get(i)));
			if(vo.size()==0)return null;
			return vo;
		}
	}
	public ArrayList<ReceiptMessageVO> getStockApproved() {
		// TODO Auto-generated method stub
		ArrayList<ReceiptMessagePO> po=service.showAllMessage();
		if(po==null) return null;
		else{
			ArrayList<ReceiptMessageVO> vo=new ArrayList<ReceiptMessageVO>();
			for(int i=0;i<po.size();i++)
				if(po.get(i).getTag()==3)
					vo.add(poToVo(po.get(i)));
			if(vo.size()==0)return null;
			return vo;
		}
	}

	public ArrayList<ReceiptMessageVO> getSaleApproved() {
		// TODO Auto-generated method stub
		ArrayList<ReceiptMessagePO> po=service.showAllMessage();
		if(po==null) return null;
		else{
			ArrayList<ReceiptMessageVO> vo=new ArrayList<ReceiptMessageVO>();
			for(int i=0;i<po.size();i++)
				if(po.get(i).getTag()==1)
					vo.add(poToVo(po.get(i)));
			if(vo.size()==0)return null;
			return vo;
		}
	}
	
	public ArrayList<ReceiptMessageVO> getFinanceApproved() {
		// TODO Auto-generated method stub
		ArrayList<ReceiptMessagePO> po=service.showAllMessage();
		if(po==null) return null;
		else{
			ArrayList<ReceiptMessageVO> vo=new ArrayList<ReceiptMessageVO>();
			for(int i=0;i<po.size();i++)
				if(po.get(i).getTag()==2)
					vo.add(poToVo(po.get(i)));
			if(vo.size()==0)return null;
			return vo;
		}
		
	}
	@Override
	public void addMessage(ReceiptMessageVO vo) {
		// TODO Auto-generated method stub
		service.addReceiptMessage(voToPo(vo));
		
	}
	@Override
	public void deleteessage(ReceiptMessageVO vo) {
		// TODO Auto-generated method stub
		service.deleteReceiptMessage(voToPo(vo));
	}
	
	public ReceiptMessagePO voToPo(ReceiptMessageVO vo){
		ReceiptMessagePO po=new ReceiptMessagePO(vo.getTag(),vo.getInfo());
		return po;
	}
	public ReceiptMessageVO poToVo(ReceiptMessagePO po){
		ReceiptMessageVO vo=new ReceiptMessageVO(po.getTag(),po.getInfo());
		return vo;
	}
}
