package Presentation.stockui.stockmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vo.StockOverOrLowVO;
import businesslogic.stockbl.stockManage.StockOverOrLowManage;
import Presentation.mainui.MainFrame;

public class OverflowDetailPanel extends OverflowPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StockOverOrLowVO overVo;
	StockOverOrLowManage manage;

	public OverflowDetailPanel(String ID, MainFrame frame) {
		super(frame);
		manage = new StockOverOrLowManage();
		overVo = manage.find(ID);
		this.remove(addGoodsBtn);
		this.remove(submitBtn);
		this.remove(btnPnl);
		IDLbl.setText("单据编号:" + overVo.getId());
		nameLbl.setText("商品名：" + overVo.getGoodsName());
		sizeLbl.setText("型号：" + overVo.getSize());
		numLbl.setText("库存数量：" + String.valueOf(overVo.getNum()));
		exactNumFld.setText(String.valueOf(overVo.getExactNum()));
		exactNumFld.setEditable(false);
		exitBtn.removeActionListener(this);
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});
	}

	public void useToReceipt() {
		btnPnl.remove(exitBtn);
	}
}
