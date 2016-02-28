package Presentation.stockui.goodsmanage;

import java.io.Serializable;
import java.util.ArrayList;

public class GoodsClassNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String UpClass;
	public ArrayList<GoodsClassNode> children = new ArrayList<GoodsClassNode>();

	public GoodsClassNode(String name, String UpClass) {
		this.name = name;
		this.UpClass = UpClass;
	}

	public String getName() {
		return name;
	}

	public String getUpClass() {
		return UpClass;
	}

	public ArrayList<GoodsClassNode> getChildren() {
		return children;
	}
}
