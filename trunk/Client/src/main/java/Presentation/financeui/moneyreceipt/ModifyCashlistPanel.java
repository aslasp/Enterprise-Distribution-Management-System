package Presentation.financeui.moneyreceipt;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vo.CashlistVO;
import vo.ClauseItemVO;
import vo.LogVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import businesslogic.userbl.User;
import businesslogic.financebl.CashList;
import businesslogicservice.financeblservice.listblservice.CashlistBLService;
import businesslogicservice.userblservice.UserBLService;

public class ModifyCashlistPanel extends AddCashReceiptPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
    CashlistBLService service;
    CashlistVO v;
    JButton modBtn;
    boolean isRed=false;
    JLabel mtitle;
	
	public ModifyCashlistPanel(String id,MainFrame frame) {
		super(frame);
		super.btnPnl.remove(addBtn);
		super.btnPnl.remove(delBtn);
		modBtn=new JButton("修改");
		modBtn.setFont(font);
		modBtn.setBackground(new Color(204, 242, 239));
		modBtn.setFocusPainted(false);
		modBtn.addActionListener(this);
		btnPnl.add(modBtn);
		try {
			service=new CashList();
			v=service.findByID(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// -----------title------------------
		titlePnl.remove(title);
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		mtitle = new JLabel("修改现金费用单");
		mtitle.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(mtitle);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.08;
		gbl.setConstraints(titlePnl, c);
		this.add(titlePnl);
		
		
		
		exitBtn.addActionListener(this);
		ID=v.getId();
		IDLbl.setText("ID:"+ID);
		user=v.getUser();
		try {
			UserBLService u=new User();
			userLbl.setText("操作员:"+u.showUser(user).getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    double money=v.getTotalMoney();
	    totalLbl.setText("总额汇总:"+money);
	    int isHurry=v.getHurry();
	    if(isHurry==1){
	    	hurryBox.setSelected(true);
	    	hurryBox.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					hurryBox.setSelected(true);
					
				}
	    		
	    	});
	    }
	    else{
	    	hurryBox.setSelected(false);
	    	hurryBox.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					hurryBox.setSelected(false);
					
				}
	    		
	    	});
	    }
	   
		super.up.remove(accountBox);
		super.up.remove(totalLbl);
		JLabel al=new JLabel();
		al.setText(v.getAccount());
		up.add(al);
		up.add(totalLbl);
		final ArrayList<ClauseItemVO> t=v.getClauselist();
		for(int i=0;i<t.size();i++){
		    ClauseItemVO tt=t.get(i);
			ArrayList<String> buffer = new ArrayList<String>();
		    buffer.add(tt.getName());
		    buffer.add(tt.getMoney()+"");
		    buffer.add(tt.getInfo());
		    crm.addRow(buffer);
		}
		table.revalidate();

	    tra=v.getClauselist();
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		     
			@Override
			public void valueChanged(ListSelectionEvent e) {
		
			//	ClauseItemVO it=t.get(e.getLastIndex());
				System.out.println("FIRST:"+e.getFirstIndex());
				System.out.println("LAST:"+e.getLastIndex());
				ClauseItemVO it=t.get(table.getSelectedRow());
				nameFld.setText(it.getName()+"");
				moneyFld.setText(it.getMoney()+"");
				moneyFld.setEditable(false);
				remarkFld.setText(it.getInfo());
			
				
			}
			
			
		    });
	}

	

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==modBtn){
			ClauseItemVO item = new ClauseItemVO((String) accountBox
					.getSelectedItem(), Double
					.parseDouble(moneyFld.getText()), remarkFld
					.getText());
			int index=table.getSelectedRow();
			tra.set(index, item);
			crm.setValueAt(index, 0, nameFld.getText());
			crm.setValueAt(index, 2, remarkFld.getText());
			table.revalidate();
			table.repaint();
		}
	  
		if(e.getSource()==submitBtn){
			
			
			CashlistVO vo=new CashlistVO(v.getId(),v.getUser(),v.getAccount(),tra,v.getTotalMoney(),0,v.getHurry());
			try {
				service = new CashList();
				String tip="修改";
				int result=0;
				if(isRed){
					tip="制定";result=service.createCashlist(vo);
				}
				
				 result=service.modify(vo);
				if (result == 0) {
					JOptionPane.showMessageDialog(null, tip+"现金费用单成功！", "提示",
							JOptionPane.CLOSED_OPTION);
					log.addLog(new LogVO(log.getdate(),parent.getUser().getID(),parent.getUser().getName(),
							tip+"了一笔现金费用单",5));
					headPane.RefreshGrades();
				} else {
					JOptionPane.showMessageDialog(null, tip+"现金费用单失败！", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==exitBtn){
			
			
		}
		
	}
	
	public void UseToModify(ActionListener ok,boolean isRed){
		submitBtn.addActionListener(ok);
		exitBtn.addActionListener(ok);
		submitBtn.addActionListener(this);
		if(isRed)
		{	this.title.setText("制定现金费用单");this.isRed=true;}
		
	}


}


