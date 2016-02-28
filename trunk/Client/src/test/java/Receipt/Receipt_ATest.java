package Receipt;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionPO;
import po.ReceiptPO;
import po.TransferItemPO;
import po.ReceiptPO.ReceiptType;
import businesslogic.financebl.Account;
import businesslogic.financebl.Collection;
import businesslogic.receiptbl.Receipt;
import businesslogic.receiptbl.ReceiptController;
import businesslogic.receiptbl.ReceiptList;
import businesslogic.receiptbl.ReceiptMessage;
import businesslogic.receiptbl.Review;
import vo.AccountVO;
import vo.CollectionVO;
import vo.ReceiptMessageVO;
import vo.ReceiptVO;
import vo.TransferItemVO;
import junit.framework.TestCase;

/*
 * Author: jincui
 * Date:2014-12-13
 * Description:Receipt模块测试套件1
 * */
public class Receipt_ATest extends TestCase{
	CollectionPO cpo;
	Review view;
	ReceiptList list;
	
	public void setUp(){
		ArrayList<TransferItemPO> itemlist=new ArrayList<TransferItemPO>();
		itemlist.add(new TransferItemPO("马建国",200,"用例测试"));
		//有一个待审批收款单
		cpo=new CollectionPO("SKD-20141207-00001","马建国","金大大","CW-00001",
				itemlist,200,0,1);
		try {
			view=new Review();
			list=new ReceiptList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//TUS 1-5转为界面测试
	//TUS1-4 
	public void test_1() {
		//改收款单通过审批  判断执行情况
		double origin=0;
		try {
			Account account=new Account();
			AccountVO acc=account.findByName("马建国");
			origin=acc.getMoney();
		
		boolean Success=false;
		view.Approve(cpo.getId(), 2);
		
		ArrayList<ReceiptPO> approvedlist=list.showApproved();
		for(int i=0;i<approvedlist.size();i++)
		{
			ReceiptPO receipt=approvedlist.get(i);
			if(receipt.getId().equals(cpo.getId()))
				{
					assertEquals(2,receipt.getStatus());
					System.out.println("改收款单已审批");
					acc=account.findByName("马建国");
					double c=acc.getMoney()-origin;
					assertEquals(200,c);
				}
		}
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//TUS1-5 单据审批不通过
	public void test_2(){
			
	}
	
	
	//TUS2-1  
	public void test_3(){
		try {
			TransferItemPO ip=cpo.getTransferlist().get(0);
			
			//cpo.set
			//Collection collect=new Collection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	//TUS3-1 筛选类型
	public void test_4(){	
		
		
	}
	
	
	//TUS3-2  筛选日期
	public void test_5() throws Exception{
		
		
	}
	
	//(String id,ReceiptType type,String user,int status,int hurry){
	public void test_6() throws Exception {
		Receipt t=new Receipt();
		ReceiptVO vo=new ReceiptVO("SKD-20141211-00001",ReceiptType.CASHLIST,"JL-00001", 0, 0);
	
	}
	
	
	public void test_7() throws Exception{
		ReceiptController co=new ReceiptController();
		String[] message=new String[]{"1"};
		co.AccurateFind(message);
		ReceiptVO vo=new ReceiptVO("SKD-20141211-00001",ReceiptType.CASHLIST,"JL-00001",
				0,0);
	
		
	}
	

}
