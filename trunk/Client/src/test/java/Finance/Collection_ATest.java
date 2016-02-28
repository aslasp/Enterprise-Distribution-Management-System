package Finance;

import java.util.ArrayList;

import po.CollectionPO;
import po.TransferItemPO;
import businesslogic.financebl.Collection;
import vo.CollectionVO;
import vo.TransferItemVO;
import junit.framework.TestCase;

public class Collection_ATest  extends TestCase{
    CollectionVO co1,co2,co3;
    Collection c;
    ArrayList<TransferItemVO> tra;
    ArrayList<CollectionVO> cle;
     
    //1组正确，2组错误
    public void setUp() throws Exception{
        tra=new ArrayList<TransferItemVO>();
        cle=new ArrayList<CollectionVO>();
        tra.add(new TransferItemVO("我在做测试",100,"LALALALA"));
    	co1=new CollectionVO("SKD-20141215-00001","XSS-0000001","马建国","CW-00001",tra,100,1,1);
    	co2=new CollectionVO("SKD-20141215-00001","XSS-0000001","马建国","CW-00001",tra,200,1,1);
    	co3=new CollectionVO("→_→","←_←","→_←","←_→",tra,250,0,0);
    	c=new Collection();
    }
    
  //TUS1-1  成功添加收款单
  	public void test_1(){
  		boolean testResult=false;
  		c.createCollection(co1);
		CollectionVO vo=null;
		vo = c.findByID(co1.getId());
		if(vo!=null)
			testResult=true;
			
		assertTrue(testResult);
				
	}
  	
  	public void test_2(){
  		c.excute(co1);
  	}
  	
  	public void test_3(){
  		c.findByID(co1.getId());
  	}
  	
  	public void test_4(){
  		c.getCollection();
  	}
  	
  	public void test_5(){
  		c.getNewID();
  	}
  	
  	public void test_6(){
  		c.voToPo(cle);
  	}
  	
  	public void test_7(){
  		c.voToPo(co1);
  	}
  	
  	public void test_8(){
  		ArrayList<TransferItemPO> pra=new ArrayList<TransferItemPO>();
  		CollectionPO po1=new CollectionPO("SKD-20141215-00001","XSS-0000001","马建国","CW-00001",pra,100,1,1);
  		c.poToVo(po1);
  	}
  	
  	public void test_9(){
  		ArrayList<CollectionPO> ppp= new ArrayList<CollectionPO>();
  		ArrayList<TransferItemPO> pra=new ArrayList<TransferItemPO>();
  		CollectionPO po1=new CollectionPO("SKD-20141215-00001","XSS-0000001","马建国","CW-00001",pra,100,1,1);
        ppp.add(po1);
  		c.poToVo(ppp);
  	}
  	
 
  	
  	
}
