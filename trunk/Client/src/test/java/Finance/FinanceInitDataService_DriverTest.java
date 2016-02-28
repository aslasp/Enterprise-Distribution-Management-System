package Finance;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import java.rmi.RemoteException;

import dataservice.financedataservice.initdataservice.FinanceInitDataService;
import dataservice.financedataservice.initdataservice.FinanceInitDataService_stub;
import po.BeginInfoPO;

public class FinanceInitDataService_DriverTest extends TestCase{
	private FinanceInitDataService initdataservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp(){
		FinanceInitDataService initdata_stub=new FinanceInitDataService_stub();
		initdataservice=initdata_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));	
		System.setOut(new PrintStream(bytes));
		
	}
	public void tearDown(){
		System.setOut(console);
	}
	
	public void testInitDatadrive() throws RemoteException {
		BeginInfoPO po=new BeginInfoPO();
		int result=initdataservice.initInfo("heheda",po);
		initdataservice.getInfo(null);
		assertEquals(0,result);
		assertEquals("Initial stock success!"+line
				+"Show beginInformation success!"+line,bytes.toString());		
	}
	
}
