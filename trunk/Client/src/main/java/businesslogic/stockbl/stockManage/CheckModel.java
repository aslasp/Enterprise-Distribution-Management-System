package businesslogic.stockbl.stockManage;

//库存盘点的model

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import businesslogicservice.stockblservice.controlblservice.StockControlBLService;

public class CheckModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<ArrayList<String>> rowData;
	String columnNames[] = { "名称", "型号", "库存数量", "库存均价", "批次", "批号", "出厂日期" };// 列名
	String result;

	// 做一个构造函数，用于初始化数据表模型
	public CheckModel(ArrayList<ArrayList<String>> list) {
		rowData = new ArrayList<ArrayList<String>>();

		if (list.size() != 0) {
			ArrayList<String> hang = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> tp = list.get(i);
				if (tp.size() != 7) {
					System.out.println("CheckModel.CheckModel()中数据有错啦！！！");
				} else {
					hang.add(tp.get(0));// 名称
					hang.add(tp.get(1));// 型号
					hang.add(tp.get(2));// 数量
					hang.add(tp.get(3));// 均价
					hang.add(tp.get(4));// 批次
					hang.add(tp.get(5));// 批号
					hang.add(tp.get(6));// 出厂日期
					// 加入到rowData
					rowData.add(hang);
				}
			}

		}
	}

	public CheckModel() {
		rowData = new ArrayList<ArrayList<String>>();

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		StockControlBLService controller = new StockControlController();
		list = controller.checkStock();

		for (int i = 0; i < list.size(); i++) {
			ArrayList<String> hang = new ArrayList<String>();
			ArrayList<String> tp = list.get(i);
			if (tp.size() != 7) {
				System.out.println("CheckModel.CheckModel()中数据有错啦！！！");
			} else {
				hang.add(tp.get(0));// 名称
				hang.add(tp.get(1));// 型号
				hang.add(tp.get(2));// 数量
				hang.add(tp.get(3));// 均价
				hang.add(tp.get(4));// 批次
				hang.add(tp.get(5));// 批号
				hang.add(tp.get(6));// 出厂日期
				// 加入到rowData
				rowData.add(hang);
			}
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

	// "名称", "型号", "库存数量", "库存均价", "批次", "批号","出厂日期"
	public ArrayList<ArrayList<String>> getExportConent() {
		ArrayList<ArrayList<String>> v = new ArrayList<ArrayList<String>>();
		ArrayList<String> head = new ArrayList<String>();
		head.add("名称");
		head.add("型号");
		head.add("库存数量");
		head.add("库存均价");
		head.add("批次");
		head.add("批号");
		head.add("出厂日期");
		v.add(head);
		for (int i = 0; i < rowData.size(); i++)
			v.add(rowData.get(i));
		return v;
	}
}
