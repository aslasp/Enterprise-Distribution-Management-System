package po;

import java.io.Serializable;

public class StockErrorPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String goodsName;
	String goodsSize;

	public StockErrorPO(String goodsName, String goodsSize) {
		this.goodsName = goodsName;
		this.goodsSize = goodsSize;
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

}
