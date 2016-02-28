package vo;

import po.ReceiptPO.ReceiptType;

public class StockErrorVO {
	String id;
	String goodsName;
	String goodsSize;
	String stockID;
	ReceiptType type;
	String date;

	public StockErrorVO(String id, String goodsName, String goodsSize,
			String stockid, String date) {
		this.id = id;
		this.goodsName = goodsName;
		this.goodsSize = goodsSize;
		this.stockID = stockid;
		type = ReceiptType.STOCKERROR;
		this.date = date;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public String getId() {
		return id;
	}

	public String getStockID() {
		return stockID;
	}

	public ReceiptType getType() {
		return type;
	}

	public String getDate() {
		return date;
	}
}