package businesslogicservice.financeblservice.initblservice;

import java.util.ArrayList;

import vo.BeginInfoVO;

public interface FinanceInitBLService {
	public int initInfo(String time,BeginInfoVO po);
	public BeginInfoVO getInfo(String time);
	public String getCurrentTime();
	public ArrayList<BeginInfoVO> showAll();
	public void setTime(String s);
	public void reset();
}
