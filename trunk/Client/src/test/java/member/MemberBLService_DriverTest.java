package member;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

import org.junit.Test;

import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import vo.MemberVO;
import businesslogic.memberbl.MemAccountInfo;
import businesslogic.memberbl.MemBaseInfo;
import businesslogic.memberbl.MemContactInfo;
import businesslogic.memberbl.Member;
import businesslogicservice.memberblservice.MemberBLService;


public class MemberBLService_DriverTest extends TestCase {
	private MemberBLService m;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream beos = null;

	// --------------------------------------------
	public void setUp() throws Exception {
		m = new	Member();
		beos = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(beos));
	}

	public void tearDown() {
		System.setOut(console);
	}

	@Test
	public void test() {
		MemberVO vo =new MemberVO(new MemBaseInfo(MemberType.JHS,MemberLevel.ONE,"JHS-0000007",
				"客户A",0,"业务员甲"),new MemAccountInfo(100000,0,0),new MemContactInfo(
						"15850698062","南大仙林校区","210046","a@164.com"));
		String message = "";
		int resultAdd = m.addMember(vo);
		int resultDel = m.deleteMember(vo.getMemberID());
		int resultMod = m.modifyMember(vo);
		m.findMember(message);
		m.showMembers();
		assertEquals(0, resultAdd);
		assertEquals(0, resultDel);
		assertEquals(0, resultMod);
		assertEquals("Add Member Successfully!" + line
				+ "Delete Member Successfully!" + line
				+ "Modify Member Successfully!" + line
				+ "Find Member Successfully!" + line
				+ "Show Members Successfully!"+line,beos.toString());
	}

}
