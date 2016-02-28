package Presentation.receiptui.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import po.ReceiptPO.ReceiptType;
import vo.CollectionVO;
import vo.PaymentVO;
import vo.ReceiptVO;
import Presentation.receiptui.Total;
import businesslogic.userbl.User;
import businesslogicservice.userblservice.UserViewService;

//经营历程表
public class OperationHistoryTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	ArrayList<ArrayList<String>>  cm=new ArrayList<ArrayList<String>>();
	private static final long serialVersionUID = 1L;
	String head[] = { "单据编号", "创建日期", "状态","业务类型", "交易客户", "交易金额", "操作员", "备注" };
//	public OperationHistoryTableModel(ArrayList<ArrayList<String>> content) {
	//	cm = content;
	//}

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
	public void setValueAt(Object value, int row, int col) {
		cm.get(row).set(col, (String) value);
		fireTableCellUpdated(row, col);
	}

	public String getColumnName(int col) {
		return head[col];
	}
	
	
	public void RefreshTable(ArrayList<ReceiptVO> vo) throws Exception{
		UserViewService user=new User();
		cm.clear();
		for(int i=0;i<vo.size();i++){
			ArrayList<String> line=new ArrayList<String>();
			ReceiptVO v=vo.get(i);
			line.add(v.getId());
			line.add(v.getDate());
			line.add(Total.getStatus(v.getStatus()));
			line.add(Total.getType(v.getType()));
			
			//销售/进货
			if(v.getType()==ReceiptType.PAYMENT)
			{
				PaymentVO pv=(PaymentVO)v;
				line.add(pv.getMemberName());
				
			}else if(v.getType()==ReceiptType.COLLECTION)
			{
				CollectionVO cv=(CollectionVO)v;
				line.add(cv.getMemberName());
			}
			else line.add(v.getMemberName());
		
			if(v.getType()==ReceiptType.STOCKOVER||v.getType()==ReceiptType.STOCKLOW)
				line.add("");
			else line.add(Total.getTotal(v));
			line.add(user.getName(v.getUser()));
			line.add(v.getInfo());
			cm.add(line);	
			
			
		}
	}
	public ArrayList<ArrayList<String>>  getExportContent(){
		ArrayList<String> head1=new ArrayList<String>();
		for(int i=0;i<head.length;i++)
			head1.add(head[i]);
		ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
		result.add(head1);
		for(int i=0;i<cm.size();i++)
			result.add(cm.get(i));
		return result;
	}
	
	public void clear(){
		cm.clear();
		
		
	}

}
