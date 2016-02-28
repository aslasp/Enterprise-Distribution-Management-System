package Presentation.uihelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AboutPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel imagePnl;
	JLabel wordLbl;
	public AboutPanel(){
		GridBagLayout gbl=new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.anchor=GridBagConstraints.CENTER;
		//
		imagePnl=new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("img/aboutUs.png");
				Image img = icon.getImage();
				g.drawImage(img, (imagePnl.getWidth()-img.getWidth(imagePnl))/2,(imagePnl.getHeight()-img.getHeight(imagePnl))/2, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
			}
		};
		imagePnl.setBackground(Color.black);
		c.gridx=0;
		c.gridy=0;
		c.weighty=0.8;
		c.weightx=1;
		gbl.addLayoutComponent(imagePnl, c);
		this.add(imagePnl);
		//
		wordLbl=new JLabel("本系统由“羽见青柠”小组开发。PM：金翠；组员：严顺宽，黄涵倩，王宁。",SwingConstants.CENTER);
		wordLbl.setFont(new Font("楷体", Font.PLAIN, 19));
		wordLbl.setForeground(Color.black);
		c.gridx=0;
		c.gridy=1;
		c.weighty=0.2;
		gbl.addLayoutComponent(wordLbl, c);
		this.add(wordLbl);
		this.setBackground(Color.WHITE);
	}
}
