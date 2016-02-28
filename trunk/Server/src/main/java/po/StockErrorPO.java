package po;

import java.io.Serializable;

public class StockErrorPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String goodsName;
	String goodsSize;
	String date;

	public StockErrorPO(String id, String goodsName, String goodsSize,
			String date) {
		this.id = id;
		this.goodsName = goodsName;
		this.goodsSize = goodsSize;
		this.date = date;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
