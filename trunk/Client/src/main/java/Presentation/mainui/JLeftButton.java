package Presentation.mainui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class JLeftButton extends JButton{
	
	public JLeftButton(String txt,ImageIcon icon,Color color){
		this(icon,color);
		this.setText(txt);;	
	}
	public JLeftButton(ImageIcon icon,Color color){
		super(icon);
		this.setFont(new Font("楷体", Font.PLAIN, 19));
		this.setForeground(Color.white);
		this.setBackground(color);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFocusPainted(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}

}
