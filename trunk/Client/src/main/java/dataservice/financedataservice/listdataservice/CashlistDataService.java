package dataservice.financedataservice.listdataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.CashlistPO;

public interface CashlistDataService extends Remote{
	public int createCashlist (CashlistPO po) ;
	public ArrayList<CashlistPO> getCashlist();
	public int getNum();
	public CashlistPO findByID(String id);
	public int modify(CashlistPO po);
}
