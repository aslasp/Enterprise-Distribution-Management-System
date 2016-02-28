package vo;

public class ReceiptMessageVO {
	 int tag;//单据类型  0经理  1销售   2财务   3库存
	 String info;
	 public ReceiptMessageVO(int t,String info){
		 this.tag=t;
		 this.info=info;
	 }
	public int getTag() {
		return tag;
	}
	public String getInfo() {
		return info;
	}

}
