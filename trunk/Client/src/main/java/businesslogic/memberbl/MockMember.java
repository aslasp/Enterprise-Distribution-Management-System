package businesslogic.memberbl;

import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;


public class MockMember extends  Member{
	private MemberType mType;
	private MemberLevel mLevel;
	private String memberID, name;
	private double MaxOwe, toReceive, toPay;
	private double points;

	
	public MockMember(String memberID, MemberType mType, MemberLevel mLevel,
			String name,double MaxOwe	) throws Exception{
		super();
		this.memberID = memberID;
		this.mType = mType;
		this.mLevel = mLevel;
		this.name = name;
		this.MaxOwe = MaxOwe;
		this.toReceive = 0;
		this.toPay = 0;
		this.points=15001;
		
	}


/*客户应收变化*/
	public void updateToReceive(double newData) {
		this.toReceive+=newData;
	
		
	}

	public void updateToPay(double newData) {
		this.toPay+=newData;
	}
	
	public MockMember find(String id){
		if(this.memberID.equals(id))
			return this;
		else 
			return null;
	}
	
	public double getDiscount(){
		return 0.95;
	}
	
	public void updatePoint(double price){
		points+=(price/100);
		
	}
	public double getToReceive(){
		return this.toReceive;
	}
	
	public double getToPay(){
		return this.toPay;
	}
	public double getPoints(){
		return this.points;
	}
}
