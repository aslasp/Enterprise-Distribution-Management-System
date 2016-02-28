package businesslogic.stockbl.gift;

import java.util.ArrayList;

import po.GiftPO;
import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import vo.GiftVO;
import businesslogic.receiptbl.Receipt;
import businesslogicservice.stockblservice.giftblservice.GiftBLService;

public class GiftController extends Receipt implements GiftBLService {

	public GiftController() throws Exception {
		// TODO 自动生成的构造函数存根
	}

	public int addGift(GiftVO vo) {
		// TODO 自动生成的方法存根
		GiftReceipt receipt = new GiftReceipt(vo.getId(), vo.getMemberID(),
				vo.getMemberName(), vo.getUser(), ReceiptType.GIFT,
				vo.getHurry(), vo.getStatus(), vo.getInfo(), vo.getGiftList());
		return receipt.add();
	}

	// 系统自动生成库存赠送单并处理==
	public int autoAdd(String MemberID, String memberName, String userID,
			ArrayList<CommodityVO> cList) {
		GiftReceipt tp = null;
		try {
			tp = new GiftReceipt();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String id = tp.getNewID();
		GiftReceipt receipt = new GiftReceipt(id, MemberID, memberName, userID,
				ReceiptType.GIFT, 0, 3, "", cList);
		receipt.add();
		// 处理
		GiftManage manage = new GiftManage();
		manage.excute(receipt);

		return 0;
	}

	public int dealGift(GiftVO vo) {
		// TODO 自动生成的方法存根
		GiftManage manage = new GiftManage();
		if (vo.getStatus() == 1) {
			return 80;
		}
		if (vo.getStatus() == 2) {
			return 81;
		}
		GiftReceipt receipt = new GiftReceipt(vo.getId(), vo.getMemberID(),
				vo.getMemberName(), vo.getUser(), ReceiptType.GIFT,
				vo.getHurry(), vo.getStatus(), vo.getInfo(), vo.getGiftList());
		return manage.excute(receipt);
	}

	// 修改，传入的参数为新的VO
	public int modify(GiftVO newVO) {
		// TODO 自动生成的方法存根
		GiftReceipt receipt = new GiftReceipt(newVO.getId(),
				newVO.getMemberID(), newVO.getMemberName(), newVO.getUser(),
				newVO.getType(), newVO.getHurry(), newVO.getStatus(),
				newVO.getInfo(), newVO.getGiftList());
		return receipt.Modify(newVO.getId());
	}

	public double getGiftCost(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		GiftManage manage = new GiftManage();
		return manage.getGiftCost(beginDate, endDate);
	}

	public ArrayList<GiftPO> getGiftList(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		GiftManage manage = new GiftManage();
		return manage.getGiftList(beginDate, endDate);
	}

	public int getGiftNum(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		GiftManage manage = new GiftManage();
		return manage.getGiftNum(beginDate, endDate);
	}

	public double getGiftMoney(String beginDate, String endDate) {
		// TODO 自动生成的方法存根
		GiftManage manage = new GiftManage();
		return manage.getGiftMoney(beginDate, endDate);
	}

	public ArrayList<GiftVO> getGiftList() {
		// TODO 自动生成的方法存根
		GiftManage manage = new GiftManage();
		return manage.getGiftList();
	}

	public GiftVO findByID(String id) {
		GiftManage manage = new GiftManage();
		return manage.findByID(id);
	}
}
