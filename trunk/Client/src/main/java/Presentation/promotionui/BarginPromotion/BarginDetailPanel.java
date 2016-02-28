package Presentation.promotionui.BarginPromotion;

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
import vo.GiftCouponProVO;
import vo.GoodsVO;
import vo.PackProVO;
import businesslogic.promotionbl.promotionController;
import businesslogicservice.promotionblservice.PromotionViewService;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.promotionui.PromotionPanel;

public class BarginDetailPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame father;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JButton exitBtn;
	JScrollPane jsp;
	JTable table;
	BarginModel btm;
	JLabel defaultTotalLbl, priceLbl, gradeLbl, fromLbl, toLbl;
	ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
	PromotionViewService service;
	PackProVO vo;
	double totalMoney, barginMoney;

	public BarginDetailPanel(MainFrame frame,String id) throws Exception {
		father = frame;
		service=new promotionController();
		vo=service.pFindByID(id);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		JLabel title = new JLabel("查看特价包详情");
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
		// --------起止时间，等级限制，原价与现价-----------------
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
		gradeLbl = new JLabel("等级限制："+vo.getMemberlevel().toString());
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
		// -------价格--------------
		JPanel moneyPnl = new JPanel();
		moneyPnl.setBackground(Color.white);
		moneyPnl.setLayout(new GridLayout(1, 5));
		//
		totalMoney=vo.getTotalValue();
		barginMoney=vo.getPackValue();
		moneyPnl.add(new JLabel());
		defaultTotalLbl = new JLabel("原价:" + totalMoney + "元");
		defaultTotalLbl.setFont(font);
		moneyPnl.add(defaultTotalLbl);
		moneyPnl.add(new JLabel());
		JLabel priceLbl = new JLabel("定价：" + barginMoney + "元");
		priceLbl.setFont(font);
		moneyPnl.add(priceLbl);
		moneyPnl.add(new JLabel());
		//
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weightx = 0.000001;
		c.weighty = 0.04;
		gbl.setConstraints(moneyPnl, c);
		this.add(moneyPnl);
		// -------表格-------------------

		btm = new BarginModel();
		table = new JTable(btm);
		table.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 4;
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
		RefreshCTable(vo.getPack().getCombine());
		exitBtn = new JButton("返回");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(166, 210, 121));
		btnPnl.add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromotionPanel ppanel=new PromotionPanel(father);
				father.setRightComponent(ppanel);
				if(service.Show()!=null)
					ppanel.RefreshProTable(service.Show());
					
			}
		});
	}
	
	public void RefreshCTable(ArrayList<CommodityVO> vo) {
		for (int i = 0; i < vo.size(); i++) {
			CommodityVO gvo=vo.get(i);
			ArrayList<String> line = new ArrayList<String>();
				line.add(gvo.getID());
				line.add(gvo.getName());
				line.add(gvo.getType());
				line.add(gvo.getNum()+"");
				line.add(gvo.getPrice() + "");
				line.add(gvo.getTotal() + "");
				
				content.add(line);
		}
	}

	class BarginModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "商品编号", "商品名", "型号", "数量", "默认单价", "总价" };

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
}
