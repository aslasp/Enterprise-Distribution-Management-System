package Presentation.receiptui;

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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import po.ReceiptPO.ReceiptType;
import vo.CollectionVO;
import vo.PaymentVO;
import vo.ReceiptVO;
import Presentation.financeui.moneyreceipt.CashDetailPanel;
import Presentation.financeui.moneyreceipt.CollectionDetailPanel;
import Presentation.financeui.moneyreceipt.PaymentDetailPanel;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.salesui.manage.purchase.PurchaseDetailPanel;
import Presentation.salesui.manage.purchase.PurchaseReturnDetailPanel;
import Presentation.salesui.manage.sale.SaleDetailPanel;
import Presentation.salesui.manage.sale.SaleReturnDetailPanel;
import Presentation.stockui.giftmanage.GiftDetailPanel;
import Presentation.stockui.stockmanage.LossDetailPanel;
import Presentation.stockui.stockmanage.OverflowDetailPanel;
import businesslogic.receiptbl.ReceiptController;
import businesslogic.userbl.User;
import businesslogicservice.receiptblservice.ReceiptBLService;
import businesslogicservice.userblservice.UserViewService;

public class ReceiptMgrPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ReceiptBLService service;
	Color color = new Color(115, 46, 126);
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	static MainFrame father;
	MyButton approvedBtn, disapprovedBtn, modBtn, refreshBtn, findBtn;
	JComboBox findbox;
	JTabbedPane tab;
	JScrollPane jsp1, jsp2;
	JTable t1, t2;
	ReceiptTableModel rtm1, rtm2;
	static ArrayList<ArrayList<Object>> c1 = new ArrayList<ArrayList<Object>>();
	ArrayList<ArrayList<Object>> c2 = new ArrayList<ArrayList<Object>>();
	// --------------
	String approvePath = "img/promotion/approved.png";
	String disapprovePath = "img/promotion/disapproved.png";
	String detailPath = "img/promotion/detail.png";
	String refreshPath = "img/promotion/refresh.png";
	String findPath = "img/promotion/find.png";

	// --------------
	public ReceiptMgrPanel(MainFrame myFather) throws Exception {
		service = new ReceiptController();
		father = myFather;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 10, 5, 10);
		c.fill = GridBagConstraints.BOTH;
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// ----------------------------
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
		// --------通过-----------------
		approvedBtn = new MyButton("通过", new ImageIcon(approvePath));
		btnPnl.add(approvedBtn);
		approvedBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ArrayList<String> choose = BatchChoose();
				if (choose != null) {
					String batch[] = new String[choose.size()];
					for (int i = 0; i < choose.size(); i++)
						batch[i] = choose.get(i);
					int result = service.Batch(batch, 2);
					if (result != 0) {
						String tip="审批失败!";
						if(batch.length==0){
							int i=t1.getSelectedRow();
							ReceiptType type=Total.getsType((String) c1.get(i).get(2));
							tip=Total.getFailReason(type, result)+tip;
						}
						JOptionPane.showMessageDialog(null, tip, "提示",
								JOptionPane.WARNING_MESSAGE);
					}
					Refresh();

				} else
					JOptionPane.showMessageDialog(null, "请选择一条单据审批！", "提示",
							JOptionPane.WARNING_MESSAGE);

			}

		});
		// -------不通过------------------
		disapprovedBtn = new MyButton("不通过", new ImageIcon(disapprovePath));
		btnPnl.add(disapprovedBtn);
		disapprovedBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*
				 * int[] row = t1.getSelectedRows(); if (row.length > 0) { for
				 * (int i = 0; i < row.length; i++) { String id =
				 * c1.get(row[i]).get(0).toString(); if (service.Approve(id, 1)
				 * != 0) JOptionPane.showMessageDialog(null, "审批失败！", "提示",
				 * JOptionPane.WARNING_MESSAGE); } Refresh(); }
				 */

				ArrayList<String> choose = BatchChoose();
				if (choose != null) {
					String batch[] = new String[choose.size()];
					for (int i = 0; i < choose.size(); i++)
						batch[i] = choose.get(i);
					service.Batch(batch, 1);
					Refresh();
				} else
					JOptionPane.showMessageDialog(null, "请选择一条单据审批！", "提示",
							JOptionPane.WARNING_MESSAGE);

			}

		});
		// -------高级审批-----------------
		modBtn = new MyButton("高级审批", new ImageIcon(detailPath));
		modBtn.addActionListener(this);
		btnPnl.add(modBtn);
		// -------刷新--------------------
		refreshBtn = new MyButton("刷新", new ImageIcon(refreshPath));
		btnPnl.add(refreshBtn);
		refreshBtn.addActionListener(this);
		// -------筛选--------------------
		String type[] = { "全部", "销售单", "销售退货单", "进货单", "进货退货单", "收款单", "付款单",
				"现金费用单", "库存报损单", "库存报溢单", "库存赠送单" };
		findbox = new JComboBox<String>(type);
		findbox.setBackground(Color.white);
		findbox.setForeground(color);
		findbox.setFont(font);
		btnPnl.add(findbox);
		findBtn = new MyButton(new ImageIcon(findPath));
		btnPnl.add(findBtn);
		findBtn.addActionListener(this);
		// ------------------------------
		tab = new JTabbedPane();
		tab.setFont(font);
		tab.setBackground(Color.white);
		tab.setForeground(color);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(tab, c);
		this.add(tab);
	
		// -------待审批-------------------
		rtm1 = new ReceiptTableModel(c1, 0);
		t1 = new JTable(rtm1);
		t1.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		// 加急显示的时候，传一个需要改变颜色的行数的Arraylist进去
		// 无参构造函数是不加急显示的
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < t1.getColumnCount() - 1; i++) {
			t1.getColumn(t1.getColumnName(i)).setCellRenderer(tcr);

		}
		jsp1 = new JScrollPane(t1);

		tab.add("待审批单据", jsp1);
		// ---------已审批------------------
		rtm2 = new ReceiptTableModel(c2, 1);
		t2 = new JTable(rtm2);
		t2.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		for (int i = 0; i < t2.getColumnCount(); i++) {
			t2.getColumn(t2.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp2 = new JScrollPane(t2);
		tab.add("已审批单据", jsp2);
		tab.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				Refresh();
			}

		});

	}

	class ReceiptTableModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "单据编号", "创建日期", "业务类型", "交易客户", "交易金额", "操作员", "备注",
				"选择" };
		String head2[] = { "单据编号", "创建日期", "业务类型", "交易客户", "交易金额", "操作员", "备注" };
		ArrayList<ArrayList<Object>> cm;
		int tableType;// 0_待审批,1_已审批

		public ReceiptTableModel(ArrayList<ArrayList<Object>> content, int t) {
			cm = content;
			tableType = t;
		}

		public int getRowCount() {
			return cm.size();
		}

		public int getColumnCount() {
			if (tableType == 0)
				return head.length;
			return head2.length;
		}

		public void addRow(ArrayList<Object> v) {
			cm.add(v);
		}

		public void removeRow(int row) {
			cm.remove(row);
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public Object getValueAt(int row, int col) {
			return cm.get(row).get(col);
		}

		public String getColumnName(int col) {
			if (tableType == 0)
				return head[col];
			return head2[col];
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if ((tableType == 0) && (columnIndex == 7))
				return true;
			else
				return false;
		}

		public void setValueAt(Object value, int row, int col) {
			cm.get(row).set(col, value);
			fireTableCellUpdated(row, col);
		}

		public void mySetValueAt(Object value, int row, int col) {
			cm.get(row).set(col, value);
		}
	}

	class MyButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		MyButton(String text, Icon icon) {
			super(text, icon);
			this.setFont(font);
			this.setForeground(color);
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

	public void Refresh() {

		try {
			ArrayList<ReceiptVO> vo = new ArrayList<ReceiptVO>();
			if (service.ToApprove() != null) {
				vo = service.ToApprove();
				ReceiptMgrPanel.this.RefreshTable(vo, 0);
			}
			if (service.Approved() != null) {
				vo = service.Approved();
				ReceiptMgrPanel.this.RefreshTable(vo, 1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// { "单据编号", "创建日期", "业务类型", "交易客户", "交易金额", "操作员", "备注" }
	// 0待审批 1已审批
	public void RefreshTable(ArrayList<ReceiptVO> vo, int t) throws Exception {
		UserViewService user = new User();
		ArrayList<ArrayList<Object>> tableContent;
		if (t == 0) {
			c1.clear();
			tableContent = c1;
		} else {
			c2.clear();
			tableContent = c2;
		}
		ArrayList<Integer> hurry = new ArrayList<Integer>();
		for (int i = 0; i < vo.size(); i++) {
			ArrayList<Object> line = new ArrayList<Object>();
			ReceiptVO v = vo.get(i);
			line.add(v.getId());
			line.add(v.getDate());
			line.add(Total.getType(v.getType()));
			if (v.getHurry() == 0)
				hurry.add(i);
			// 销售/进货
			if (v.getType() == ReceiptType.PAYMENT) {
				PaymentVO pv = (PaymentVO) v;
				line.add(pv.getMemberName());

			} else if (v.getType() == ReceiptType.COLLECTION) {
				CollectionVO cv = (CollectionVO) v;
				line.add(cv.getMemberName());
			} else
				line.add(v.getMemberName());

			if (v.getType() == ReceiptType.STOCKOVER
					|| v.getType() == ReceiptType.STOCKLOW)
				line.add("");
			else
				line.add(Total.getTotal(v));
			line.add(user.getName(v.getUser()));
			line.add(v.getInfo());
			if (t == 0)
				line.add(new Boolean(false));
			tableContent.add(line);

		}
		if(t==0){
			DefaultTableCellRenderer tcr = new MyTableCellRenderer(hurry);
			for (int i = 0; i < t1.getColumnCount() - 1; i++) {
				t1.getColumn(t1.getColumnName(i)).setCellRenderer(tcr);

			}
		}
		
		if(t==0){
			t1.revalidate();
		}else
			t2.revalidate();
		ReceiptMgrPanel.this.repaint();

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == modBtn) {
			if (t1.getSelectedRow() < 0)
				JOptionPane.showMessageDialog(null, "请选择一条待审批单据进行高级审批！", "提示",
						JOptionPane.WARNING_MESSAGE);
			else {
				int i = t1.getSelectedRow();
				String id = c1.get(i).get(0).toString();
				String type = c1.get(i).get(2).toString();
				ReceiptType rtype = Total.getsType(type);
				JPanel pane = getRightAdvancePanel(id, rtype);
				father.setRightComponent(new AdvancedReceiptPanel(pane, father,
						id, rtype));

			}
		} else if (e.getSource() == refreshBtn) {
			Refresh();

		} else if (e.getSource() == findBtn) {
			ArrayList<ReceiptVO> receiptApprove;
			String find = findbox.getSelectedItem().toString();
			// {"全部","销售单","销售退货单","进货单","进货退货单","收款单",
			// "付款单","现金费用单","库存报损单","库存报溢单","库存赠送单"}
			ReceiptType type = null;
			int i = tab.getSelectedIndex();
			if (find.equals("全部")) {
				if (i == 0)
					receiptApprove = service.ToApprove();
				else
					receiptApprove = service.Approved();
			} else {
				type = Total.getsType(find);
				if (i == 0)
					receiptApprove = service.ToApprove(type);
				else
					receiptApprove = service.Approved(type);
			}
			if (receiptApprove == null) {
				if (i == 0)
					c1.clear();
				else
					c2.clear();
				ReceiptMgrPanel.this.repaint();
				JOptionPane.showMessageDialog(null, "没有符合条件的单据！");
			} else {
				try {
					RefreshTable(receiptApprove, i);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	// 获取收付款位置 0收款单 1付款单 2现金费用单
	public static int findChosen(String id, ArrayList<ArrayList<Object>> table,
			int sign) {
		String type = "现金费用单";
		if (sign == 1)
			type = "收款单";
		else if (sign == 2)
			type = "付款单";

		int count = -1;
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i).get(2).equals(type)) {
				count++;
				if (table.get(i).get(0).equals(id))
					break;
			}
		}
		return count;

	}

	public ArrayList<String> BatchChoose() {
		ArrayList<String> choose = new ArrayList<String>();
		for (int i = 0; i < c1.size(); i++) {
			if ((boolean) t1.getValueAt(i, 7))
				choose.add((String) c1.get(i).get(0));
		}
		if (choose.size() == 0)
			return null;
		else
			return choose;
	}

	public static JPanel getRightAdvancePanel(String id, ReceiptType rtype) {
		try {
			switch (rtype) {
			case PURCHASE:
				PurchaseDetailPanel pane = new PurchaseDetailPanel(id, father);
				pane.useToReceipt();
				return pane;
			case PAYMENT:
				PaymentDetailPanel paypane = new PaymentDetailPanel(father,
						findChosen(id, c1, 2));
				paypane.useToReceipt();
				return paypane;
			case COLLECTION:
				CollectionDetailPanel colpane = new CollectionDetailPanel(
						father, findChosen(id, c1, 1));
				colpane.useToReceipt();
				return colpane;
			case CASHLIST:
				CashDetailPanel cpane = new CashDetailPanel(father, findChosen(
						id, c1, 0));
				cpane.useToReceipt();
				return cpane;
			case GIFT:
				GiftDetailPanel gift = new GiftDetailPanel(id, father);
				gift.useToReceipt();
				return gift;
				/*
				 * case PURCHASERETURN: PurchaseReturnDetailPanel prpane=new
				 * PurchaseReturnDetailPanel(father,findChosen( id,c1,4));
				 * prpane.useToReceipt(); father.setRightComponent(new
				 * AdvancedReceiptPanel( prpane, father, id));
				 */
			case SALE:
				SaleDetailPanel sale = new SaleDetailPanel(id, father);
				sale.useToReceipt();
				return sale;
			case PURCHASERETURN:
				PurchaseReturnDetailPanel purchaseR = new PurchaseReturnDetailPanel(
						id, father);
				purchaseR.useToReceipt();
				return purchaseR;
			case SALERETURN:
				SaleReturnDetailPanel saleR = new SaleReturnDetailPanel(id,
						father);
				saleR.useToReceipt();
				return saleR;
			case STOCKOVER:
				OverflowDetailPanel overflowDetailPanel = new OverflowDetailPanel(
						id, father);
				overflowDetailPanel.useToReceipt();
				return overflowDetailPanel;
			case STOCKLOW:
				LossDetailPanel lossDetailPanel = new LossDetailPanel(id,
						father);
				lossDetailPanel.useToReceipt();
				return lossDetailPanel;
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}
}
