package Promotion;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;













import po.CouponPO;
import po.GiftCouponProPO;
import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import po.PromotionPO.PromotionType;
import vo.CouponVO;
import vo.GiftCouponProVO;
import vo.PromotionVO;
import businesslogic.promotionbl.giftCouponPro;
import businesslogic.promotionbl.promotion;
import businesslogic.promotionbl.giftGoodPro;
import businesslogic.promotionbl.promotionController;
import businesslogic.stockbl.goods.MockGoods;
import junit.framework.TestCase;

/*
 * 创建赠品促销策略(基本信息，添加赠品)，并发布
 */
public class addpromotionTest extends TestCase {
	private Date startDate, endDate;
	private DateFormat form;
	private PromotionVO promotion;
	private MemberLevel level;
	private MemberType type;
	private MockGoods good1, good2, good3, good4, good;
	private promotionController list;

	public void setUp() throws Exception {
		form = new SimpleDateFormat("yyyy/mm/dd");
		String s = "2014/12/10";
		startDate = form.parse(s);
		s = "2014/12/25";
		endDate = form.parse(s);
		level = MemberLevel.ONE;
		type = MemberType.XSS;
		list = new promotionController();
		good3 = new MockGoods();
		good4 = new MockGoods();
		good1 = new MockGoods("01010001", "飞利浦日光灯", "SR01", 10, 100, 200);
		good2 = new MockGoods("02010002", "蓝之恋吊灯", "TP05", 20, 1000, 2000);

	}

	public void testAddPromotion() throws Exception {
		// 创建一个代金券促销策略
		ArrayList<CouponVO> pp=new ArrayList<CouponVO>();
		CouponVO ppp=new CouponVO("2014120500001",100,false);
		pp.add(ppp);
		
		promotion = new GiftCouponProVO("DJQ-20141205-001","b","c",MemberLevel.ONE,pp,100);
		good3 = good1.getGoods("01010001");
		good4 = good2.getGoods("02010002");
		list.Add(promotion);
		double value = ppp.getValue();
		// 统计赠品总值
		GiftCouponProVO pro=(GiftCouponProVO) list.find(promotion.getId(), PromotionType.GIFTCOUPON);
		assertEquals(value, pro.getTotalValue());
		list.Delete(pro.getId(),PromotionType.GIFTCOUPON );
		
		assertEquals(value, pro.getTotalValue());
		// 发布促销策略
		//int resultRelease = pro.Release(pro);
		//assertEquals(0, resultRelease);
	//	assertEquals(1, pro.GetRelease());
		// 赠品列表录取新的赠品信息
		//assertEquals(good4, pro.getgiftList().get(0));
		//// 促销策略列表出现新添加的策略
		//assertEquals(pro, list.getProList().get(0));
		giftCouponPro cou=new giftCouponPro();
		double couvalue=cou.getCouponValue(ppp.getId());
		assertEquals(ppp.getValue(),couvalue);
		

	}

}
