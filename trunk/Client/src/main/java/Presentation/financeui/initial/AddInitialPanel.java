package Presentation.financeui.initial;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import businesslogic.financebl.Init;
import businesslogicservice.financeblservice.initblservice.FinanceInitBLService;
import vo.AccountVO;
import vo.BeginInfoVO;
import vo.GoodsVO;
import vo.MemberVO;
import Presentation.financeui.InitialPanel;
import Presentation.mainui.MainFrame;

public class AddInitialPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JTabbedPane tab;
	GoodsInitialPanel goodsInitialPanel;
	AccountInitialPanel accountInitialPanel;
	MemberInitialPanel memberInitialPanel;
	JButton submitBtn, exitBtn;
    MainFrame parent;
    JPanel btnPnl;
    JLabel title;
    GridBagLayout gbl;
    GridBagConstraints c;
	public AddInitialPanel(MainFrame frame) {
		parent=frame;
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		c.insets = new Insets(5, 20, 5, 20);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		title = new JLabel("期初建账");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.08;
		gbl.setConstraints(titlePnl, c);
		this.add(titlePnl);
		// ---------选项卡---------------
		c.fill=GridBagConstraints.BOTH;
		tab = new JTabbedPane();
		tab.setBackground(Color.white);
		tab.setFont(font);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 6;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(tab, c);
		this.add(tab);
		//----------------------------
		goodsInitialPanel=new GoodsInitialPanel(parent);
		tab.add("商品信息初始化",goodsInitialPanel);
		goodsInitialPanel.setParent(this);
		//----------------------------
		memberInitialPanel=new MemberInitialPanel(parent);
		tab.add("客户信息初始化",memberInitialPanel);
		memberInitialPanel.setParent(this);
		//----------------------------
		accountInitialPanel=new AccountInitialPanel(parent);
		tab.add("账户信息初始化",accountInitialPanel);
		accountInitialPanel.setParent(this);
		// ---------buttons-----------
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
		exitBtn = new JButton("取消");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitBtn.addActionListener(this);
		btnPnl.add(exitBtn);
	}
	public static void main(String[] args) {
		JFrame testFrame = new JFrame();
		testFrame.setBounds(100, 50, 920, 600);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		AddInitialPanel gp = new AddInitialPanel(parent);
//		gp.setBounds(0, 0, 920, 600);
//		testFrame.add(gp);
//		testFrame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submitBtn){
			 Date dt=new Date();
		     SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		     System.out.println(matter.format(dt));
		     String time=matter.format(dt);
		     ArrayList<GoodsVO> goods=goodsInitialPanel.getGoods();
		     ArrayList<MemberVO> member=memberInitialPanel.getMember();
		     ArrayList<AccountVO> account=accountInitialPanel.getAccount();
		     BeginInfoVO begin=new BeginInfoVO(time,goods,member,account,parent.getUser().getID());
		     try {
		    	 FinanceInitBLService init=new Init();
				if(init.getCurrentTime().equals(time)){
					JOptionPane.showMessageDialog(null,"您今天已新建了套帐，一天之内不能重复新建套帐！","提示",JOptionPane.WARNING_MESSAGE);
					parent.setRightComponent(new InitialPanel(parent));
				}
				else{
				int result=init.initInfo(time, begin);
				if(result==0){
					JOptionPane.showMessageDialog(null,"添加成功!","提示",JOptionPane.WARNING_MESSAGE);
					InitialPanel initPanel=new InitialPanel(parent);
					parent.setRightComponent(initPanel);
					initPanel.refreshInitialTable(init.showAll());
				}
				
				else{
					JOptionPane.showMessageDialog(null,"这句话不可能出现→_→:我想抽到小马甲的台历！！！","提示",JOptionPane.WARNING_MESSAGE);
				}
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==exitBtn){
			InitialPanel ip=new InitialPanel(parent);
			parent.setRightComponent(ip);
			try {
				FinanceInitBLService init=new Init();
				ip.refreshInitialTable(init.showAll());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void setFocus(int i){
		tab.setSelectedIndex(i);
	}
}
