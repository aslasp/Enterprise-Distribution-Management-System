package Presentation.userui.usermanage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import businesslogic.userbl.User;
import businesslogicservice.userblservice.UserBLService;
import Presentation.mainui.MainFrame;
import Presentation.uihelper.UIhelper;
import Presentation.userui.UserMgrPanel;


public class DelUserDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton submitBtn;
	JLabel textLbl;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int dlgWidth = screenWidth * 25 / 100;
	int dlgHeight = screenHeight * 25 / 100;
	Container pnl;
	UserBLService service;
	public DelUserDialog(final ArrayList<String> ID,final MainFrame parent) {
		pnl = this.getContentPane();
		pnl.setBackground(Color.white);
		pnl.setLayout(null);
		//
		// ------------------textLbl------------------------------------
		textLbl = new JLabel("你确定要删除选中用户 吗？");
		textLbl.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textLbl.setBounds(dlgWidth * 28 / 100, dlgHeight * 20 / 100,
				dlgWidth * 80 / 100, dlgHeight * 16 / 100);
		pnl.add(textLbl);
		// -------------------submitBtn---------------------------------------------
		submitBtn = new JButton("确定删除");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		submitBtn.setBounds(dlgWidth * 35 / 100, dlgHeight * 58 / 100,
				dlgWidth * 30 / 100, dlgHeight * 16 / 100);
		submitBtn.setFocusPainted(false);
		pnl.add(submitBtn);
		submitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					service=new User();
				for(int i=0;i<ID.size();i++)
					service.deleteUser(ID.get(i));
					DelUserDialog.this.dispose();
					UserMgrPanel mgr=new UserMgrPanel(parent);
					parent.setRightComponent(mgr);
					if(service.showAll()!=null)
					mgr.RefreshUserTable(service.showAll());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
			}
		});
		//
		this.setTitle("删除确认");
		this.setBounds((screenWidth - dlgWidth) / 2,
				(screenHeight - dlgHeight) / 2, dlgWidth, dlgHeight);

		this.setResizable(false);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
