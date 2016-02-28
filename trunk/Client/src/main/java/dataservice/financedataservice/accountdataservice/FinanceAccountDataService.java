package dataservice.financedataservice.accountdataservice;

import java.util.ArrayList;

import po.AccountPO;

public interface FinanceAccountDataService {
	public int addAccount(AccountPO po);
    public int deleteAccount(AccountPO po);
    public int modifyAccount(AccountPO po,String name);
    public ArrayList<AccountPO> findAccount(String s);
    public ArrayList<AccountPO> showAll();
    public AccountPO findByName(String s); 
    public int addMoney(String name,double m);
    public int delMoney(String name,double m);
}
