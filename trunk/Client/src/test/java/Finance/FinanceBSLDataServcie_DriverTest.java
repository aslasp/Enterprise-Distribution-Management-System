package Finance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import po.BSLPO;
import dataservice.financedataservice.BSLdataservice.FinanceBSLDataService;
import dataservice.financedataservice.BSLdataservice.FinanceBSLDataService_stub;
import junit.framework.TestCase;

public class FinanceBSLDataServcie_DriverTest extends TestCase{
	private FinanceBSLDataService BSLdataservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp(){
		FinanceBSLDataService BSLdata_stub=new FinanceBSLDataService_stub();
		BSLdataservice=BSLdata_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));	
	}
	
	public void tearDown(){
		System.setOut(console);
	}
	
	public void testBSLDataDrive(){
		BSLPO vo=new BSLPO();
		BSLdataservice.viewBSL();
		int result=BSLdataservice.export(vo);		
		assertEquals(0,result);
		assertEquals("View businessstatementlist success!"+line
				+"Export businessstatementlist success!"+line,bytes.toString());		
	}
}
