package businesslogic.promotionbl;

public class MockCoupon extends coupon{

	private String id;
	private double faceValue;
	private Boolean isUse;
	
	public MockCoupon(String id,double value) throws Exception{
		super();
		this.id=id;
		this.faceValue=value;
		this.isUse=false;
	}
	
	public MockCoupon find(String ID){
		if(ID.equals(id))
			return this;
		else return null;
	}
	public void Use(){
		this.isUse=true;
	}
	
	public double GetFaceValue(){
		return this.faceValue;
	}

	public boolean GetIsUse(){
		return this.isUse;
	}
	
	public String getID(){
		return this.id;
	}
}
