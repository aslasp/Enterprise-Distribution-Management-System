package Presentation.financeui.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vo.AccountVO;
import Presentation.mainui.MainFrame;

public class ModAccountPanel extends AddAccountPanel{
	MainFrame parent;
	AccountVO vo;
	JLabel levelbl;
	public ModAccountPanel(String name,MainFrame frame) throws Exception{
		super(frame);
		parent=frame;
		vo=service.findByName(name);
		title.setText("修改客户");

		nameFld.setText(vo.getName());
		moneyFld.setText(String.valueOf(vo.getMoney()));
		moneyFld.setEditable(false);
		 submitBtn.removeActionListener(add);
		 submitBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				if(nameFld.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请输入账户名称","提示",JOptionPane.CLOSED_OPTION);
				}
				else{
					AccountVO VO=new AccountVO(vo.getName(),vo.getMoney());
					int result=service.modifyAccount(VO,nameFld.getText());
				if(result==0){
					JOptionPane.showMessageDialog(null,"修改账户成功！","提示",JOptionPane.CLOSED_OPTION);
				}else{
					JOptionPane.showMessageDialog(null,"账户重名，修改账户失败！","提示",JOptionPane.WARNING_MESSAGE);
				}
				}
				Update();
					
				}
			});
		 
		
	}

}
