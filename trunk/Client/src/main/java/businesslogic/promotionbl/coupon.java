package businesslogic.promotionbl;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.text.NumberFormat;
import java.util.ArrayList;

import businesslogic.utilitybl.getDate;
import businesslogic.utilitybl.getServer;
import dataservice.promotiondataservice.CouponDataService;
import dataservice.promotiondataservice.PromotionDataService;
import po.CouponPO;
import vo.CouponVO;

public class coupon {
	//String id,  double money,boolean isUse
	CouponDataService service;
	public coupon() throws Exception{
		String host=getServer.getServer();
		String url="rmi://"+host+"/couponService";
	
		service=(CouponDataService)Naming.lookup(url);
	}
	private CouponVO  poToVo(CouponPO po){
		CouponVO vo=new CouponVO(po.getId(),po.getValue(),po.getIsUse());
		vo.setUseDate(po.getUseDate());
		return vo;
	}
	
	private CouponPO voToPo(CouponVO vo){
		CouponPO po=new CouponPO(vo.getId(),vo.getValue(),vo.getIsUse());
		po.setUseDate(vo.getUseDate());
		return po;
	}
	
	
	 ArrayList<CouponPO> voTPo(ArrayList<CouponVO> vo){
		ArrayList<CouponPO> po=new ArrayList<CouponPO>();
		for(int i=0;i<vo.size();i++)
			po.add(voToPo(vo.get(i)));
		return po;
	}
	
	
	ArrayList<CouponVO> poTVo(ArrayList<CouponPO> po){
		ArrayList<CouponVO> vo=new ArrayList<CouponVO>();
		for(int i=0;i<po.size();i++)
			vo.add(poToVo(po.get(i)));
		return vo;
	}
	
	public ArrayList<CouponVO> getAllCoupon(){
		ArrayList<CouponPO> p=service.getAllCoupon();
		if(p!=null)
			return poTVo(p);
		return null;
	}
	public ArrayList<CouponVO> createNewCoupon(ArrayList<CouponVO> vo){
		String id[]=getCouponId(vo.size());
		ArrayList<CouponVO> cou=new ArrayList<CouponVO>();
		for(int i=0;i<vo.size();i++){
			CouponVO c=vo.get(i);
			c.setId(id[i]);
			cou.add(c);
			
		}
		addCoupon(cou);
		return cou;
		
		
	}
	
	public void addCoupon(ArrayList<CouponVO> v){
		if(v!=null)
			service.addCoupon(voTPo(v));
	}
	
	public void modifyCoupon(String id,boolean status, String useDate){
		if(!id.equals(""))
			service.ModifyCoupon(id,status,useDate);
	}
	public String[] getCouponId(int n){
		 NumberFormat nf = NumberFormat.getInstance();
	     nf.setMinimumIntegerDigits(5); 
	     nf.setGroupingUsed(false);
	     String date=getDate.getdate();
		String[] result=new String[n];
		ArrayList<CouponVO> cou=getAllCoupon();
		if(cou==null){
			for(int i=1;i<=n;i++)
				result[i-1]=date+nf.format(i);
		}else{
			int size=cou.size();
			String id=cou.get(size-1).getId();
			
		if(date.equals(id.substring(0, 8)))
		{	id=id.substring(8);
			double d=Double.parseDouble(id)+1;
			for(int i=0;i<n;i++)
				result[i]=date+nf.format(d++);}
		else{
			for(int i=1;i<=n;i++)
				result[i-1]=date+nf.format(i);
		}
		}
		
		return result;
	}

}
