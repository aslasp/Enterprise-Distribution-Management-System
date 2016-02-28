package Finance;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import java.rmi.RemoteException;

import po.AccountPO;
import dataservice.financedataservice.accountdataservice.FinanceAccountDataService;
import dataservice.financedataservice.accountdataservice.FinanceAccountDataService_stub;



public class FinanceAccountDataService_DriverTest extends TestCase{
	private FinanceAccountDataService accountdataservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp(){
		FinanceAccountDataService accountdata_stub=new FinanceAccountDataService_stub();
		accountdataservice=accountdata_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));	
		System.setOut(new PrintStream(bytes));
		
	}
	public void tearDown(){
		System.setOut(console);
	}
	
	public void testAccountDatadrive() throws RemoteException {
		AccountPO po=new AccountPO();
		int resultAdd=accountdataservice.addAccount(po);
		int resultDel=accountdataservice.deleteAccount(po);
		int resultMod=accountdataservice.modifyAccount(po,"NewName");
		accountdataservice.findAccount(null);
		assertEquals(0,resultAdd);
		assertEquals(0,resultDel);
		assertEquals(0,resultMod);
		assertEquals("Add account success!"+line
				+"Delete account success!"+line
				+"Modify account success!"+line
				+"Find account success!"+line,bytes.toString());		
	}
	
}
