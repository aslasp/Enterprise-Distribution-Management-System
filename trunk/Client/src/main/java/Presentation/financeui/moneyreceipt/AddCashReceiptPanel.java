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

import vo.AccountVO;
import vo.CashlistVO;
import vo.ClauseItemVO;
import vo.LogVO;
import Presentation.financeui.CollectionPanel;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import businesslogic.financebl.Account;
import businesslogic.financebl.CashList;
import businesslogic.financebl.Collection;
import businesslogic.financebl.Payment;
import businesslogicservice.financeblservice.accountblservice.FinanceAccountBLService;
import businesslogicservice.financeblservice.listblservice.CashlistBLService;
import businesslogicservice.financeblservice.listblservice.CollectionBLService;
import businesslogicservice.financeblservice.listblservice.PaymentBLService;

public class AddCashReceiptPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
	JScrollPane jsp;
	JTable table;
	CashReceiptModel crm;
	JComboBox<String> accountBox;
	JLabel IDLbl, userLbl, totalLbl;
	JButton submitBtn, exitBtn, addBtn, delBtn;
	JTextField nameFld, moneyFld, remarkFld;
	MainFrame parent;
	JCheckBox hurryBox;
	CashlistBLService service;
	String ID;
	String user;
	double totalMoney;
	ArrayList<ClauseItemVO> tra=new ArrayList<ClauseItemVO>();
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	JPanel btnPnl;
	JPanel up;
	JPanel titlePnl;
	JLabel title;
	
	public AddCashReceiptPanel(MainFrame frame) {
		parent=frame;
		
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
	    title = new JLabel("创建现金费用单");
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
		crm = new CashReceiptModel();
		table = new JTable(crm);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
				DefaultTableCellRenderer tcr = new MyTableCellRenderer();
				for (int i = 0; i < table.getColumnCount(); i++) {
					table.getColumn(table.getColumnName(i)).setCellRenderer(
							tcr);
				}
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
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
		infoPnl.setLayout(new GridLayout(2, 1));
		up = new JPanel();
		up.setBackground(Color.white);
		infoPnl.add(up);
		JPanel down = new JPanel();
		down.setBackground(Color.white);
		infoPnl.add(down);
		//------加急---------------
		hurryBox=new JCheckBox("加急");
		hurryBox.setFont(font);
		hurryBox.setFocusPainted(false);
		hurryBox.setBackground(Color.white);
		up.add(hurryBox);
		up.add(new JLabel("     "));
		// ------ID----------------
		try {
			service=new CashList();
			ID=service.getNewID();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IDLbl = new JLabel("ID: "+ID);
		IDLbl.setFont(font);
		up.add(IDLbl);
		up.add(new JLabel("     "));
		// ------user---------------
		user=parent.getUser().getName();
		userLbl = new JLabel("操作员: "+user);
		userLbl.setFont(font);
		up.add(userLbl);
		up.add(new JLabel("     "));
		// ------account---------
		JLabel accLbl = new JLabel("账户:");
		accLbl.setFont(font);
		up.add(accLbl);
		//---------------------------
		ArrayList<String> st=new ArrayList<String>();
		FinanceAccountBLService fin=null;
		try {
			fin=new Account();
			
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
		ArrayList<AccountVO> account=fin.showAll();
		if(account==null){
			String accountText[] = { "当前无账户可选" };
			accountBox = new JComboBox<String>(accountText);
			accountBox.setEditable(false);
		}
		else{
			for(AccountVO vo:account){
			    st.add(vo.getName());
		    }
		    String accountText[] =new String[st.size()]; 
		    for(int i=0;i<st.size();i++){
		    	accountText[i]=st.get(i);
		    }
		    accountBox = new JComboBox<String>(accountText);
		}
		//---------------------------
		accountBox.setFont(font);
		accountBox.setBackground(Color.white);
		up.add(accountBox);
		up.add(new JLabel("     "));
		// -----总额-------------------
		totalLbl = new JLabel("总额: "+totalMoney);
		totalLbl.setFont(font);
		up.add(totalLbl);
		//-------条目清单---------------
		JLabel itemLbl=new JLabel("条目项：");
		itemLbl.setFont(font);
		down.add(itemLbl);
		down.add(new JLabel("      "));
		//-------条目名--------------
		JLabel nameLbl=new JLabel("条目名：");
		nameLbl.setFont(font);
		down.add(nameLbl);
		nameFld=new JTextField(5);
		nameFld.setFont(font);
		down.add(nameFld);
		down.add(new JLabel("      "));
		//-------金额-----------------
		JLabel moneyLbl=new JLabel("金额：");
		moneyLbl.setFont(font);
		down.add(moneyLbl);
		moneyFld=new JTextField(3);
		moneyFld.setFont(font);
		down.add(moneyFld);
		down.add(new JLabel("      "));
		//------备注------------------
		JLabel remarkLbl=new JLabel("备注：");
		remarkLbl.setFont(font);
		down.add(remarkLbl);
		remarkFld=new JTextField(15);
		remarkFld.setFont(font);
		down.add(remarkFld);
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
		//
		addBtn = new JButton("添加条目");
		addBtn.setFont(font);
		addBtn.setBackground(Color.white);
		addBtn.setFocusPainted(false);
		addBtn.addActionListener(this);
		btnPnl.add(addBtn);
		delBtn = new JButton("删除选中");
		delBtn.setFont(font);
		delBtn.setBackground(Color.white);
		delBtn.setFocusPainted(false);
		delBtn.addActionListener(this);
		btnPnl.add(delBtn);
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.addActionListener(this);
		btnPnl.add(submitBtn);
		exitBtn = new JButton("取消");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitBtn.addActionListener(this);
		btnPnl.add(exitBtn);
	}

	class CashReceiptModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "条目名", "金额", "备注" };

		public int getRowCount() {
			return content.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public String getValueAt(int row, int col) {
			return content.get(row).get(col);
		}
		
		public void setValueAt(int row,int col,String s){
			content.get(row).set(col, s);
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
	
	public void Update() {
		CollectionPanel mgr = new CollectionPanel(parent);
		parent.setRightComponent(mgr);
		try {
			service=new CashList();
			CollectionBLService bb=new Collection();
			PaymentBLService pp=new Payment();
			if (bb.getCollection()!=null)
				mgr.RefreshCollectionTable(bb.getCollection());
			if(service.getCashlist()!= null)
				mgr.RefreshCashlistTable(service.getCashlist());
			if(pp.getPayment()!=null)
			    mgr.RefreshPaymentTable(pp.getPayment());
				
			mgr.setSelectedTab(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			if (nameFld.getText().equals("")||moneyFld.getText().equals("")||remarkFld.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入信息", "提示",
						JOptionPane.WARNING_MESSAGE);
			else {
				ClauseItemVO item;
			try{
				double mmm=Double.parseDouble(moneyFld.getText());
				if(mmm<0){
					JOptionPane.showMessageDialog(null, "请输入正确的金额！", "提示",
							JOptionPane.WARNING_MESSAGE);
					moneyFld.setText("");
				}
				else{
				item= new ClauseItemVO(nameFld.getText(), Double.parseDouble(moneyFld.getText()),remarkFld.getText());
				tra.add(item);
				ArrayList<String> buffer = new ArrayList<String>();
				buffer.add(nameFld.getText());
				buffer.add(moneyFld.getText());
				buffer.add(remarkFld.getText());
				crm.addRow(buffer);
				table.revalidate();
				totalMoney += Double.parseDouble(moneyFld.getText());
				totalLbl.setText("总额:" + totalMoney);
				nameFld.setText("");
				moneyFld.setText("");
				remarkFld.setText("");
				}
			}catch(NumberFormatException e11){
				JOptionPane.showMessageDialog(null, "转账金额输入有误", "提示",JOptionPane.WARNING_MESSAGE);
				moneyFld.setText("");
			}
		
	       }
		}
		else if (e.getSource() == delBtn) {
			
			int seleted = table.getSelectedRow();
			if (seleted < 0) {
				JOptionPane.showMessageDialog(null, "请选择一行", "提示",
						JOptionPane.WARNING_MESSAGE);
			} else {
				ClauseItemVO item = new ClauseItemVO(crm.getValueAt(seleted, 0), Double.parseDouble(crm.getValueAt(seleted, 1)), crm.getValueAt(seleted, 2));
				
				for(int i=0;i<tra.size();i++){
				   ClauseItemVO gai=tra.get(i);
				   if(gai.getInfo().equals(item.getInfo())&&gai.getMoney()==item.getMoney()&&gai.getName().equals(item.getName())){
				       tra.remove(i);
				       break;
				   }
				}
				totalMoney -= Double.parseDouble(crm.getValueAt(seleted, 1));
				crm.removeRow(seleted);
				table.revalidate();
				totalLbl.setText("总额:" + totalMoney);
				table.clearSelection();
			}
		}
		else if(e.getSource()==submitBtn){
			if(tra.size()==0){
				JOptionPane.showMessageDialog(null, "请输入条目清单", "提示",JOptionPane.WARNING_MESSAGE);
			}
			else{
			int isHurry=1;
			if(hurryBox.isSelected())
				isHurry=0;
			CashlistVO vo=new CashlistVO(ID,parent.getUser().getID(),(String)accountBox.getSelectedItem(),tra,totalMoney,0,isHurry);

			try {
				service = new CashList();
				int result=service.createCashlist(vo);
				if (result == 0) {
					JOptionPane.showMessageDialog(null, "创建现金费用单成功！", "提示",
							JOptionPane.CLOSED_OPTION);
					log.addLog(new LogVO(log.getdate(),parent.getUser().getID(),parent.getUser().getName(),
							"创建一笔现金费用单",4));
					headPane.RefreshGrades();
				}
				else if(result==3){
					JOptionPane.showMessageDialog(null, "账户余额不足哦，不能创建该现金费用单！", "提示",
							JOptionPane.CLOSED_OPTION);
				}
				else {
					JOptionPane.showMessageDialog(null, "创建现金费用单失败！", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
				Update();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
		else if(e.getSource()==exitBtn){
			Update();
			
		}
	}
}
