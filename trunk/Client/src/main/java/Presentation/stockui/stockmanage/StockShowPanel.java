package Presentation.stockui.stockmanage;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.uihelper.DateChooser;
import businesslogic.stockbl.stockManage.StockControlController;
import businesslogicservice.stockblservice.controlblservice.StockControlBLService;

//库存查看
public class StockShowPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DateChooser from, to;
	JButton submitBtn, showBtn;
	JScrollPane jsp;
	JTable table;
	StockShowTableModel sstm;
	MainFrame father;

	public StockShowPanel(MainFrame frame) {
		father = frame;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		JLabel title = new JLabel("库存查看");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(titlePnl, c);
		this.add(titlePnl);
		// -----------时间段--------------
		c.fill = GridBagConstraints.BOTH;
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
		//
		showBtn = new JButton("显示");
		showBtn.addActionListener(this);
		showBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		showBtn.setFocusPainted(false);
		showBtn.setBackground(Color.white);
		timePnl.add(showBtn);
		//
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(timePnl, c);
		this.add(timePnl);
		// ------table---------------
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		StockControlBLService controller = new StockControlController();
		list = controller.showStock(from.getDate(), to.getDate());
		sstm = new StockShowTableModel(list);
		table = new JTable(sstm);
		table.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new TableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}

		jsp = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 6;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
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
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.addActionListener(this);
		btnPnl.add(submitBtn);
	}

	class StockShowTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> rowData;
		String columnNames[] = { "商品编号", "商品名称", "商品型号", "出库数量", "出库金额",
				"入库数量", "入库金额", "销售数量", "销售金额", "进货数量", "进货金额" };// 列名

		public StockShowTableModel(ArrayList<ArrayList<String>> list) {
			rowData = new ArrayList<ArrayList<String>>();

			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> hang = new ArrayList<String>();
				ArrayList<String> tp = list.get(i);
				for (int j = 0; j < tp.size(); j++) {
					hang.add(tp.get(j));
				}

				// 加入到rowData
				rowData.add(hang);
			}
		}

		// 得到共有多少行
		public int getRowCount() {
			// TODO 自动生成的方法存根
			return this.rowData.size();
		}

		// 得到共有多少列
		public int getColumnCount() {
			// TODO 自动生成的方法存根
			return columnNames.length;
		}

		// 得到某行某列的数据
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO 自动生成的方法存根
			return ((ArrayList<String>) this.rowData.get(rowIndex))
					.get(columnIndex);
		}

		// 得到列名称
		public String getColumnName(int column) {
			// TODO 自动生成的方法存根
			return columnNames[column];
		}

	}

	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getActionCommand().equals("确定")) {
			father.setRightComponent(new StockPanel(father));
		}

		if (e.getActionCommand().equals("显示")) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			StockControlBLService controller = new StockControlController();
			if (from.getDate().compareTo(to.getDate()) > 0) {
				JOptionPane.showMessageDialog(null, "       请确定时间段合法哈~", null,
						JOptionPane.WARNING_MESSAGE);
			}
			list = controller.showStock(from.getDate(), to.getDate());
			sstm = new StockShowTableModel(list);
			table.setModel(sstm);
			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new TableCellRenderer();
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		}

	}

	// table的渲染器
	class TableCellRenderer extends MyTableCellRenderer {
		/**
			 * 
			 */
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			// 设置列宽
			table.getColumn("商品编号").setPreferredWidth(160);
			table.getColumn("商品名称").setPreferredWidth(130);

			return super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
		}

	}

}
