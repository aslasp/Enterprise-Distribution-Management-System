package Sales;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogic.salesbl.SalesController;
import junit.framework.TestCase;

public class Purchase_ATest extends TestCase{
	SalesController controller;
	ArrayList<CommodityVO> cmlist;
	PurchaseVO purchase1,purchase2;
	
	public void setUp() throws Exception{
		controller=new SalesController();
		cmlist=new ArrayList<CommodityVO>();
		CommodityVO com=new CommodityVO("0001-SR01-0001","蓝之恋吊灯","SR01",1000,800,1,1000,800
				,"在做测试");
		cmlist.add(com);
		purchase1 = new PurchaseVO("JHD-20141208-00001", "金大大","JHS-00001","2", "XS-00002", cmlist,
				"",0,0,1);
		purchase2 = new PurchaseVO("JHD-20141208-00002", "金大大","JHS-00001","2", "XS-00002", cmlist,
				"",0,0,1);
	}
	
	//TUS 1-2 根据进货单 创建一条进货退货单
	public void test_1(){
		int addPurchase=controller.addPurchase(purchase1);
		boolean create=false;
		if(addPurchase==0){
			PurchaseVO purchase=controller.PFindByID("JHD-20141208-00001");
			if(purchase!=null){
				String id=controller.getNewID(ReceiptType.PURCHASERETURN);
				PurchaseReturnVO preturn=new PurchaseReturnVO(id,purchase.getMemberName(),
						purchase.getMemberID(),"JL-00001",0,"",1,purchase.getPurchaseList(),
						purchase.getTotalInAll(),purchase.getStockid(),purchase.getId());
				int addReturn=controller.addPurchaseReturn(preturn);
				if(addReturn==0){
					PurchaseReturnVO ppreturn=controller.PRFindByID(id);
					create=true;
					assertEquals(purchase.getMemberName(),ppreturn.getMemberName());
					assertEquals(purchase.getTotalInAll(),ppreturn.getTotalInAll());
				}
			}
			
			
		}
		assertEquals(true,create);
		
	}
	
	//TUS 2-1 2-2根据type找进货单
	public void test_2(){
		String message="金大大";
		String type="客户";
		ArrayList<PurchaseVO> plist=controller.findPurchase(message, type);
		for(PurchaseVO vo:plist){
			assertEquals(message,vo.getMemberName());
		}
		message="1";
		type="仓库";
		plist=controller.findPurchase(message, type);
		if(plist!=null){
			for(PurchaseVO vo:plist)
			assertEquals(message,vo.getStockid());
		}
		
	}
	
	//TUS 1-3修改一个进货单
	public void test_3(){
		PurchaseVO purchase=controller.PFindByID("JHD-20141208-00001");
		ArrayList<CommodityVO> clist=purchase.getPurchaseList();
		CommodityVO com=new CommodityVO("0001-SR01-0002","蓝之恋吊灯","SR01",1000,800,1,1000,800
				,"在做测试");
		clist.add(com);
		purchase.setPurchaseList(clist);
		int result=controller.modifyPurchase(purchase);
		assertEquals(0,result);
		purchase=controller.PFindByID("JHD-20141208-00001");
		boolean isExist=false;
		for(CommodityVO v:purchase.getPurchaseList())
			if(v.getID().equals("0001-SR01-0002"))
			{
				isExist=true;break;
			}
		assertEquals(true,isExist);
	}
}
