package Presentation.salesui.manage.sale;

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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import vo.LogVO;
import vo.SaleReturnVO;
import vo.SaleVO;
import businesslogic.salesbl.SalesController;
import businesslogicservice.salesblservice.SalesBLService;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import Presentation.salesui.manage.CommodityTableModel;
import Presentation.salesui.manage.SaleMgrPanel;

//只能改价格 尚未实现
public class AddSaleReturnPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame father;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JScrollPane jsp;
	CommodityTableModel cm;
	JTable table;
	JCheckBox hurryBox;
	JTextField remarkFld;
	JButton submitBtn, exitBtn;
	JLabel title,IDLbl, memberLbl, clerkLbl, userLbl, stockLbl, totalOriginLbl,
			totalProDiscountLbl, totalFinDiscountLbl, totalToPayLbl,remarkLbl
			;
	JPanel btnPnl,p2;
	SaleVO vo;
	SalesBLService service;
	String pid;
	ArrayList<ArrayList<String>> cmContent;
	ArrayList<Double> last_bid = new ArrayList<Double>();
	double totalMoney;
	//退货 查看复用  销售退货完全逆操作
	public AddSaleReturnPanel(MainFrame frame ,String id) throws Exception{
		this(frame);
		
		vo=service.SFindByID(id);

		totalMoney=vo.getTotalValue();
		pid=service.getNewID(ReceiptType.SALERETURN);
		IDLbl.setText("编号："+pid);
		memberLbl.setText("销售商:"+vo.getMemberName());
		clerkLbl.setText("业务员:"+vo.getClerk());
		userLbl.setText("操作员："+frame.getUser().getName());
		stockLbl.setText("仓库"+vo.getStockid());
		totalFinDiscountLbl.setText("退货总额："+vo.getTotalValue()+"(折让后)");
		RefreshCTable(vo.getSalesList());
		
	}
	public AddSaleReturnPanel(MainFrame frame) throws Exception  {
		father = frame;	
		service=new SalesController();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 10, 5, 10);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// ----------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		title = new JLabel("创建销售退货单");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(titlePnl, c);
		this.add(titlePnl);
		// -----中间----------------
		c.fill = GridBagConstraints.BOTH;
		JPanel midPnl = new JPanel();
		midPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 3;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.3;
		gbl.setConstraints(midPnl, c);
		this.add(midPnl);
		midPnl.setLayout(new GridLayout(3, 1));
		JPanel p1 = new JPanel();
		 p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		p3.setBackground(Color.white);
		midPnl.add(p1);
		midPnl.add(p2);
		midPnl.add(p3);
		// --------ID----------------
	
		IDLbl = new JLabel("编号：");
		IDLbl.setFont(font);
		p1.add(IDLbl);
		p1.add(new JLabel("      "));
		// --------客户---------------
		 memberLbl = new JLabel("客户：");
		memberLbl.setFont(font);
		p1.add(memberLbl);
		p1.add(new JLabel("      "));
		// -------业务员---------------
		 clerkLbl = new JLabel("业务员：");
		clerkLbl.setFont(font);
		p1.add(clerkLbl);
		p1.add(new JLabel("      "));
		// -------仓库----------------
		 stockLbl = new JLabel("仓库：");
		stockLbl.setFont(font);
		p1.add(stockLbl);
		p1.add(new JLabel("      "));
		// ------操作员----------------
		userLbl = new JLabel("操作员：" );
		userLbl.setFont(font);
		p1.add(userLbl);
		p1.add(new JLabel("      "));
		// ------备注------------------
		remarkLbl = new JLabel("备注：");
		remarkLbl.setFont(font);
		p2.add(remarkLbl);
		remarkFld = new JTextField(21);
		remarkFld.setFont(font);
		p2.add(remarkFld);
		// -------加急----------------
		hurryBox = new JCheckBox("加急");
		hurryBox.setFont(font);
		hurryBox.setBackground(Color.white);
		p2.add(hurryBox);
		p2.add(new JLabel("      "));
		totalFinDiscountLbl = new JLabel("退货总额：");
		totalFinDiscountLbl.setFont(font);
		p2.add(totalFinDiscountLbl);
	

		JLabel tableLbl = new JLabel("销售退货商品列表       ");
		tableLbl.setFont(new Font("微软雅黑", Font.PLAIN, 21));
		p3.add(tableLbl);
		cm=new CommodityTableModel();
		table = new JTable(cm){
		public boolean isCellEditable(int row,int column){
			if(column==6||column==3)
				return true;
			else return false;
		}};
		cmContent=cm.getContent();
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 4;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(jsp, c);
		this.add(jsp);
		
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	
		// -------buttons-----------------
		 btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 9;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		//
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		btnPnl.add(submitBtn);
		submitBtn.addActionListener(this);
		exitBtn = new JButton("取消");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitBtn.addActionListener(this);
		btnPnl.add(exitBtn);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitBtn){
			try {
				SaleMgrPanel sp=new SaleMgrPanel(father);
				father.setRightComponent(sp);
				sp.RefreshPanel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}else if(e.getSource()==submitBtn){
		int hurry=1;
		if(hurryBox.isSelected())
			hurry=0;
		SaleReturnVO v=new SaleReturnVO(pid,father.getUser().getID(),vo,0,
				remarkFld.getText(),hurry);
		int result=service.addSaleReturn(v);
		if (result == 0) {
			JOptionPane.showMessageDialog(null, "销售退货单创建成功");
			SaleMgrPanel pmg;
			try {
				pmg = new SaleMgrPanel(father);
				father.setRightComponent(pmg);
				pmg.RefreshPanel();
				log.addLog(new LogVO(log.getdate(),father.getUser()
						.getID(), father.getUser().getName(), "创建一笔销售退货单", 4));
				headPane.RefreshGrades();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else
			JOptionPane.showMessageDialog(null, "创建失败！", "提示",
					JOptionPane.WARNING_MESSAGE);		
	}

}
	
	public void RefreshCTable(ArrayList<CommodityVO> VO){
		for (int i = 0; i < VO.size(); i++) {
			ArrayList<String> line = new ArrayList<String>();
			CommodityVO vo = (CommodityVO) VO.get(i);
			line.add(vo.getID());
			line.add(vo.getName());
			line.add(vo.getType());
			line.add((vo.getNum()+""));
			line.add(Double.toString(vo.getPrice()));
			line.add(Double.toString(vo.getTotal()));
			line.add(vo.getTip());
			last_bid.add(vo.getCost()/vo.getNum());

			cmContent.add(line);
		}
		table.revalidate();
	}
	
}