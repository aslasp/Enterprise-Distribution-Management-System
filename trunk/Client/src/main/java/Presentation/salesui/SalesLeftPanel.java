package Presentation.salesui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import businesslogic.memberbl.Member;
import businesslogic.salesbl.SaleList;
import businesslogicservice.memberblservice.MemberBLService;
import businesslogicservice.salesblservice.SaleListBLService;
import vo.UserVO;
import Presentation.mainui.JLeftButton;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.memberui.MemberMgrPanel;
import Presentation.salesui.manage.PurchaseMgrPanel;
import Presentation.salesui.manage.SaleMgrPanel;
import Presentation.salesui.manage.sale.AddSalePanel;
import Presentation.uihelper.AboutPanel;
import Presentation.uihelper.UIhelper;
import Presentation.uihelper.UserInfoButton;

public class SalesLeftPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLeftButton purchaseBtn, saleBtn, memberBtn,aboutBtn,backBtn;
	Color salesColor;
	MainFrame parent;
	JPanel headPane;
	public SalesLeftPanel(MainFrame frame) throws Exception{
		salesColor=frame.getTheme()[0];
		parent=frame;

		//===构造头像
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints c=new GridBagConstraints();
	
		UserVO user=frame.getUser();
		this.setLayout(grid);
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;c.gridy=0;
		c.gridheight=2;
		
		headPane=new headPane(parent,this,user,0);
		grid.setConstraints(headPane, c);
		this.add(headPane);
		
		
		//----功能菜单--------
		JPanel down=new JPanel();
		c.weightx=3;c.weighty=4;
		c.gridx=0;c.gridy=2;
		c.gridheight=6;
		c.gridwidth=1;
		grid.setConstraints(down, c);
		this.add(down);
		down.setBackground(salesColor);
		down.setLayout(new GridLayout(6,1));
		
		memberBtn = new JLeftButton("客户管理",
				new ImageIcon("img/sales/memberMgr.png"),salesColor);
		memberBtn.addActionListener(this);
		down.add(memberBtn);
		// 进货管理按钮
		purchaseBtn = new JLeftButton("进货管理", new ImageIcon(
				"img/sales/purchaseMgr.png"),salesColor);
		purchaseBtn.addActionListener(this);
		down.add(purchaseBtn);
		// 销售管理按钮
		saleBtn = new JLeftButton("销售管理", new ImageIcon("img/sales/saleMgr.png"),
				salesColor);
		saleBtn.addActionListener(this);
		down.add(saleBtn);
		// 关于按钮
		aboutBtn = new JLeftButton("关于系统", new ImageIcon("img/icon-about.png"),
				salesColor);
		aboutBtn.addActionListener(this);
		down.add(aboutBtn);
		
		backBtn=new JLeftButton("收起菜单",new ImageIcon("img/mainFrame/back.png"),salesColor);
		backBtn.addActionListener(this);
		c.gridx=0;c.gridy=8;
		grid.setConstraints(backBtn, c);
		this.add(backBtn);
		this.setBackground(salesColor);
		
}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource()==purchaseBtn){
				PurchaseMgrPanel pgr=new PurchaseMgrPanel(parent);
				parent.setRightComponent(pgr);
				pgr.RefreshPanel();
			}else if(e.getSource()==memberBtn){
				MemberMgrPanel mgr=new MemberMgrPanel(parent);
				parent.setRightComponent(mgr);
				mgr.RefreshPanel();
			}else if(e.getSource()==saleBtn){
				SaleMgrPanel sp=new SaleMgrPanel(parent);
				parent.setRightComponent(sp);
				sp.RefreshPanel();
			
			}else if(e.getSource()==aboutBtn){
				parent.setRightComponent(new AboutPanel());
			}else if(e.getSource()==backBtn){
				parent.setLeftComponent(new SaleLeftShortPanel(parent));
			}
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
	}
}
