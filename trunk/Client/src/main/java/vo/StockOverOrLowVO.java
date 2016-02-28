package vo;

import po.ReceiptPO.ReceiptType;

public class StockOverOrLowVO extends ReceiptVO {
	String goodsName;
	String size;
	int num;
	int exactNum;
	int gap;

	public StockOverOrLowVO(String id, String userID, ReceiptType type,
			int status, int hurry, String info, String goodsName, String size,
			int num, int exactNum) {
		super(id, "", "", userID, type, status, hurry, info);
		this.goodsName = goodsName;
		this.size = size;
		this.num = num;
		this.exactNum = exactNum;
		this.gap = num - exactNum;

	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getSize() {
		return size;
	}

	public int getNum() {
		return num;
	}

	public int getExactNum() {
		return exactNum;
	}

	public int getGap() {
		return gap;
	}

}
