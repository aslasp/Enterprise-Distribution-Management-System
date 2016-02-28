package businesslogic.stockbl.gift;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import vo.GiftVO;
import vo.UserVO;
import businesslogic.userbl.User;
import businesslogicservice.stockblservice.giftblservice.GiftBLService;
import businesslogicservice.userblservice.UserBLService;

public class GiftModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	ArrayList<ArrayList<String>> rowData;
	String columnNames[] = { "编号", "日期", "客户编号", "客户姓名", "赠品种数", "状态", "创建者" };

	// 做一个构造函数，用于初始化数据表模型
	public GiftModel(ArrayList<GiftVO> list) {
		rowData = new ArrayList<ArrayList<String>>();

		if (list.size() != 0) {
			ArrayList<String> hang = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				GiftVO vo = list.get(i);
				hang.add(vo.getId());
				hang.add(vo.getDate());
				hang.add(vo.getMemberID());
				hang.add(vo.getMemberName());
				hang.add(String.valueOf(vo.getGiftList().size()));
				int status = vo.getStatus();
				if (status == 0) {
					hang.add("待审批");
				} else if (status == 1) {
					hang.add("审批不通过");
				} else if (status == 2) {
					hang.add("待执行");
				} else if (status == 3) {
					hang.add("执行完毕");
				}
				String userName = "";
				try {
					UserBLService userController = new User();
					userName = userController.showUser(vo.getUser()).getName();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				hang.add(userName);

				// 加入到rowData
				rowData.add(hang);
			}

		}
	}

	public GiftModel() {
		rowData = new ArrayList<ArrayList<String>>();

		ArrayList<GiftVO> list = new ArrayList<GiftVO>();
		GiftBLService controller = null;
		try {
			controller = new GiftController();
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		list = controller.getGiftList();
		for (int i = 0; i < list.size(); i++) {
			ArrayList<String> hang = new ArrayList<String>();
			GiftVO vo = list.get(i);
			hang.add(vo.getId());
			hang.add(vo.getDate());
			hang.add(vo.getMemberID());
			hang.add(vo.getMemberName());
			hang.add(String.valueOf(vo.getGiftList().size()));
			int status = vo.getStatus();
			if (status == 0) {
				hang.add("待审批");
			} else if (status == 1) {
				hang.add("审批不通过");
			} else if (status == 2) {
				hang.add("待执行");
			} else if (status == 3) {
				hang.add("执行完毕");
			}

			String userName = "";
			try {
				UserBLService userController = new User();
				if (vo.getUser() == null) {
					userName = "系统";
				} else {
					UserVO u = userController.showUser(vo.getUser());
					if (u != null) {
						userName = u.getName();
					} else {
						userName = "系统";
					}
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			hang.add(userName);

			// 加入到rowData
			rowData.add(hang);
		}
	}

	public void addRow(ArrayList<String> v) {
		rowData.add(v);
	}

	public void removeRow(int row) {
		rowData.remove(row);
	}

	// 得到共有多少行
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}

	// 得到共有多少列
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return columnNames.length;
	}

	// 得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		return ((ArrayList<String>) this.rowData.get(rowIndex))
				.get(columnIndex);
	}

	// 得到列名称
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return columnNames[column];
	}
}
