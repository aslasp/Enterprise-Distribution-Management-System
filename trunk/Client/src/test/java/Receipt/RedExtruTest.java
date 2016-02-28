package Receipt;



import businesslogic.financebl.CashList;
import businesslogic.financebl.MockAccount;
import businesslogic.financebl.MockCashList;
import businesslogic.financebl.MockcashListItem;
import businesslogic.receiptbl.Receipt;
import businesslogic.receiptbl.Review;
import junit.framework.TestCase;

/*
 * 红冲单据测试，以红冲先进费用单为例
 * */
public class RedExtruTest extends TestCase{
	private Review review;
	private Receipt receipt;
	private MockCashList cashList;
	private MockAccount account;
	private MockcashListItem item1,item2;
	
	
	public void setUp() throws Exception{
		account=new MockAccount("邮政储蓄","6221503000010256558",100000);
		review=new Review();
		item1=new MockcashListItem("公费旅游",12000,"10-13日员工奖励");
		item2=new MockcashListItem("添置高清投影仪",20000,"无");
						
	}	
	public void testRedExtru() throws Exception{
		cashList=new MockCashList("XJFYD-20141015-00001","14010001",account);
		cashList.AddItem(item1);
		cashList.AddItem(item2);
		double money=item1.getMoney()+item2.getMoney();
		//费用单总额计算
		assertEquals(money,cashList.getTotal());
	//	review.RedExtru(cashList, 0);
		//红冲入账的结果,该账户余额减少
		assertEquals(68000.0,account.getBalance());
		
	}

}
