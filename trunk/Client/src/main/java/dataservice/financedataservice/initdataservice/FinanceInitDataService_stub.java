package dataservice.financedataservice.initdataservice;

import java.util.ArrayList;

import po.BeginInfoPO;

public class FinanceInitDataService_stub implements FinanceInitDataService{

	@Override
	public int initInfo(String time, BeginInfoPO po) {
		System.out.println("Initial stock success!");
		return 0;
	}

	@Override
	public BeginInfoPO getInfo(String time) {
		System.out.println("Show beginInformation success!");
		return new BeginInfoPO();
	}

	@Override
	public String getCurrentTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BeginInfoPO> showAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTime(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
