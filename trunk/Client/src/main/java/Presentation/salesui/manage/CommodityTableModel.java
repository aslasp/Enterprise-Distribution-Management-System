package Presentation.salesui.manage;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class CommodityTableModel extends AbstractTableModel{

	
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<String>> cmContent=new ArrayList<ArrayList<String>>();
	String head[] = { "商品编号","名称", "型号","数量","单价","金额","备注" };
	public int getRowCount() {
		return cmContent.size();
	}
	
	public boolean isCellEditable(int row,int column){
		if(column==3||column==4||column==6)
			return true;
		else
			return false;
	}
	public void addRow(ArrayList<String>  s) {
		cmContent.add(s);
	}
 

	public int getColumnCount() {
		return head.length;
	}

	public String getValueAt(int row, int col) {
		return cmContent.get(row).get(col);
	}
	public String getColumnName(int col){
		return head[col];
	}
	 public void setValueAt(Object value, int row, int column){  
		 double v=0;
		 if(column==6){
			 cmContent.get(row).set(column,(String)value ); 
				fireTableCellUpdated(row, column);
		 }
		 else{
			 if(((String)value).equals(""))
				 JOptionPane.showMessageDialog(null, "请输入合法数值!");
			 else{
				 try{
					 v=Double.parseDouble((String)value);
					 if(v<=0)
						 JOptionPane.showMessageDialog(null, "请输入合法数值!"); 
					 else{
						 cmContent.get(row).set(column,(String)value ); 
						 fireTableCellUpdated(row, column);}
					
				 }
				 catch(Exception e){
					 JOptionPane.showMessageDialog(null, "请输入数字!");
				 }
			}
	}
			
}  
	
	
	public ArrayList<ArrayList<String>> getContent(){
		return cmContent;
	}
	
}

