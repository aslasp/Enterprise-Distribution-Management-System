package businesslogic.promotionbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;














import dataservice.promotiondataservice.PromotionDataService;
import dataservice.salesdataservice.SalesDataService;
import businesslogic.utilitybl.getServer;
import businesslogicservice.promotionblservice.PromotionBLService;
import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import po.PromotionPO;
import po.PromotionPO.PromotionType;
import vo.GiftVO;
import vo.PromotionVO;
import vo.SaleVO;
//release 去掉？？？当前促销是否还有效？
public  class promotion {
	
	PromotionDataService service;
	public promotion() throws Exception{
		String host=getServer.getServer();
		String url="rmi://"+host+"/promotionService";
	
		service=(PromotionDataService)Naming.lookup(url);
	
	}
	public String getNewID(PromotionType type){return null;};
	
	//发布和添加？
	public  int Add(PromotionVO vo){return 0;};
	public  int Modify(PromotionVO vo){return 0;};
	public PromotionVO findByID(String id){return null;};
	public void Excute(String proid,SaleVO vo){};
	public PromotionVO Match(SaleVO vo){return null;};
	//子类不重写
	public ArrayList<PromotionVO> Show(){
		ArrayList<PromotionPO> po=service.showAll();
		if(po==null) return null;
		else{
			ArrayList<PromotionVO> vo=new ArrayList<PromotionVO>();
			for(int i=0;i<po.size();i++)
				vo.add(poToVo(po.get(i)));

			return vo;
		}
		
	}
	
	
		
	
	public PromotionVO poToVo(PromotionPO po){
		PromotionVO vo=new PromotionVO(po.getID(),po.getStartDate(),po.getEndDate(),
				po.getType(),po.getLevel());
		return vo;
	}
	public int Delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public  PromotionPO find(String id){
		PromotionPO po;
		//仅赠品促销需要另外执行
		if( (po=service.find(id,PromotionType.GIFTGOODS ))!=null)
			return po;
		else if( (po=service.find(id,PromotionType.GIFTCOUPON ))!=null)
			return po;
		else return null;
			
	}
}
