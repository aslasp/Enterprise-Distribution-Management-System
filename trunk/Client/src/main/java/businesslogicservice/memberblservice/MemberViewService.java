package businesslogicservice.memberblservice;

import java.util.ArrayList;

import vo.MemberVO;

public interface MemberViewService {
	public MemberVO findById(String ID);
	public ArrayList<MemberVO> showMembers();
	public String[] getAllMemberName();
	public int getSaleNum();
	public int getPurchaseNum();
}
