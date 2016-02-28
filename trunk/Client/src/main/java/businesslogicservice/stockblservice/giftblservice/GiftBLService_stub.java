package businesslogicservice.stockblservice.giftblservice;

import java.util.ArrayList;

import po.GiftPO;
import vo.GiftVO;

public class GiftBLService_stub implements GiftBLService {

	public int addGift(GiftVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("add gift succeed!");
		return 0;
	}

	public int dealGift(GiftVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("deal gift succeed!");
		return 0;
	}

	public double getAccount(GiftVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("get gift account succeed!");
		return 0;
	}

	public double getGiftCost() {
		// TODO 自动生成的方法存根
		System.out.println("return gift cost succeed!");
		return 0;
	}

	public ArrayList<GiftPO> getGiftList(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		System.out.println("get gift list succeed!");
		return null;
	}

	public int getGiftNum(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		System.out.println("get gift num succeed!");
		return 0;
	}

	public double getGiftMoney(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		System.out.println("get gift money succeed!");
		return 0;
	}

	public ArrayList<GiftVO> getGiftList() {
		// TODO 自动生成的方法存根
		System.out.println("get gift num succeed!");
		return null;
	}

	public GiftVO findByID(String id) {
		// TODO 自动生成的方法存根
		System.out.println("find gift By id succeed!");
		return null;
	}

	@Override
	public double getGiftCost(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		System.out.println("get gift cost succeed!");
		return 0;
	}

	@Override
	public int modify(GiftVO newVO) {
		// TODO 自动生成的方法存根
		return 0;
	}

}
