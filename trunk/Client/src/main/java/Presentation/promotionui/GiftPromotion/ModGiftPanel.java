package Presentation.promotionui.GiftPromotion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import po.PromotionPO.PromotionType;
import vo.GiftGoodsProVO;
import vo.LogVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;


public class ModGiftPanel extends AddGiftPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GiftGoodsProVO vo;
	public ModGiftPanel(String ID,MainFrame myFather) throws Exception {
		super(myFather);
		vo=(GiftGoodsProVO)service.find(ID, PromotionType.GIFTGOODS);
		title.setText("修改赠品赠送策略");
		id=vo.getId();
		from.setTime(vo.getStartDate());
		to.setTime(vo.getEndDate());
		memberGradeBox.setSelectedItem(vo.getMemberlevel().toString());
		limitFld.setText(String.valueOf(vo.getTotalValue()));
		RefreshTable(vo.getGiftList());
		//加监听!
		submitBtn.removeActionListener(slisten);
		submitBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(from.getDate().compareTo(to.getDate())>0)
					JOptionPane.showMessageDialog(null, "促销时间段输入不合法！", "提示",
							JOptionPane.WARNING_MESSAGE);
				else{
				GiftGoodsProVO vo=getGiftPro();
				if (service.Modify(vo) == 0) {
					JOptionPane.showMessageDialog(null, "策略修改成功", "提示",
							JOptionPane.WARNING_MESSAGE);
					try {
						update();
						log.addLog(new LogVO(log.getdate(), parent.getUser()
								.getID(), parent.getUser().getName(), "修改一条赠品促销策略",
								3));
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
