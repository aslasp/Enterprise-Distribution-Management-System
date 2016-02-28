package Receipt;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import businesslogic.receiptbl.ReceiptController;
import junit.framework.TestCase;
import po.ReceiptPO;
import dataservice.receiptdataservice.ReceiptDataService;

public class ReceiptDataService_DriverTest extends TestCase{
	private ReceiptDataService receiptdataservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp() throws Exception{
		ReceiptDataService receiptdata_stub=(ReceiptDataService) new ReceiptController();
		receiptdataservice=receiptdata_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));
		
	}
	public void tearDown(){
		System.setOut(console);
	}
	public void testReceiptDatadrive() throws RemoteException{
		ReceiptPO po=new ReceiptPO();
	//	receiptdataservice.init();
		boolean resultAdd=false;
		if(receiptdataservice.Add(po)==null)
			resultAdd=true;
		int resultMod=receiptdataservice.Modify(po);
		ReceiptPO receipt=receiptdataservice.findById("SKD-20141015-00001");
		receiptdataservice.showAll();
		assertEquals(true,resultAdd);
		assertEquals(0,resultMod);
		assertEquals("init Success!"+line+
				"Add Receipt Success!"+line+
				"Modify Receipt Success!"+line+
				"find Receipt (id=SKD-20141015-00001) Success!"+line+
				"Show All Receipt Success!"+line,bytes.toString()
				);
	}
}
