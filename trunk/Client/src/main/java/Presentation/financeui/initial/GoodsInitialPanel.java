package Presentation.financeui.initial;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import vo.GoodsVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;

public class GoodsInitialPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	GoodsModel gm;
	JTable goodsTable;
	JScrollPane jsp;
	JButton addBtn, delBtn;
	AddInitialPanel subparent; 
	MainFrame parent;
	ArrayList<ArrayList<String>> goodsC = new ArrayList<ArrayList<String>>();
	ArrayList<GoodsVO> goods=new ArrayList<GoodsVO>();
	JPanel btnPnl;
	public GoodsInitialPanel(MainFrame frame) {
		parent=frame;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		c.fill = GridBagConstraints.BOTH;
		// --------表格-------------
		gm = new GoodsModel();
		goodsTable = new JTable(gm);
		goodsTable.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < goodsTable.getColumnCount(); i++) {
			goodsTable.getColumn(goodsTable.getColumnName(i)).setCellRenderer(
					tcr);
		}
		jsp = new JScrollPane(goodsTable);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(jsp, c);
		this.add(jsp);
		// ------商品增删处-----------
		btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		//
		addBtn = new JButton("添加商品");
		addBtn.setFont(font);
		addBtn.setBackground(new Color(204, 242, 239));
		addBtn.setFocusPainted(false);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new addGoodsInitial(parent,GoodsInitialPanel.this);				
			}
		});
		btnPnl.add(addBtn);
		delBtn = new JButton("删除选中");
		delBtn.setFont(font);
		delBtn.setBackground(new Color(204, 242, 239));
		delBtn.setFocusPainted(false);
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int allselected[]=goodsTable.getSelectedRows();
				if(allselected.length<=0)
					JOptionPane.showMessageDialog(null,"请选择商品!","提示",JOptionPane.WARNING_MESSAGE);
				else{
					for(int j=allselected.length-1;j>=0;j--){
					int selected=allselected[j];
					for(int i=selected+1;i<goodsC.size();i++){	
						    String buffer[]=goodsC.get(i).get(0).split("-");
						    String front=buffer[0]+"-"+buffer[1];
							double d=Double.parseDouble(buffer[2])-1;
							NumberFormat nf = NumberFormat.getInstance();
						     nf.setMinimumIntegerDigits(4); 
						     nf.setGroupingUsed(false);
							 goodsC.get(i).set(0,front+"-"+nf.format(d));					
					}
					gm.removeRow(selected);
					goodsTable.revalidate();					
				}
				}
			}
		});
		btnPnl.add(delBtn);
	}

	class GoodsModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "商品编号", "商品名称", "分类", "型号", "默认进价", "默认售价" };

		public int getRowCount() {
			return goodsC.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public String getValueAt(int row, int col) {
			return goodsC.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}
		public void addRow(ArrayList<String> v) {
			goodsC.add(v);
		}

		public void removeRow(int row) {
			goodsC.remove(row);
			goods.remove(row);
		}

	}
	
	public String getNewID(String id){
		     String[] buffer=id.split("-");
		     String front=buffer[0]+'-'+buffer[1];
		     double d=goodsC.size()+1;
			 NumberFormat nf = NumberFormat.getInstance();
		     nf.setMinimumIntegerDigits(4); 
		     nf.setGroupingUsed(false);
		     return front+"-"+nf.format(d);
		
	}
	
	 public void RefreshCTable(ArrayList<Object> VO){
			for(Object oo:VO){
				boolean isChongfu=false;
				GoodsVO vo=(GoodsVO)oo;
				ArrayList<String> line=new ArrayList<String>();
				line.add(getNewID(vo.getGoodsID()));
				line.add(vo.getName());
				line.add(vo.getGoodsClass());
				line.add(vo.getSize());
				line.add(0+"");
				line.add(0+"");
				for(ArrayList<String> te:goodsC){
					if(te.get(1).equals(vo.getName())&&te.get(2).equals(vo.getGoodsClass())&&te.get(3).equals(vo.getSize())&&te.get(4).equals(0+"")&&te.get(5).equals(0+"")){
						isChongfu=true;
					}
				}
				if(isChongfu==false){
				  goodsC.add(line);	
				  goods.add(vo);
				}
				else
				  JOptionPane.showMessageDialog(null,"您已选择过该商品!","提示",JOptionPane.WARNING_MESSAGE);
			}
			goodsTable.revalidate();
	 }
	
	public void RefreshCTable(GoodsVO vo){
		ArrayList<String> line=new ArrayList<String>();
		line.add(getNewID(vo.getGoodsID()));
		line.add(vo.getName());
		line.add(vo.getGoodsClass());
		line.add(vo.getSize());
		line.add(0+"");
		line.add(0+"");
		goodsC.add(line);
		goods.add(vo);
	}
	
	public ArrayList<GoodsVO> getGoods(){
		if(goods.size()==0)
			return null;
		return goods;
	}
	
	public void setParent(AddInitialPanel pane){
		this.subparent=pane;
	}
}
