package Finance;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import java.rmi.RemoteException;

import businesslogicservice.financeblservice.initblservice.FinanceInitBLService;
import businesslogicservice.financeblservice.initblservice.FinanceInitBLService_stub;

import vo.BeginInfoVO;

public class FinanceInitBLService_DriverTest extends TestCase{
	private FinanceInitBLService initblservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp(){
		FinanceInitBLService initbl_stub=new FinanceInitBLService_stub();
		initblservice=initbl_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));	
		System.setOut(new PrintStream(bytes));
		
	}
	public void tearDown(){
		System.setOut(console);
	}
	
	public void testInitBLdrive() throws RemoteException {
		BeginInfoVO vo=new BeginInfoVO();
		int result=initblservice.initInfo("heheda",vo);
		initblservice.getInfo(null);
		assertEquals(0,result);
		assertEquals("Initial stock success!"+line
				+"Show beginInformation success!"+line,bytes.toString());		
	}
	

}
