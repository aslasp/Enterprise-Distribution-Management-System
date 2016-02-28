package Presentation.promotionui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import businesslogic.promotionbl.promotionController;
import businesslogic.receiptbl.ReceiptController;
import businesslogicservice.promotionblservice.PromotionBLService;
import businesslogicservice.receiptblservice.ReceiptBLService;
import vo.UserVO;
import Presentation.mainui.JLeftButton;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.receiptui.ReceiptMgrPanel;
import Presentation.receiptui.ReportMgrPanel;
import Presentation.uihelper.AboutPanel;

public class ProLeftPanel extends JPanel implements ActionListener{
	
	//头像 info到时候动态获取根据user  size需要父类添加时get
	JLeftButton receipt,promotion,view;
	JLeftButton aboutBtn,backBtn;
	JPanel headPane;
	Color color;
	MainFrame parent;
	PromotionBLService service;
	public ProLeftPanel(MainFrame frame) throws Exception{
		parent=frame;
		//setSize(frame.getWidth()*225/1000,frame.getHeight());
		//去掉
		service=new promotionController();
		UserVO user=parent.getUser();
		color=parent.getTheme()[0];
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints c=new GridBagConstraints();
	
		this.setLayout(grid);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;c.gridy=0;
		c.gridheight=2;
		
		headPane=new headPane(parent,this,user,0);
		grid.setConstraints(headPane, c);
		this.add(headPane);
		
		JPanel down=new JPanel();
		c.weightx=3;c.weighty=4;
		c.gridx=0;c.gridy=2;
		c.gridheight=6;
		c.gridwidth=1;
		grid.setConstraints(down, c);
		this.add(down);
		down.setBackground(color);
		down.setLayout(new GridLayout(6,1));
		
		
		
		ImageIcon receiptImg=new ImageIcon("img/promotion/receiptView.png");
		receipt=new JLeftButton("单据审批",receiptImg,color);
	
		ImageIcon proImg=new ImageIcon("img/promotion/promotion.png");
		promotion=new JLeftButton("策略制定",proImg,color);
	
		ImageIcon viewImg=new ImageIcon("img/promotion/view.png");
		
		view=new JLeftButton("业绩查看",viewImg,color);
	
		
		receipt.addActionListener(this);
		promotion.addActionListener(this);
		view.addActionListener(this);
		
		aboutBtn = new JLeftButton("关于系统", new ImageIcon("img/icon-about.png"),
				color);
		
		aboutBtn.addActionListener(this);
		
		

		
		down.add(receipt);
		down.add(promotion);
		down.add(view);
		down.add(aboutBtn);
		
		
		backBtn=new JLeftButton("收起菜单",new ImageIcon("img/mainFrame/back.png"),
				color);
		
		backBtn.addActionListener(this);
		c.gridx=0;c.gridy=8;
		grid.setConstraints(backBtn, c);
		this.add(backBtn);
		this.setBackground(color);
			
	}
	
	public void actionPerformed(ActionEvent e){
		try {
		if(e.getSource()==promotion){
			//promotion.setBackground(parent.getTheme()[1]);;
			PromotionPanel pp=new PromotionPanel(parent);
			parent.setRightComponent(pp);
			if(service.Show()!=null){
				pp.RefreshProTable(service.Show());
			}
		}else if(e.getSource()==backBtn){
			
				parent.setLeftComponent(new ProLeftShortPanel(parent));
			
		}else if(e.getSource()==view){
			ReportMgrPanel report=new ReportMgrPanel(parent);
			parent.setRightComponent(report);
			report.RefreshTable();
			
		}else if(e.getSource()==receipt){
			ReceiptMgrPanel rmg=new ReceiptMgrPanel(parent);
			parent.setRightComponent(rmg);
			rmg.Refresh();
		}else if(e.getSource()==aboutBtn){
			parent.setRightComponent(new AboutPanel());
		}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
