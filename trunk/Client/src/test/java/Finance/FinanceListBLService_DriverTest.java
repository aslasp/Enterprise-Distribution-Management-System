package Finance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CollectionVO;
import vo.TransferItemVO;
import businesslogic.financebl.Collection;
import businesslogicservice.financeblservice.listblservice.CollectionBLService;
//import businesslogicservice.financeblservice.listblservice.CollectionBLService_stub;
import junit.framework.TestCase;

public class FinanceListBLService_DriverTest extends TestCase{

	private CollectionBLService listblservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp(){
		CollectionBLService listbl_stub;
		try {
			listbl_stub = new Collection();
			listblservice=listbl_stub;
			bytes=new ByteArrayOutputStream();
			console=System.out;
			System.setOut(new PrintStream(bytes));	
			System.setOut(new PrintStream(bytes));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void tearDown(){
		System.setOut(console);
	}
	
	public void testListBLdrive() throws RemoteException {
		//String id,String MID,String Mname,String user,ArrayList<TransferItemVO> d,double e,int istatus,int ihurry
		ArrayList<TransferItemVO> tra=new ArrayList<TransferItemVO>();
		tra.add(new TransferItemVO("小马甲",100,"我在做测试"));
		CollectionVO vo1=new CollectionVO("SKD-20141230-09999", "JHS-0000001", "金金", "CW-00001", tra, 0, 0, 0);
		int result1=listblservice.createCollection(vo1);
		assertEquals(0,result1);
	//	assertEquals("Create collection success!"+line
    //                  ,bytes.toString());		
		CollectionVO result2=listblservice.findByID("SKD-20141230-09999");
		assertEquals(result2.getId(),"SKD-20141230-09999");		
	}
	
}
