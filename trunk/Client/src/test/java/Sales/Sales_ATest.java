package Sales;

import java.util.ArrayList;

import junit.framework.TestCase;
import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import vo.SaleReturnVO;
import vo.SaleVO;
import businesslogic.salesbl.SalesController;

public class Sales_ATest extends TestCase{
		SalesController controller;
		ArrayList<CommodityVO> cmlist;
		CommodityVO ccm;
		public void setUp() throws Exception{
			controller=new SalesController();
			cmlist=new ArrayList<CommodityVO>();
			ArrayList<CommodityVO> commodity=new ArrayList<CommodityVO>();
			CommodityVO com=new CommodityVO("0001-SR01-0001","蓝之恋吊灯","SR01",1000,800,1,1000,800
					,"在做测试");
			commodity.add(com);
			ccm=new CommodityVO("0001-SR01-0002","蓝之恋吊灯","SR01",1000,800,1,1000,800
					,"在做测试");
			double[] total=new double[]{800,1000,1000,0,1000};
			double[] discount=new double[]{0,0,0,0};
			SaleVO sale=new SaleVO("金金",commodity,"XSD-20141205-00001","金大大","XSS-00001",
					"XS-00001",0,0,"","1","","",total,discount);
			controller.addSale(sale);
			
		}
	
	//String id,String user,SaleVO s,int status,
		//String info,int hurry)
	///TUS 1-1根据进货单 创建一条销售退货单
		public void test_1(){
			SaleVO sale=controller.SFindByID("XSD-20141205-00001");
			String id=controller.getNewID(ReceiptType.SALERETURN);
			SaleReturnVO saleReturn =new SaleReturnVO(id,"JL-00001",sale,
					0,"",1);
			int addReturnResult=controller.addSaleReturn(saleReturn);
			assertEquals(0,addReturnResult);
			SaleReturnVO saler=controller.SRFindByID(saleReturn.getId());
			if(saler!=null)
			{
				assertEquals(saleReturn.getUser(),saler.getClerk());
				assertEquals(saleReturn.getClerk(),saler.getClerk());
			}
			
			
		}
		
		
		//TUS2-1  2-2根据type找销售单
		public void test_2(){
			String message="金大大";
			String type="客户";
			ArrayList<SaleVO> salelist=controller.findSale(message, type);
			if(salelist!=null)
				for(SaleVO vo:salelist)
					assertEquals(message,vo.getMemberName());
			
			type="业务员";
			message="金金";
			salelist=controller.findSale(message, type);
			if(salelist!=null)
				for(SaleVO vo:salelist)
					assertEquals(message,vo.getClerk());
		}
		
		
		//TUS1-4修改一个销售单
		public void test_3(){
			SaleVO sale=controller.SFindByID("XSD-20141205-00001");
			ArrayList<CommodityVO> clist=sale.getSalesList();
			clist.add(ccm);
			sale.setSaleList(clist);
			int result=controller.modifySale(sale);
			assertEquals(0,result);
			sale=controller.SFindByID("XSD-20141205-00001");
			boolean isExist=false;
			for(CommodityVO v:sale.getSalesList())
				if(v.getID().equals("0001-SR01-00002"))
				{
					isExist=true;break;
				}
			assertEquals(true,isExist);
			
			
			
			
		}
}
