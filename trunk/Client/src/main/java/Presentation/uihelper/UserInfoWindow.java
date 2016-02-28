package Presentation.uihelper;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JWindow;

public class UserInfoWindow extends JWindow{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton exitBtn,changeUserBtn,modifyMessageBtn;
	public UserInfoWindow(int x,int y){
		this.setBounds(x, y, 320, 280);
		Container pnl=this.getContentPane();
		pnl.setBackground(Color.white);
		this.setVisible(true);
	}

}
