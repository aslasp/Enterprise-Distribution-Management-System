package po;

import java.io.Serializable;

public class ReceiptMessagePO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int tag;//单据类型  0经理  1销售   2财务   3库存
	 String info;
	 public ReceiptMessagePO(int t,String info){
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
