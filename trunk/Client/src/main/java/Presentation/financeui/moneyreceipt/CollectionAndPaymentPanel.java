package Presentation.financeui.moneyreceipt;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import po.MemberPO.MemberType;
import vo.AccountVO;
import vo.MemberVO;
import vo.TransferItemVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import businesslogic.financebl.Account;
import businesslogic.memberbl.Member;
import businesslogicservice.financeblservice.accountblservice.FinanceAccountBLService;
import businesslogicservice.memberblservice.MemberBLService;

public class CollectionAndPaymentPanel extends JPanel {
	/**
	 * 创建付款单和收款单继承了此类 此类需要加监听
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JLabel IDLbl, userLbl, totalLbl;
	// JTextField accountFld, moneyFld, remarkFld;
	JTextField moneyFld, remarkFld;
	JComboBox<String> memBox, accountBox;
	JScrollPane jsp;
	JTable table;
	TransferListModel tlm;
	ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
    JButton submitBtn, exitBtn, addBtn, delBtn;
	MainFrame parent;
	JCheckBox hurryBox;
	String ID;
	String user;
	ArrayList<TransferItemVO> tra = new ArrayList<TransferItemVO>();
	double totalMoney = 0;
	JPanel item2;
	JPanel memPnl;
	ArrayList<String> st;

	public CollectionAndPaymentPanel(MainFrame frame) {
		parent = frame;
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// ---------------------------------
		c.fill = GridBagConstraints.BOTH;
		JPanel mPnl = new JPanel();
		mPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 6;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(mPnl, c);
		this.add(mPnl);
		GridBagLayout mgbl = new GridBagLayout();
		GridBagConstraints mc = new GridBagConstraints();
		mPnl.setLayout(mgbl);
		mc.fill = GridBagConstraints.BOTH;
		// --------------------------------
		JPanel left = new JPanel();
		left.setBackground(Color.white);
		mc.gridx = 0;
		mc.gridy = 0;
		mc.gridheight = GridBagConstraints.REMAINDER;
		mc.gridwidth = 3;
		mc.weightx = 0.6;
		mc.weighty = 1;
		mgbl.setConstraints(left, mc);
		mPnl.add(left);
		JPanel right = new JPanel();
		right.setBackground(Color.white);
		mc.gridx = 3;
		mc.gridy = 0;
		mc.gridheight = GridBagConstraints.REMAINDER;
		mc.gridwidth = 2;
		mc.weightx = 0.4;
		mc.weighty = 1;
		mgbl.setConstraints(right, mc);
		mPnl.add(right);
		// ----------left-------------------
		GridBagLayout lgbl = new GridBagLayout();
		GridBagConstraints lc = new GridBagConstraints();
		lc.fill = GridBagConstraints.BOTH;
		left.setLayout(lgbl);
		tlm = new TransferListModel(content);
		table = new JTable(tlm);
		table.getTableHeader().setReorderingAllowed(false);

		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		
		jsp = new JScrollPane(table);
		lc.gridx = 0;
		lc.gridy = 0;
		lc.gridheight = 5;
		lc.gridwidth = GridBagConstraints.REMAINDER;
		lc.weightx = 1;
		lc.weighty = 1;
		lgbl.setConstraints(jsp, lc);
		left.add(jsp);
		// --------itemPnl------------------
		JPanel itemPnl = new JPanel();
		itemPnl.setLayout(new GridLayout(2, 1));
		JPanel item1 = new JPanel();
		item1.setBackground(Color.white);
		itemPnl.add(item1);
		item2 = new JPanel();
		item2.setBackground(Color.white);
		itemPnl.add(item2);
		itemPnl.setBackground(Color.white);
		lc.gridx = 0;
		lc.gridy = 5;
		lc.gridheight = 1;
		lc.gridwidth = GridBagConstraints.REMAINDER;
		lc.weightx = 1;
		lc.weighty = 0.1;
		lgbl.setConstraints(itemPnl, lc);
		left.add(itemPnl);
		// ---account------
		JLabel accountLbl = new JLabel("银行账户：");
		accountLbl.setFont(font);
		item1.add(accountLbl);


		st = new ArrayList<String>();
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
			String accountText[] = new String[st.size()];
			for (int i = 0; i < st.size(); i++) {
				accountText[i] = st.get(i);
			}
			accountBox = new JComboBox<String>(accountText);
		}
		accountBox.setFont(font);
		accountBox.setBackground(Color.white);
		item1.add(accountBox);
		// ---money--------
		JLabel moneyLbl = new JLabel("转账金额：");
		moneyLbl.setFont(font);
		item1.add(moneyLbl);
		moneyFld = new JTextField(8);
		moneyFld.setFont(font);
		item1.add(moneyFld);
		// ---remark-------
		JLabel remarkLbl = new JLabel("备注：");
		remarkLbl.setFont(font);
		item2.add(remarkLbl);
		remarkFld = new JTextField(12);
		remarkFld.setFont(font);
		item2.add(remarkFld);
		addBtn = new JButton("加入列表");
		addBtn.setFont(font);
		addBtn.setBackground(new Color(204, 242, 239));
		addBtn.setFocusPainted(false);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addBtn) {
					if (moneyFld.getText().equals("")
							|| remarkFld.getText().equals(""))
						JOptionPane.showMessageDialog(null, "请输入信息", "提示",
								JOptionPane.WARNING_MESSAGE);
					else {
						TransferItemVO item;
						try {
							double mm= Double.parseDouble(moneyFld.getText());
							if(mm<0){
								JOptionPane.showMessageDialog(null, "请输入正确的金额！",
										"提示", JOptionPane.WARNING_MESSAGE);
								moneyFld.setText("");
							}
							else{
							item = new TransferItemVO((String) accountBox
									.getSelectedItem(), Double
									.parseDouble(moneyFld.getText()), remarkFld
									.getText());
							tra.add(item);
							ArrayList<String> buffer = new ArrayList<String>();
							buffer.add((String) accountBox.getSelectedItem());
							buffer.add(moneyFld.getText());
							buffer.add(remarkFld.getText());
							tlm.addRow(buffer);
							table.revalidate();
							accountBox.setSelectedIndex(0);
							totalMoney += Double.parseDouble(moneyFld.getText());
							totalLbl.setText("总额汇总:" + totalMoney);
							moneyFld.setText("");
							remarkFld.setText("");
							}
						} catch (NumberFormatException e11) {
							JOptionPane.showMessageDialog(null, "转账金额输入有误",
									"提示", JOptionPane.WARNING_MESSAGE);
							moneyFld.setText("");
						}

					}
				}
			}
		});
		item2.add(addBtn);
		delBtn = new JButton("从表中删除");
		delBtn.setFont(font);
		delBtn.setBackground(new Color(204, 242, 239));
		delBtn.setFocusPainted(false);
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == delBtn) {
					int seleted = table.getSelectedRow();
					if (seleted < 0) {
						JOptionPane.showMessageDialog(null, "请选择一行", "提示",
								JOptionPane.WARNING_MESSAGE);
					} else {
						TransferItemVO item = new TransferItemVO(tlm
								.getValueAt(seleted, 0), Double.parseDouble(tlm
								.getValueAt(seleted, 1)), tlm.getValueAt(
								seleted, 2));
						for(int i=0;i<tra.size();i++){
							TransferItemVO gai=tra.get(i);
							if(gai.getAccount().equals(item.getAccount())&&gai.getInfo().equals(item.getInfo())&&gai.getMoney()==item.getMoney()){
								tra.remove(i);
								break;
							}
						}

						tra.remove(item);
						totalMoney -= Double.parseDouble(tlm.getValueAt(
								seleted, 1));
						tlm.removeRow(seleted);
						table.revalidate();
						totalLbl.setText("总额汇总:" + totalMoney);
						table.clearSelection();
					}
				}
			}
		});
		item2.add(delBtn);
		// ----------right--------------------
		right.setLayout(new GridLayout(8, 1));
		// -----------ID---------------------
		JPanel IDPnl = new JPanel();
		IDPnl.setBackground(Color.white);
		IDLbl = new JLabel("ID: " + ID);
		IDLbl.setFont(font);
		IDPnl.add(IDLbl);
		right.add(IDPnl);
		// -----------user---------------------
		JPanel userPnl = new JPanel();
		userPnl.setBackground(Color.white);
		user = parent.getUser().getName();
		userLbl = new JLabel("操作员: " + user);
		userLbl.setFont(font);
		userPnl.add(userLbl);
		right.add(userPnl);
		// ---------member-----------------
		memPnl = new JPanel();
		memPnl.setBackground(Color.white);
		right.add(memPnl);
		JLabel memLbl = new JLabel("客户：");
		memLbl.setFont(font);
		memPnl.add(memLbl);
		// _____________
		ArrayList<String> mn = new ArrayList<String>();
		MemberBLService mem = null;
		
			try {
				mem = new Member();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		
		ArrayList<MemberVO> member =new ArrayList<MemberVO>();
		ArrayList<MemberVO> member1 = mem.show(MemberType.JHS);
		ArrayList<MemberVO> member2 = mem.show(MemberType.XSS);
		if(member1!=null)
		for(int i=0;i<member1.size();i++){
			member.add(member1.get(i));
		}
		if(member2!=null)
		for(int i=0;i<member2.size();i++){
			member.add(member2.get(i));
		}
		
		if (member.size()==0) {
			String memText[] = { "当前无客户可选" };
			memBox = new JComboBox<String>(memText);
			memBox.setEditable(false);
		} else {
			for (MemberVO vo : member) {
				mn.add(vo.getName());
			}
			String memText[] = new String[mn.size()];
			for (int i = 0; i < mn.size(); i++) {
				memText[i] = mn.get(i);
			}
			memBox = new JComboBox<String>(memText);
		}
		// _____________

		memBox.setFont(font);
		memBox.setBackground(Color.white);
		memPnl.add(memBox);
		
		// --------总额汇总------------------
		JPanel moneyPnl = new JPanel();
		moneyPnl.setBackground(Color.white);
		totalLbl = new JLabel("总额汇总:" + totalMoney);
		totalLbl.setFont(font);
		moneyPnl.add(totalLbl);
		right.add(moneyPnl);
		// ------加急---------------
		JPanel hurryPnl = new JPanel();
		hurryPnl.setBackground(Color.white);
		right.add(hurryPnl);
		hurryBox = new JCheckBox("加急");
		hurryBox.setFont(font);
		hurryBox.setFocusPainted(false);
		hurryBox.setBackground(Color.white);
		hurryPnl.add(hurryBox);
		// -------buttons-----------------
		JPanel btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 8;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		//
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		btnPnl.add(submitBtn);
		exitBtn = new JButton("取消");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		btnPnl.add(exitBtn);
	}

	class TransferListModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		String head[] = { "银行账户", "转账金额", "备注" };

		public TransferListModel(ArrayList<ArrayList<String>> content) {
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
		
		public void setValueAt(int row,int col,String s){
			c.get(row).set(col, s);
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

}
