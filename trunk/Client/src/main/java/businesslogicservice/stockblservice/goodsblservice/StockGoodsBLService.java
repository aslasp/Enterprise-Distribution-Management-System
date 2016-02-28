package businesslogicservice.stockblservice.goodsblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.GoodsVO;

public interface StockGoodsBLService {
	public int addGoods(GoodsVO vo);

	public int deleteGoods(GoodsVO vo);

	public int modifyGoods(GoodsVO vo);

	public ArrayList<GoodsVO> findGoods(String message);
	
	public GoodsVO findByID(String id) throws RemoteException;

	public ArrayList<GoodsVO> showGoods();

	public ArrayList<GoodsVO> showGoodsByClass(String className);
	
	public String[] getAllGoodsName();
	
	public String getMaxID();
}
