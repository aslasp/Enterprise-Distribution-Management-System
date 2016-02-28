package Promotion;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import po.MemberPO.MemberLevel;
import po.PromotionPO.PromotionType;
import junit.framework.TestCase;
import vo.CommodityVO;
import vo.GiftVO;
import vo.PackProVO;
import vo.PackVO;
import vo.PromotionVO;
import vo.SaleVO;
import businesslogic.promotionbl.promotionController;
import businesslogicservice.promotionblservice.PromotionBLService;
import businesslogicservice.promotionblservice.PromotionMatchService;

public class PromotionBLService_DriverTest extends TestCase{
	private PromotionBLService promotionblservice;
	String line=System.getProperty("line.separator");
	PrintStream console=null;
	ByteArrayOutputStream bytes=null;
	ArrayList<CommodityVO> commodity;
	double[] total,discount;
	public void setUp() throws Exception{
		PromotionBLService promotionbl_stub=new promotionController();
		promotionblservice=promotionbl_stub;
		bytes=new ByteArrayOutputStream();
		console=System.out;
		System.setOut(new PrintStream(bytes));
		commodity=new ArrayList<CommodityVO>();
		total=new double[]{100,120,110,0,110};
		discount=new double[]{0,0,0,0};
		
	}
	public void tearDown(){
		System.setOut(console);
	}
	
	//tring id,String startDate, String endDate, PromotionType type,
	//MemberLevel memberlevel)
	//String clerk, ArrayList<CommodityVO> salesList, String id,
	//String memberName,String memberID, String user,  int status,
	//int hurry,String info, String stockid,String proid,String couponid, double[] total,double[] discount
	public void testPromotionBLDrive() throws Exception{
		double totalValue=1000;
		double packValue=600;
		PackVO p=new PackVO(totalValue,packValue,commodity);
		String startDate="20141209";String endDate="20150112";
		String id=promotionblservice.getNewID(PromotionType.PACK);
		PromotionVO vo=new PackProVO(id,startDate,endDate,MemberLevel.ONE,p);
		CommodityVO com=new CommodityVO("0001-SR01-0001","蓝之恋吊灯","SR01",1000,800,1,1000,800
				,"在做测试");
		commodity.add(com);
		SaleVO salevo=new SaleVO("金金",commodity,"XSD-20141205-00001","马建国","XSS-00001",
				"XS-00001",0,0,"","1","","",total,discount);
		int resultAdd=promotionblservice.Add(vo);
		int resultMod=promotionblservice.Modify(vo);
		PromotionMatchService matchservice=new promotionController();
		SaleVO resultMat=matchservice.Match(salevo);
		//GiftVO giftvo=promotionblservice.Present(vo);
		promotionblservice.Show();
		assertEquals(0,resultAdd);
		assertEquals(0,resultMod);
	
		
	}
	
}
