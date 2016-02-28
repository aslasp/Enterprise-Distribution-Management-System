package Presentation.stockui.stockmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vo.LogVO;
import vo.StockOverOrLowVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import businesslogic.stockbl.stockManage.StockOverOrLowManage;
import businesslogic.stockbl.stockManage.StockOverReceipt;

public class ModOverPanel extends OverflowPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StockOverOrLowVO overVo;
	StockOverOrLowManage manage;
	boolean isRed = false;

	public ModOverPanel(String ID, MainFrame frame) {
		super(frame);
		manage = new StockOverOrLowManage();
		overVo = manage.find(ID);
		this.btnPnl.remove(addGoodsBtn);
		IDLbl.setText("单据编号:" + overVo.getId());
		nameLbl.setText("商品名：" + overVo.getGoodsName());
		sizeLbl.setText("型号：" + overVo.getSize());
		numLbl.setText("库存数量：" + String.valueOf(overVo.getNum()));
		exactNumFld.setText(String.valueOf(overVo.getExactNum()));
		submitBtn.removeActionListener(this);
		exitBtn.removeActionListener(this);
	}

	public void UseToModify(ActionListener ok, boolean isRed) {
		submitBtn.addActionListener(ok);
		exitBtn.addActionListener(ok);
		submitBtn.addActionListener(new SubmitActionListener());

		if (isRed) {
			title.setText("制定库存报溢单");
			this.isRed = true;
		}
	}

	class SubmitActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			int exactNum = 0;
			try {
				exactNum = Integer.parseInt(exactNumFld.getText());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "         请确定你的输入合法噢~",
						null, JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (exactNum < 0) {
				JOptionPane.showMessageDialog(null, "       请确定实际库存数量合法噢~",
						null, JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (exactNum <= overVo.getNum()) {
				JOptionPane.showMessageDialog(null, "       您确定当前是库存报溢嘛？",
						null, JOptionPane.WARNING_MESSAGE);
				return;
			}

			StockOverReceipt receipt = new StockOverReceipt(overVo.getId(),
					overVo.getMemberName(), overVo.getMemberID(),
					overVo.getUser(), overVo.getHurry(), overVo.getInfo(),
					overVo.getGoodsName(), overVo.getSize(), overVo.getNum(),
					exactNum);
			if (receipt.Modify(overVo.getId()) == 0) {
				log.addLog(new LogVO(log.getdate(), parent.getUser().getID(),
						parent.getUser().getName(), "修改一笔库存报溢/报损单", 3));
				try {
					headPane.RefreshGrades();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "修改失败", "提示",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
