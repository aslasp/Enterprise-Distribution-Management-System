package businesslogic.stockbl.goodsClass;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JTree;

import vo.GoodsClassVO;
import businesslogic.stockbl.goods.MockGoods;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

public class MockGoodsClass implements StockGoodsClassBLService {
	String classID;
	String name;
	MockGoodsClass upClass;

	public MockGoodsClass() {

	}

	public String getClassID() {
		return classID;
	}

	public String getName() {
		return name;
	}

	public MockGoodsClass getUpClass() {
		return upClass;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUpClass(MockGoodsClass upClass) {
		this.upClass = upClass;
	}

	public MockGoodsClass(String classID, String name, MockGoodsClass upClass) {
		this.classID = classID;
		this.name = name;
		this.upClass = upClass;
	}

	public int addGoodsClass(GoodsClassVO vo) {
		// TODO 自动生成的方法存根
		return 0;
	}

	public int deleteGoodsClass(GoodsClassVO vo) {
		// TODO 自动生成的方法存根
		return 0;
	}

	public ArrayList<MockGoods> findsGoodsInClass(MockGoodsClass goodsClass)
			throws Exception {
		ArrayList<MockGoods> goodsList = new ArrayList<MockGoods>();
		ArrayList<MockGoods> result = new ArrayList<MockGoods>();
		MockGoods good1 = new MockGoods("00020001", "飞利浦日光灯", "SR01", 10, 200,
				100);
		MockGoods good2 = new MockGoods("00020002", "飞利浦日光灯", "SR02", 20, 300,
				200);

		goodsList.add(good1);
		goodsList.add(good2);

		for (int i = 0; i < goodsList.size(); i++) {
			if (goodsList.get(i).getGoodsID().substring(0, 4)
					.equals(goodsClass.classID)) {
				result.add(goodsList.get(i));
			}
		}

		return result;
	}

	public ArrayList<GoodsClassVO> show() {
		// TODO 自动生成的方法存根
		return null;
	}

	public JTree getClassTree() {
		// TODO 自动生成的方法存根
		return null;
	}

	public int recordClassTree(JTree tree) throws RemoteException {
		// TODO 自动生成的方法存根
		return 0;
	}

	public GoodsClassVO showGoodsClassInfo(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	public int modifyGoodsClass(GoodsClassVO oldVO, GoodsClassVO newVO) {
		// TODO 自动生成的方法存根
		return 0;
	}

}
