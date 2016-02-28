package Sales;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import po.CommodityPO;
import po.PurchasePO;
import po.PurchaseReturnPO;
import po.SalePO;
import po.SaleReturnPO;
import po.ReceiptPO.ReceiptType;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import dataservice.salesdataservice.SalesDataService;
import dataservice.salesdataservice.SalesDataService_stub;

public class SalesDataSevice_DriverTest extends TestCase{
	private SalesDataService s;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream beos = null;

	// --------------------------------------------
	public void setUp() {
		s = new SalesDataService_stub();
		beos = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(beos));
	}

	public void tearDown() {
		System.setOut(console);
	}

	@Test
	public void test() {
		ArrayList<CommodityPO> al=new ArrayList<CommodityPO>();
		CommodityPO item =new CommodityPO("0001-001-0001","飞利浦日光灯","SRO1",100,158,100,198,98,"这是个灯");
		al.add(item);
		double discount[]=new double[]{1,1,1,1};
		double total[]=new double[]{2,2,2,2,2};
        PurchasePO pp=new PurchasePO("JHD-20141208-00001", "金大大","JHS-00001","2", "XS-00002", null,
				"",0,0,1, 0);
        
        
		
		PurchaseReturnPO prp=new PurchaseReturnPO("JHTHD-20141208-00001", "JHS-00001","金大大","2",
				"JHD-20141208-00001","XS-00002",
				al,"",12000,0,0);


		SalePO sp=new SalePO("金大大",al,"XSD-20141202-00001",
				"JHS-0000001","马建国","XS-00001",1,1,"这是个销售单","02","SP-20141208-001", "", discount,total);
		
		
    	SaleReturnPO srp=new SaleReturnPO("金大大",al,"XSTHD-20141202-00001",
    			"JHS-0000001","马建国","XS-00001",1,1,"","2",discount,total);
		String message="";
		s.createPurchase(pp);
		s.createPurchaseReturn(prp);
		s.createSale(sp);
		s.createSaleReturn(srp);
		//
		s.updatePurchase(pp);
		s.updatePurchaseReturn(prp);
		s.updateSale(sp);
		s.updateSaleReturn(srp);
		//
		s.showPurchase();
		s.showPurchaseReturn();
		s.showSale();
		s.showSaleReturn();
		//
		String type="时间区间";
		message="2014120120141209";
		s.findPurchase(message,type);
		s.findPurchaseReturn(message,type);
		s.findSale(message,type);
		s.findSaleReturn(message,type);
		//
		assertEquals("createPurchase" + line
				+ "createPurchaseReturn" + line
				+ "createSale"+ line
				+ "createSaleReturn" + line
				+ "updatePurchase" + line
				+ "updatePurchaseReturn"+ line
				+ "updateSale" + line
				+ "updateSaleReturn" + line
				+ "showPurchase" + line
				+ "showPurchaseReturn"+ line
				+ "showSale" + line
				+ "showSaleReturn" + line
				,beos.toString());
	}

}