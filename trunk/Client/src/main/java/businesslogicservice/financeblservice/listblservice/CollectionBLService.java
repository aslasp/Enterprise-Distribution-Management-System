package businesslogicservice.financeblservice.listblservice;

import java.util.ArrayList;

import vo.CollectionVO;

public interface CollectionBLService {
	public int createCollection(CollectionVO vo);
	public ArrayList<CollectionVO> getCollection();
	public String getNewID();
	public CollectionVO findByID(String s);
	public int modify(CollectionVO vo);
}
