package Receipt;



import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import vo.MemberVO;
import businesslogic.financebl.MockAccount;
import businesslogic.financebl.MockCollection;
import businesslogic.memberbl.MemAccountInfo;
import businesslogic.memberbl.MemBaseInfo;
import businesslogic.memberbl.MemContactInfo;
import businesslogic.memberbl.Member;
import businesslogic.memberbl.MockMember;
import businesslogic.receiptbl.Receipt;
import businesslogic.receiptbl.Review;
import junit.framework.TestCase;
/*
 * 总经理审批单据,审批不通过/通过,以收款单为例
 * 单据状态有五：1.草稿  2.待审批  3.审批不通过  4.审批通过待执行 5.执行完毕
 * */
public class ApproveReceiptTest extends TestCase{
	private Review review;
	private Receipt collect_A,collect_B;
	private MockCollection collect;
	private MemberVO mem;
	private MockAccount account;
	private Member member;
	
	public void setUp() throws Exception {
		member=new Member();
		review=new Review();
		account=new MockAccount("邮政储蓄","6221503000010256558",10000);
		collect_A=new MockCollection("SKD-2014/10/15-00001");
		collect_B=new MockCollection("SKD-2014/10/15-00002");
		mem=new MemberVO(new MemBaseInfo(MemberType.JHS,MemberLevel.ONE,"JHS-0000007",
				"客户A",0,"业务员甲"),new MemAccountInfo(100000,0,0),new MemContactInfo(
						"15850698062","南大仙林校区","210046","a@164.com"));
		member.changeToReceive(mem.getMemberID(),300);
	}
	
	public void testApproveReceipt(){
		//审批，修改收款单信息
		collect=(MockCollection)collect_A;
		collect.AddCollectItem("140001", 200);
		collect.AddCollectItem("140002",300);
		assertEquals(500.0,collect.getTotalMoney());
		//审批不通过
		review.Approve(collect.getId(), 0);
		//assertEquals("审批不通过",collect.getStatus());
		collect=(MockCollection)collect_B;
		collect.AddCollectItem("140001",300);
		//审批通过
		review.Approve(collect.getId(), 1);
	//	assertEquals("审批通过",collect.getStatus());
		//执行单据
		//现审批成后即执行
		//assertEquals("执行完毕",collect.getStatus());
		//客户应收减少,公司账户余额增加
		MemberVO mem=member.findById(collect.getMemberIDByOrder(0));
		assertEquals(200.0,mem.getToReceive());	
		assertEquals(10300.0,account.getBalance());
		
	}
}
