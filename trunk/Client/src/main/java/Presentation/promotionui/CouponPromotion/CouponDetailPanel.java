package Presentation.promotionui.CouponPromotion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.GiftCouponProVO;
import businesslogic.promotionbl.promotionController;
import businesslogicservice.promotionblservice.PromotionViewService;
import Presentation.mainui.MainFrame;
import Presentation.promotionui.PromotionPanel;

public class CouponDetailPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame father;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JLabel fromLbl,toLbl,gradeLbl,limitLbl,priceLbl,numLbl;
	JButton submitBtn;
	PromotionViewService service;
	GiftCouponProVO vo;
	public CouponDetailPanel(MainFrame frame,String id) throws Exception{
		father = frame;
		service=new promotionController();
		vo=service.gpFindByID(id);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// ----------------------------
		JPanel titlePnl=new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1,1));
		JLabel title = new JLabel("查看代金券赠送策略");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(titlePnl, c);
		this.add(titlePnl);
		// -----------------------------
		c.fill=GridBagConstraints.BOTH;
		JPanel mPnl = new JPanel();
		mPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 6;
		c.gridwidth =GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(mPnl, c);
		this.add(mPnl);
		mPnl.setLayout(new BorderLayout(200, 200));
		mPnl.add(new JLabel(), BorderLayout.WEST);
		// -------------------------------
		JPanel funcPnl = new JPanel();
		funcPnl.setBackground(Color.white);
		funcPnl.setLayout(new GridLayout(6, 1));
		// --------起止时间-----------------
		JPanel timePnl = new JPanel();
		timePnl.setBackground(Color.white);
		funcPnl.add(timePnl);
		fromLbl=new JLabel("起始于："+vo.getStartDate());
		fromLbl.setFont(font);
		timePnl.add(fromLbl);
		timePnl.add(new JLabel());
		//
		toLbl=new JLabel("截止于："+vo.getEndDate());
		toLbl.setFont(font);
		timePnl.add(toLbl);
		// ---------限制客户等级-------------
		JPanel gradePnl = new JPanel();
		gradePnl.setBackground(Color.white);
		funcPnl.add(gradePnl);
		JLabel gradeLbl = new JLabel("客户等级限制："+vo.getMemberlevel().toString());
		gradeLbl.setFont(font);
		gradePnl.add(gradeLbl);
		// -------设定满赠金额----------------
		JPanel limitPnl = new JPanel();
		limitPnl.setBackground(Color.white);
		funcPnl.add(limitPnl);
		JLabel limitLbl = new JLabel("满赠金额："+vo.getTotalValue());
		limitLbl.setFont(font);
		limitPnl.add(limitLbl);
		// -------设定单张代金券面值----------------
		JPanel pricePnl = new JPanel();
		pricePnl.setBackground(Color.white);
		funcPnl.add(pricePnl);
		JLabel priceLbl = new JLabel("单张面值："+vo.getCouponList().get(0).getValue());
		priceLbl.setFont(font);
		pricePnl.add(priceLbl);
		// -------限制总额----------------
		JPanel totalPnl = new JPanel();
		totalPnl.setBackground(Color.white);
		funcPnl.add(totalPnl);
		JLabel totalLbl = new JLabel("数量："+vo.getCouponList().size());
		totalLbl.setFont(font);
		totalPnl.add(totalLbl);
		// -------------------------------
		mPnl.add(funcPnl, BorderLayout.CENTER);
		mPnl.add(new JLabel(), BorderLayout.EAST);
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
		// -------------------------------
		submitBtn = new JButton("返回");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PromotionPanel ppanel=new PromotionPanel(father);
				father.setRightComponent(ppanel);
				if(service.Show()!=null)
					ppanel.RefreshProTable(service.Show());
				
			}
		});
		btnPnl.add(submitBtn);
	}
}
