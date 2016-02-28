package dataservice.financedataservice.accountdataservice;

import java.util.ArrayList;

import po.AccountPO;

public class FinanceAccountDataService_stub implements FinanceAccountDataService{

	public int addAccount(AccountPO po) {
		System.out.println("Add account success!");
		return 0;
	}

	public int deleteAccount(AccountPO po) {
		System.out.println("Delete account success!");
		return 0;
	}

	public int modifyAccount(AccountPO po,String name) {
		System.out.println("Modify account success!");
		return 0;
	}

	public ArrayList<AccountPO> findAccount(String s) {
		System.out.println("Find account success!");
		return new ArrayList<AccountPO>();
	}

	public ArrayList<AccountPO> showAll() {
		System.out.println("Show all accouts success!");
		return new ArrayList<AccountPO>();
	}

	public AccountPO findByName(String s) {
		// TODO 自动生成的方法存根
		return null;
	}

	public int addMoney(String id, double m) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delMoney(String id, double m) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
