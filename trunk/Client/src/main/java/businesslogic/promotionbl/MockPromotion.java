package businesslogic.promotionbl;

import java.util.ArrayList;

import po.MemberPO.MemberLevel;
import businesslogic.salesbl.Sale;
import vo.CouponVO;
import vo.GiftCouponProVO;
import vo.GoodsVO;
import vo.PromotionVO;
import vo.SaleVO;

public class MockPromotion extends promotion{
	private double minsMoney;
	private double discount;
	
	
	//折扣促销
	public MockPromotion(double money,double discount) throws Exception{
		super();
		this.minsMoney=money;
		this.discount=discount;
	}
	
	public PromotionVO Match(SaleVO sale){
		if(sale.getTotalValue()>=minsMoney){
			System.out.println("已匹配促销！");
			ArrayList<CouponVO> pp=new ArrayList<CouponVO>();
			CouponVO ppp=new CouponVO("2014120500001",100,false);
			pp.add(ppp);
			return new GiftCouponProVO("DJQ-20141205-001","b","c",MemberLevel.ONE,pp,100);
		
			}
		else return null;
		
	}
	
	public double getminsMoney(){
		return minsMoney;
	}
	
	public double getdiscount(){
		return discount;
	}

	

	
	
	
	
}
