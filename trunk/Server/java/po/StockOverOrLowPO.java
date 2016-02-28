package po;

import java.io.Serializable;
import java.util.Date;

import Data.receiptdata.ReceiptType;

public class StockOverOrLowPO extends ReceiptPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String goodsName;
	String size;
	int num;
	int exactNum;
	int gap;

	public StockOverOrLowPO(String id, String memberName, String memberID,
			String userID, ReceiptType type, Date createDate, int status,
			int hurry, String info, String stockid,String goodsName, String size, int num,
			int exactNum) {
		super(id, memberName, memberID, userID, type, createDate, status, hurry, info, stockid);
		this.goodsName = goodsName;
		this.size = size;
		this.num = num;
		this.exactNum = exactNum;
		this.gap = exactNum-num;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getExactNum() {
		return exactNum;
	}

	public void setExactNum(int exactNum) {
		this.exactNum = exactNum;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}
}
