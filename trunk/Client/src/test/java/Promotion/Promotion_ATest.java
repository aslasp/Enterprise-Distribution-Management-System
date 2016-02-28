package Promotion;

import java.util.ArrayList;

import po.GiftCouponProPO;
import po.GiftGoodProPO;
import po.MemberPO.MemberLevel;
import po.PromotionPO;
import po.PromotionPO.PromotionType;
import vo.CommodityVO;
import vo.CouponVO;
import vo.DiscountProVO;
import vo.GiftCouponProVO;
import vo.GiftGoodsProVO;
import vo.PackProVO;
import vo.PackVO;
import vo.PromotionVO;
import businesslogic.promotionbl.giftGoodPro;
import businesslogic.promotionbl.promotionController;
import junit.framework.TestCase;

//TUS1 promotion
public class Promotion_ATest extends TestCase{
	promotionController controll;
	GiftGoodsProVO  goodpro;
	String startDate,endDate;
	ArrayList<CommodityVO> commodity;
	public void setUp() throws Exception{
		controll=new promotionController();
		commodity=new ArrayList<CommodityVO>();
		
	}
	
	
	//TUS1-1  正常添加一条赠品促销策略
	public void test_1(){
		CommodityVO vo=new CommodityVO("0001-SR01-0001","蓝之恋吊灯","SR01",1000,800,1,1000,800
				,"在做测试");
		startDate="20141201";endDate="20141212";
		commodity.add(vo);
		goodpro=new GiftGoodsProVO("SP-20141128-001",startDate,endDate,MemberLevel.ONE,commodity,
				10000);
		int result=controll.Add(goodpro);
		assertEquals(0,result);
		
		System.out.println("添加一条赠品促销完成");
		PromotionVO gfp=controll.find(goodpro.getId(), PromotionType.GIFTGOODS);
		GiftGoodsProVO v=(GiftGoodsProVO)gfp;
		CommodityVO com=v.getGiftList().get(0);
		assertEquals(vo.getID(),com.getID());
		
		
		
	}
	//String id,String startDate,String endDate,MemberLevel l,
	//ArrayList<CouponVO> coupon,double value
	//(String id,  double money,boolean isUse) 
	//TUS1-2 添加一条代金券促销策略
	public void test_2(){
		startDate="20141209";endDate="20150112";
		String id=controll.getNewID(PromotionType.GIFTCOUPON);
		//代金券id创建策略是自动生成
		CouponVO cv1=new CouponVO("",50,false);
		ArrayList<CouponVO> coupon=new ArrayList<CouponVO>();
		for(int i=0;i<10;i++)
			coupon.add(cv1);
		GiftCouponProVO couponPro=new GiftCouponProVO(id,startDate,endDate,MemberLevel.THREE,
				coupon,1000);
		int result=controll.Add(couponPro);
		PromotionVO vo=controll.find(id, PromotionType.GIFTCOUPON);
		if(vo!=null){
			GiftCouponProVO gifcvo=(GiftCouponProVO)vo;
			assertEquals(1000.0,gifcvo.getTotalValue());
		}
				
		
		
	}
	//(String id,String startDate,String endDate,MemberLevel l,
	//ArrayList<Double> count,
	//ArrayList<CommodityVO>  goods)
	
	//TUS1-3 添加一条折扣促销策略
	public void test_3(){
		CommodityVO vo=new CommodityVO("0001-SR01-0002","蓝之恋吊灯","SR01",1000,800,1,1000,800
				,"在做测试");
		commodity.add(vo);
		commodity.add(vo);
		ArrayList<Double> discount=new ArrayList<Double>();
		discount.add(0.9);discount.add(0.8);
		String id=controll.getNewID(PromotionType.DISCOUNT);
		startDate="20141209";endDate="20150112";
		DiscountProVO dis=new DiscountProVO(id,startDate,endDate,MemberLevel.ONE,
				discount,commodity);
		int result=controll.Add(dis);
		PromotionVO pro=controll.find(id, PromotionType.DISCOUNT);
		if(pro!=null){
			DiscountProVO dispro=(DiscountProVO)pro;
			discount=dispro.getCountList();
			commodity=dispro.getGoodsList();
			assertEquals(900.0,discount.get(0)*commodity.get(0).getPrice());
			assertEquals(800.0,discount.get(1)*commodity.get(1).getPrice());
			
			
		}
		
		
		
			
	}
	
	//(String id,String startDate,String endDate,MemberLevel l,
	 //PackVO pack
	//double totalValue, double packValue,
	// ArrayList<CommodityVO> combine) {
			
	//TUS1-4 添加一条特价包促销策略
	public void test_4(){
		double totalValue=1000;
		double packValue=600;
		PackVO p=new PackVO(totalValue,packValue,commodity);
		startDate="20141209";endDate="20150112";
		String id=controll.getNewID(PromotionType.PACK);
		PackProVO pack=new PackProVO(id,startDate,endDate,MemberLevel.ONE,p);
		int result=controll.Add(pack);
		PromotionVO vo=controll.find(id, PromotionType.PACK);
		if(vo!=null){
			pack=(PackProVO)vo;
			assertEquals(1000.0,pack.getTotalValue());
			assertEquals(600.0,pack.getPackValue());
		}
		
				
	}
	

	
	

}
