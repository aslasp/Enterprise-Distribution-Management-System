package Presentation.financeui.moneyreceipt;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vo.CollectionVO;
import vo.LogVO;
import vo.TransferItemVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import businesslogic.financebl.Collection;
import businesslogic.userbl.User;
import businesslogicservice.financeblservice.listblservice.CollectionBLService;
import businesslogicservice.userblservice.UserBLService;

public class ModifyCollectionPanel extends CollectionAndPaymentPanel implements ActionListener{
		/**
		 * 修改收款单
		 * 加了监听
		 */
		private static final long serialVersionUID = 1L;
	    CollectionBLService service;
	    CollectionVO v;
	    JButton modBtn;
		boolean isRed=false;
		JLabel title;
		public ModifyCollectionPanel(String id,MainFrame frame) {
			super(frame);
			super.item2.remove(addBtn);
			super.item2.remove(delBtn);
			modBtn=new JButton("修改");
			modBtn.setFont(font);
			modBtn.setBackground(new Color(204, 242, 239));
			modBtn.setFocusPainted(false);
			modBtn.addActionListener(this);
			item2.add(modBtn);
			try {
				service=new Collection();
				v=service.findByID(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// -----------title------------------
			JPanel titlePnl = new JPanel();
			titlePnl.setBackground(Color.white);
			titlePnl.setLayout(new GridLayout(1, 1));
			 title = new JLabel("修改收款单");
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
		   
			super.memPnl.remove(memBox);
			JLabel ml=new JLabel();
			ml.setText(v.getMemberName());
			memPnl.add(ml);
			final ArrayList<TransferItemVO> t=v.getTransferlist();
			for(int i=0;i<t.size();i++){
			    TransferItemVO tt=t.get(i);
				ArrayList<String> buffer = new ArrayList<String>();
			    buffer.add(tt.getAccount());
			    buffer.add(tt.getMoney()+"");
			    buffer.add(tt.getInfo());
			    tlm.addRow(buffer);
			}
			table.revalidate();

		    tra=v.getTransferlist();
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			     
				@Override
				public void valueChanged(ListSelectionEvent e) {
			
				//	TransferItemVO it=t.get(e.getFirstIndex());
					TransferItemVO it=t.get(table.getSelectedRow());
					int ac=st.indexOf(it.getAccount());
					accountBox.setSelectedIndex(ac);
					moneyFld.setText(it.getMoney()+"");
					moneyFld.setEditable(false);
					remarkFld.setText(it.getInfo());
				
				}
			    });
		}

		public static void main(String[] args) {
			JFrame testFrame = new JFrame();
			testFrame.setBounds(100, 50, 920, 600);
			testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/*		ModifyCollectionPanel gp = new ModifyCollectionPanel();
			gp.setBounds(0, 0, 920, 600);
			testFrame.add(gp);
			testFrame.setVisible(true);
	*/
		}

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==modBtn){
				TransferItemVO item = new TransferItemVO((String) accountBox
						.getSelectedItem(), Double
						.parseDouble(moneyFld.getText()), remarkFld
						.getText());
				int index=table.getSelectedRow();
				tra.set(index, item);
				tlm.setValueAt(index, 0, (String) accountBox.getSelectedItem());
				tlm.setValueAt(index, 2, remarkFld.getText());
				table.revalidate();
				table.repaint();
			}
		  
			if(e.getSource()==submitBtn){
				
				
				CollectionVO vo=new CollectionVO(v.getId(),v.getMemberID(),v.getMemberName(),v.getUser(),tra,v.getTotalMoney(),0,v.getHurry());

				try {
					service = new Collection();
					String tip="修改";
					int result=0;
					if(isRed){
						tip="制定";result=service.createCollection(vo);
					}
					 result=service.modify(vo);
					if (result == 0) {
						JOptionPane.showMessageDialog(null, tip+"收款单成功！", "提示",
								JOptionPane.CLOSED_OPTION);
						log.addLog(new LogVO(log.getdate(),parent.getUser().getID(),parent.getUser().getName(),
								tip+"了一笔收款单",5));
						headPane.RefreshGrades();
					} else {
						JOptionPane.showMessageDialog(null, tip+"收款单失败！", "提示",
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
			if(isRed){
				this.isRed=true;this.title.setText("制定收款单");
			}
			
		}
	

}
