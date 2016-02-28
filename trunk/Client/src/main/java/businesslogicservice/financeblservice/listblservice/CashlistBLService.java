package businesslogicservice.financeblservice.listblservice;

import java.util.ArrayList;

import vo.CashlistVO;

public interface CashlistBLService {
	public int createCashlist(CashlistVO vo);
	public ArrayList<CashlistVO> getCashlist();
	public String getNewID();
	public CashlistVO findByID(String s);
	public int modify(CashlistVO vo);
}
