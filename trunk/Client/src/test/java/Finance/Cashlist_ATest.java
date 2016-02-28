package Finance;

import java.util.ArrayList;

import po.CashlistPO;
import po.ClauseItemPO;
import businesslogic.financebl.CashList;
import vo.CashlistVO;
import vo.ClauseItemVO;
import junit.framework.TestCase;

public class Cashlist_ATest extends TestCase{
	    CashlistVO ca1,ca2,ca3;
	    CashList c;
	    ArrayList<ClauseItemVO> cla;
	    ArrayList<CashlistVO> ple;
	     
	    //1组正确，2组错误
	    public void setUp() throws Exception{
	        cla=new ArrayList<ClauseItemVO>();
	        ple=new ArrayList<CashlistVO>();
	        cla.add(new ClauseItemVO("我在做测试",100,"LALALALA"));
	    	ca1=new CashlistVO("XJFYD-20141215-00001","马建国","CW-00001",cla,100,1,1);
	    	ca2=new CashlistVO("XJFYD-20141215-00001","马建国","CW-00001",cla,200,1,1);
	    	ca3=new CashlistVO("→_→","←_←","→_←",cla,250,0,0);
	    	c=new CashList();
	    }
	    
	  //TUS1-1  成功添加现金费用单
	  	public void test_1(){
	  		boolean testResult=false;
	  		int result=c.createCashlist(ca1);
			if(result==0)
				testResult=true;
				
			assertTrue(testResult);
					
		}
	  	
	  	public void test_2(){
	  		c.excute(ca1);
	  	}
	  	
	  	public void test_3(){
	  		c.getCashlist();
	  	}
	  	
	  	public void test_4(){
	  		c.getNewID();
	  	}
	  	
	  	
	  	public void test_6(){
	  		c.voToPo(ple);
	  	}
	  	
	  	public void test_7(){
	  		c.voToPo(ca1);
	  	}
	  	
	  	public void test_8(){
	  		ArrayList<ClauseItemPO> pra=new ArrayList<ClauseItemPO>();
	  		CashlistPO po1=new CashlistPO("XJFYD-20141215-00001","马建国","CW-00001",pra,100,1,1);
	  		c.poToVo(po1);
	  	}
	  	
	  	public void test_9(){
	  		ArrayList<CashlistPO> ppp= new ArrayList<CashlistPO>();
	  		ArrayList<ClauseItemPO> pra=new ArrayList<ClauseItemPO>();
	  		CashlistPO po1=new CashlistPO("FKD-20141215-00001","马建国","CW-00001",pra,100,1,1);
	        ppp.add(po1);
	  		c.poToVo(ppp);
	  	}
	  	
	  	public void test_10(){
	  		
	  	}
}
