package Presentation.financeui.initial;

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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import vo.AccountVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import businesslogic.financebl.Account;
import businesslogicservice.financeblservice.accountblservice.FinanceAccountBLService;

public class AccountInitialPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	AccountModel am;
	ArrayList<ArrayList<String>> accountC = new ArrayList<ArrayList<String>>();
	JTable accountTable;
	JScrollPane jsp;
	JButton addBtn, delBtn;
	JComboBox<String> accountBox;
	MainFrame parent;
	AddInitialPanel subparent;
	ArrayList<Integer> haveSelected=new ArrayList<Integer>();
	ArrayList<AccountVO> account=new ArrayList<AccountVO>();
	JPanel btnPnl;
	public AccountInitialPanel(MainFrame frame) {
		parent = frame;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		c.fill = GridBagConstraints.BOTH;
		// --------表格-------------
		am = new AccountModel();
		accountTable = new JTable(am);
		accountTable.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < accountTable.getColumnCount(); i++) {
			accountTable.getColumn(accountTable.getColumnName(i))
					.setCellRenderer(tcr);
		}
		jsp = new JScrollPane(accountTable);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(jsp, c);
		this.add(jsp);
		// ------账户增删处-----------
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
		// ---------------------------
		ArrayList<String> st = new ArrayList<String>();
		FinanceAccountBLService fin = null;
		try {
			fin = new Account();

		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ArrayList<AccountVO> account = fin.showAll();
		if (account == null) {
			String accountText[] = { "当前无账户可选" };
			accountBox = new JComboBox<String>(accountText);
			accountBox.setEditable(false);
		} else {
			for (AccountVO vo : account) {
				st.add(vo.getName());
			}
			String accountText[] = new String[st.size()+1];
			accountText[0]="请选择账户";
			for (int i = 0; i < st.size(); i++) {
				accountText[i+1] = st.get(i);
			}
			accountBox = new JComboBox<String>(accountText);
		}
		// ---------------------------
		accountBox.setBackground(Color.white);
		accountBox.setFont(font);
		btnPnl.add(accountBox);
		//
		//
		addBtn = new JButton("添加账户");
		addBtn.setFont(font);
		addBtn.setBackground(new Color(204, 242, 239));
		addBtn.setFocusPainted(false);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accountBox.getSelectedIndex()==0)
				    parent.setRightComponent(new addAccountInitial(parent,AccountInitialPanel.this));
				else{
					int index=accountBox.getSelectedIndex();
					boolean isIn=false;
					for(Integer inte:haveSelected){
						if(index==inte){
							isIn=true;
						}
					}
					if(isIn==true){
						 JOptionPane.showMessageDialog(null,"您已选择了该账户!","提示",JOptionPane.WARNING_MESSAGE);
					}
					else{
					haveSelected.add(index);
					FinanceAccountBLService ain = null;
					try {
						ain = new Account();

					} catch (MalformedURLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (RemoteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (NotBoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					ArrayList<AccountVO> account = ain.showAll();
					AccountVO vv=account.get(index-1);
					RefreshTable(vv);
					accountTable.revalidate();
				}
				}
			}
		});
		btnPnl.add(addBtn);
		delBtn = new JButton("删除选中");
		delBtn.setFont(font);
		delBtn.setBackground(new Color(204, 242, 239));
		delBtn.setFocusPainted(false);
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int allselected[]=accountTable.getSelectedRows();
				if(allselected.length<=0)
					JOptionPane.showMessageDialog(null,"请选择账户!","提示",JOptionPane.WARNING_MESSAGE);
				else{
					for(int j=allselected.length-1;j>=0;j--){
					int selected=allselected[j];
					haveSelected.remove(selected);
					am.removeRow(selected);
					accountTable.revalidate();					
				}
				}
			}
		});
		btnPnl.add(delBtn);
	}

	class AccountModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "账户名", "余额" };

		public int getRowCount() {
			return accountC.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public String getValueAt(int row, int col) {
			return accountC.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {
			accountC.add(v);
		}

		public void removeRow(int row) {
			accountC.remove(row);
			account.remove(row);
		}

	}
	
	public void RefreshTable(AccountVO vo){
		ArrayList<String> line=new ArrayList<String>();
		line.add(vo.getName());
		line.add(String.valueOf(vo.getMoney()));
		accountC.add(line);	
	    account.add(vo);
	}
	
	public ArrayList<AccountVO> getAccount(){
		if(account.size()==0)
			return null;
		return account;
	}
	
	public void setParent(AddInitialPanel pane){
		this.subparent=pane;
	}
}
