package Presentation.financeui.initial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vo.AccountVO;
import Presentation.financeui.account.AddAccountPanel;
import Presentation.mainui.MainFrame;

public class addAccountInitial extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AddAccountPanel p;
	AccountInitialPanel tab;
	MainFrame parent;
	public addAccountInitial(MainFrame pa,final AccountInitialPanel tab ){
		try {
			p=new AddAccountPanel(pa);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parent=pa;
		this.tab=tab;
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.CENTER);
		
		p.submitBtn.removeActionListener(p.add);
		p.exitBtn.removeActionListener(p.exit);
		p.submitBtn.addActionListener(this);
		p.exitBtn.addActionListener(this);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==p.submitBtn){
			p.name=p.nameFld.getText();
			p.money=p.moneyFld.getText();
			if (p.name.equals("")||p.money.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",JOptionPane.CLOSED_OPTION);
			} else {
				AccountVO vo = new AccountVO(p.name,Double.parseDouble(p.money));
				tab.RefreshTable(vo);
				parent.setRightComponent(tab.subparent);
				tab.subparent.setFocus(2);
				tab.subparent.accountInitialPanel.haveSelected.add(-1);
				
			}
		}
		 else if(e.getSource()==p.exitBtn){
				parent.setRightComponent(tab.subparent);
				tab.subparent.setFocus(2);				
			}
		}
		
	

}
	
	
	



