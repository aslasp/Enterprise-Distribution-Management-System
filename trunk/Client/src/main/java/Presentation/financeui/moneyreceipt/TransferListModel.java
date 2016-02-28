package Presentation.financeui.moneyreceipt;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
//转账列表的TableModel,收款单和付款单公用
public class TransferListModel extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
		String head[]={"银行账户","转账金额","备注"};
		public TransferListModel(ArrayList<ArrayList<String>> content){
			c=content;
		}
		public int getRowCount() {
			return c.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public String getValueAt(int row, int col) {
			return c.get(row).get(col);
		}
		public String getColumnName(int col){
			return head[col];
		}
		public void addRow(ArrayList<String> v) {

			c.add(v);
		}
		public void removeRow(int row) {
			c.remove(row);
		}
		
	
}
