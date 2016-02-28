package Presentation.promotionui.GiftPromotion;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import po.MemberPO.MemberLevel;
import po.PromotionPO.PromotionType;
import vo.CommodityVO;
import vo.GiftGoodsProVO;
import vo.GoodsVO;
import vo.LogVO;
import Presentation.mainui.ChooseGoodsFatherPane;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import Presentation.promotionui.PromotionPanel;
import Presentation.stockui.ChooseGoodsDialog;
import Presentation.uihelper.DateChooser;
import businesslogic.promotionbl.promotionController;
import businesslogicservice.promotionblservice.PromotionBLService;

public class AddGiftPanel extends ChooseGoodsFatherPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JTextField limitFld;
	DateChooser from, to;
	JScrollPane jsp;
	JTable table;
	AddGiftModel btm;
	double totalMoney;
	JComboBox<String> memberGradeBox;
	ArrayList<Double> last_bid = new ArrayList<Double>();
	JButton submitBtn, exitBtn, addGoodsBtn, delGoodsBtn;
	PromotionBLService service;
	submitListener slisten;
	JLabel title;
	String id="";
	public AddGiftPanel(MainFrame myFather) throws Exception {
		parent =myFather;
		service = new promotionController();
		id = service.getNewID(PromotionType.GIFTGOODS);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 45, 10, 45);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		title = new JLabel("制定赠品赠送策略");
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
		// ---------function---------------------
		JPanel mPnl = new JPanel();
		mPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 6;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(mPnl, c);
		this.add(mPnl);
		GridBagLayout bl = new GridBagLayout();
		GridBagConstraints bc = new GridBagConstraints();
		mPnl.setLayout(bl);
		// --------------------------------------
		bc.fill = GridBagConstraints.BOTH;
		btm = new AddGiftModel();
		table = new JTable(btm);
		table.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		bc.gridx = 0;
		bc.gridy = 0;
		bc.gridheight = 5;
		bc.gridwidth = 3;
		bc.weightx = 10;
		bc.weighty = 10;
		bl.setConstraints(jsp, bc);
		mPnl.add(jsp);

		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		btm.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int i = e.getLastRow();
				int j = e.getColumn();
				if (j == 4 && i != cmContent.size() - 1) {
					int num = Integer.parseInt((String) table.getValueAt(i, j));
					double p = Double.parseDouble((String) table.getValueAt(i,
							j - 1));
					double t = Double.parseDouble((String) table.getValueAt(i,
							j + 1));
					int n = ((int) (t / p));
					table.setValueAt(num * p + "", i, j + 1);
					int index = cmContent.size() - 1;
					int total = Integer.parseInt(cmContent.get(index).get(4));
					double money = Double.parseDouble(cmContent.get(index).get(
							5));
					table.setValueAt(total - n + num + "", index, 4);
					table.setValueAt(money - t + num * p + "", index, 5);

				}

			}

		});
		// ---------增加商品和删除商品按钮---------
		JPanel gPnl = new JPanel();
		gPnl.setLayout(new GridLayout(1, 2));
		addGoodsBtn = new JButton("添加商品");
		addGoodsBtn.setFont(font);
		addGoodsBtn.setBackground(Color.white);
		addGoodsBtn.setFocusPainted(false);
		addGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog addGoodsDlg = new ChooseGoodsDialog(AddGiftPanel.this);
			}
		});
		gPnl.add(addGoodsBtn);
		delGoodsBtn = new JButton("删除商品");
		delGoodsBtn.setFont(font);
		delGoodsBtn.setBackground(Color.white);
		delGoodsBtn.setFocusPainted(false);
		delGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] row = table.getSelectedRows();
				if (row.length > 0) {
					for (int i = 0; i < row.length; i++) {
						if(row[i]==cmContent.size()-1){
							JOptionPane.showMessageDialog(null, "我是统计数据怎么能删我呢！");
						}else{
							cmContent.remove(row[i]);
							last_bid.remove(row[i]);
							table.revalidate();
						}
					}
					cmContent.remove(cmContent.size() - 1);
					addTotal();
					table.clearSelection();

				} else
					JOptionPane.showMessageDialog(null, "请选择要删除的商品", "提示",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		gPnl.add(delGoodsBtn);
		// -----------------------------------
		bc.gridx = 0;
		bc.gridy = 6;
		bc.gridheight = 1;
		bc.gridwidth = 2;
		bc.weightx = 0.1;
		bc.weighty = 0.1;
		bl.setConstraints(gPnl, bc);
		mPnl.add(gPnl);
		// --------right-----------------------
		JPanel rightPnl = new JPanel();
		rightPnl.setBackground(Color.white);
		bc.gridx = 3;
		bc.gridy = 0;
		bc.gridheight = 8;
		bc.gridwidth = 2;
		bc.weightx = 6;
		bc.weighty = 30;
		bl.setConstraints(rightPnl, bc);
		mPnl.add(rightPnl);
		rightPnl.setLayout(new GridLayout(6, 1));
		// ---------起止时间---------------------
		JPanel timePnl = new JPanel();
		timePnl.setBackground(Color.white);
		rightPnl.add(timePnl);
		JPanel fP = new JPanel();
		fP.setBackground(Color.white);
		fP.add(new JLabel("起始于"));
		from = new DateChooser();
		fP.add(from);
		timePnl.add(fP);
		JPanel tP = new JPanel();
		tP.setBackground(Color.white);
		tP.add(new JLabel("截止于"));
		to = new DateChooser();
		tP.add(to);
		timePnl.add(tP);
		// ----------客户等级限制-----------------
		JPanel gradePnl = new JPanel();
		gradePnl.setBackground(Color.white);
		JLabel gradeLbl = new JLabel("客户等级限制：");
		gradeLbl.setFont(font);
		gradePnl.add(gradeLbl);
		String boxText[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE" };
		memberGradeBox = new JComboBox<String>(boxText);
		memberGradeBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		memberGradeBox.setBackground(Color.white);
		gradePnl.add(memberGradeBox);
		rightPnl.add(gradePnl);
		// ---------满赠金额设置------------------
		JPanel limitPnl = new JPanel();
		limitPnl.setBackground(Color.white);
		rightPnl.add(limitPnl);
		JLabel limitLbl = new JLabel("满赠金额：");
		limitLbl.setFont(new Font("微软雅黑", Font.BOLD, 15));
		limitPnl.add(limitLbl);
		limitFld = new JTextField(11);
		limitFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		limitPnl.add(limitFld);
		// ---------buttons--------------------
		rightPnl.add(new JLabel());
		rightPnl.add(new JLabel());
		JPanel btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		slisten = new submitListener();
		submitBtn.addActionListener(slisten);
		btnPnl.add(submitBtn);
		btnPnl.add(new JLabel("           "));
		exitBtn = new JButton("取消");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		btnPnl.add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					update();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rightPnl.add(btnPnl);
	}

	class AddGiftModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "商品编号", "商品名", "型号", "单价", "数量", "总计" };

		public int getRowCount() {
			return cmContent.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public boolean isCellEditable(int row, int col) {
			if (col == 4 && row != cmContent.size() - 1)
				return true;
			else
				return false;
		}

		public void setValueAt(Object value, int row, int col) {
			try {

				double n = Double.parseDouble((String) value);

				if (n <= 0)
					JOptionPane.showMessageDialog(null, "请输入合法数值");
				else {
					cmContent.get(row).set(col, (String) value);
					fireTableCellUpdated(row, col);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "请输入数量");
			}

		}

		public Object getValueAt(int row, int col) {
			return cmContent.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {
			cmContent.add(v);
		}

		public void removeRow(int row) {
			cmContent.remove(row);
		}
	}

	// 商品编号","商品名","型号","默认单价","数量",总计}
	public void RefreshCTable(ArrayList<Object> vo) {
		if (cmContent.size() != 0)
			cmContent.remove(cmContent.size() - 1);
		for (int i = 0; i < vo.size(); i++) {
			GoodsVO gvo = (GoodsVO) vo.get(i);
			ArrayList<String> line = new ArrayList<String>();
			int exist = find(gvo.getGoodsID());
			if (exist < 0) {
				line.add(gvo.getGoodsID());
				line.add(gvo.getName());
				line.add(gvo.getSize());
				line.add(gvo.getPrice() + "");
				line.add("1");
				line.add(gvo.getPrice() + "");
				last_bid.add(gvo.getLastPurchasePrice());
				cmContent.add(line);
			} else {
				int num = Integer.parseInt(cmContent.get(exist).get(4)) + 1;
				double p = Double.parseDouble(cmContent.get(exist).get(5));
				cmContent.get(exist).set(4, num + "");
				cmContent.get(exist).set(5, num * gvo.getPrice() + "");
			}

		}

		addTotal();

	}
	
	public void RefreshTable(ArrayList<CommodityVO> list){
		for(int i=0;i<list.size();i++){
			CommodityVO com=list.get(i);
			ArrayList<String> line = new ArrayList<String>();
			line.add(com.getID());
			line.add(com.getName());
			line.add(com.getType());
			line.add(com.getPrice() + "");
			line.add(com.getNum()+"");
			line.add(com.getTotal() + "");
			last_bid.add(com.getLast_bid());
			cmContent.add(line);
			
		}
		addTotal();	
	}

	private int find(String id) {
		for (int i = 0; i < cmContent.size(); i++) {
			if (id.equals(cmContent.get(i).get(0)))
				return i;
		}
		return -1;
	}

	public void update() throws Exception {
		PromotionPanel proPanel = new PromotionPanel((MainFrame) parent);
		((MainFrame) parent).setRightComponent(proPanel);
		service = new promotionController();
		if (service.Show() != null)
			proPanel.RefreshProTable(service.Show());
	}
	
	public GiftGoodsProVO  getGiftPro(){
		ArrayList<CommodityVO> cmlist = new ArrayList<CommodityVO>();
		for (int j = 0; j < table.getRowCount() - 1; j++) {
			ArrayList<String> line = cmContent.get(j);
			double cost = Double.parseDouble(line.get(4)) * last_bid.get(j);
			CommodityVO cv = new CommodityVO(line.get(0), line.get(1),
					line.get(2), Double.parseDouble(line.get(3)),
					last_bid.get(j), Integer.parseInt(line.get(4)),
					Double.parseDouble(line.get(5)), cost, "");
			cmlist.add(cv);
		}
		String startDate = from.getDate();
		String endDate = to.getDate();
		MemberLevel level = MemberLevel.valueOf((String) memberGradeBox
				.getSelectedItem());
		
		GiftGoodsProVO vo = new GiftGoodsProVO(id, startDate, endDate,
				level, cmlist, Double.parseDouble(limitFld.getText()));
		return vo;
	}

	class submitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(from.getDate().compareTo(to.getDate())>0)
				JOptionPane.showMessageDialog(null, "促销时间段输入不合法！", "提示",
						JOptionPane.WARNING_MESSAGE);
			else if(limitFld.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入满赠总价！", "提示",
						JOptionPane.WARNING_MESSAGE);
			else if(cmContent.size()==0){
				JOptionPane.showMessageDialog(null, "请选择赠品！", "提示",
						JOptionPane.WARNING_MESSAGE);
			}else{
				try{
					double p=Double.parseDouble(limitFld.getText());
					if(p<0)
						JOptionPane.showMessageDialog(null, "请输入不小于0的满赠总价！", "提示",
								JOptionPane.WARNING_MESSAGE);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null, "满赠总价请输入数值！", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
					
			GiftGoodsProVO vo=getGiftPro();
			if (service.Add(vo) == 0) {
				JOptionPane.showMessageDialog(null, "策略添加成功", "提示",
						JOptionPane.WARNING_MESSAGE);
				try {
					update();
					log.addLog(new LogVO(log.getdate(), parent.getUser()
							.getID(), parent.getUser().getName(), "创建一条赠品促销策略",
							4));
					headPane.RefreshGrades();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null, "添加失败", "提示",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	// 商品编号","商品名","型号","默认单价","数量",总计}
	public void addTotal() {
		// int size=cmContent.size();
		int total = 0;
		double money = 0;
		ArrayList<String> line = new ArrayList<String>();
		// cmContent.remove(size-1);
		if (cmContent.size() != 0) {
			for (int i = 0; i < cmContent.size(); i++) {
				line = cmContent.get(i);
				total += Integer.parseInt(line.get(4));
				money += Double.parseDouble(line.get(5));

			}
			ArrayList<String> tail = new ArrayList<String>();
			tail.add("赠品总值");
			tail.add("");
			tail.add("");
			tail.add("");
			tail.add("" + total);
			tail.add("" + money);
			cmContent.add(tail);
			table.validate();
		}

	}
}
