package Presentation.stockui.goodsmanage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vo.GoodsVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import Presentation.stockui.StockMessage;
import Presentation.uihelper.UIhelper;

public class DelGoodsDialog extends JDialog {

	/**
	 * 取消按钮要做麽==_yan
	 */
	private static final long serialVersionUID = 1L;
	JButton submitBtn;
	JLabel textLbl;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int dlgWidth = screenWidth * 25 / 100;
	int dlgHeight = screenHeight * 25 / 100;
	Container pnl;
	GoodsVO vo;

	public DelGoodsDialog(GoodsVO vo) {
		this.vo = vo;
		pnl = this.getContentPane();
		pnl.setBackground(Color.white);
		pnl.setLayout(null);
		// ------------------textLbl------------------------------------
		textLbl = new JLabel("确定要删除这个商品吗？");
		textLbl.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textLbl.setBounds(dlgWidth * 28 / 100, dlgHeight * 20 / 100,
				dlgWidth * 80 / 100, dlgHeight * 16 / 100);
		pnl.add(textLbl);
		// -------------------submitBtn---------------------------------------------
		submitBtn = new JButton("确定删除");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		submitBtn.setBackground(new Color(251, 147, 121));
		submitBtn.setBounds(dlgWidth * 35 / 100, dlgHeight * 58 / 100,
				dlgWidth * 30 / 100, dlgHeight * 16 / 100);
		submitBtn.setFocusPainted(false);
		submitBtn.addActionListener(new SubmitBtnListener());
		pnl.add(submitBtn);
		//
		this.setTitle("删除确认");
		this.setBounds((screenWidth - dlgWidth) / 2,
				(screenHeight - dlgHeight) / 2, dlgWidth, dlgHeight);

		this.setResizable(false);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	class SubmitBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			StockGoodsBLService controller = new GoodsController();
			int result = controller.deleteGoods(vo);
			DelGoodsDialog.this.dispose();
			if (result != 0) {
				String stringResult = StockMessage.getStringResult(result);
				JOptionPane.showMessageDialog(null, stringResult, null,
						JOptionPane.WARNING_MESSAGE);
			}

		}

	}
}
