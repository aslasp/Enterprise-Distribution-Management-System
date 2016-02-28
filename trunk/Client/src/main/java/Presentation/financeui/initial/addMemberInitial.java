package Presentation.financeui.initial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import po.MemberPO.MemberType;
import vo.MemberVO;
import Presentation.mainui.MainFrame;
import Presentation.memberui.AddMemberPanel;

public class addMemberInitial extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AddMemberPanel p;
	MemberInitialPanel tab;
	MainFrame parent;
	public addMemberInitial(MainFrame pa,final MemberInitialPanel tab ){
		try {
			p=new AddMemberPanel(pa);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parent=pa;
		this.tab=tab;
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.CENTER);
		p.typeCbox.removeItemListener(p.typel);
		p.typeCbox.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String t = p.typeCbox.getSelectedItem().toString();
				if (t.equals("进货商"))
					p.mtype = MemberType.JHS;
				else
					p.mtype = MemberType.XSS;
				

				p.ID = tab.getNewID(p.mtype);
				p.IDLbl.setText("编号：" + p.ID);
			}
			
		});
		p.submitBtn.removeActionListener(p.add);
		p.cancelBtn.removeActionListener(p.add);
		p.submitBtn.addActionListener(this);
		p.cancelBtn.addActionListener(this);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==p.submitBtn){
			if (p.ID == null ||p. ID.equals("")) {
				JOptionPane.showMessageDialog(null, "请选择用户类型，并输入信息！", "提示",
						JOptionPane.CLOSED_OPTION);}
			else{
				MemberVO vo=p.getMemberVO();
				tab.RefreshTable(vo);
				parent.setRightComponent(tab.subparent);
				tab.subparent.setFocus(1);
				tab.subparent.memberInitialPanel.haveSelected.add(-1);
			}
			}else if(e.getSource()==p.cancelBtn){
				parent.setRightComponent(tab.subparent);
				tab.subparent.setFocus(1);
				
			}
		}
		
	

}
	
	
	


