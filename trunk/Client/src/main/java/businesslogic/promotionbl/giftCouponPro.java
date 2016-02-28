package businesslogic.promotionbl;


import java.text.NumberFormat;
import java.util.ArrayList;











import javax.swing.JTabbedPane;

import businesslogic.utilitybl.getDate;
import po.CouponPO;
import po.GiftCouponProPO;
import po.PromotionPO;
import po.PromotionPO.PromotionType;
import vo.CommodityVO;
import vo.CouponVO;
import vo.GiftCouponProVO;
import vo.PromotionVO;
import vo.SaleVO;

//同类型 界面处理 放在同一行
public class giftCouponPro extends promotion{
	
	private double totalValue;
	private coupon coupon;
	public giftCouponPro() throws Exception{
		super();
		coupon=new coupon();
		
	}
	
	
	public void useCoupon(String couponid,boolean status){
		//代金券使用日期 属性 用于计算代金券支出
		 coupon.modifyCoupon(couponid,status,getDate.getdate());
		
	}
	
	public double getCouponValue(String id){
		ArrayList<CouponVO> vo=coupon.getAllCoupon();
		if(vo==null) return -1;//不存在
		for(int i=0;i<vo.size();i++){
			CouponVO cv=vo.get(i);
				if(id.equals(cv.getId())){
					if(cv.getIsUse()==true)
						return -2;//代金券已被使用
					else return cv.getValue();
				}
					
			}
		return -1;//该代金券id不存在
	}
	
	
	
	public ArrayList<GiftCouponProVO> show(){
		ArrayList<PromotionPO> po=service.show(PromotionType.GIFTCOUPON);
		if(po==null) return null;
		else{
			ArrayList<GiftCouponProVO> vo=new ArrayList<GiftCouponProVO>();
			for(int i=0;i<po.size();i++){
				GiftCouponProPO cpv=(GiftCouponProPO)po.get(i);
				vo.add(poToVo(cpv));
			}
			return vo;
		}
		
	}
	
		
	@Override
	public int Add(PromotionVO vo) {
		// TODO Auto-generated method stub
		GiftCouponProVO v=(GiftCouponProVO)vo;
		ArrayList<CouponVO> cvo=v.getCouponList();
		v.setCouponList(coupon.createNewCoupon(cvo));
		return service.Add(voToPo(v));
	}
	

	@Override
	public int Modify(PromotionVO vo) {
		// TODO Auto-generated method stub
		if(vo==null) return 1;
		else{
			GiftCouponProVO v=(GiftCouponProVO)vo;
			GiftCouponProPO po=voToPo(v);
			PromotionPO p=po;
			return service.Modify(p);
		}
	}

	@Override
	public int Delete(String id) {
		// TODO Auto-generated method stub
		return service.Delete(id, PromotionType.GIFTCOUPON);
	}
	
	
	
	//String id,String startDate,String endDate,MemberLevel l,
	//ArrayList<CouponVO> coupon,double value
	private GiftCouponProVO poToVo(GiftCouponProPO po){
		ArrayList<CouponVO> cvo=coupon.poTVo(po.getCouponList());
		GiftCouponProVO vo=new GiftCouponProVO(po.getID(),po.getStartDate(),
				po.getEndDate(),po.getLevel(),cvo,po.getTotalValue());
		vo.setIsMatch(po.IsMatch());
		return vo;
		
	}
	
	private GiftCouponProPO voToPo(GiftCouponProVO vo){
		ArrayList<CouponPO> cpo=coupon.voTPo(vo.getCouponList());
		GiftCouponProPO po=new GiftCouponProPO(vo.getId(),vo.getStartDate(),
				vo.getEndDate(),vo.getMemberlevel(),cpo,vo.getTotalValue());
		po.setIsMatch(vo.IsMatch());
		return po;
		
	}

	@Override
	public PromotionVO Match(SaleVO vo) {
		// TODO Auto-generated method stub
		double total=vo.getTotalValue();
		double maxcoupon=0;
		PromotionVO result=null;
		ArrayList<GiftCouponProVO> pro=show();
		if(pro==null)
			return null;
		else{
			for(int i=0;i<pro.size();i++)
			{String start=pro.get(i).getStartDate();
			String end=pro.get(i).getEndDate();
			String date=vo.getId().split("-")[1];
			if(start.compareTo(date)<=0&&end.compareTo(date)>=0){
				if(total>=pro.get(i).getTotalValue())
				{
					double value=getCouponValue(pro.get(i).getCouponList());
					if(maxcoupon<value)
						{maxcoupon=value;result=pro.get(i);}
				}
				
				
			}}
			if(result!=null){
				GiftCouponProVO cou=(GiftCouponProVO)result;
				if(result.IsMatch()){
					
					cou.setCouponList(coupon.createNewCoupon(cou.getCouponList()));
					
				}else{
					cou.setIsMatch(true);	
				}
				service.Modify(voToPo(cou));
			}
			
			return result;
		}
	}

	
	public double getCouponValue(ArrayList<CouponVO> vo){
		double t=0;
		for(int i=0;i<vo.size();i++)
			t+=vo.get(i).getValue();
		return t;
	}
	public String getNewID() {
		// TODO Auto-generated method stub
		String id=null;
		ArrayList<PromotionPO> gpp=service.show(PromotionType.GIFTCOUPON);
		if(gpp==null) id="001";
		else{
			int i=gpp.size();
			String date=gpp.get(i-1).getID().substring(4, 12);
			if(date.equals(getDate.getdate())){
			Double d=Double.parseDouble(gpp.get(i-1).getID().substring(13))+1;
			 NumberFormat nf = NumberFormat.getInstance();
		     nf.setMinimumIntegerDigits(3); 
		     nf.setGroupingUsed(false);
		     id=nf.format(d);}
			else id="001";
		}
		return "DJQ-"+getDate.getdate()+"-"+id;
	}

	

	
	public PromotionVO findByID(String id){
		GiftCouponProPO gpo=(GiftCouponProPO)service.find(id, PromotionType.GIFTCOUPON);
		if(gpo==null) return null;
		else return poToVo(gpo);
		
	}
	
	
	public double getCouponCost(String startDate ,String endDate){
		ArrayList<CouponVO> vo=coupon.getAllCoupon();
		double cost=0;
		if(vo==null) return 0;
		for(int i=0;i<vo.size();i++){
			String date=vo.get(i).getUseDate();
			boolean isValid=startDate.compareTo(date)<=0&&endDate.compareTo(date)>=0;
			if(isValid){
			cost+=vo.get(i).getValue();
		}
	}
		return cost;
	}

}
