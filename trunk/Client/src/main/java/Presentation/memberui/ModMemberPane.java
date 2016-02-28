package Presentation.memberui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.memberbl.MemAccountInfo;
import businesslogic.memberbl.MemBaseInfo;
import businesslogic.memberbl.MemContactInfo;
import po.MemberPO.MemberType;
import vo.MemberVO;
import Presentation.mainui.MainFrame;

public class ModMemberPane extends AddMemberPanel {
	MainFrame parent;
	MemberVO vo;
	JLabel levelbl;
	public ModMemberPane(String id,MainFrame frame) throws Exception{
		super(frame);
		parent=frame;
		vo=service.findById(id);
		title.setText("修改客户");
		IDLbl.setText("编号："+vo.getMemberID());
		typePnl.remove(typeCbox);
		//typeCbox.removeItemListener(typel);
		String memberType="";
		switch(vo.getmType().toString()){
		case "JHS":memberType="进货商";break;
		case "XSS":memberType="销售商";break;
		default:memberType="未知";
		}
		typeLbl.setText("类型:"+memberType);
		
		levelbl=new JLabel("客户等级:"+vo.getMemberID().toString());
		
		nameFld.setText(vo.getName());
		phoneFld.setText(vo.getTel());
		 addressFld.setText(vo.getAddress()); 
		 postcodeFld.setText(vo.getPostcode());
		 EMailFld.setText(vo.getEMail());
		 defaultClerkFld.setText(vo.getDefaultClerk());
		 submitBtn.removeActionListener(add);
		 submitBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					MemBaseInfo bInfo=new MemBaseInfo(vo.getmType(),vo.getmLevel(),vo.getMemberID(),nameFld.getText(),vo.getPoints(),defaultClerkFld.getText());
					MemContactInfo cInfo=new MemContactInfo(phoneFld.getText(), addressFld.getText(),
							postcodeFld.getText(),  EMailFld.getText());
					MemAccountInfo aInfo=new MemAccountInfo(vo.getMaxOwe(),vo.getToReceive(),vo.getToPay());
				
					
					MemberVO VO=new MemberVO(bInfo,aInfo,cInfo);
					int result=service.modifyMember(VO);
				if(result==0){
					JOptionPane.showMessageDialog(null,"修改客户成功！","提示",JOptionPane.CLOSED_OPTION);
				}else{
					JOptionPane.showMessageDialog(null,"修改客户失败！","提示",JOptionPane.WARNING_MESSAGE);
				}
				Update();
					
				}
			});
	
		 
		
	}

}
