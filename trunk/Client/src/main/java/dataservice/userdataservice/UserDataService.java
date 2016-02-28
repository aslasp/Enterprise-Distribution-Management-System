package dataservice.userdataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.UserPO;
//Remote Exception 改何去何从  By jin
public interface UserDataService extends Remote {
	

	public int add(UserPO po) ;

	public int delete(String ID) ;

	public int modify(UserPO po) ;

	public UserPO showUserInfo(String ID) ;
	
	public ArrayList<UserPO> showAll();
}
