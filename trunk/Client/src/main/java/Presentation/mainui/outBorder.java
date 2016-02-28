package Presentation.mainui;



import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class outBorder extends TitledBorder{
	static Border border=BorderFactory.createBevelBorder(BevelBorder.LOWERED);
	public outBorder(String title){
		super(border,title);
		Font style=new Font("黑体",Font.BOLD,12);
		this.setTitleFont(style);
		this.setTitlePosition(TitledBorder.CENTER);
		this.setTitleJustification(TitledBorder.LEADING);
	}
	 
}
