package Presentation.stockui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import vo.UserVO;
import Presentation.mainui.JLeftButton;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.stockui.giftmanage.GiftPanel;
import Presentation.stockui.goodsmanage.GoodsPanel;
import Presentation.stockui.stockmanage.StockPanel;
import Presentation.uihelper.AboutPanel;

public class StockLeftPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLeftButton  goodsBtn, stockBtn, giftBtn, aboutBtn,backBtn;
	Color stockColor;
	MainFrame parent;
	JPanel headPane;
	public StockLeftPanel(MainFrame frame) {
		parent=frame;
		stockColor=frame.getTheme()[0];
		//--------构造头像----------
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
				
				
				JPanel down=new JPanel();
				c.weightx=3;c.weighty=4;
				c.gridx=0;c.gridy=2;
				c.gridheight=6;
				c.gridwidth=1;
				grid.setConstraints(down, c);
				this.add(down);
				down.setBackground(stockColor);
				down.setLayout(new GridLayout(6,1));
				
		// 商品管理按钮
		goodsBtn = new JLeftButton("商品管理", new ImageIcon("img/stock/goodsMgr.png")
		,stockColor);
		goodsBtn.addActionListener(this);
		down.add(goodsBtn);
		// 库存管理按钮
		stockBtn = new JLeftButton("库存管理", new ImageIcon(
				"img/stock/stockMgr.png"),stockColor);
		stockBtn.addActionListener(this);
		down.add(stockBtn);
		// 库存赠送按钮
		giftBtn = new JLeftButton("库存赠送", new ImageIcon("img/stock/giftMgr.png"),
				stockColor);
		
		giftBtn.addActionListener(this);
		down.add(giftBtn);
		// 关于按钮
		aboutBtn = new JLeftButton("关于系统", new ImageIcon("img/icon-about.png"),
				stockColor);
		aboutBtn.addActionListener(this);
		down.add(aboutBtn);
		
		
		backBtn=new JLeftButton("收起菜单",new ImageIcon("img/mainFrame/back.png"),
				stockColor);
		
		backBtn.addActionListener(this);
		c.gridx=0;c.gridy=8;
		grid.setConstraints(backBtn, c);
		this.add(backBtn);
		this.setBackground(stockColor);
		
		
	}
//goodsBtn, stockBtn, giftBtn, 
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backBtn){
			parent.setLeftComponent(new StockLeftShortPanel(parent));
		}else if(e.getSource()==aboutBtn){
			parent.setRightComponent(new AboutPanel());
		}else if(e.getSource()==goodsBtn){
			parent.setRightComponent(new GoodsPanel(parent));
		}else if(e.getSource()==stockBtn){
			parent.setRightComponent(new StockPanel(parent));
		}
		else if(e.getSource()==giftBtn){
			parent.setRightComponent(new GiftPanel(parent));
		}
		
	}
}
