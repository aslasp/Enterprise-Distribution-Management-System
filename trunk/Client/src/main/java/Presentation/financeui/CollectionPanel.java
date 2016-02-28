package Presentation.financeui;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import vo.CashlistVO;
import vo.CollectionVO;
import vo.PaymentVO;
import Presentation.financeui.moneyreceipt.AddCashReceiptPanel;
import Presentation.financeui.moneyreceipt.AddCollectionPanel;
import Presentation.financeui.moneyreceipt.AddPaymentPanel;
import Presentation.financeui.moneyreceipt.CashDetailPanel;
import Presentation.financeui.moneyreceipt.CollectionDetailPanel;
import Presentation.financeui.moneyreceipt.PaymentDetailPanel;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import businesslogic.financebl.CashList;
import businesslogic.financebl.Collection;
import businesslogic.financebl.Payment;
import businesslogic.userbl.User;
import businesslogic.utilitybl.getStatus;
import businesslogicservice.financeblservice.listblservice.CashlistBLService;
import businesslogicservice.financeblservice.listblservice.CollectionBLService;
import businesslogicservice.financeblservice.listblservice.PaymentBLService;
import businesslogicservice.userblservice.UserBLService;

public class CollectionPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTabbedPane tab;
	JTable t1, t2, t3;
	CollectionModel cm1, cm2;
	CashModel cashm;
	JScrollPane jsp1, jsp2, jsp3;
	ArrayList<ArrayList<String>> c1 = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> c2 = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> c3 = new ArrayList<ArrayList<String>>();
	// --------------------------
	MyButton collectionBtn, payBtn, cashBtn, refreshBtn, detailBtn;
	MainFrame parent;

	public CollectionPanel(MainFrame frame) {
		parent = frame;
		this.setBackground(Color.white);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 40, 3, 40);
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
		// ------制定收款单-----------------
		collectionBtn = new MyButton("制定收款单", new ImageIcon(
				"img/finance/receipts.png"));
		collectionBtn.addActionListener(this);
		collectionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPnl.add(collectionBtn);
		// -----制定付款单--------------------
		payBtn = new MyButton("制定付款单",
				new ImageIcon("img/finance/receipts.png"));
		payBtn.addActionListener(this);
		payBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPnl.add(payBtn);
		// ------制定现金费用单-----------------
		cashBtn = new MyButton("制定现金费用单", new ImageIcon(
				"img/finance/receipts.png"));
		cashBtn.addActionListener(this);
		cashBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPnl.add(cashBtn);
		// -------刷新------------------------
		refreshBtn = new MyButton("刷新",
				new ImageIcon("img/finance/refresh.png"));
		refreshBtn.addActionListener(this);
		refreshBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPnl.add(refreshBtn);
		// ------查看详情----------------------
		detailBtn = new MyButton("查看详情", new ImageIcon(
				"img/finance/details.png"));
		detailBtn.addActionListener(this);
		detailBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPnl.add(detailBtn);
		// --------tab-----------------
		tab = new JTabbedPane();
		tab.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tab.setBackground(Color.white);
		tab.setForeground(new Color(242, 125, 5));
		c.insets = new Insets(3, 40, 20, 40);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(tab, c);
		this.add(tab);
		// ------收款单-----------------
		cm1 = new CollectionModel(c1);
		t1 = new JTable(cm1);
		t1.getColumnModel().getColumn(0).setPreferredWidth(100);
		t1.getTableHeader().setReorderingAllowed(false);

		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < t1.getColumnCount(); i++) {
			t1.getColumn(t1.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp1 = new JScrollPane(t1);
		tab.add("收款单", jsp1);
		// ------付款单-----------------
		cm2 = new CollectionModel(c2);
		t2 = new JTable(cm2);
		t2.getColumnModel().getColumn(0).setPreferredWidth(100);
		t2.getTableHeader().setReorderingAllowed(false);

		// table 渲染器，设置文字内容居中显示，设置背景色等
		for (int i = 0; i < t2.getColumnCount(); i++) {
			t2.getColumn(t2.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp2 = new JScrollPane(t2);
		tab.add("付款单", jsp2);
		// -------现金费用单-------------
		cashm = new CashModel();
		t3 = new JTable(cashm);
		t3.getColumnModel().getColumn(0).setPreferredWidth(100);
		t3.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		for (int i = 0; i < t3.getColumnCount(); i++) {
			t3.getColumn(t3.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp3 = new JScrollPane(t3);
		tab.add("现金费用单", jsp3);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == collectionBtn) {
			parent.setRightComponent(new AddCollectionPanel(parent));
		} else if (e.getSource() == payBtn) {
			parent.setRightComponent(new AddPaymentPanel(parent));
		} else if (e.getSource() == cashBtn) {
			parent.setRightComponent(new AddCashReceiptPanel(parent));
		} else if (e.getSource() == refreshBtn) {
			CollectionPanel mgr = new CollectionPanel(parent);
			parent.setRightComponent(mgr);

			try {
				PaymentBLService pp = new Payment();
				CollectionBLService bb = new Collection();
				CashlistBLService cc = new CashList();
				if (pp.getPayment() != null)
					mgr.RefreshPaymentTable(pp.getPayment());
				if (bb.getCollection() != null)
					mgr.RefreshCollectionTable(bb.getCollection());
				if (cc.getCashlist() != null)
					mgr.RefreshCashlistTable(cc.getCashlist());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			mgr.setSelectedTab(0);
		} else if (e.getSource() == detailBtn) {
			if (tab.getSelectedIndex() == 0) {
				int selected = t1.getSelectedRow();
				if (selected < 0) {
					JOptionPane.showMessageDialog(null, "请选择需要查看的单据", "提示",
							JOptionPane.CLOSED_OPTION);
				} else {
					parent.setRightComponent(new CollectionDetailPanel(parent,
							selected));
				}
			} else if (tab.getSelectedIndex() == 1) {
				int selected = t2.getSelectedRow();
				if (selected < 0) {
					JOptionPane.showMessageDialog(null, "请选择需要查看的单据", "提示",
							JOptionPane.CLOSED_OPTION);
				} else {
					parent.setRightComponent(new PaymentDetailPanel(parent,
							selected));
				}
			} else if (tab.getSelectedIndex() == 2) {
				int selected = t3.getSelectedRow();
				if (selected < 0) {
					JOptionPane.showMessageDialog(null, "请选择需要查看的单据", "提示",
							JOptionPane.CLOSED_OPTION);
				} else {
					parent.setRightComponent(new CashDetailPanel(parent,
							selected));
				}
			}
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
			this.setForeground(new Color(242, 125, 5));
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

	class CashModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "编号", "状态", "日期", "操作员", "银行账户", "总额" };

		public int getRowCount() {
			return c3.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public String getValueAt(int row, int col) {
			return c3.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {

			c3.add(v);
		}

		public void removeRow(int row) {
			c3.remove(row);
		}

	}

	class CollectionModel extends AbstractTableModel {

		/**
		 * 
		 */
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		private static final long serialVersionUID = 1L;
		String head[] = { "编号", "状态", "日期", "客户", "操作员", "总额汇总" };

		public CollectionModel(ArrayList<ArrayList<String>> content) {
			c = content;
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

	public void RefreshCollectionTable(ArrayList<CollectionVO> vo)
			throws Exception {
		for (CollectionVO VO : vo) {
			ArrayList<String> lineInfo = new ArrayList<String>();
			lineInfo.add(VO.getId());
			lineInfo.add(getStatus.getstatus(VO.getStatus()));
			lineInfo.add(VO.getDate());
			lineInfo.add(VO.getMemberName());
			UserBLService user = new User();
			lineInfo.add(user.showUser(VO.getUser()).getName());
			lineInfo.add(String.valueOf(VO.getTotalMoney()));
			c1.add(lineInfo);
		}
	}

	public void RefreshPaymentTable(ArrayList<PaymentVO> vo) throws Exception {
		for (PaymentVO VO : vo) {
			ArrayList<String> lineInfo = new ArrayList<String>();
			lineInfo.add(VO.getId());
			new getStatus();
			lineInfo.add(getStatus.getstatus(VO.getStatus()));
			lineInfo.add(VO.getDate());
			lineInfo.add(VO.getMemberName());
			UserBLService user = new User();
			lineInfo.add(user.showUser(VO.getUser()).getName());
			lineInfo.add(String.valueOf(VO.getTotalMoney()));
			c2.add(lineInfo);
		}
	}

	public void RefreshCashlistTable(ArrayList<CashlistVO> vo) throws Exception {
		for (CashlistVO VO : vo) {
			ArrayList<String> lineInfo = new ArrayList<String>();
			lineInfo.add(VO.getId());
			lineInfo.add(getStatus.getstatus(VO.getStatus()));
			lineInfo.add(VO.getDate());
			UserBLService user = new User();
			lineInfo.add(user.showUser(VO.getUser()).getName());
			lineInfo.add(VO.getAccount());
			lineInfo.add(String.valueOf(VO.getTotalMoney()));
			c3.add(lineInfo);
		}
	}

	public void setSelectedTab(int i) {
		tab.setSelectedIndex(i);
	}
}
