package businesslogic.financebl;

import java.util.ArrayList;

import vo.ClauseItemVO;

public class MockCashList extends CashList {
	private String id;
	private String userid;
	private Account account;
	private ArrayList<ClauseItemVO> list;
	private double total;

	public MockCashList(String id, String uID, Account account)
			throws Exception {
		this.id = id;
		this.userid = uID;
		this.account = account;
		this.total = 0;
		this.list = new ArrayList<ClauseItemVO>();
	}

	public void AddItem(ClauseItemVO item) {
		this.list.add(item);
		this.total += item.getMoney();
	}

	public void DeleteItem(ClauseItemVO item) {
		this.list.remove(item);
		this.total -= item.getMoney();
	}

	public void setTotal(double totalMoney) {
		this.total = totalMoney;
	}

	public Account getAccout() {
		return this.account;
	}

	public double getTotal() {
		return this.total;
	}

}
