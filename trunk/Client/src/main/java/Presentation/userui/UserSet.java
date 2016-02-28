package Presentation.userui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Presentation.mainui.LoginFrame;
import Presentation.mainui.MainFrame;

//监听多次密码输错无法更新
public class UserSet extends JPopupMenu implements ActionListener{
	MainFrame parent;
	JMenuItem caccount,cpass;
	public  UserSet(JFrame frame,JPanel pane){
			parent=(MainFrame)frame;
			ImageIcon account=new ImageIcon("img/mainFrame/close.png");
			caccount=new JMenuItem("切换帐号",account);
			ImageIcon pass=new ImageIcon("img/mainFrame/min.png");
			cpass=new JMenuItem("修改密码",pass);
			this.setBorder(BorderFactory.createLineBorder(parent.getTheme()[0]));
			add(caccount);
			add(cpass);
			caccount.addActionListener(this);
			cpass.addActionListener(this);
	}

public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==caccount){
		parent.getMessageThread().stopThead();
		parent.dispose();
		
		try {
			new LoginFrame();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}else if(e.getSource()==cpass){
		JDialog Change=new ChangeKeyDialog(parent);
		
	}
	
}
	

}
