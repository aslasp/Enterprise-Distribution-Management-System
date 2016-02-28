package Presentation.receiptui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import po.ReceiptPO.ReceiptType;
import Presentation.financeui.moneyreceipt.ModifyCashlistPanel;
import Presentation.financeui.moneyreceipt.ModifyCollectionPanel;
import Presentation.financeui.moneyreceipt.ModifyPaymentPanel;
import Presentation.mainui.MainFrame;
import Presentation.salesui.manage.purchase.ModPurchasePanel;
import Presentation.salesui.manage.purchase.ModPurchaseReturnPanel;
import Presentation.salesui.manage.sale.ModSalePanel;
import Presentation.salesui.manage.sale.ModSaleReturnPanel;
import Presentation.stockui.stockmanage.ModLossPanel;
import Presentation.stockui.stockmanage.ModOverPanel;
import businesslogic.receiptbl.ReceiptController;
import businesslogicservice.receiptblservice.ReceiptBLService;

public class AdvancedReceiptPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel infoPnl, btnPnl;
	static MainFrame father;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	Color color = new Color(115, 46, 126);
	//
	String id;
	MyButton approvedBtn, disapprovedBtn, modBtn;
	JButton exitBtn;
	ReceiptType type;
	JPanel exitPnl;

	public AdvancedReceiptPanel(JPanel info, MainFrame frame, String id,
			ReceiptType type) {
		infoPnl = info;
		father = frame;
		this.id = id;
		this.type = type;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 10, 5, 10);

		this.setBackground(Color.white);
		this.setLayout(gbl);
		// ----------------------------
		c.fill = GridBagConstraints.HORIZONTAL;
		btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		// --------通过-----------------
		approvedBtn = new MyButton("通过", new ImageIcon(
				"img/promotion/approved.png"));
		btnPnl.add(approvedBtn);
		approvedBtn.addActionListener(this);
		// -------不通过------------------
		disapprovedBtn = new MyButton("不通过", new ImageIcon(
				"img/promotion/disapproved.png"));
		btnPnl.add(disapprovedBtn);
		disapprovedBtn.addActionListener(this);
		// --------修改-------------------
		modBtn = new MyButton("修改", new ImageIcon("img/promotion/modify.png"));
		btnPnl.add(modBtn);
		modBtn.addActionListener(this);
	/*	boolean isAbleMod = type != ReceiptType.STOCKERROR
				&& type != ReceiptType.STOCKLOW
				&& type != ReceiptType.STOCKOVER && type != ReceiptType.GIFT;
		if (!isAbleMod)*/
			
		// -------------------------------
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(infoPnl, c);
		this.add(infoPnl);
		// --------exitPnl------------
		exitPnl = new JPanel();
		exitPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 7;
		c.gridheight = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(exitPnl, c);
		this.add(exitPnl);
		exitBtn = new JButton("返回");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitPnl.add(exitBtn);
		exitBtn.addActionListener(this);
	}

	class MyButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		MyButton(String text, Icon icon) {
			super(text, icon);
			this.setFont(font);
			this.setForeground(color);
			this.setBorderPainted(false);
			this.setBackground(Color.white);
			this.setFocusPainted(false);
		}

		MyButton(Icon icon) {
			super(icon);
			this.setBorderPainted(false);
			this.setBackground(Color.white);
			this.setFocusPainted(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			ReceiptBLService service = new ReceiptController();
			if (e.getSource() == exitBtn) {
				update();

			} else if (e.getSource() == approvedBtn) {
				int result=service.Approve(id, 2) ;
				if (result!= 0){			
					String tip=Total.getFailReason(type, result);
					JOptionPane.showMessageDialog(null,tip+ "审批失败！", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
				else
					update();
			} else if (e.getSource() == disapprovedBtn) {
				if (service.Approve(id, 1) != 0)
					JOptionPane.showMessageDialog(null, "审批失败！", "提示",
							JOptionPane.WARNING_MESSAGE);
				else
					update();

			} else if (e.getSource() == modBtn) {
				modOkListener ok = new modOkListener();
				JPanel pane = getModPanel(id, type, ok, false,father);

				if (pane != null) {
					AdvancedReceiptPanel advance = new AdvancedReceiptPanel(
							pane, father, id, type);
					advance.btnPnl.remove(advance.modBtn);
					father.setRightComponent(advance);
					advance.remove(advance.exitPnl);
				} 

			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void update() {
		ReceiptMgrPanel pane;
		try {
			pane = new ReceiptMgrPanel(father);
			father.setRightComponent(pane);
			pane.Refresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class modOkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JPanel pane = ReceiptMgrPanel.getRightAdvancePanel(id, type);
			father.setRightComponent(new AdvancedReceiptPanel(pane, father, id,
					type));

		}

	}

	public static JPanel getModPanel(String id, ReceiptType type,
			ActionListener ok, boolean isRed,MainFrame father) throws Exception {

		switch (type) {
		case SALE:
			ModSalePanel sale = new ModSalePanel(id, father);
			sale.UseToModify(ok, isRed);
			return sale;

		case COLLECTION:
			ModifyCollectionPanel collection = new ModifyCollectionPanel(id,
					father);
			collection.UseToModify(ok, isRed);
			return collection;
		case PAYMENT:
			ModifyPaymentPanel payment = new ModifyPaymentPanel(id, father);
			payment.UseToModify(ok, isRed);
			return payment;
		case CASHLIST:
			ModifyCashlistPanel cashlist = new ModifyCashlistPanel(id, father);
			cashlist.UseToModify(ok, isRed);
			return cashlist;
		case PURCHASE:
			ModPurchasePanel purchase = new ModPurchasePanel(id, father);
			purchase.UseToModify(ok, isRed);
			return purchase;
		case PURCHASERETURN:
			ModPurchaseReturnPanel purchaseReturn = new ModPurchaseReturnPanel(
					id, father);
			purchaseReturn.UseToModify(ok, isRed);
			return purchaseReturn;
		case SALERETURN:
			ModSaleReturnPanel saleReturn = new ModSaleReturnPanel(id, father);
			saleReturn.UseToModify(ok, isRed);
			return saleReturn;
		case STOCKOVER:
			ModOverPanel over = new ModOverPanel( id,father);
			over.UseToModify(ok, isRed);
			return over;
		case STOCKLOW:
			ModLossPanel low = new ModLossPanel( id,father);
			low.UseToModify(ok, isRed);
			return low;
		case GIFT:
			Presentation.stockui.giftmanage.ModGiftPanel gift = new Presentation.stockui.giftmanage.ModGiftPanel(
					 id,father);
			gift.UseToModify(ok, isRed);
			return gift;
		default:
			return null;
		}

	}
	
	

}
