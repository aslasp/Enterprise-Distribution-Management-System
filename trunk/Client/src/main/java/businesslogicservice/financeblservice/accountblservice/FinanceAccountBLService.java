package businesslogicservice.financeblservice.accountblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.AccountVO;

public interface FinanceAccountBLService {
	public int addAccount(AccountVO vo);
    public int deleteAccount(AccountVO vo);
    public int modifyAccount(AccountVO vo,String name);
    public ArrayList<AccountVO> findAccount(String s);
    public ArrayList<AccountVO> showAll();
    public AccountVO findByName(String s) throws RemoteException; 
}
