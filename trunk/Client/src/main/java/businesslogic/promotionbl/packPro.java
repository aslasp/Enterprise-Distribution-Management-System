package businesslogic.promotionbl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import po.DiscountProPO;
import po.GiftCouponProPO;
import po.PackProPO;
import po.PromotionPO;
import po.PromotionPO.PromotionType;
import vo.CommodityVO;
import vo.DiscountProVO;
import vo.PackProVO;
import vo.PackVO;
import vo.PromotionVO;
import vo.SaleVO;
import businesslogic.stockbl.goods.Goods;
import businesslogic.utilitybl.getDate;

//特价包促销
public class packPro extends promotion{
	
	pack pack;
	public packPro() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromotionVO findByID(String id){
		PackProPO gpo=(PackProPO)service.find(id, PromotionType.PACK);
		if(gpo==null) return null;
		else return poToVo(gpo);
	}
	
	public ArrayList<PackProVO> show(){
		ArrayList<PromotionPO> po=service.show(PromotionType.PACK);
		if(po==null)
			return null;
		else{
			ArrayList<PackProVO> vo=new ArrayList<PackProVO>();
			for(int i=0;i<po.size();i++)
				vo.add(poToVo((PackProPO)po.get(i)));
			return vo;
		}
	}

	@Override
	public int Add(PromotionVO vo) {
		// TODO Auto-generated method stub
		PackProVO v=(PackProVO) vo;
		return service.Add(voToPo(v));
	}

	@Override
	public int Modify(PromotionVO vo) {
		// TODO Auto-generated method stub
		if(vo==null) return 1;
		else {
			PackProPO po=voToPo((PackProVO)vo);
			PromotionPO p=po;
			return service.Modify(p);
		}
	}

	@Override
	public int Delete(String id) {
		// TODO Auto-generated method stub
		return service.Delete(id, PromotionType.PACK);
	}

	

	@Override
	public PromotionVO Match(SaleVO vo) {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> clist=vo.getSalesList();
		double maxvalue=0;
		PromotionVO result=null;
		if(clist==null) return null;
		else {
			ArrayList<PackProVO> pro=show();
			if(pro==null)return null;
			else{
				for(int i=0;i<pro.size();i++){
					String start=pro.get(i).getStartDate();
					String end=pro.get(i).getEndDate();
					String date=vo.getId().split("-")[1];
					if(start.compareTo(date)<=0&&end.compareTo(date)>=0){
						ArrayList<CommodityVO> prolist=pro.get(i).getPack().getCombine();
						if(searchIsMatch(prolist,clist))
						{	double packdis=pro.get(i).getTotalValue()-pro.get(i).getPackValue();
							if(maxvalue<packdis)
							{
								maxvalue=packdis;result=pro.get(i);
							}
						}
					}	
					
				}
				return result;
			}
		}
		
	}
	
	

	
	//必须包含整个特价包
	public boolean searchIsMatch(ArrayList<CommodityVO> prolist,ArrayList<CommodityVO> list){
		boolean tag=false;
		for(int i=0;i<prolist.size();i++){
			tag=false;
			for(int j=0;j<list.size();j++){
				if(list.get(j).getID().equals(prolist.get(i).getID()))
				{tag=true;break;}
			}
			if(!tag)
				return false;
		}
		return true;
	}

	
	public SaleVO excute(PromotionVO pro,SaleVO vo){
		PackProVO v=(PackProVO)pro;
		vo.setProDiscount(v.getTotalValue()-v.getPackValue());
		return vo;
	}
	
	public String getNewID() {
		// TODO Auto-generated method stub
		String id=null;
		ArrayList<PromotionPO> gpp=service.show(PromotionType.PACK);
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
		return "TJB-"+getDate.getdate()+"-"+id;
	}

	public PackProPO voToPo(PackProVO vo){
		PackProPO po=new PackProPO(vo.getId(),vo.getStartDate(),vo.getEndDate(),
				vo.getMemberlevel(),pack.voToPo(vo.getPack()));
		po.setIsMatch(vo.IsMatch());
		return po;
	}
	
	
	public PackProVO poToVo(PackProPO po){
		PackProVO vo=new PackProVO(po.getID(),po.getStartDate(),po.getEndDate(),
				po.getLevel(),pack.poToVo(po.getPackList()));
		vo.setIsMatch(po.IsMatch());
		return vo;
	}
	

}
