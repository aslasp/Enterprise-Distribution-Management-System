package dataservice.financedataservice.initdataservice;

import java.util.ArrayList;

import po.BeginInfoPO;

public interface FinanceInitDataService {
	public int initInfo(String time,BeginInfoPO po);
	public BeginInfoPO getInfo(String time);
	public String getCurrentTime();
	public ArrayList<BeginInfoPO> showAll();
	public void setTime(String s);
	public void reset();
}
