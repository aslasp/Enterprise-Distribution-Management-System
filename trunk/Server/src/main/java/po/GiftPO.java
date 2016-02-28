package po;

import java.io.Serializable;
import java.util.ArrayList;

public class GiftPO extends ReceiptPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CommodityPO> giftList;

	public GiftPO() {

	}

	public GiftPO(String id, String memberID, String membername, String userID,
			String info, int status, int hurry, ArrayList<CommodityPO> giftList) {
		super(id, memberID, membername, userID, ReceiptType.GIFT, info, status,
				hurry);
		this.giftList = giftList;
	}

	public ArrayList<CommodityPO> getGiftList() {
		return giftList;
	}

	public void setGiftList(ArrayList<CommodityPO> giftList) {
		this.giftList = giftList;
	}
}
