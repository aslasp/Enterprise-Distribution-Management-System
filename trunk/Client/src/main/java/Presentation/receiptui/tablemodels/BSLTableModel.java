package Presentation.receiptui.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

//经营情况表
public class BSLTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<String>>  cm=new ArrayList<ArrayList<String>>();
	String[] head={"销售收入", "商品报溢", "成本调价", "进货退货差价", "代金券与实际收款差额",
			"折让金额", "总收入","销售成本", "商品报损", "商品赠出", "总支出" ,"总利润"};
	
	public int getRowCount() {
		return cm.size();
	}

	public int getColumnCount() {
		return head.length;
	}

	public void addRow(ArrayList<String> v) {
		cm.add(v);
	}

	public void removeRow(int row) {
		cm.remove(row);
	}

	public String getValueAt(int row, int col) {
		return cm.get(row).get(col);
	}

	public String getColumnName(int col) {
		return head[col];
	}
	
	public boolean isCellEditable(int rowIndex,int columnIndex){
		return false;
	}
	
	public void RefreshTable(double[] data){
		cm.clear();
		ArrayList<String> st=new ArrayList<String>();
		for(int i=0;i<data.length;i++){
			st.add(String.valueOf(data[i]));
		}
		cm.add(st);
	}
	
	public void initial(){
		
	
	
		
	}
	
	public ArrayList<ArrayList<String>> getExportContent(){
		ArrayList<String> head1=new ArrayList<String>();
		for(int i=0;i<head.length;i++)
			head1.add(head[i]);
		ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
		result.add(head1);
		for(int i=0;i<cm.size();i++)
			result.add(cm.get(i));
		return result;
	}
}
