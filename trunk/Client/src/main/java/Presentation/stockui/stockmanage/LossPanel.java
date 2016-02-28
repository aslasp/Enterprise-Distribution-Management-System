package Presentation.stockui.stockmanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import po.ReceiptPO.ReceiptType;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;
import vo.GoodsVO;
import vo.LogVO;
import vo.StockOverOrLowVO;
import Presentation.mainui.ChooseGoodsFatherPane;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import Presentation.stockui.ChooseGoodsDialog;

public class LossPanel extends ChooseGoodsFatherPane implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton submitBtn, exitBtn, addGoodsBtn;
	JTextField exactNumFld;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JLabel IDLbl, nameLbl, sizeLbl, numLbl;
	GoodsVO goodsVO;
	JLabel title;
	JPanel btnPnl;

	public LossPanel(MainFrame frame) {
		parent = frame;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		title = new JLabel("库存报损");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.08;
		gbl.setConstraints(titlePnl, c);
		this.add(titlePnl);
		// --------中间-------------------------
		c.fill = GridBagConstraints.BOTH;
		JPanel mid = new JPanel();
		mid.setBackground(Color.white);
		mid.setLayout(new GridLayout(7, 1));
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 6;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(mid, c);
		this.add(mid);
		mid.add(new JLabel());
		// ---------商品编号-----------------
		JPanel IDPnl = new JPanel();
		IDPnl.setBackground(Color.white);
		mid.add(IDPnl);
		IDLbl = new JLabel("商品编号：_______________");
		IDLbl.setFont(font);
		IDPnl.add(IDLbl);
		// --------商品名-------------------
		JPanel namePnl = new JPanel();
		namePnl.setBackground(Color.white);
		mid.add(namePnl);
		nameLbl = new JLabel("商品名：_________");
		nameLbl.setFont(font);
		namePnl.add(nameLbl);
		// --------型号--------------------
		JPanel sizePnl = new JPanel();
		sizePnl.setBackground(Color.white);
		mid.add(sizePnl);
		sizeLbl = new JLabel("型号：_______");
		sizeLbl.setFont(font);
		sizePnl.add(sizeLbl);
		// -------库存数量-------------------
		JPanel numPnl = new JPanel();
		numPnl.setBackground(Color.white);
		mid.add(numPnl);
		numLbl = new JLabel("库存数量：_____");
		numLbl.setFont(font);
		numPnl.add(numLbl);
		// --------报损数量-----------------
		JPanel lossPnl = new JPanel();
		lossPnl.setBackground(Color.white);
		mid.add(lossPnl);
		JLabel lossLbl = new JLabel("实际数量：");
		lossLbl.setFont(font);
		lossPnl.add(lossLbl);
		exactNumFld = new JTextField(6);
		exactNumFld.setFont(font);
		lossPnl.add(exactNumFld);
		// -------buttons-----------------
		btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 8;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		//
		addGoodsBtn = new JButton("添加商品");
		addGoodsBtn.setFont(font);
		addGoodsBtn.setBackground(Color.white);
		addGoodsBtn.setFocusPainted(false);
		addGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChooseGoodsDialog(LossPanel.this);
				if (goodsVO != null) {
					IDLbl.setText("商品编号：" + goodsVO.getGoodsID());
					nameLbl.setText("商品名：" + goodsVO.getName());
					sizeLbl.setText("型号：" + goodsVO.getSize());
					numLbl.setText("库存数量：" + goodsVO.getNumInStock());
				} else {
					return;
				}
			}
		});
		btnPnl.add(addGoodsBtn);
		submitBtn = new JButton("确定");
		submitBtn.addActionListener(this);
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		btnPnl.add(submitBtn);
		exitBtn = new JButton("取消");
		exitBtn.addActionListener(this);
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		btnPnl.add(exitBtn);
	}

	public void RefreshCTable(ArrayList<Object> VO) {
		goodsVO = (GoodsVO) VO.get(0);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getActionCommand().equals("确定")) {
			// 检测是否选择了商品
			if (goodsVO == null) {
				JOptionPane.showMessageDialog(null, "         请选择报损商品!", null,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 监测实际库存是否填写合格
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

			if (exactNum >= goodsVO.getNumInStock()) {
				JOptionPane.showMessageDialog(null, "       您确定当前是库存报损嘛？",
						null, JOptionPane.WARNING_MESSAGE);
				return;
			}

			StockOverOrLowVO vo = new StockOverOrLowVO("", parent.getUser()
					.getID(), ReceiptType.STOCKLOW, 3, 0, "",
					goodsVO.getName(), goodsVO.getSize(),
					goodsVO.getNumInStock(), exactNum);
			StockControlBLService controller = new StockControlController();
			controller.addStockOverOrLow(vo);

			StockPanel sp = new StockPanel(parent);
			sp.tab.setSelectedIndex(2);
			parent.setRightComponent(sp);

			log.addLog(new LogVO(log.getdate(), parent.getUser().getID(),
					parent.getUser().getName(), "创建一笔库存报溢/报损单", 3));
			try {
				headPane.RefreshGrades();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("取消")) {
			StockPanel sp = new StockPanel(parent);
			sp.tab.setSelectedIndex(2);
			parent.setRightComponent(sp);
		}
	}
}
