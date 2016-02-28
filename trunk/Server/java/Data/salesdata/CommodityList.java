package Data.salesdata;

import java.util.ArrayList;

public class CommodityList {
	private ArrayList<Commodity> list;

	public CommodityList() {
		list = new ArrayList<Commodity>();
	}

	public void add(String goodID, String goodName, String goodSize, int num,
			double price, double totalPrice, String memo) {
		list.add(new Commodity(goodID, goodName, goodSize, num, price,
				totalPrice, memo));
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
		list.set(index, new Commodity(goodID, goodName, goodSize, num, price,
				totalPrice, memo));
	}

	private int find(String goodID) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).goodID.equals(goodID)) {
				index = i;
				break;
			}
		}
		return index;
	}
	public ArrayList<Commodity> show(){
		return list;
	}
}
