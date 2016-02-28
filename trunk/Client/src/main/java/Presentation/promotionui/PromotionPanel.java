package Presentation.promotionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import po.PromotionPO.PromotionType;
import vo.PromotionVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.promotionui.BarginPromotion.AddBarginPanel;
import Presentation.promotionui.BarginPromotion.BarginDetailPanel;
import Presentation.promotionui.BarginPromotion.ModBarginPanel;
import Presentation.promotionui.CouponPromotion.AddCouponPanel;
import Presentation.promotionui.CouponPromotion.CouponDetailPanel;
import Presentation.promotionui.CouponPromotion.ModCouponPanel;
import Presentation.promotionui.DiscountPromotion.AddDiscountPanel;
import Presentation.promotionui.DiscountPromotion.DiscountDetailPanel;
import Presentation.promotionui.DiscountPromotion.ModDiscountPanel;
import Presentation.promotionui.GiftPromotion.AddGiftPanel;
import Presentation.promotionui.GiftPromotion.GiftDetailPanel;
import Presentation.promotionui.GiftPromotion.ModGiftPanel;
import Presentation.uihelper.MyDateFormat;
import businesslogic.promotionbl.promotionController;
import businesslogicservice.promotionblservice.PromotionViewService;

public class PromotionPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	MainFrame father;
	JTable proTbl;
	JScrollPane jsp;
	PromotionTableModel ptm;
	ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
	MyButton addBtn, delBtn, modBtn, refreshBtn, detailBtn;
	Color color = new Color(115, 46, 126);

	public PromotionPanel(MainFrame myFather) {
		father = myFather;
		this.setBackground(Color.white);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 20, 10, 20);
		// --------------------------
		JPanel top = new JPanel();
		top.setBackground(Color.white);
		top.setLayout(new GridLayout(1, 5));
		// --------------------------

		addBtn = new MyButton("制定新策略", new ImageIcon("img/promotion/add.png"));
		addBtn.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				JPopupMenu menu = new JPopupMenu();

				menu.setBackground(Color.white);
				menu.setBorder(BorderFactory.createLineBorder(color));
				JMenuItem bargin = new JMenuItem("创建特价包");
				bargin.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				bargin.setOpaque(false);
				bargin.setForeground(color);
				JMenuItem coupon = new JMenuItem("制定代金券赠送策略");
				coupon.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				coupon.setOpaque(false);
				coupon.setForeground(color);
				JMenuItem gift = new JMenuItem("制定赠品赠送策略");
				gift.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				gift.setOpaque(false);
				gift.setForeground(color);
				JMenuItem discount = new JMenuItem("制定打折促销策略");
				discount.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				discount.setOpaque(false);
				discount.setForeground(color);
				menu.add(bargin);
				menu.add(coupon);
				menu.add(gift);
				menu.add(discount);

				bargin.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						try {
							father.setRightComponent(new AddBarginPanel(father));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				coupon.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							father.setRightComponent(new AddCouponPanel(father));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				gift.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							father.setRightComponent(new AddGiftPanel(father));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				discount.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							father.setRightComponent(new AddDiscountPanel(
									father));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});

				menu.show(father, e.getX(), e.getY());
			}

		});
		top.add(addBtn);
		//
		delBtn = new MyButton("删除策略", new ImageIcon("img/promotion/del.png"));
		delBtn.addActionListener(new ActionListener() {
			// 当已匹配时不可删除 若为代金券 则促销时间已过时 匹配与否均可删除
			public void actionPerformed(ActionEvent e) {
				int row = proTbl.getSelectedRow();
				if (row >= 0) {
					ArrayList<String> info = content.get(row);
					String id = info.get(0);
					PromotionType type = getChangeProType.getProType(info
							.get(3));
					try {
						JDialog delDlg = new DelProDialog(id, type, father);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "请选择要删除的促销策略");
			}
		});
		top.add(delBtn);
		//
		modBtn = new MyButton("修改策略", new ImageIcon("img/promotion/modify.png"));
		modBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 该促销已匹配时是不可修改
				try {
					int i = proTbl.getSelectedRow();
					if (i >= 0) {
						PromotionViewService service = new promotionController();
						String id = content.get(i).get(0);
						PromotionType type = getChangeProType
								.getProType(content.get(i).get(3));
						PromotionVO vo = service.find(id, type);
						if (vo.IsMatch())
							JOptionPane.showMessageDialog(null,
									"该促销策略匹配中，暂无法修改！");
						else {
							if (type == PromotionType.DISCOUNT)
								father.setRightComponent(new ModDiscountPanel(
										id, father));
							else if (type == PromotionType.GIFTCOUPON)
								father.setRightComponent(new ModCouponPanel(id,
										father));
							else if (type == PromotionType.PACK)
								father.setRightComponent(new ModBarginPanel(id,
										father));
							else
								father.setRightComponent(new ModGiftPanel(id,
										father));

						}

					} else
						JOptionPane.showMessageDialog(null, "请选择要修改的促销策略");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		top.add(modBtn);
		//
		refreshBtn = new MyButton("刷新列表", new ImageIcon(
				"img/promotion/refresh.png"));
		top.add(refreshBtn);
		//
		detailBtn = new MyButton("查看详情", new ImageIcon(
				"img/promotion/detail.png"));
		detailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = proTbl.getSelectedRow();
					if (i >= 0) {
						String type = content.get(i).get(3);
						String id = content.get(i).get(0);
						if (type.equals("商品赠送"))
							father.setRightComponent(new GiftDetailPanel(
									father, id));
						else if (type.equals("代金券赠送"))
							father.setRightComponent(new CouponDetailPanel(
									father, id));
						else if (type.equals("特价包"))
							father.setRightComponent(new BarginDetailPanel(
									father, id));
						else
							father.setRightComponent(new DiscountDetailPanel(
									father, id));
					} else
						JOptionPane.showMessageDialog(null, "请选择要查看的促销策略!");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		top.add(detailBtn);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(top, c);
		this.add(top);
		// --------table-------------------
		ptm = new PromotionTableModel();
		proTbl = new JTable(ptm);
		proTbl.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < proTbl.getColumnCount(); i++) {
			proTbl.getColumn(proTbl.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(proTbl);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(jsp, c);
		this.add(jsp);
	}

	public void RefreshProTable(ArrayList<PromotionVO> vo) {
		content.clear();
		for (int i = 0; i < vo.size(); i++) {
			ArrayList<String> line = new ArrayList<String>();
			PromotionVO v = vo.get(i);
			line.add(v.getId());
			line.add(MyDateFormat.FomatDate(v.getStartDate()));
			line.add(MyDateFormat.FomatDate(v.getEndDate()));
			line.add(getChangeProType.getProTypeString(v.getType()));
			content.add(line);
		}
		PromotionPanel.this.repaint();
	}

	class PromotionTableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "编号", "促销起始时间", "促销中止时间", "促销策略类型" };

		public int getRowCount() {
			return content.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public Object getValueAt(int row, int col) {
			return content.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}
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
}
