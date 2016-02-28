package Presentation.receiptui.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import po.ReceiptPO.ReceiptType;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import vo.ReceiptVO;
import vo.SaleReturnVO;
import vo.SaleVO;
import Presentation.salesui.manage.PurchaseMgrPanel;
import businesslogic.userbl.User;
import businesslogicservice.userblservice.UserBLService;

public class SaleDetailTableModel extends AbstractTableModel{
	/**
	 * 没加监听（方法没有全部实现）
	 */
	
	ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();
	private static final long serialVersionUID = 1L;
	String head[]={"单据编号","日期","状态","类型","销售商","操作员","总额合计"};

	
	public int getRowCount() {
		return c.size();
	}

	public int getColumnCount() {
		return head.length;
	}

	public Object getValueAt(int row, int col) {
		return c.get(row).get(col);
	}
	public String getColumnName(int col){
		return head[col];
	}
	public void addRow(ArrayList<String> v){
		c.add(v);
	}
	public void removeRow(int row){
		c.remove(row);
	}

//加急置顶显示  显示图标
//单据编号","日期","状态","类型","供应商","操作员","总额合计
public void RefreshList(ArrayList<ReceiptVO> vo) throws Exception{
	UserBLService user=new User();
	c.clear();
	for(int i=0;i<vo.size();i++){
		ReceiptVO v=vo.get(i);
		ArrayList<String> line=new ArrayList<String>();
		line.add(v.getId());
		line.add(v.getDate());
		int s=v.getStatus();
		if(s==0){
			line.add("待审批");
		}else if(s==1)
			line.add("审批不通过");
		else if(s==2)
			line.add("审批通过");
		else if(s==3)line.add("执行完毕");
		
		String name=user.showUser(v.getUser()).getName();
		if(v.getType()==ReceiptType.SALE)
		{
			line.add("销售单");;
		SaleVO pv=(SaleVO)v;line.add(v.getMemberName());line.add(name);
			line.add(pv.getTotalOrigin()+"");line.add(pv.getTotalValue()+"");
		}else{
			line.add("销售退货单");
			SaleReturnVO prv=(SaleReturnVO)v;
			line.add(v.getMemberName());line.add(name);
			line.add(prv.getTotal()[1]+"");line.add(prv.getTotal()[2]+"");
		}
		
		c.add(line);
		
		}
}
//"单据编号","日期","状态","类型","交易客户","操作员","总额合计"}
	public ArrayList<ArrayList<String>>  getExportConent(){
		ArrayList<ArrayList<String>> v=new ArrayList<ArrayList<String>>();
		ArrayList<String> head=new ArrayList<String>();
		head.add("单据编号");head.add("日期");
		head.add("状态");head.add("类型");
		head.add("销售商");head.add("操作员");
		head.add("总额合计");
		v.add(head);
		for(int i=0;i<c.size();i++)
			v.add(c.get(i));
		return v;
	}
	public void clear(){
		c.clear();
	}

}
