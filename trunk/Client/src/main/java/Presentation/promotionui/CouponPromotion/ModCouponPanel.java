package Presentation.promotionui.CouponPromotion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import po.PromotionPO.PromotionType;
import vo.GiftCouponProVO;
import vo.LogVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;

public class ModCouponPanel extends AddCouponPanel{
	

	/**
	 * 
	 */
	//处于代金券编号问题  基本匹配后不可修改 可修改满赠总价和起始时期
	private static final long serialVersionUID = 1L;
	GiftCouponProVO vo;
	public ModCouponPanel(String ID,MainFrame myFather) throws Exception {
		super(myFather);
		vo=(GiftCouponProVO)service.find(ID, PromotionType.GIFTCOUPON);
		title.setText("修改代金券赠送策略");
		from.setTime(vo.getStartDate());
		to.setTime(vo.getEndDate());
		isLimitValid=true;
		id=vo.getId();
		memberGradeBox.setSelectedItem(vo.getMemberlevel().toString());
		limitFld.setText(String.valueOf(vo.getTotalValue()));
		priceFld.setText(String.valueOf(vo.getCouponList().get(0).getValue()));
		priceFld.setEditable(false);
		totalFld.setText(String.valueOf(vo.getCouponList().size()));
		totalFld.setEditable(false);
		//加监听！
		submitBtn.removeActionListener(this);
		submitBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					startDate = from.getDate();
					endDate = to.getDate();
					if(startDate.compareTo(endDate)>0)
						JOptionPane.showMessageDialog(null, "促销时间段输入不合法！", "提示",
								JOptionPane.WARNING_MESSAGE);
					else if(!isLimitValid)
						JOptionPane.showMessageDialog(null, "请输入合法满赠总价！", "提示",
								JOptionPane.WARNING_MESSAGE);
					else{
						GiftCouponProVO vo=getCouponPro();
						if (service.Modify(vo) == 0) {
							JOptionPane.showMessageDialog(null, "策略修改成功", "提示",
								JOptionPane.WARNING_MESSAGE);
							update();
							log.addLog(new LogVO(log.getdate(), father.getUser()
								.getID(), father.getUser().getName(),
								"修改一条代金券促销策略", 2));
							headPane.RefreshGrades();

						} else
							JOptionPane.showMessageDialog(null, "添加失败", "提示",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
}
