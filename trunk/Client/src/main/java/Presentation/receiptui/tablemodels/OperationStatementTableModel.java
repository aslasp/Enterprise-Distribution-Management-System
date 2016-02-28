package Presentation.receiptui.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

//经营情况表
public class OperationStatementTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<String>>  cm=new ArrayList<ArrayList<String>>();
	ArrayList<String> totalIn,totalOut;
	String[] head={"","","","","","",""};
	String[] headin={"销售收入","商品报溢收入","成本调价收入","进退货差价收入","代金券差额收入","折让金额","总收入"};
	String[] headout={"销售成本","商品报损支出","库存赠送支出","代金券使用支出","总支出","",""};
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
	
	public void RefreshTable(double[] data){
		cm.clear();
		initial();
		ArrayList<String> line=new ArrayList<String>();
		line.add("收入类");
		for(int i=0;i<6;i++) line.add("");
		cm.add(line);line=new ArrayList<String>();
		cm.add(totalIn);
		for(int i=0;i<head.length;i++)
			line.add(data[i]+"");
		cm.add(line);line=new ArrayList<String>();
		line.add("支出类");
		for(int i=0;i<6;i++) line.add("");
		cm.add(line);line=new ArrayList<String>();
		
		cm.add(totalOut);
		for(int i=head.length;i<head.length+5;i++)
			line.add(data[i]+"");
		for(int i=0;i<head.length-5;i++) line.add("");
		cm.add(line); line=new ArrayList<String>();
		line.add("利润");
		for(int i=0;i<6;i++) line.add("");
		cm.add(line);line=new ArrayList<String>();
		line.add(data[headin.length+5]+"");
		for(int i=0;i<6;i++) line.add("");
		cm.add(line);
		
		
		
		
		
	}
	
	public void initial(){
		totalIn=new ArrayList<String>();
		totalOut=new ArrayList<String>();
		for(int i=0;i<head.length;i++)
		{
			totalIn.add(headin[i]);
			totalOut.add(headout[i]);
			
		}
	
	
		
	}
	
	public ArrayList<ArrayList<String>> getExportContent(){
		
		return cm;
	}
}
