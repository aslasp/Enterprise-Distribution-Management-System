package Finance;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import dataservice.financedataservice.listdataservice.CollectionDataService;
import dataservice.financedataservice.listdataservice.CollectionDataService_stub;
import po.CollectionPO;


public class FinanceListDataService_DriverTest extends TestCase{
	private CollectionDataService listdataservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp(){
		CollectionDataService listdata_stub=new CollectionDataService_stub();
		listdataservice=listdata_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));	
		System.setOut(new PrintStream(bytes));
		
	}
	public void tearDown(){
		System.setOut(console);
	}
	
	public void testListDatadrive() throws RemoteException {
	    CollectionPO po1=new CollectionPO(line, line, line, line, null, 0, 0, 0);
;
		int result1=listdataservice.createCollection(po1);
		assertEquals(0,result1);
		assertEquals("Create collection success!"+line
			         ,bytes.toString());		
	}
	
}
