package Presentation.receiptui.tablemodels;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


public class TestGroupHeaderTable extends JFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestGroupHeaderTable() {
		super("Groupable Header Example");
		BSLTableModel dm=new BSLTableModel();
		JTable table=new JTable(dm){
//		DefaultTableModel dm = new DefaultTableModel();
//		dm.setDataVector(new Object[][] {
//				{ "119", "foo", "bar", "ja", "ko", "zh" },
//				{ "911", "bar", "foo", "en", "fr", "pt" } }, 
//				new Object[] {"销售收入", "库存报溢收入", "成本调价收入", "进货收入", "代金券收入",
//				"折让金额", "总收入","销售支出", "报损支出", "赠送支出", "总支出" ,"总利润"});
//	
//		JTable table = new JTable(dm) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected JTableHeader createDefaultTableHeader() {
				return new MyTableHeader(columnModel);
			}
		};
		TableColumnModel cm = table.getColumnModel();
		ColumnGroup g_name = new ColumnGroup("收入类");
		g_name.add(cm.getColumn(0));
		ColumnGroup g_income = new ColumnGroup("商品类收入");
		g_income.add(cm.getColumn(1));
		g_income.add(cm.getColumn(2));
		g_income.add(cm.getColumn(3));
		g_income.add(cm.getColumn(4));
		g_name.add(g_income);
		g_name.add(cm.getColumn(5));
		g_name.add(cm.getColumn(6));
		ColumnGroup g_lang = new ColumnGroup("支出类");
		g_lang.add(cm.getColumn(7));
		ColumnGroup g_other = new ColumnGroup("商品类支出");
		g_other.add(cm.getColumn(8));
		g_other.add(cm.getColumn(9));
		g_lang.add(g_other);
		g_lang.add(cm.getColumn(10));
		ColumnGroup g_profit = new ColumnGroup("利润");
		g_profit.add(cm.getColumn(11));
		MyTableHeader header = (MyTableHeader) table.getTableHeader();
		header.addColumnGroup(g_name);
		header.addColumnGroup(g_lang);
		header.addColumnGroup(g_profit);
		TableCellRenderer renderer = new MyHeaderButtonRenderer();
		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < model.getColumnCount(); i++) {
			model.getColumn(i).setHeaderRenderer(renderer);
		}
		table.getTableHeader().setUI(new MyGroupTableHeaderUI());
		JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll);
		setSize(600, 300);
	}

	public static void main(String[] args) {
		TestGroupHeaderTable frame = new TestGroupHeaderTable();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}
