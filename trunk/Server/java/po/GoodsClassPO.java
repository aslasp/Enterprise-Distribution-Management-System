package po;

import java.io.Serializable;

public class GoodsClassPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ID;
	String name;
	String upClass;

	public GoodsClassPO(String id,String name, String upClass) {
		this.ID=id;
		this.name = name;
		this.upClass = upClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpClass() {
		return upClass;
	}

	public void setUpClass(String upClass) {
		this.upClass = upClass;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
