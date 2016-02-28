package businesslogicservice.stockblservice.giftblservice;

import java.util.ArrayList;

import po.GiftPO;
import vo.GiftVO;

public interface GiftBLService {
	public int addGift(GiftVO vo);

	public int dealGift(GiftVO vo);

	public int modify(GiftVO newVO);
	
	// 商品赠送支出
	public double getGiftCost(String beginDate, String endDate);

	// 获取所有商品赠送清单
	public ArrayList<GiftVO> getGiftList();

	// 获取某段时间内的商品赠送清单
	public ArrayList<GiftPO> getGiftList(String beginDate, String endDate);

	// 获得某段时间内的商品赠送总量
	public int getGiftNum(String beginDate, String endDate);

	// 获得某段时间内的商品赠送总额
	public double getGiftMoney(String beginDate, String endDate);

	public GiftVO findByID(String id);
}
