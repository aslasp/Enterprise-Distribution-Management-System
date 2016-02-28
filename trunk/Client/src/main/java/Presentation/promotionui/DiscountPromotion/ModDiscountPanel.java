package Presentation.promotionui.DiscountPromotion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import po.PromotionPO.PromotionType;
import vo.DiscountProVO;
import vo.LogVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;


public class ModDiscountPanel extends AddDiscountPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DiscountProVO vo;
	ArrayList<Object> content=new ArrayList<Object>();
	public ModDiscountPanel(String ID,MainFrame myFather) throws Exception {
		super(myFather);
		vo=(DiscountProVO)service.find(ID, PromotionType.DISCOUNT);
		title.setText("修改打折促销策略");
		id=vo.getId();
		from.setTime(vo.getStartDate());
		to.setTime(vo.getEndDate());
		memberGradeBox.setSelectedItem(vo.getMemberlevel().toString());
		content.addAll(vo.getGoodsList());
		RefreshCTable(content,vo.getCountList());
		//加监听！
		submitBtn.removeActionListener(slisten);;
		submitBtn.removeAll();
		submitBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(from.getDate().compareTo(to.getDate())>0)
					JOptionPane.showMessageDialog(null, "促销时间段输入不合法！", "提示",
							JOptionPane.WARNING_MESSAGE);
				else{
					DiscountProVO vo=getDiscountPro();
					if (service.Modify(vo) == 0) {
						JOptionPane.showMessageDialog(null, "策略修改成功", "提示",
							JOptionPane.WARNING_MESSAGE);
					try {
						update();
						log.addLog(new LogVO(log.getdate(), parent.getUser()
								.getID(), parent.getUser().getName(), "修改一条折扣促销策略",
								2));
						headPane.RefreshGrades();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "修改失败", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
	}
}
