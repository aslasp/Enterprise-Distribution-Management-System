package businesslogic.stockbl.goodsClass;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.GoodsClassVO;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

public class GoodsClassController implements StockGoodsClassBLService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int addGoodsClass(GoodsClassVO vo) {
		// TODO 自动生成的方法存根
		GoodsClass gc = new GoodsClass(vo.getName(), vo.getUpClassName());
		int result = 0;
		try {
			result = gc.addGoodsClass();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public int deleteGoodsClass(GoodsClassVO vo) {
		// TODO 自动生成的方法存根
		GoodsClass gc = new GoodsClass(vo.getName(), vo.getUpClassName());
		return gc.deleteGoodsClass();
	}

	//分别传入原来的分类信息和新的分类信息，用新的信息来创建一个商品分类对象
	public int modifyGoodsClass(GoodsClassVO oldVO,GoodsClassVO newVO) {
		// TODO 自动生成的方法存根
		GoodsClass gc = new GoodsClass(newVO.getName(),newVO.getUpClassName());
		return gc.modifyGoodsClass(oldVO);
	}

	public ArrayList<GoodsClassVO> show() {
		// TODO 自动生成的方法存根
		GoodsClassManage manage = new GoodsClassManage();
		return manage.show();
	}

	public GoodsClassVO showGoodsClassInfo(String name) {
		// TODO 自动生成的方法存根
		GoodsClassManage manage = new GoodsClassManage();
		GoodsClassVO vo = null;

		try {
			vo = manage.showGoodsClassInfo(name);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vo;
	}
}
