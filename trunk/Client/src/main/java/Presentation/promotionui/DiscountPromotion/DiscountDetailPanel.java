package Presentation.promotionui.DiscountPromotion;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import vo.CommodityVO;
import vo.DiscountProVO;
import vo.GoodsVO;
import businesslogic.promotionbl.promotionController;
import businesslogicservice.promotionblservice.PromotionViewService;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.promotionui.PromotionPanel;

public class DiscountDetailPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	MainFrame father;
	JScrollPane jsp;
	JTable table;
	DiscountModel dm;
	ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
	JLabel fromLbl, toLbl, gradeLbl;
	JButton submitBtn;
	PromotionViewService service;
	DiscountProVO vo;

	public DiscountDetailPanel(MainFrame frame,String id) throws Exception {
		father = frame;
		service=new promotionController();
		vo=service.dtFindByID(id);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 45, 5, 45);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		JLabel title = new JLabel("查看打折促销策略");
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
		// --------起止时间与等级限制-----------------
		JPanel timePnl = new JPanel();
		timePnl.setBackground(Color.white);
		fromLbl = new JLabel("起始于："+vo.getStartDate());
		fromLbl.setFont(font);
		timePnl.add(fromLbl);
		timePnl.add(new JLabel());
		toLbl = new JLabel("截止于："+vo.getEndDate());
		toLbl.setFont(font);
		timePnl.add(toLbl);
		timePnl.add(new JLabel());
		gradeLbl = new JLabel("客户等级限制："+vo.getMemberlevel().toString());
		gradeLbl.setFont(font);
		timePnl.add(gradeLbl);
		//
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.04;
		gbl.setConstraints(timePnl, c);
		this.add(timePnl);
		// -------表格-------------------
		c.fill = GridBagConstraints.BOTH;
		dm = new DiscountModel();
		table = new JTable(dm);
		table.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 10;
		c.weighty = 10;
		gbl.setConstraints(jsp, c);
		this.add(jsp);
		// -------buttons-----------------
		JPanel btnPnl = new JPanel();
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
		RefreshCTable(vo.getGoodsList(),vo.getCountList());
		submitBtn = new JButton("返回");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		btnPnl.add(submitBtn);
		submitBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PromotionPanel ppanel=new PromotionPanel(father);
				father.setRightComponent(ppanel);
				if(service.Show()!=null)
					ppanel.RefreshProTable(service.Show());

			}
		});
	}

	class DiscountModel extends AbstractTableModel {
		/**
		 * 
		 */

		private static final long serialVersionUID = 1L;
		String head[] = { "商品编号", "商品名", "型号", "单价", "折扣", "折后单价" };

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
	
	public void RefreshCTable(ArrayList<CommodityVO> vo, ArrayList<Double> dis) {
		for (int i = 0; i < vo.size(); i++) {
			CommodityVO gvo = (CommodityVO) vo.get(i);
			ArrayList<String> line = new ArrayList<String>();
				line.add(gvo.getID());
				line.add(gvo.getName());
				line.add(gvo.getType());
				line.add(gvo.getPrice() + "");
				line.add(dis.get(i) + "");
				line.add(gvo.getPrice() * dis.get(i) + "");
				content.add(line);
		}
	}
	
}
