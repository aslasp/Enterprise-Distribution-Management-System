package Presentation.promotionui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.promotionbl.*;
import businesslogicservice.promotionblservice.PromotionBLService;
import po.PromotionPO.PromotionType;
import vo.PromotionVO;
import Presentation.mainui.MainFrame;
import Presentation.uihelper.MyDateFormat;
import Presentation.uihelper.UIhelper;

public class DelProDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton submitBtn;
	JLabel textLbl;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int dlgWidth = screenWidth * 25 / 100;
	int dlgHeight = screenHeight * 25 / 100;
	Container pnl;
	PromotionType type;
	MainFrame father;
	PromotionBLService service;
	public DelProDialog(final String id,final PromotionType type,final MainFrame father) throws Exception{
		service=new promotionController();
		this.father=father;
		pnl = this.getContentPane();
		pnl.setBackground(Color.white);
		pnl.setLayout(null);
		//
		// ------------------textLbl------------------------------------
		textLbl = new JLabel();
		textLbl.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textLbl.setBounds(dlgWidth * 28 / 100, dlgHeight * 20 / 100,
				dlgWidth * 80 / 100, dlgHeight * 16 / 100);
		pnl.add(textLbl);
		// -------------------submitBtn---------------------------------------------
		submitBtn = new JButton();
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		submitBtn.setBounds(dlgWidth * 35 / 100, dlgHeight * 58 / 100,
				dlgWidth * 30 / 100, dlgHeight * 16 / 100);
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(251, 147, 121));
		pnl.add(submitBtn);
		
		PromotionVO vo=service.find(id, type);
		boolean isInvalidCouponPro=(type==PromotionType.GIFTCOUPON&&
				MyDateFormat.getToday().compareTo(vo.getEndDate())>0);
		if(vo.IsMatch()&&!isInvalidCouponPro){
			textLbl.setText("该促销策略匹配中，暂不能删除！");
			submitBtn.setText("确定");
			submitBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					update();
				}
				
			});
		}
		else{
			textLbl.setText("确定删除促销策略"+id+"!");
			submitBtn.setText("确认删除");
			submitBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					int result=service.Delete(id, type);
					if(result!=0){
						JOptionPane.showMessageDialog(null, "删除失败！");
					}
					update();
				}
			});
		}
		
		//
		this.setTitle("删除确认");
		this.setBounds((screenWidth - dlgWidth) / 2,
				(screenHeight - dlgHeight) / 2, dlgWidth, dlgHeight);

		this.setResizable(false);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void update(){
		DelProDialog.this.dispose();
		PromotionPanel pp=new PromotionPanel(father);
		father.setRightComponent(pp);
		if(service.Show()!=null)
			pp.RefreshProTable(service.Show());
			
		
	}
	
}
