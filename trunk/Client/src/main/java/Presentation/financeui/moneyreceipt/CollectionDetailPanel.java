package Presentation.financeui.moneyreceipt;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import vo.CollectionVO;
import vo.TransferItemVO;
import Presentation.financeui.CollectionPanel;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import businesslogic.financebl.CashList;
import businesslogic.financebl.Collection;
import businesslogic.financebl.Payment;
import businesslogic.userbl.User;
import businesslogicservice.financeblservice.listblservice.CashlistBLService;
import businesslogicservice.financeblservice.listblservice.CollectionBLService;
import businesslogicservice.financeblservice.listblservice.PaymentBLService;
import businesslogicservice.userblservice.UserBLService;

public class CollectionDetailPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
	JScrollPane jsp;
	JTable table;
	TransferListModel tlm;
	JLabel IDLbl, userLbl, memLbl, totalLbl, accountLbl,
			moneyLbl, remarkLbl, hurryLbl;
	JButton submitBtn;
	MainFrame parent;
	String hurry;
	String ID;
	String user;
	String member;
	double totalMoney;
	CollectionBLService cc = null;
	CollectionVO vo;
	JPanel btnPnl;

	public CollectionDetailPanel(MainFrame frame, int selected) {
		parent = frame;

		try {
			cc = new Collection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<CollectionVO> vv = cc.getCollection();
		vo = vv.get(selected);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		JLabel title = new JLabel("收款单");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.08;
		gbl.setConstraints(titlePnl, c);
		this.add(titlePnl);
		// ------table---------------------
		c.fill = GridBagConstraints.BOTH;
		tlm = new TransferListModel();
		table = new JTable(tlm);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);

		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 4;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.6;
		gbl.setConstraints(jsp, c);
		this.add(jsp);

		// ------infoPnl--------------------
		JPanel infoPnl = new JPanel();
		infoPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.2;
		gbl.setConstraints(infoPnl, c);
		this.add(infoPnl);
		infoPnl.setLayout(new GridLayout(1, 1));
		JPanel up = new JPanel();
		up.setBackground(Color.white);
		infoPnl.add(up);

		// ------加急---------------
		if (vo.getHurry() == 0)
			hurry = "否";
		else
			hurry = "是";
		hurryLbl = new JLabel("加急: " + hurry);
		hurryLbl.setFont(font);
		up.add(hurryLbl);
		up.add(new JLabel("     "));
		// ------ID----------------
		ID = vo.getId();
		IDLbl = new JLabel("ID: " + ID);
		IDLbl.setFont(font);
		up.add(IDLbl);
		up.add(new JLabel("     "));
		// ------user---------------
		UserBLService uu = null;
		try {
			uu = new User();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user = uu.showUser(vo.getUser()).getName();
		userLbl = new JLabel("操作员: " + user);
		userLbl.setFont(font);
		up.add(userLbl);
		up.add(new JLabel("     "));
		// ------supplier-----seller---------
		member = vo.getMemberName();
		JLabel memLbl = new JLabel("客户: " + member);
		memLbl.setFont(font);
		up.add(memLbl);
		up.add(new JLabel("     "));
		// -----总额-------------------
		totalMoney = vo.getTotalMoney();
		totalLbl = new JLabel("总额: " + totalMoney);
		totalLbl.setFont(font);
		up.add(totalLbl);
		// ------table-----------------
		RefreshTransferlistTable(vo.getTransferlist());
		// -------buttons-----------------
		btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 8;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.addActionListener(this);
		btnPnl.add(submitBtn);
	}

	class TransferListModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "银行账户", "转账金额", "备注" };

		public int getRowCount() {
			return content.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public String getValueAt(int row, int col) {
			return content.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {

			content.add(v);
		}

		public void removeRow(int row) {
			content.remove(row);
		}

	}

	// public static void main(String[] args) {
	// JFrame testFrame = new JFrame();
	// testFrame.setBounds(100, 50, 920, 600);
	// testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//
	// CashDetailPanel gp = new CashDetailPanel();
	// gp.setBounds(0, 0, 920, 600);
	// testFrame.add(gp);
	// testFrame.setVisible(true);
	// }

	public void RefreshTransferlistTable(ArrayList<TransferItemVO> vo) {
		for (TransferItemVO VO : vo) {
			ArrayList<String> lineInfo = new ArrayList<String>();
			lineInfo.add(VO.getAccount());
			lineInfo.add(String.valueOf(VO.getMoney()));
			lineInfo.add(VO.getInfo());
			content.add(lineInfo);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == submitBtn) {
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

				mgr.setSelectedTab(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void useToReceipt() {
		btnPnl.remove(submitBtn);
	}

}