package User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import po.UserPO.UserJob;
import junit.framework.TestCase;
import vo.UserVO;
import businesslogic.userbl.User;
import businesslogicservice.userblservice.UserBLService;

//11-17 By jin 肯定失败 User没有语句输出
public class UserBLService_DriverTest extends TestCase {
	private UserBLService userBLService;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream bytes = null;

	public void setUp() throws Exception {
		UserBLService userbl_stub = new User();
		userBLService = userbl_stub;
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	public void tearDown() {
		System.setOut(console);
	}

	//String name, String ID, String password, UserJob job,double grades)
	public void testUserBLDrive() {
		UserVO vo = new UserVO("jc小金金","JL-00001","123456",UserJob.MANAGER,100);
		int resultAdd = userBLService.addUser(vo);
		int resultMod = userBLService.modifyUser(vo);
		int resultDel = userBLService.deleteUser(vo.getID());
		int resultLog = userBLService.login(null, null);
		userBLService.showUser(null);

		assertEquals(0, resultAdd);
		assertEquals(0, resultMod);
		assertEquals(0, resultDel);
		assertEquals(0, resultLog);

		assertEquals("Add user succeed!" + line
				+ "Modify user succeed!" + line
				+ "delete user succeed!" + line
				+ "Login succeed!" + line
				+ "Show users succeed!" + line,bytes.toString());

	}
}
