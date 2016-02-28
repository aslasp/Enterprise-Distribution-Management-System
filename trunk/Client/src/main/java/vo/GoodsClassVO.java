package vo;

public class GoodsClassVO {
	String name;
	String upClassName;

	public GoodsClassVO(String name, String upClass) {
		this.name = name;
		this.upClassName = upClass;
	}

	public String getName() {
		return name;
	}

	public String getUpClassName() {
		return upClassName;
	}
}
