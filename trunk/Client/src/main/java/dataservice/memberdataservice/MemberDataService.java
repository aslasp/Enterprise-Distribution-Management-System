package dataservice.memberdataservice;
import java.rmi.Remote;
import java.util.ArrayList;

import po.MemberPO;
import po.MemberPO.MemberType;

public interface MemberDataService extends Remote{
	public int add(MemberPO po);
	public int delete(String memID);
	public int modify(MemberPO po);
	public ArrayList<MemberPO> find(String message);
	public ArrayList<MemberPO> showAll();
	public MemberPO findByID(String ID);
	public int getNum(MemberType type);
	public ArrayList<MemberPO> show(MemberType type);
	public int changeToReceive(String id,double m);
	public int changeToPay(String id,double m);
}
