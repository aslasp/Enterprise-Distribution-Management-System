package businesslogicservice.promotionblservice;


import vo.PromotionVO;
import vo.SaleVO;

public interface PromotionMatchService {
	public SaleVO Match(SaleVO vo);
	public double getCouponValue(String id);
}
