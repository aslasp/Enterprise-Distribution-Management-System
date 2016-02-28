package Presentation.financeui.initial;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.AccountVO;
import vo.BeginInfoVO;
import vo.GoodsVO;
import vo.MemberVO;
import businesslogic.financebl.Init;
import businesslogic.userbl.User;
import businesslogicservice.financeblservice.initblservice.FinanceInitBLService;
import businesslogicservice.userblservice.UserBLService;
import Presentation.mainui.MainFrame;

public class InitialDetailPanel extends AddInitialPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel timeLbl,userLbl;
	JPanel infoPnl;
	FinanceInitBLService init=null;
	BeginInfoVO vo;
	
	public InitialDetailPanel(MainFrame frame,int selected) {
		super(frame);
		
		try {
			init=new Init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<BeginInfoVO> vv=init.showAll();
		vo=vv.get(selected);
		
		title.setText("账套详情");
		exitBtn.setBackground(new Color(166, 210, 121));
		exitBtn.setText("返回");
		btnPnl.remove(submitBtn);
		//--------------------------------
		infoPnl=new JPanel();
		infoPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.08;
		gbl.setConstraints(infoPnl, c);
		this.add(infoPnl, c);
		//---------建账时间---------------
		timeLbl=new JLabel("建账时间:"+vo.getTime());
		timeLbl.setFont(font);
		infoPnl.add(timeLbl);
		//----------操作员------------------
		String userName=null;
		try {
			UserBLService user=new User();
			userName=user.showUser(vo.getUserID()).getName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userLbl=new JLabel("操作员:"+userName);
		userLbl.setFont(font);
		infoPnl.add(userLbl);
		//--------------------------------
		goodsInitialPanel.btnPnl.remove(goodsInitialPanel.addBtn);
		goodsInitialPanel.btnPnl.remove(goodsInitialPanel.delBtn);
		goodsInitialPanel.remove(goodsInitialPanel.btnPnl);
		ArrayList<Object> good = new ArrayList<Object>();
		if(vo.getGoods()!=null)
		for(GoodsVO g:vo.getGoods()){
			good.add(g);
		}
		goodsInitialPanel.RefreshCTable(good);
		//---------------------------
		memberInitialPanel.btnPnl.remove(memberInitialPanel.addBtn);
		memberInitialPanel.btnPnl.remove(memberInitialPanel.delBtn);
		memberInitialPanel.btnPnl.remove(memberInitialPanel.memberBox);
		memberInitialPanel.remove(memberInitialPanel.btnPnl);
		if(vo.getMember()!=null)
			for(MemberVO m:vo.getMember()){
				memberInitialPanel.RefreshTable(m);
			}
		//----------------------------
		accountInitialPanel.btnPnl.remove(accountInitialPanel.addBtn);
		accountInitialPanel.btnPnl.remove(accountInitialPanel.delBtn);
		accountInitialPanel.btnPnl.remove(accountInitialPanel.accountBox);
		accountInitialPanel.remove(accountInitialPanel.btnPnl);
		//----------------------------
		if(vo.getAccount()!=null)
			for(AccountVO a:vo.getAccount()){
				accountInitialPanel.RefreshTable(a);
			}
	}

}
