package businesslogic.promotionbl;

import java.util.ArrayList;

import po.PromotionPO;
import po.PromotionPO.PromotionType;
import vo.DiscountProVO;
import vo.GiftCouponProVO;
import vo.GiftGoodsProVO;
import vo.PackProVO;
import vo.PromotionVO;
import vo.SaleVO;
import businesslogicservice.promotionblservice.PromotionBLService;
import businesslogicservice.promotionblservice.PromotionMatchService;
import businesslogicservice.promotionblservice.PromotionViewService;

public class promotionController implements PromotionViewService,
		PromotionMatchService, PromotionBLService {

	private static giftCouponPro giftcoupon;
	private static giftGoodPro giftgood;
	private packPro pack;
	private discountPro discount;

	public promotionController() throws Exception {
		giftcoupon = new giftCouponPro();
		giftgood = new giftGoodPro();
		pack = new packPro();
		discount = new discountPro();

	}

	public String getNewID(PromotionType type) {
		// TODO Auto-generated method stub
		switch (type) {
		case GIFTCOUPON:
			return giftcoupon.getNewID();
		case GIFTGOODS:
			return giftgood.getNewID();
		case PACK:
			return pack.getNewID();
		default:
			return discount.getNewID();
		}
	}

	public int Delete(String id, PromotionType type) {
		// TODO Auto-generated method stub
		switch (type) {
		case GIFTCOUPON:
			return giftcoupon.Delete(id);
		case GIFTGOODS:
			return giftgood.Delete(id);
		case PACK:
			return pack.Delete(id);
		default:
			return discount.Delete(id);
		}
	}

	public int Add(PromotionVO vo) {
		// TODO Auto-generated method stub
		switch (vo.getType()) {
		case GIFTCOUPON:
			return giftcoupon.Add(vo);
		case GIFTGOODS:
			return giftgood.Add(vo);
		case PACK:
			return pack.Add(vo);
		default:
			return discount.Add(vo);
		}
	}

	public int Modify(PromotionVO vo) {
		// TODO Auto-generated method stub
		if (vo == null)
			return 1;
		switch (vo.getType()) {
		case GIFTCOUPON:
			return giftcoupon.Modify(vo);
		case GIFTGOODS:
			return giftgood.Modify(vo);
		case PACK:
			return pack.Modify(vo);
		default:
			return discount.Modify(vo);
		}
	}

	// 促销策略适配 优先级 商品 代金券 折扣 特价包
	public SaleVO Match(SaleVO vo) {
		// TODO Auto-generated method stub
		PromotionVO pro = null;
		if ((pro = giftgood.Match(vo)) != null) {
			vo.setProid(pro.getId());
			return vo;
		} else if ((pro = giftcoupon.Match(vo)) != null) {
			vo.setProid(pro.getId());
			return vo;
		} else if ((pro = discount.Match(vo)) != null) {
			vo.setProid(pro.getId());
			vo = discount.excute(pro, vo);
			return vo;
		} else if ((pro = pack.Match(vo)) != null) {
			vo.setProid(pro.getId());
			vo = pack.excute(pro, vo);
			return vo;
		}
		return vo;
	}

	public ArrayList<PromotionVO> Show() {
		// TODO Auto-generated method stub
		promotion promotion = null;
		try {
			promotion = new promotion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return promotion.Show();
	}

	@Override
	public double getCouponValue(String id) {
		// TODO Auto-generated method stub
		return giftcoupon.getCouponValue(id);
	}

	public static void Excute(String proid, SaleVO vo) {
		// TODO Auto-generated method stub
		promotion pro;
		try {
			pro = new promotion();
			PromotionPO po = pro.find(proid);
			if (po != null) {
				if (po.getType() == PromotionType.GIFTGOODS) {
					giftgood.Excute(po, vo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public PromotionVO find(String id, PromotionType type) {
		// TODO Auto-generated method stub
		switch (type) {
		case PACK:
			return pack.findByID(id);
		case DISCOUNT:
			return discount.findByID(id);
		case GIFTGOODS:
			return giftgood.findByID(id);
		default:
			return giftcoupon.findByID(id);
		}

	}

	
	//----各类型单独查找----------
	public GiftCouponProVO gpFindByID(String id) {
		// TODO Auto-generated method stub
		try {

			return (GiftCouponProVO) giftcoupon.findByID(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public GiftGoodsProVO ggFindByID(String id) {
		// TODO Auto-generated method stub
		try {

			return (GiftGoodsProVO) giftgood.findByID(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DiscountProVO dtFindByID(String id) {
		// TODO Auto-generated method stub
		try {

			return (DiscountProVO) discount.findByID(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PackProVO pFindByID(String id) {
		// TODO Auto-generated method stub
		try {

			return (PackProVO) pack.findByID(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
