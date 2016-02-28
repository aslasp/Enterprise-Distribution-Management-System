package Presentation.memberui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import businesslogic.memberbl.Member;
import businesslogicservice.memberblservice.MemberBLService;
import Presentation.mainui.MainFrame;
import Presentation.uihelper.UIhelper;


public class DelMemberDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	JLabel sureLbl;
	JButton sureBtn;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int dialogWidth = screenWidth /4;
	int dialogHeight = screenHeight / 4;
	MainFrame parent;
	MemberBLService service;

	public DelMemberDialog(final ArrayList<String> ID,MainFrame frame){
		parent=frame;
		Container pnl=this.getContentPane();
		pnl.setBackground(Color.white);
		pnl.setLayout(null);
		//---------------sureLbl-----------------------------------
		sureLbl=new JLabel("确定要删除选中客户 吗？");
		sureLbl.setBounds(dialogWidth*25/100,
				dialogHeight*10/100, dialogWidth*60/100, dialogHeight*30/100);
		sureLbl.setFont(new Font("微软雅黑", Font.BOLD, 15));
		pnl.add(sureLbl);
		//---------------sureBtn-----------------------------------
		sureBtn=new JButton("我确定要删除");
		sureBtn.setBounds(dialogWidth*25/100,
				dialogHeight*50/100, dialogWidth*50/100, dialogHeight*15/100);
		sureBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sureBtn.setFocusPainted(false);
		sureBtn.setBackground(new Color(251, 147, 121));
		pnl.add(sureBtn);
		
		sureBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					service=new Member();
					for(int i=0;i<ID.size();i++)
					service.deleteMember(ID.get(i));
					DelMemberDialog.this.dispose();
					MemberMgrPanel mgr=new MemberMgrPanel(parent);
					parent.setRightComponent(mgr);
				if(service.showMembers()!=null)
					mgr.RefreshMemberTable(service.showMembers());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		//---------------------------------------------------------
		this.setTitle("删除确认");
		this.setBounds((screenWidth - dialogWidth) / 2,
				(screenHeight - dialogHeight) / 2, dialogWidth, dialogHeight);
		this.setResizable(false);
		this.setModal(true);
		this.setIconImage(UIhelper.getImage("img/sales/delMember-blue.png"));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
