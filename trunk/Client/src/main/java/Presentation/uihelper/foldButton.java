package Presentation.uihelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class foldButton extends JButton{
	private static final long serialVersionUID = 1L;
	public foldButton(){
		super("收起",new ImageIcon("img/fold.jpg"));
		this.addActionListener(new foldBtnListener());
	}
	class foldBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
