package businesslogic.memberbl;

import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;

public class MemBaseInfo {
	 public MemberType mType; 
	 public MemberLevel mLevel;
	 public String memberID,name;
	 public double points;
	 public String defaultClerk;
	public MemBaseInfo(MemberType mType, MemberLevel mLevel, String memberID,
			String name, double points,String clerk) {
		
		this.mType = mType;
		this.mLevel = mLevel;
		this.memberID = memberID;
		this.name = name;
		this.points = points;
		this.defaultClerk=clerk;
	}
	 
	
}
