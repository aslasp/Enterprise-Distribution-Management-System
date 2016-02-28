package businesslogic.financebl;

import vo.ClauseItemVO;

public class MockcashListItem extends ClauseItemVO{
	private String name;
	private double money;
	private String  remark;
	public MockcashListItem(String name,double money,String remark){
		super(name,money,remark);
		this.name=name;
		this.money=money;
		this.remark=remark;
	
	}
	
	public double getMoney(){
		return this.money;
	}
	

}
