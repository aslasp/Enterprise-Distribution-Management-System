package Presentation.salesui.manage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import po.ReceiptPO.ReceiptType;
import vo.ReceiptVO;
import vo.SaleReturnVO;
import vo.SaleVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.receiptui.Total;
import Presentation.salesui.manage.sale.AddSalePanel;
import Presentation.salesui.manage.sale.AddSaleReturnPanel;
import Presentation.salesui.manage.sale.SaleDetailPanel;
import Presentation.salesui.manage.sale.SaleReturnDetailPanel;
import Presentation.uihelper.MySort;
import businesslogic.salesbl.SaleList;
import businesslogic.salesbl.SalesController;
import businesslogic.userbl.User;
import businesslogicservice.salesblservice.SaleListBLService;
import businesslogicservice.salesblservice.SalesBLService;
import businesslogicservice.userblservice.UserBLService;

//销售管理主界面
public class SaleMgrPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
	MyButton saleBtn, saleReturnBtn, searchBtn, refreshBtn, detailBtn;
	JTable table;
	JScrollPane jsp;
	SaleMgrModel smm;
	JTextField searchFld;
	String keyWord;
	MainFrame parent;
	JComboBox bo;
	SaleListBLService listservice;

	public SaleMgrPanel(MainFrame frame) throws Exception {
		parent = frame;
		listservice = new SaleList();
		this.setBackground(Color.WHITE);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 3, 3, 3);
		c.fill = GridBagConstraints.BOTH;
		// -----------------------------
		JPanel btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		// ---------创建销售单--------------
		saleBtn = new MyButton("创建销售单",
				new ImageIcon("img/sales/sale-blue.png"));
		saleBtn.addActionListener(this);
		btnPnl.add(saleBtn);
		// --------创建销售退货单-------------
		saleReturnBtn = new MyButton("创建销售退货单", new ImageIcon(
				"img/sales/disapproved-blue.png"));
		saleReturnBtn.addActionListener(this);
		btnPnl.add(saleReturnBtn);
		// ---------refresh----------------
		refreshBtn = new MyButton("刷新", new ImageIcon(
				"img/sales/refresh-blue.png"));
		refreshBtn.addActionListener(this);
		btnPnl.add(refreshBtn);
		// ---------detail------------------
		detailBtn = new MyButton("查看详情", new ImageIcon("img/sales/detail.png"));
		detailBtn.addActionListener(this);
		btnPnl.add(detailBtn);
		// --------搜索-------------------
		searchFld = new JTextField(15);
		searchFld.setFont(new Font("楷体", Font.PLAIN, 13));
		searchFld.getDocument().addDocumentListener(new SearchFldListener());
		btnPnl.add(searchFld);
		// ---------------------------------
		searchBtn = new MyButton(new ImageIcon("img/sales/find-blue.png"));
		searchBtn.addActionListener(new SearchBtnListener());
		btnPnl.add(searchBtn);
		String[] se={"按客户查找","按业务员查找","按商品名查找","按仓库查找"};
		bo=new JComboBox(se);
		bo.setSelectedIndex(0);
		bo.setBackground(Color.white);
		bo.setForeground(frame.getTheme()[0]);
		btnPnl.add(bo);
		// -----------销售列表-----------------------
		smm = new SaleMgrModel();
		table = new JTable(smm);
		table.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 6;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(jsp, c);
		this.add(jsp);
	}

	class SearchFldListener implements DocumentListener {

		public void insertUpdate(DocumentEvent e) {
			keyWord = searchFld.getText();
		}

		public void removeUpdate(DocumentEvent e) {
			keyWord = searchFld.getText();
		}

		public void changedUpdate(DocumentEvent e) {
			keyWord = searchFld.getText();
		}

	}

	class SearchBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub			
			String txt=searchFld.getText();
			if(txt.equals("")){
				JOptionPane.showMessageDialog(null, "请输入查找内容!", "提示",
						JOptionPane.WARNING_MESSAGE);
			}else{
				SalesBLService service=null;
				try {
					service=new SalesController();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<ReceiptVO> vo=new ArrayList<ReceiptVO>();
				int i=bo.getSelectedIndex();
				//"按客户查找","按业务员查找",""按商品名查找","按仓库查找"};
					String t="客户";
					switch(i){
						case 1:t="业务员";break;
						case 2:t="商品名";break;
						case 3:t="仓库";break;
					}
					if(service.findSale(txt, t)!=null)
						vo.addAll(service.findSale(txt, t));
					if(service.findSaleReturn(txt, t)!=null)
						vo.addAll(service.findSaleReturn(txt, t));
					if(vo.size()==0){
						JOptionPane.showMessageDialog(null, "没有符合要求的单据！");
					}
					else{
						vo=MySort.sort(vo);
						try {
							RefreshSaleTable(vo);						
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}				
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if (e.getSource() == saleBtn) {
				parent.setRightComponent(new AddSalePanel(parent));
			}else if (e.getSource() == saleReturnBtn){
				int t = table.getSelectedRow();
				if (t >= 0) {
					String type=c.get(t).get(3);
					if(type.equals("销售单")){
						String pid = c.get(t).get(0);
						parent.setRightComponent(new AddSaleReturnPanel(parent, pid));
					}
					else
						JOptionPane.showMessageDialog(null, "请选择一条销售单进行退货!", "提示",
							JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "请选择一条销售单进行退货!", "提示",
						JOptionPane.WARNING_MESSAGE);
			
			}else if(e.getSource()==detailBtn){
			
				int t=table.getSelectedRow();
				if(t>=0){
					String type=c.get(t).get(3);
					String pid = c.get(t).get(0);
					if(type.equals("销售单"))
						parent.setRightComponent(new SaleDetailPanel(pid,parent));
					else 
						parent.setRightComponent(new SaleReturnDetailPanel(pid,parent));
							
				}
		
			}else if(e.getSource()==refreshBtn){
				RefreshPanel();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	class MyButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		MyButton(String text, Icon icon) {
			super(text, icon);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			this.setForeground(new Color(47, 73, 136));
			this.setBorderPainted(false);
			this.setBackground(Color.white);
			this.setFocusPainted(false);
		}

		MyButton(Icon icon) {
			super(icon);
			this.setBorderPainted(false);
			this.setBackground(Color.white);
			this.setFocusPainted(false);
		}
	}

	class SaleMgrModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "单据编号", "日期", "状态", "类型", "销售商", "操作员", "折让前金额",
				"折让后金额" };

		public int getRowCount() {
			return c.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public Object getValueAt(int row, int col) {
			return c.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {
			c.add(v);
		}

		public void removeRow(int row) {
			c.remove(row);
		}
	}
	

	// "单据编号", "日期", "状态", "类型", "销售商", "操作员", "折让前金额",
	// "折让后金额"
	public void RefreshSaleTable(ArrayList<ReceiptVO> vo) throws Exception {
		UserBLService user = new User();
		c.clear();
		for (int i = 0; i < vo.size(); i++) {
			ReceiptVO v = vo.get(i);
			ArrayList<String> line = new ArrayList<String>();
			line.add(v.getId());
			line.add(v.getDate());
			int s = v.getStatus();
			line.add(Total.getStatus(s));
			String name = user.showUser(v.getUser()).getName();
			if (v.getType() == ReceiptType.SALE) {
				line.add("销售单");
				SaleVO pv = (SaleVO) v;
				line.add(v.getMemberName());
				line.add(name);
				line.add(pv.getTotalOrigin() + "");
				line.add(pv.getTotalValue() + "");
			} else {
				line.add("销售退货单");
				SaleReturnVO prv = (SaleReturnVO) v;
				line.add(v.getMemberName());
				line.add(name);
				line.add(prv.getTotal()[1] + "");
				line.add(prv.getTotal()[2] + "");
			}

			c.add(line);

		}
		table.revalidate();
		SaleMgrPanel.this.repaint();
	}

	public void RefreshPanel() throws Exception {
		if (listservice.getAllSale() != null)
			SaleMgrPanel.this.RefreshSaleTable(listservice.getAllSale());
	}
}
