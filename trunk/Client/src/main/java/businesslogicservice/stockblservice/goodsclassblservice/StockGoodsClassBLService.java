package businesslogicservice.stockblservice.goodsclassblservice;

import java.util.ArrayList;

import vo.GoodsClassVO;

public interface StockGoodsClassBLService {
	public int addGoodsClass(GoodsClassVO vo);

	public int deleteGoodsClass(GoodsClassVO vo);

	public int modifyGoodsClass(GoodsClassVO oldVO, GoodsClassVO newVO);

	public ArrayList<GoodsClassVO> show();

	public GoodsClassVO showGoodsClassInfo(String name);
}
