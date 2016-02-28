package businesslogicservice.userblservice;

import java.util.ArrayList;

import po.UserPO;
import po.UserPO.UserJob;
import vo.UserVO;

public interface UserBLService {
	public int login(String ID, String password);

	public int addUser(UserVO vo);

	public int deleteUser(String ID);

	public int modifyUser(UserVO vo);

	public UserVO showUser(String UserID);
	
	public ArrayList<UserVO> showAll();
	public String NewUserID(UserJob job);
}
