package Presentation.salesui.manage.sale;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Presentation.uihelper.UIhelper;

public class UseCouponDialog extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int dialogWidth = screenWidth / 4;
	int dialogHeight = screenHeight / 4;
	Container pnl;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	Color color=new Color(254,239,182);
	JTextField couponFld;
	String couponText;//文本框的内容已经存入这里
	JButton submitBtn,exitBtn;
	AddSalePanel parent;
	public UseCouponDialog(AddSalePanel pane) {
		parent=pane;
		pnl = this.getContentPane();
		pnl.setLayout(new GridLayout(4, 1));
		pnl.setBackground(color);
		// -----title------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(color);
		pnl.add(titlePnl);
		JLabel title = new JLabel("请输入代金券编号。");
		title.setFont(font);
		titlePnl.add(title);
		// -----warning------------
		JPanel warningPnl = new JPanel();
		warningPnl.setBackground(color);
		pnl.add(warningPnl);
		JLabel warning = new JLabel("注意：代金券使用后不可反悔！");
		warning.setFont(font);
		warningPnl.add(warning);
		// -----couponFld--------
		JPanel couponPnl = new JPanel();
		couponPnl.setBackground(color);
		pnl.add(couponPnl);
		couponFld = new JTextField(15);
		couponFld.setFont(font);
		couponPnl.add(couponFld);
		couponFld.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent e) {
				couponText=couponFld.getText();
			}
			
			public void insertUpdate(DocumentEvent e) {
				couponText=couponFld.getText();
			}
			
			public void changedUpdate(DocumentEvent e) {
				couponText=couponFld.getText();
			}
		});
		//------buttons------------
		JPanel btnPnl=new JPanel();
		btnPnl.setBackground(color);
		pnl.add(btnPnl);
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.addActionListener(this);
		btnPnl.add(submitBtn);
		exitBtn = new JButton("取消");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitBtn.addActionListener(this);
		btnPnl.add(exitBtn);
		this.setTitle("输入代金券编号");
		this.setBounds((screenWidth - dialogWidth) / 2,
				(screenHeight - dialogHeight) / 2, dialogWidth, dialogHeight);

		this.setResizable(false);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==submitBtn){
			parent.couponUse(couponFld.getText());
			UseCouponDialog.this.dispose();
		}else if(e.getSource()==exitBtn){
			UseCouponDialog.this.dispose();
		}
	}

	
}
