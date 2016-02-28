package businesslogic.stockbl.goods;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.GoodsVO;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;

public class GoodsController implements StockGoodsBLService {

	public int addGoods(GoodsVO vo) {
		// TODO 自动生成的方法存根
		Goods good = VOToGoods(vo);
		return good.addGoods();
	}

	public int deleteGoods(GoodsVO vo) {
		// TODO 自动生成的方法存根
		Goods good = VOToGoods(vo);
		return good.deleteGoods(vo.getGoodsID());
	}

	public int modifyGoods(GoodsVO vo) {
		// TODO 自动生成的方法存根
		Goods good = VOToGoods(vo);
		return good.modifyGoods(vo.getGoodsID());
	}

	public ArrayList<GoodsVO> findGoods(String message) {
		// TODO 自动生成的方法存根
		Goods good = new Goods();
		return good.findGoods(message);
	}

	public GoodsVO findByID(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		Goods good = new Goods();
		return good.findByID(id);
	}

	public ArrayList<GoodsVO> showGoods() {
		// TODO 自动生成的方法存根
		Goods good = new Goods();
		return good.showGoods();
	}

	public String getMaxID() {
		Goods good = new Goods();
		return good.getMaxID();
	}

	public ArrayList<GoodsVO> showGoodsByClass(String className) {
		// TODO 自动生成的方法存根
		Goods good = new Goods();
		return good.showGoodsByClass(className);
	}

	private Goods VOToGoods(GoodsVO vo) {
		Goods goods = new Goods(vo.getGoodsID(), vo.getName(), vo.getSize(),
				vo.getGoodsClass(), vo.getNumInStock(), vo.getPurchasePrice(),
				vo.getPrice(), vo.getLastPurchasePrice(), vo.getLastPrice(),
				vo.getManufactureDate(), vo.getMinNumInstock());
		return goods;
	}

	@Override
	public String[] getAllGoodsName() {
		Goods good = new Goods();
		return good.getAllGoodsName();
	}

}
