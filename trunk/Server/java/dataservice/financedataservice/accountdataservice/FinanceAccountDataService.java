package dataservice.financedataservice.accountdataservice;

import java.rmi.Remote;

import po.AccountPO;

public interface FinanceAccountDataService  extends Remote{
	public int addAccount(AccountPO po);
    public int deleteAccount(AccountPO po);
    public int modifyAccount(AccountPO po);
    public AccountPO findAccount(String s);
}
