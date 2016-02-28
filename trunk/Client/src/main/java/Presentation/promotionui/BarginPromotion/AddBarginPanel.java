package Presentation.promotionui.BarginPromotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import vo.GoodsVO;
import vo.LogVO;
import vo.PackProVO;
import vo.PackVO;
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

public class AddBarginPanel extends ChooseGoodsFatherPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	PromotionBLService service;
	JComboBox<String> memberGradeBox;
	DateChooser from, to;
	JButton submitBtn, exitBtn, addGoodsBtn, delGoodsBtn;
	JScrollPane jsp;
	JTable table;
	AddBarginModel btm;
	JLabel defaultTotalLbl;
	JTextField priceFld;
	double totalMoney, barginMoney;
	submitListener slisten;
	ArrayList<Double> last_bid = new ArrayList<Double>();
	boolean IsPackValid=false;
	String id="";
	JLabel title;
	public AddBarginPanel(MainFrame myFather) throws Exception {
		parent = myFather;		
		totalMoney = 0;
		barginMoney = 0;
		service = new promotionController();
		id = service.getNewID(PromotionType.PACK);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		title = new JLabel("创建特价包");
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
		JPanel gradePnl = new JPanel();
		gradePnl.setBackground(Color.white);
		timePnl.add(gradePnl);
		JLabel gradeLbl = new JLabel("等级限制：");
		gradeLbl.setFont(font);
		gradePnl.add(gradeLbl);
		String boxText[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE" };
		memberGradeBox = new JComboBox<String>(boxText);
		memberGradeBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		memberGradeBox.setBackground(Color.white);
		gradePnl.add(memberGradeBox);

		//
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.04;
		gbl.setConstraints(timePnl, c);
		this.add(timePnl);
		// ---------------------------------
		JPanel moneyPnl = new JPanel();
		moneyPnl.setBackground(Color.white);
		moneyPnl.setLayout(new GridLayout(1, 5));
		moneyPnl.add(new JLabel());
		defaultTotalLbl = new JLabel("原价:" + totalMoney + "元");
		defaultTotalLbl.setFont(font);
		moneyPnl.add(defaultTotalLbl);

		// ---------------------------------
		JPanel nowPnl = new JPanel();
		nowPnl.setBackground(Color.white);
		JLabel priceLbl = new JLabel("定价：");
		priceLbl.setFont(font);
		nowPnl.add(priceLbl);
		priceFld = new JTextField(8);
		priceFld.setFont(font);
		priceFld.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent e){
				try{
					double price=Double.parseDouble(priceFld.getText());
					if(price>=totalMoney){
						JOptionPane.showMessageDialog(null, "特价必须小于原价！", "提示",
								JOptionPane.WARNING_MESSAGE);
						priceFld.setText("");
						IsPackValid=false;
					}
					else if(price<0){
						JOptionPane.showMessageDialog(null, "请输入合法数值！", "提示",
							JOptionPane.WARNING_MESSAGE);
						priceFld.setText("");
						IsPackValid=false;
					}	
					else{
						IsPackValid=true;
					}
				}catch(Exception err){
					JOptionPane.showMessageDialog(null, "请输入数值！", "提示",
							JOptionPane.WARNING_MESSAGE);
					priceFld.setText("");
					IsPackValid=false;
				}
			}
		});
		nowPnl.add(priceFld);
		moneyPnl.add(nowPnl);
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

		btm = new AddBarginModel();
		table = new JTable(btm);
		table.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		btm.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int i = e.getLastRow();
				int j = e.getColumn();
				if (j == 3) {
					int num = Integer.parseInt((String) table.getValueAt(i, j));
					double t = Double.parseDouble((String) table.getValueAt(i,
							j + 2));
					totalMoney -= t;
					double p = Double.parseDouble((String) table.getValueAt(i,
							j + 1));
					totalMoney += num * p;
					defaultTotalLbl.setText("原价：" + totalMoney + "元");
					table.setValueAt(num * p + "", i, j + 2);
				}

			}

		});
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
		//
		addGoodsBtn = new JButton("添加商品");
		addGoodsBtn.setFont(font);
		addGoodsBtn.setBackground(Color.white);
		addGoodsBtn.setFocusPainted(false);
		addGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog addGoodsDlg = new ChooseGoodsDialog(AddBarginPanel.this);
			}
		});
		btnPnl.add(addGoodsBtn);
		delGoodsBtn = new JButton("删除商品");
		delGoodsBtn.setFont(font);
		delGoodsBtn.setBackground(Color.white);
		delGoodsBtn.setFocusPainted(false);
		delGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] row = table.getSelectedRows();
				if (row.length > 0) {
					for (int i = 0; i < row.length; i++) {
						totalMoney -= Double.parseDouble(cmContent.get(row[i])
								.get(5));
						defaultTotalLbl.setText("原价：" + totalMoney + "元");
						cmContent.remove(row[i]);
						last_bid.remove(row[i]);
						table.revalidate();
						table.clearSelection();
					}
				} else
					JOptionPane.showMessageDialog(null, "请选择要删除的商品", "提示",
							JOptionPane.WARNING_MESSAGE);
			}

		});
		btnPnl.add(delGoodsBtn);
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		btnPnl.add(submitBtn);
		slisten = new submitListener();
		submitBtn.addActionListener(slisten);
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
	}

	class AddBarginModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "商品编号", "商品名", "型号", "数量", "默认单价", "总价" };

		public int getRowCount() {
			return cmContent.size();
		}

		public boolean isCellEditable(int row, int column) {
			if (column == 3)
				return true;
			else
				return false;
		}

		public int getColumnCount() {
			return head.length;
		}

		public void setValueAt(Object value, int row, int column) {

			try {

				double n = Double.parseDouble((String) value);

				if (n <= 0)
					JOptionPane.showMessageDialog(null, "请输入合法数值");
				else {
					cmContent.get(row).set(column, (String) value);
					fireTableCellUpdated(row, column);
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

	public void RefreshCTable(ArrayList<Object> vo) {
		if(vo.get(0)instanceof GoodsVO){
			for (int i = 0; i < vo.size(); i++) {
				GoodsVO gvo = (GoodsVO) vo.get(i);
				ArrayList<String> line = new ArrayList<String>();
				int exist = find(gvo.getGoodsID());
				if (exist < 0) {
					line.add(gvo.getGoodsID());
					line.add(gvo.getName());
					line.add(gvo.getSize());
					line.add("1");
					line.add(gvo.getPrice() + "");
					line.add(gvo.getPrice() + "");
					totalMoney += gvo.getPrice();
					last_bid.add(gvo.getLastPurchasePrice());
					cmContent.add(line);
				} else {
					int num = Integer.parseInt(cmContent.get(exist).get(3)) + 1;
					double p = Double.parseDouble(cmContent.get(exist).get(5));
					cmContent.get(exist).set(3, num + "");
					cmContent.get(exist).set(5, num * gvo.getPrice() + "");
					totalMoney -= p;
					totalMoney += num * gvo.getPrice();
				}

			}
		}else {
			for (int i = 0; i < vo.size(); i++) {
				ArrayList<String> line = new ArrayList<String>();
				CommodityVO com = (CommodityVO) vo.get(i);
				line.add(com.getID());
				line.add(com.getName());
				line.add(com.getType());
				line.add((com.getNum()+""));
				line.add(Double.toString(com.getPrice()));
				line.add(Double.toString(com.getTotal()));
				last_bid.add(com.getCost()/com.getNum());
				totalMoney+=com.getTotal();
				cmContent.add(line);
			}
		}
		this.defaultTotalLbl.setText("原价:" + totalMoney + "元");

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

	class submitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(from.getDate().compareTo(to.getDate())>0)
				JOptionPane.showMessageDialog(null, "促销时间段输入不合法！", "提示",
						JOptionPane.WARNING_MESSAGE);
			else if(!IsPackValid)
				JOptionPane.showMessageDialog(null, "请输入特价包特价！", "提示",
						JOptionPane.WARNING_MESSAGE);
			else if(cmContent.size()==0){
				JOptionPane.showMessageDialog(null, "请选择特价包！", "提示",
						JOptionPane.WARNING_MESSAGE);			
			}else{
			    PackProVO vo=getPackPro();
				if (service.Add(vo) == 0) {
					JOptionPane.showMessageDialog(null, "策略添加成功", "提示",
							JOptionPane.WARNING_MESSAGE);
					try {
						log.addLog(new LogVO(log.getdate(), parent.getUser()
							.getID(), parent.getUser().getName(),
							"创建一条特价包促销策略", 4));
						headPane.RefreshGrades();
						update();
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
	
	public PackProVO getPackPro(){
		ArrayList<CommodityVO> cmlist = new ArrayList<CommodityVO>();
		for (int j = 0; j < table.getRowCount(); j++) {
			ArrayList<String> line = cmContent.get(j);
			double cost = Double.parseDouble(line.get(3)) * last_bid.get(j);
			CommodityVO cv = new CommodityVO(line.get(0), line.get(1),
				line.get(2), Double.parseDouble(line.get(4)),
				last_bid.get(j), Integer.parseInt(line.get(3)),
				Double.parseDouble(line.get(5)), cost, "");
			cmlist.add(cv);
		}
		String startDate = from.getDate();
		String endDate = to.getDate();
		MemberLevel level = MemberLevel.valueOf((String) memberGradeBox
			.getSelectedItem());
		
		PackVO pack = new PackVO(totalMoney, Double.parseDouble(priceFld
			.getText()), cmlist);
		PackProVO vo = new PackProVO(id, startDate, endDate, level, pack);
		return vo;
	}

}
