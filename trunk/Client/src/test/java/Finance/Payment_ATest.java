package Finance;

import java.util.ArrayList;

import po.PaymentPO;
import po.TransferItemPO;
import vo.PaymentVO;
import vo.TransferItemVO;
import businesslogic.financebl.Payment;
import junit.framework.TestCase;

public class Payment_ATest  extends TestCase{
	    PaymentVO pa1,pa2,pa3;
	    Payment p;
	    ArrayList<TransferItemVO> tra;
	    ArrayList<PaymentVO> ple;
	     
	    //1组正确，2组错误
	    public void setUp() throws Exception{
	        tra=new ArrayList<TransferItemVO>();
	        ple=new ArrayList<PaymentVO>();
	        tra.add(new TransferItemVO("我在做测试",100,"LALALALA"));
	    	pa1=new PaymentVO("FKD-20141215-00001","XSS-0000001","马建国","CW-00001",tra,100,1,1);
	    	pa2=new PaymentVO("FKD-20141215-00001","XSS-0000001","马建国","CW-00001",tra,200,1,1);
	    	pa3=new PaymentVO("→_→","←_←","→_←","←_→",tra,250,0,0);
	    	p=new Payment();
	    }
	    
	  //TUS1-1  成功添加付款单
	  	public void test_1(){
	  		boolean testResult=false;
	  		int result=p.createPayment(pa1);
			if(result==0)
				testResult=true;
				
			assertTrue(testResult);
					
		}
	  	
	  	public void test_2(){
	  		p.excute(pa1);
	  	}
	  	
	  	public void test_3(){
	  		p.getPayment();
	  	}
	  	
	  	public void test_4(){
	  		p.getNewID();
	  	}
	  	
	  	
	  	public void test_6(){
	  		p.voToPo(ple);
	  	}
	  	
	  	public void test_7(){
	  		p.voToPo(pa1);
	  	}
	  	
	  	public void test_8(){
	  		ArrayList<TransferItemPO> pra=new ArrayList<TransferItemPO>();
	  		PaymentPO po1=new PaymentPO("FKD-20141215-00001","XSS-0000001","马建国","CW-00001",pra,100,1,1);
	  		p.poToVo(po1);
	  	}
	  	
	  	public void test_9(){
	  		ArrayList<PaymentPO> ppp= new ArrayList<PaymentPO>();
	  		ArrayList<TransferItemPO> pra=new ArrayList<TransferItemPO>();
	  		PaymentPO po1=new PaymentPO("FKD-20141215-00001","XSS-0000001","马建国","CW-00001",pra,100,1,1);
	        ppp.add(po1);
	  		p.poToVo(ppp);
	  	}
	  	
	  	public void test_10(){
	  		
	  	}
	  	
	  	
}
