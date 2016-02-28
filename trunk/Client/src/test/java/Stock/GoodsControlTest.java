package Stock;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import junit.framework.TestCase;
import vo.GoodsClassVO;
import vo.GoodsVO;
import businesslogic.stockbl.goods.GoodsController;

//rmi 错误
public class GoodsControlTest extends TestCase {
	GoodsClassVO goodsClass1, goodsClass2;
	String ClassName1;
	String upClass1;
	String ClassID1;
	GoodsController gController;
	GoodsVO good1, good2;

	public void setUp() throws ParseException {
		goodsClass1 = new GoodsClassVO("飞利浦", "灯具");
		goodsClass2 = new GoodsClassVO("日光灯", "飞利浦");
		gController = new GoodsController();
		try {
			good1 = new GoodsVO("0004-SR01-0000", "飞利浦日光灯", "SR01", 0, 100.0,
					150.0, 0.0, 0.0, "飞利浦", "", 30);
			good2 = new GoodsVO("0004-SR02-0000", "飞利浦日光灯", "SR02", 0, 100.0,
					150.0, 0.0, 0.0, "飞利浦", "", 30);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public void testAdd() throws RemoteException {
		// 查找商品分类
		GoodsVO good = gController.showGoodsByClass(goodsClass2.getName()).get(
				0);
		assertEquals(good1.getName(), good.getName());
		// 添加商品
		ArrayList<GoodsVO> goodsList = null;
		try {
			goodsList = gController.showGoodsByClass(goodsClass2.getName());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(goodsList.size(), 2);
	}
}
