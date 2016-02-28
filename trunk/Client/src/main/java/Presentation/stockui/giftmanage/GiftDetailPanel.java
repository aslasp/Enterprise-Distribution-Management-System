package Presentation.stockui.giftmanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import vo.GiftVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import businesslogic.stockbl.gift.GiftCommodityListModel;
import businesslogic.stockbl.gift.GiftController;
import businesslogicservice.stockblservice.giftblservice.GiftBLService;

public class GiftDetailPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JScrollPane jsp;
	JTable table;
	GiftCommodityListModel gcm;
	JLabel memberLbl;
	JButton exitBtn;
	MainFrame father;
	JPanel btnPnl;

	public GiftDetailPanel(String id, MainFrame frame) {
		GiftBLService giftController = null;
		try {
			giftController = new GiftController();
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		GiftVO giftVO=giftController.findByID(id);
		father = frame;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		JLabel title = new JLabel("库存赠送单");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 2;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0.1;
		gbl.setConstraints(titlePnl, cons);
		this.add(titlePnl);
		// ------------------------------------
		cons.fill = GridBagConstraints.BOTH;
		JPanel memberPnl = new JPanel();
		memberPnl.setBackground(Color.white);
		memberLbl = new JLabel("客户:" + giftVO.getMemberID() + " "
				+ giftVO.getMemberName());
		memberLbl.setFont(font);
		memberPnl.add(memberLbl);
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0.1;
		gbl.setConstraints(memberPnl, cons);
		this.add(memberPnl);
		// ---------table--------------------------
		GiftCommodityListModel gcm = new GiftCommodityListModel(
				giftVO.getGiftList());

		table = new JTable(gcm);
		table.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 5;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		gbl.setConstraints(jsp, cons);
		this.add(jsp);
		// -------buttons-----------------
		btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridheight = 2;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0.1;
		gbl.setConstraints(btnPnl, cons);
		this.add(btnPnl);
		exitBtn = new JButton("返回");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				father.setRightComponent(new GiftPanel(father));

			}
		});
		btnPnl.add(exitBtn);
	}
	
	public void useToReceipt() {
		// TODO Auto-generated method stub
	
		btnPnl.remove(exitBtn);
		
		
	}
}
