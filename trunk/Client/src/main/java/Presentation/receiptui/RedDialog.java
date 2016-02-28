package Presentation.receiptui;

import java.awt.*;

import javax.swing.*;

import Presentation.mainui.MainFrame;
import Presentation.uihelper.UIhelper;

import java.awt.event.*;

public class RedDialog extends JDialog implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int dialogWidth = screenWidth * 2 / 3;
	int dialogHeight = screenHeight * 2 / 3;


	public RedDialog(MainFrame owner,boolean modal,JPanel jp){
		super(owner,modal);

	    this.add(jp);
	    
		this.setBounds((screenWidth - dialogWidth) / 2,
				(screenHeight - dialogHeight) / 2, dialogWidth, dialogHeight);

		this.setVisible(true);
	    		
	}
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

	
}


