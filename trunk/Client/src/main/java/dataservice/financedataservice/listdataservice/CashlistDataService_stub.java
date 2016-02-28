package dataservice.financedataservice.listdataservice;

import java.util.ArrayList;

import po.CashlistPO;

public class CashlistDataService_stub implements CashlistDataService{
	public int createCashlist(CashlistPO po) {
		System.out.println("Create cashlist success!");
		return 0;
	}

	public ArrayList<CashlistPO> getCashlist() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CashlistPO findByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(CashlistPO po) {
		// TODO Auto-generated method stub
		return 0;
	}
}
