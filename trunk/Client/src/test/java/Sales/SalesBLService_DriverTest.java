package Sales;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import po.CommodityPO;
import po.SalePO;
import vo.CommodityVO;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import vo.SaleReturnVO;
import vo.SaleVO;
import businesslogic.salesbl.SalesController;
import businesslogicservice.salesblservice.PurchaseBLService;
import businesslogicservice.salesblservice.SalesBLService;

public class SalesBLService_DriverTest extends TestCase {
	private SalesBLService s;
	private PurchaseBLService p;
	String line = System.getProperty("line.separator");
	PrintStream console = null;
	ByteArrayOutputStream beos = null;

	// --------------------------------------------
	public void setUp() throws Exception {
		p=new SalesController();
		s = new SalesController();
		beos = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(beos));
	}

	public void tearDown() {
		System.setOut(console);
	}

	@Test
	public void test() {
		ArrayList<CommodityVO> commodity=new ArrayList<CommodityVO>();
		CommodityVO com=new CommodityVO("0001-SR01-0001","蓝之恋吊灯","SR01",1000,800,1,1000,800
				,"在做测试");
		commodity.add(com);
		double[] total=new double[]{100,120,110,0,110};
		double[] discount=new double[]{0,0,0,0};
		
		PurchaseVO pv =new PurchaseVO("JHD-20141208-00001", "金大大","JHS-00001","2", "XS-00002", null,
				"",0,0,1);
		//String id,String MemName,String MemID,String user, int status,
		//String info,int hurry,ArrayList<CommodityVO> list,double total,String sid,String purid
		PurchaseReturnVO prv=new PurchaseReturnVO("JHTHD-20141208-00001", "金大大","JHS-00001","XS-00002",
				0,"",1,commodity,12000,"2","JHD-20141208-00001");
		SaleVO sv=new SaleVO("金金",commodity,"XSD-20141205-00001","马建国","XSS-00001",
				"XS-00001",0,0,"","1","","",total,discount);
		//String id,String user,SaleVO s,int status,
		//String info,int hurry
		SaleReturnVO srv=new SaleReturnVO("XSD-20141205-00001","XS-00001",sv,0,"",0);
		String message="";
		//
		s.addSale(sv);
		sv.setCouponid("2014120800001");
		s.modifySale(sv);
		s.showSale();
		s.findSale(message, "客户");
		//
		p.addPurchaseReturn(prv);
		prv.setTotalInAll(12222);
		p.modifyPurchaseReturn(prv);
		p.showPurchaseReturn();
		message="2";
		p.findPurchaseReturn(message,"仓库");
		//
		p.addPurchase(pv);
		pv.setAdjustCost(1200);
		p.modifyPurchase(pv);
		p.showPurchase();
		p.findPurchase(message, "仓库");
		//
		s.addSaleReturn(srv);
		s.modifySaleReturn(srv);
		s.showSaleReturn();
		message="金金";
		s.findSaleReturn(message,"业务员");
		//
		assertEquals("Purchase Successfully!"+ line
				+"Modify Purchase Successfully!"+ line
				+"showPurchase!"+ line
				+"findPurchase!"+ line
				+"PurchaseReturn Successfully!"+ line
				+"Modify PurchaseReturn Successfully!"+ line
				+"showPurchaseReturn!"+ line
				+"findPurchaseReturn!"+ line
				+"Sale Successfully!"+ line
				+"Modify Sale Successfully!"+ line
				+"showSale!"+ line
				+"findSale!"+ line
				+"SaleReturn Successfully!"+ line
				+"Modify SaleReturn Successfully!"+ line
				+"showSaleReturn!"+ line
				+"findSaleReturn!"+ line,beos.toString());
		
	}

}
