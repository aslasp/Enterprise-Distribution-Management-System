package User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import junit.framework.TestCase;
import po.UserPO;
import dataservice.userdataservice.UserDataService;
import dataservice.userdataservice.UserDataService_stub;

public class UserDataService_DriverTest extends TestCase {
	private UserDataService userDataService;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream bytes = null;

	public void setUp() {
		UserDataService userDataService_stub = new UserDataService_stub();
		userDataService = userDataService_stub;
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	public void tearDown() {
		System.setOut(console);
	}

	public void testStockGoodsDataDrive() throws RemoteException {
		UserPO po = new UserPO(null, null, null, null, 0);
		int resultAdd = userDataService.add(po);
		int resultMod = userDataService.modify(po);
		int resultDel = userDataService.delete(po.getID());
		// int resultLog = userDataService.login(null, null);
		userDataService.showUserInfo(null);

		assertEquals(0, resultAdd);
		assertEquals(0, resultMod);
		assertEquals(0, resultDel);
		// assertEquals(0, resultLog);

		assertEquals("add user in file succeed!" + line
				+ "modify user in file succeed!" + line
				+ "delete user in file succeed!" + line + "Login succeed!"
				+ line + "show users in file succeed!" + line, bytes.toString());
	}
}
