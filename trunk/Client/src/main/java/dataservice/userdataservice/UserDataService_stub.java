package dataservice.userdataservice;

import java.rmi.RemoteException;


import java.util.ArrayList;

import po.UserPO;

public class UserDataService_stub implements UserDataService {

	public String Find(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("Login succeed!");
		return null;
	}
	
	public int add(UserPO po) {
		// TODO 自动生成的方法存根
		System.out.println("add user in file succeed!");
		return 0;
	}

	public int delete(UserPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("delete user in file succeed!");
		return 0;
	}

	public int modify(UserPO po) {
		// TODO 自动生成的方法存根
		System.out.println("modify user in file succeed!");
		return 0;
	}

	public UserPO showUserInfo(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("show users in file succeed!");
		return null;
	}

	public int delete(String ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<UserPO> showAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
