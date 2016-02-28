package businesslogic.salesbl;

import java.util.ArrayList;

import vo.CommodityVO;

public class MockCommodityList {
	private ArrayList<CommodityVO> list;
	private double TotalValue;

	public MockCommodityList() {
		list = new ArrayList<CommodityVO>();
	}

	public void add(String goodID, String goodName, String goodType, int num,
			double price, String memo) {
		double totalPrice=num*price;
		double last_bid=100;
		double totalCost=last_bid*num;
		list.add(new CommodityVO(goodID, goodName, goodType, price, last_bid,
				num, totalPrice, totalCost, memo));
	}

	public boolean delete(String goodID) {
		int index = find(goodID);
		if (index == -1)
			return false;
		else
			list.remove(index);
		return true;
	}

	public void modify(String goodID, String goodName, String goodSize,
			int num, double price, double totalPrice, String memo) {
		int index = find(goodID);
		double last_bid=100;
		double totalCost=last_bid*num;
		list.set(index, new CommodityVO(goodID, goodName, goodSize,  price,last_bid,num,
				totalPrice,totalCost, memo));
	}

	private int find(String goodID) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getID().equals(goodID)) {
				index = i;
				break;
			}
		}
		return index;
	}
	public ArrayList<CommodityVO> show(){
		return list;
	}
}
