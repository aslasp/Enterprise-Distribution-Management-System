package dataservice.memberdataservice;

import java.util.ArrayList;

import po.MemberPO;
import po.MemberPO.MemberType;

public class MemberDataService_stub implements MemberDataService{

	public int add(MemberPO po) {
		// TODO Auto-generated method stub
		System.out.println("Add Successfully!");
		return 0;
	}

	public int delete(MemberPO po) {
		// TODO Auto-generated method stub
		System.out.println("Delete Successfully!");
		return 0;
	}

	public int modify(MemberPO po) {
		// TODO Auto-generated method stub
		System.out.println("Modify Successfully!");
		return 0;
	}

	

	@Override
	public int delete(String memID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<MemberPO> find(String message) {
		System.out.println("Find Successfully!");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MemberPO> showAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberPO findByID(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNum(MemberType type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<MemberPO> show(MemberType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int changeToReceive(String id, double m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeToPay(String id, double m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
