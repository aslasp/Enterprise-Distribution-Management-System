package Presentation.financeui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import vo.AccountVO;
import Presentation.financeui.account.AddAccountPanel;
import Presentation.financeui.account.DelAccountDialog;
import Presentation.financeui.account.ModAccountPanel;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import businesslogic.financebl.Account;
import businesslogicservice.financeblservice.accountblservice.FinanceAccountBLService;

public class AccountPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyButton addBtn, delBtn, modBtn, refreshBtn, searchBtn;
	JTextField searchFld;
	JTable table;
	AccountTableModel atm;
	ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
	JScrollPane jsp;
	String keyWord;
	MainFrame parent;

	public AccountPanel(MainFrame frame) {
		parent = frame;
		this.setBackground(Color.white);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints wn = new GridBagConstraints();
		wn.insets = new Insets(3, 50, 3, 50);
		wn.fill = GridBagConstraints.BOTH;
		// -----------------------------
		JPanel btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		wn.gridx = 0;
		wn.gridy = 0;
		wn.gridheight = 2;
		wn.gridwidth = GridBagConstraints.REMAINDER;
		wn.weightx = 1;
		wn.weighty = 0.1;
		gbl.setConstraints(btnPnl, wn);
		this.add(btnPnl);
		// ------增-----------------------
		addBtn = new MyButton("添加账户", new ImageIcon("img/finance/add.png"));
		addBtn.addActionListener(this);
		btnPnl.add(addBtn);
		// -----删------------------------
		delBtn = new MyButton("删除账户", new ImageIcon("img/finance/delete.png"));
		delBtn.addActionListener(this);
		btnPnl.add(delBtn);
		// -------改----------------------
		modBtn = new MyButton("修改账户属性", new ImageIcon("img/finance/modify.png"));
		modBtn.addActionListener(this);
		btnPnl.add(modBtn);
		// -------刷新---------------------
		refreshBtn = new MyButton("刷新",
				new ImageIcon("img/finance/refresh.png"));
		refreshBtn.addActionListener(this);
		btnPnl.add(refreshBtn);
		// ---------搜索--------------------
		searchFld = new JTextField(15);
		searchFld.setFont(new Font("楷体", Font.BOLD, 13));
		// 没写监听
		btnPnl.add(searchFld);
		// --------查找按钮-------------------
		searchBtn = new MyButton(new ImageIcon("img/finance/find.png"));
		searchBtn.addActionListener(this);
		btnPnl.add(searchBtn);
		// --------表格----------------------

		atm = new AccountTableModel();
		table = new JTable(atm);
		table.getTableHeader().setReorderingAllowed(false);

		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		wn.gridx = 0;
		wn.gridy = 2;
		wn.gridheight = 6;
		wn.gridwidth = GridBagConstraints.REMAINDER;
		wn.weightx = 1;
		wn.weighty = 1;
		gbl.setConstraints(jsp, wn);
		this.add(jsp);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			try {
				parent.setRightComponent(new AddAccountPanel(parent));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == delBtn) {
			int[] i = table.getSelectedRows();
			ArrayList<AccountVO> name = new ArrayList<AccountVO>();
			if (i.length > 0) {
				for (int j = 0; j < i.length; j++) {
					AccountVO n = new AccountVO((String) table.getValueAt(i[j],
							0), Double.parseDouble((String) table.getValueAt(
							i[j], 1)));
					name.add(n);
				}
				JDialog delDlg = new DelAccountDialog(name, parent);
			} else {
				JOptionPane.showMessageDialog(null, "请选择账户", "提示",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getSource() == modBtn) {
			int i = table.getSelectedRow();
			if (i >= 0) {
				String name = (String) table.getValueAt(i, 0);

				try {

					parent.setRightComponent(new ModAccountPanel(name, parent));

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "请选择账户", "提示",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getSource() == refreshBtn) {
			try {
				FinanceAccountBLService service = new Account();
				AccountPanel mgr = new AccountPanel(parent);
				parent.setRightComponent(mgr);
				if (service.showAll() != null)
					mgr.RefreshAccountTable(service.showAll());
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == searchBtn) {
			try {
				String s = searchFld.getText();
				if (s.equals(""))
					JOptionPane.showMessageDialog(null, "请输入查找名称", "提示",
							JOptionPane.WARNING_MESSAGE);
				else {
					FinanceAccountBLService service = new Account();
					AccountPanel mgr = new AccountPanel(parent);
					parent.setRightComponent(mgr);
					ArrayList<AccountVO> vv = service.findAccount(s);
					ArrayList<AccountVO> al = new ArrayList<AccountVO>();
					if (vv != null)
						for (AccountVO vo : vv)
							al.add(vo);
					mgr.RefreshAccountTable(al);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class AccountTableModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "账户名称", "余额" };

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

	public void RefreshAccountTable(ArrayList<AccountVO> vo) {
		for (AccountVO VO : vo) {
			ArrayList<String> lineInfo = new ArrayList<String>();
			lineInfo.add(VO.getName());
			lineInfo.add(String.valueOf(VO.getMoney()));
			c.add(lineInfo);
		}
	}
}
