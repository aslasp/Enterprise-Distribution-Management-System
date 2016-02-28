package Presentation.uihelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class UserInfoButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JWindow userInfoWindow;
	public UserInfoButton(String username,Icon icon,Color themeColor){
		super(username, icon);
		this.setFont(new Font("楷体", Font.PLAIN, 19));
		this.setForeground(Color.white);
		this.setBorderPainted(false);
		this.setBackground(themeColor);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFocusPainted(false);
		this.addMouseListener(new UserInfoBtnListener());
	}
	class UserInfoBtnListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			userInfoWindow=new UserInfoWindow(e.getXOnScreen()-10,e.getYOnScreen()+20);
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
