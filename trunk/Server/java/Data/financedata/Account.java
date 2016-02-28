package Data.financedata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dataservice.financedataservice.accountdataservice.FinanceAccountDataService;
import po.AccountPO;

public class Account implements FinanceAccountDataService{

	//这是要干嘛？？？？
	public static void main(String[] args) {
		Account a=new Account();
		AccountPO b=new AccountPO("张三");
		a.addAccount(b);
	}

	public int addAccount(AccountPO po) {
	    AccountPO a=findAccount(po.getName());
	    if(a!=null)
		    return 1;
	    else{
	    	try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("account.ser"));                     
                oos.writeObject(po);
                oos.close();                       
        } catch (Exception ex) {  
        	ex.printStackTrace();   
        }
	    	
	    }
	    
	    AccountPO b;
	    //为什么要输出？为了测试吗 By jin
	    try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("account.ser"));
            b = (AccountPO) ois.readObject();
            System.out.println("账户的名称为" + b.getName()+",账户的金额为"+b.getMoney());
            ois.close();
    } catch (Exception ex) {
            ex.printStackTrace();
    }
	    return 0;
	}

	public int deleteAccount(AccountPO po) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int modifyAccount(AccountPO po) {
		// TODO Auto-generated method stub
		return 0;
	}

	public AccountPO findAccount(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}

