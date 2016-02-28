package businesslogic.stockbl.stockManage;

//库存报损单model

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import vo.StockOverOrLowVO;
import businesslogic.userbl.User;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import businesslogicservice.userblservice.UserBLService;

public class LowModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<ArrayList<String>> rowData;
	String columnNames[] = { "编号", "名称", "型号", "差额", "创建者", "创建日期", "状态" };// 列名
	String result;

	// 做一个构造函数，用于初始化数据表模型
	public LowModel(ArrayList<StockOverOrLowVO> list) {
		rowData = new ArrayList<ArrayList<String>>();

		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> hang = new ArrayList<String>();
				StockOverOrLowVO vo = list.get(i);
				hang.add(vo.getId());
				hang.add(vo.getGoodsName());
				hang.add(vo.getSize());
				int gap = vo.getGap();
				if (gap < 0) {
					gap = -gap;
				}
				hang.add(String.valueOf(gap));
				hang.add(getUserNameByID(vo.getUser()));
				hang.add(vo.getDate());
				if (vo.getStatus() == 0) {
					hang.add("待审批");
				} else if (vo.getStatus() == 1) {
					hang.add("审批不通过");
				} else if (vo.getStatus() == 2) {
					hang.add("审批通过");
				} else if (vo.getStatus() == 3) {
					hang.add("执行完毕");
				}

				// 加入到rowData
				rowData.add(hang);
			}

		}
	}

	public LowModel() {
		rowData = new ArrayList<ArrayList<String>>();

		ArrayList<StockOverOrLowVO> list = new ArrayList<StockOverOrLowVO>();
		StockControlBLService controller = new StockControlController();
		list = controller.showStockLowReceipt();

		for (int i = 0; i < list.size(); i++) {
			ArrayList<String> hang = new ArrayList<String>();
			StockOverOrLowVO vo = list.get(i);
			hang.add(vo.getId());
			hang.add(vo.getGoodsName());
			hang.add(vo.getSize());
			int gap = vo.getGap();
			if (gap < 0) {
				gap = -gap;
			}
			hang.add(String.valueOf(gap));
			hang.add(getUserNameByID(vo.getUser()));
			hang.add(vo.getDate());
			if (vo.getStatus() == 0) {
				hang.add("待审批");
			} else if (vo.getStatus() == 1) {
				hang.add("审批不通过");
			} else if (vo.getStatus() == 2) {
				hang.add("审批通过");
			} else if (vo.getStatus() == 3) {
				hang.add("执行完毕");
			}

			// 加入到rowData
			rowData.add(hang);
		}
	}

	// 根据单据的userID找到userName
	private String getUserNameByID(String id) {
		String userName = "";
		try {
			UserBLService controller = new User();
			userName = controller.showUser(id).getName();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return userName;

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
