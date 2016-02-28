package member;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businesslogic.memberbl.MemAccountInfo;
import businesslogic.memberbl.MemBaseInfo;
import businesslogic.memberbl.MemContactInfo;
import businesslogic.memberbl.Member;
import po.MemberPO;
import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import vo.MemberVO;
import junit.framework.TestCase;

public class Member_ATest extends TestCase{
	MemberVO memA,memB,memC;
	Member member;
	//一组合法，两组不合法
	public void setUp() throws Exception{
		memA=new MemberVO(new MemBaseInfo(MemberType.JHS,MemberLevel.ONE,"JHS-0000007",
				"客户A",0,"业务员甲"),new MemAccountInfo(100000,0,0),new MemContactInfo(
						"15850698062","南大仙林校区","210046","a@164.com"));
		memB=new MemberVO(new MemBaseInfo(MemberType.XSS,MemberLevel.FIVE,"XSS-0000007",
				"客户B",0,"业务员甲"),new MemAccountInfo(100000,0,0),new MemContactInfo(
				"158","南大仙林校区","2106","a@164.com"));
		memC=new MemberVO(new MemBaseInfo(MemberType.XSS,MemberLevel.FIVE,"XSS-0000008",
				"客户C",0,"业务员甲"),new MemAccountInfo(100000,0,0),new MemContactInfo(
				"15850698062","南大仙林校区","210046","a164.com"));
		member=new Member();
		

		
	}	
	//TUS1-1  成功添加客户
	public void test_1(){
		boolean testResult=false;
		member.addMember(memA);
		MemberVO vo=member.findById(memA.getMemberID());
		if(vo!=null)
			testResult=true;
			
		assertTrue(testResult);
		
		
	}
	
	
	
}
