package businesslogicservice.financeblservice.accountblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.AccountVO;

public class FinanceAccountBLService_stub implements FinanceAccountBLService{

	@Override
	public int addAccount(AccountVO vo) {
		System.out.println("Add Account Success!");
		return 0;
	}

	@Override
	public int deleteAccount(AccountVO vo) {
		System.out.println("Delete Account Success!");
		return 0;
	}

	@Override
	public int modifyAccount(AccountVO vo, String name) {
		System.out.println("Modify Account Success!");
		return 0;
	}

	@Override
	public ArrayList<AccountVO> findAccount(String s) {
		System.out.println("Find Account Success!");
		return null;
	}

	@Override
	public ArrayList<AccountVO> showAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountVO findByName(String s) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
