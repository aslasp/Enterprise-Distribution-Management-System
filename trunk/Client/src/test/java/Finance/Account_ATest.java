package Finance;

import java.rmi.RemoteException;

import businesslogic.financebl.Account;
import vo.AccountVO;
import junit.framework.TestCase;

public class Account_ATest extends TestCase{
	AccountVO accA,accB,accC;
	Account account;
    
	//一组合法，2组不合法
	public void setUp() throws Exception{
		accA=new AccountVO("张三",230);
		accB=new AccountVO("",0);
		accC=new AccountVO("张三",Double.parseDouble("233"));
		account=new Account();
	}
	
	//TUS1-1  成功添加账户
	public void test_1(){
		boolean testResult=false;
		account.addAccount(accA);
		AccountVO vo=null;
		try {
			vo = account.findByName(accA.getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(vo!=null)
			testResult=true;
			
		assertTrue(testResult);
				
	}
	
	//TUS1-2 成功删除账户
		public void test_2(){
		account.deleteAccount(accA);
		}
		
	//TUS1-3 成功修改账户
		public void test_3(){
		account.modifyAccount(accA, "张三");					
		}
				
	//TUS1-4 成功查询账户
		public void test_4(){
		account.findAccount(accA.getName())	;	
		}
		
	//TUS2-1
	    public void test_5(){
		account.delMoney("张三", 20);	
		}
	    
	//TUS2-2
	    public void test_6(){
		account.addMoney("张三", 20);	
		}
	    
	//TUS2-3
	    public void test_7(){		
		}
	    
	//TUS3-1
	    public void test_8(){
					
		}
	    
	//TUS3-2
	    public void test_9(){
					
		}
	    
	//TUS3-3
	    public void test_10(){
					
		}
	
}
