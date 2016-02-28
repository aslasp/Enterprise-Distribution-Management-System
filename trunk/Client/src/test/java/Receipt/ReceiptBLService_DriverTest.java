package Receipt;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import po.ReceiptPO.ReceiptType;
import junit.framework.TestCase;
import vo.ReceiptMessageVO;
import vo.ReceiptVO;
import businesslogic.receiptbl.Receipt;
import businesslogic.receiptbl.ReceiptController;
import businesslogic.receiptbl.ReceiptMessage;
import businesslogic.utilitybl.getDate;
import businesslogicservice.receiptblservice.ReceiptBLService;




public class ReceiptBLService_DriverTest extends TestCase{
	private ReceiptBLService receiptblservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	public void setUp() throws Exception{
		ReceiptBLService receiptbl_stub=new ReceiptController();
		receiptblservice=receiptbl_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));
		
	}
	
	public void tearDown(){
		System.setOut(console);
	}
	
	public void testReceiptBLDrive() throws Exception{
	//	int resultAdd=receiptblservice.Add(new ReceiptVO("JHD-20141015-00001",ReceiptType.SALE,
			//	"JL-00001",0,1));
		ReceiptVO recvo =new ReceiptVO("JHD-20141015-00001",ReceiptType.SALE,"JL-00001",0,1);
		//int resultMod=receiptblservice.Modify("JHD-20141015-00001");
		//if(resultMod==1) System.out.println("该单据无法修改！");
		String[] id={"SKD-20141012-00002","FKD-20141015-00003"};
		int resultBat=receiptblservice.Batch(id,2);
		int resultApr=receiptblservice.Approve("XJFYD-20141013-00001",1);
		ReceiptVO vo=new ReceiptVO("JHD-20141015-00001",ReceiptType.SALE,
				"JL-00001",0,1);
		ReceiptMessage message = new ReceiptMessage();
		message.addMessage(new ReceiptMessageVO(0,getDate.getAllDate()+"新单据"+recvo.getId()+"待审批！"));
		Receipt receipt=new Receipt();
		receipt.Send(recvo.getId());
		receipt.Reply("XJFYD-20141013-00001",ReceiptType.CASHLIST , resultApr);
		//receiptblservice.Refresh();
		//receiptblservice.Refresh();	
		assertEquals(0,resultApr);
		//assertEquals(0,resultMod);
		assertEquals(0,resultBat);
		assertEquals("Add Receipt Success!"+line
				    +"Modify Receipt (id=JHD-20141015-00001) Success!"+line
				    +"Batch Receipt(id=SKD-20141012-00002) Success!"+line
				    +"Batch Receipt(id=FKD-20141015-00003) Success!"+line
				    +"Approve Receipt(id=XJFYD-20141013-00001) Success!"+line
				    +"Send Receipt Success!"+line
				    +"Reply to the User(id=13020001) Success!"+line
				    +"View All Receipt Success!"+line
				    +"Refresh Receipt Success!"+line
				,bytes.toString());
		
		
	}
	
}
