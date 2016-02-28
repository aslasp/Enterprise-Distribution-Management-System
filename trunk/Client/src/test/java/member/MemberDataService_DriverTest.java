package member;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

import org.junit.Test;

import po.MemberPO;
import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import dataservice.memberdataservice.MemberDataService;
import dataservice.memberdataservice.MemberDataService_stub;

public class MemberDataService_DriverTest extends TestCase{
	private MemberDataService m;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream beos = null;

	// --------------------------------------------
	public void setUp() {
		m = new MemberDataService_stub();
		beos = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(beos));
	}

	public void tearDown() {
		System.setOut(console);
	}

	@Test
	public void test() {
		MemberPO po = new MemberPO("JHS-0000001", MemberType.JHS, MemberLevel.ONE,"马建国","23333","深圳市蛇口区",
				"210046","a@qq.com","一米五小公主", 100, 0,0,0);;
		String message = "";
		m.add(po);
		m.delete(po.getMemberID());
		m.modify(po);
		m.find(message);
		assertEquals("Add Successfully!" + line
				+ "Delete Successfully!" + line
				+ "Modify Successfully!" + line
				+ "Find Successfully!" + line,beos.toString());
	}

}
