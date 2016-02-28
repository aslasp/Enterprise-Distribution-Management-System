package Presentation.promotionui.BarginPromotion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import po.MemberPO.MemberLevel;
import po.PromotionPO.PromotionType;
import vo.CommodityVO;
import vo.LogVO;
import vo.PackProVO;
import vo.PackVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;


public class ModBarginPanel extends AddBarginPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PackProVO vo;
	ArrayList<Object> content=new ArrayList<Object>();
	
	public ModBarginPanel(String ID,MainFrame myFather) throws Exception {
		super(myFather);
		vo=(PackProVO)service.find(ID, PromotionType.PACK);
		//------------------
		id=vo.getId();
		title.setText("修改特价包促销策略");
		from.setTime(vo.getStartDate());	
		to.setTime(vo.getEndDate());
		IsPackValid=true;
		memberGradeBox.setSelectedItem(vo.getMemberlevel().toString());
		defaultTotalLbl.setText("原价:"+vo.getTotalValue()+"元");
		priceFld.setText(String.valueOf(vo.getPackValue()));
		content.addAll(vo.getPack().getCombine());
		RefreshCTable(content);
		submitBtn.removeActionListener(slisten);
		submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(from.getDate().compareTo(to.getDate())>0)
					JOptionPane.showMessageDialog(null, "促销时间段输入不合法！", "提示",
							JOptionPane.WARNING_MESSAGE);
				else if(!IsPackValid)
					JOptionPane.showMessageDialog(null, "请输入特价包特价！", "提示",
							JOptionPane.WARNING_MESSAGE);
				else{
				    PackProVO vo=getPackPro();
					if (service.Modify(vo) == 0) {
						JOptionPane.showMessageDialog(null, "策略修改成功", "提示",
								JOptionPane.WARNING_MESSAGE);
						try {
							log.addLog(new LogVO(log.getdate(), parent.getUser()
								.getID(), parent.getUser().getName(),
								"修改一条特价包促销策略", 3));
							headPane.RefreshGrades();
							update();
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
