package businesslogic.stockbl.gift;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import vo.CommodityVO;

public class GiftCommodityListModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<ArrayList<String>> rowData;
	String columnNames[] = { "编号", "名称", "型号", "数量" };// 列名

	// 做一个构造函数，用于初始化数据表模型
	public GiftCommodityListModel(ArrayList<CommodityVO> list) {
		rowData = new ArrayList<ArrayList<String>>();

		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> hang = new ArrayList<String>();
				CommodityVO vo = list.get(i);
				hang.add(vo.getID());
				hang.add(vo.getName());
				hang.add(vo.getType());
				hang.add(String.valueOf(vo.getNum()));
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

	// 设置表格某些列可以双击修改
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 3) {
			return true;
		} else {
			return false;
		}
	}

	public void setValueAt(Object value, int row, int col) {
		ArrayList<String> hang = rowData.get(row);
		hang.set(col, (String) value);
		rowData.set(row, hang);
		fireTableCellUpdated(row, col);
	}
}
