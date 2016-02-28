package Finance;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import vo.BSLVO;
import businesslogicservice.financeblservice.BSLblservice.FinanceBSLBLService;
import businesslogicservice.financeblservice.BSLblservice.FinanceBSLBLService_stub;


public class FinanceBSLBLService_DriverTest extends TestCase{
	private FinanceBSLBLService BSLblservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp(){
		FinanceBSLBLService BSLbl_stub=new FinanceBSLBLService_stub();
		BSLblservice=BSLbl_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));	
	}
	
	public void tearDown(){
		System.setOut(console);
	}
	
	public void testBSLBLDrive(){
		BSLVO vo=new BSLVO();
		BSLblservice.viewBSL();
		int result=BSLblservice.export(vo);		
		assertEquals(0,result);
		assertEquals("View businessstatementlist success!"+line
				+"Export businessstatementlist success!"+line,bytes.toString());		
	}
}
