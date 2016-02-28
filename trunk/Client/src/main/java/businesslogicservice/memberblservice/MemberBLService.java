package businesslogicservice.memberblservice;
import java.util.ArrayList;

import po.MemberPO;
import po.MemberPO.MemberType;
import vo.MemberVO;


public interface MemberBLService {
	public int addMember(MemberVO vo);
	public int deleteMember(String memID) ;
	public int modifyMember(MemberVO vo);
	public ArrayList<MemberVO> findMember(String message);
	public ArrayList<MemberVO> showMembers();
	public String getNewID(MemberType type);
	public MemberVO findById(String ID);
	public ArrayList<MemberVO> show(MemberType type);
}
