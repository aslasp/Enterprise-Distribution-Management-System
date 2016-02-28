package businesslogic.promotionbl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import po.CommodityPO;
import po.DiscountProPO;
import po.GoodsPO;
import po.PackProPO;
import po.PromotionPO;
import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import po.PromotionPO.PromotionType;
import vo.CommodityVO;
import vo.DiscountProVO;
import vo.GoodsVO;
import vo.PackProVO;
import vo.PromotionVO;
import vo.SaleVO;
import businesslogic.salesbl.Commodity;
import businesslogic.stockbl.goods.Goods;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.utilitybl.getDate;

public  class discountPro extends promotion{
	
	//private double totalValue,discountValue;//折后总额；
	Commodity com;
	public discountPro() throws Exception {
		super();
		com=new Commodity();
	
		// TODO Auto-generated constructor stub
	}

	public PromotionVO Match(SaleVO vo){
		ArrayList<CommodityVO> clist=vo.getSalesList();
		if(clist==null) return null;
		else {
			ArrayList<DiscountProVO> pro=show();
			if(pro==null)return null;
			else{
				for(int i=0;i<pro.size();i++){
					String start=pro.get(i).getStartDate();
					String end=pro.get(i).getEndDate();
					String date=vo.getId().split("-")[1];
					if(start.compareTo(date)<=0&&end.compareTo(date)>=0){
					ArrayList<CommodityVO> prolist=pro.get(i).getGoodsList();
					for(int j=0;j<clist.size();j++){
						if(searchInList(clist.get(j).getID(),prolist))
							return pro.get(i);
					}
				}}
				return null;
			}
		}
		
	}
	
	
	public double getDiscountValue(ArrayList<CommodityVO> list,DiscountProVO v){
		ArrayList<CommodityVO> clist=v.getGoodsList();
		ArrayList<Double> dis=v.getCountList();
		double discount=0;;
		for(int i=0;i<clist.size();i++){
			for(int j=0;j<list.size();j++){
				if(clist.get(i).getID().equals(list.get(j).getID()))
					discount+=(list.get(j).getPrice()*(1-dis.get(i)));
					
			}
		}
		return discount;
	}
	
	
	public SaleVO excute(PromotionVO pro,SaleVO vo){
		ArrayList<CommodityVO> list=vo.getSalesList();
		double value=getDiscountValue(list,(DiscountProVO)pro);
	
		vo.setProDiscount(value);
		return vo;
		
	}
	
	public PromotionVO findByID(String id){
		DiscountProPO gpo=(DiscountProPO)service.find(id, PromotionType.DISCOUNT);
		if(gpo==null) return null;
		else return poToVo(gpo);
	}
	public boolean searchInList(String id,ArrayList<CommodityVO> list){
		for(int i=0;i<list.size();i++)
			if(list.get(i).getID().equals(id))
				return true;
		return false;
		
	}

	
	
	
	
	public ArrayList<DiscountProVO> show(){
		ArrayList<PromotionPO> po=service.show(PromotionType.DISCOUNT);
		if(po==null)
			return null;
		else{
			ArrayList<DiscountProVO> vo=new ArrayList<DiscountProVO>();
			for(int i=0;i<po.size();i++)
				vo.add(poToVo((DiscountProPO)po.get(i)));
			return vo;
		}
	}

	@Override
	public int Add(PromotionVO vo) {
		// TODO Auto-generated method stub
		DiscountProVO v=(DiscountProVO)vo;
		return service.Add(voToPo(v));
	}

	@Override
	public int Modify(PromotionVO vo) {
		// TODO Auto-generated method stub
		if(vo==null) return 1;
		else{
			DiscountProPO po=voToPo((DiscountProVO)vo);
			PromotionPO p=po;
			return service.Modify(p);
		}
	}

	@Override
	public int Delete(String id) {
		// TODO Auto-generated method stub
		return service.Delete(id, PromotionType.DISCOUNT);
	}
	
	//String id,String startDate,String endDate,MemberLevel l,
	//ArrayList<Double> count,
	//ArrayList<GoodsPO>  goods,double total,double discount
	private DiscountProPO voToPo(DiscountProVO vo){
		ArrayList<CommodityVO> cvlist=vo.getGoodsList();
		ArrayList<CommodityPO> cplist=com.voTPo(cvlist);
		
		DiscountProPO po=new DiscountProPO(vo.getId(),vo.getStartDate(),vo.getEndDate(),
				vo.getMemberlevel(),vo.getCountList(),cplist
				);
		po.setIsMatch(vo.IsMatch());
		return po;
	}
	
	
	public DiscountProVO poToVo(DiscountProPO po){
		ArrayList<CommodityPO> cplist=po.getGoodsList();
		ArrayList<CommodityVO> cvlist=com.poTVo(cplist);
		
		DiscountProVO vo=new DiscountProVO(po.getID(),po.getStartDate(),po.getEndDate(),
				po.getLevel(),po.getCountList(),cvlist
				);
		vo.setIsMatch(po.IsMatch());
		return vo;
	}





	public String getNewID() {
		// TODO Auto-generated method stub
		String id=null;
		ArrayList<PromotionPO> gpp=service.show(PromotionType.DISCOUNT);
		if(gpp==null) id="001";
		else{
			int i=gpp.size();
			String date=gpp.get(i-1).getID().substring(3, 11);
			if(date.equals(getDate.getdate())){
			Double d=Double.parseDouble(gpp.get(i-1).getID().substring(12))+1;
			 NumberFormat nf = NumberFormat.getInstance();
		     nf.setMinimumIntegerDigits(3); 
		     nf.setGroupingUsed(false);
		     id=nf.format(d);}
			else id="001";
		}
		return "ZQ-"+getDate.getdate()+"-"+id;
	}
	
	//String id,String startDate,String endDate,MemberLevel l,
//	ArrayList<Double> count,
	//ArrayList<GoodsVO>  goods,double total,double discount
	
}

