package Presentation.stockui.giftmanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.uihelper.DateChooser;
import businesslogic.stockbl.gift.GiftModel;

public class GiftPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame father;
	MyButton giftBtn, refreshBtn, detailBtn;
	DateChooser fromDC, toDC;
	JScrollPane jsp;
	JTable giftTbl;
	GiftModel gm;
	ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
	Color color = new Color(51, 125, 86);
	Font font = new Font("微软雅黑", Font.PLAIN, 15);

	public GiftPanel(MainFrame frame) {
		father = frame;

		//
		this.setBackground(Color.white);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 50, 10, 50);
		// ---------------------------------------
		JPanel topPnl = new JPanel();
		topPnl.setBackground(Color.white);
		// ----------------------------------------
		giftBtn = new MyButton("创建库存赠送单", new ImageIcon("img/stock/gift.png"));
		giftBtn.addActionListener(this);
		topPnl.add(giftBtn);
		detailBtn = new MyButton("查看详情", new ImageIcon("img/stock/detail.png"));
		detailBtn.addActionListener(this);
		topPnl.add(detailBtn);
		refreshBtn = new MyButton("刷新", new ImageIcon("img/stock/refresh.png"));
		refreshBtn.addActionListener(this);
		topPnl.add(refreshBtn);
		// ---------------------------------------------
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(topPnl, c);
		this.add(topPnl);
		// ----------------------------------------------
		gm = new GiftModel();
		giftTbl = new JTable(gm);
		giftTbl.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < giftTbl.getColumnCount(); i++) {
			giftTbl.getColumn(giftTbl.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(giftTbl);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 10;
		c.gridheight = c.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(jsp, c);
		this.add(jsp);
	}

	class MyButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		MyButton(String text, Icon icon) {
			super(text, icon);
			setFont(font);
			setForeground(color);
			setBorderPainted(false);
			setBackground(Color.white);
			setFocusPainted(false);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == giftBtn) {
			father.setRightComponent(new CreateGiftPanel(father));

			gm = new GiftModel();
			giftTbl.setModel(gm);
			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			for (int i = 0; i < giftTbl.getColumnCount(); i++) {
				giftTbl.getColumn(giftTbl.getColumnName(i))
						.setCellRenderer(tcr);
			}
		}
		if (e.getSource() == detailBtn) {
			int rownum = giftTbl.getSelectedRow();
			if (rownum == -1) {
				JOptionPane.showMessageDialog(null, "           请选择一个赠送单",
						null, JOptionPane.WARNING_MESSAGE);
			} else {
				String id = (String) giftTbl.getValueAt(rownum, 0);
				father.setRightComponent(new GiftDetailPanel(id, father));
			}
		}
		if (e.getSource() == refreshBtn) {
			gm = new GiftModel();
			giftTbl.setModel(gm);
			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			for (int i = 0; i < giftTbl.getColumnCount(); i++) {
				giftTbl.getColumn(giftTbl.getColumnName(i))
						.setCellRenderer(tcr);
			}
		}
	}
}
