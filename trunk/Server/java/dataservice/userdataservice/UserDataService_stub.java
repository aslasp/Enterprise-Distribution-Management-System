package dataservice.userdataservice;

import java.rmi.*;

import po.UserPO;

public class UserDataService_stub implements UserDataService {

	public int add(UserPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("add user in file succeed!");
		return 0;
	}

	public int delete(UserPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("delete user in file succeed!");
		return 0;
	}

	public int modify(UserPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("modify user in file succeed!");
		return 0;
	}

	public UserPO showUserInfo(String ID) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("show users in file succeed!");
		return null;
	}

	
}
