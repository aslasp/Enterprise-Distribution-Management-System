package Presentation.financeui.initial;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;


import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import vo.GoodsClassVO;
import vo.GoodsVO;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.stockui.goodsmanage.GoodsClassNode;
import Presentation.uihelper.UIhelper;

public class addGoodsInitial extends JDialog {

	/**
	 * 使用方法： 此类适用于需要选择商品的情形（如进货，销售，创建特价包，折扣等）,
	 * 使用此dialog的上一级Panel应继承ChooseGoodsFatherPane,
	 * chooseGoodsFatherPane继承了JPanel，唯一的区别是他有一个二维ArrayList成员变量用来给表格赋值
	 * 本dialog里的左侧是选择商品的表格，右侧是当前已选商品 没写完: 1.JTree的建立 2.通过点击JTree改变左侧表格内容
	 * 3.把右侧表格内容传出外层
	 */
	private static final long serialVersionUID = 1L;
	// ArrayList<ArrayList<String>> selected=new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> rightTblMessage = new ArrayList<ArrayList<String>>();
	ChosenTblModel ctm;
	GoodsTblModel gtm;
	ArrayList<ArrayList<String>> leftTblMessage = new ArrayList<ArrayList<String>>();
	// ChooseGoodsFatherPane father;
	GoodsInitialPanel father;
	//
	public JButton submitBtn, exitBtn, addBtn, delBtn;
	JTree tree;
	JScrollPane jspLeft, jspRight;
	JTable goodsTbl, chosenTbl;
	Container pnl;
	public StockGoodsBLService service;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int dialogWidth = screenWidth * 2 / 3;
	int dialogHeight = screenHeight * 2 / 3;
	//
	StockGoodsClassBLService controller;
	JScrollPane treeJsp = null;
	DefaultTreeModel treeModel = null;
	String nodeName = null;// 原有节点名称
	GoodsClassNode newNode = null;
    public AddListener add;
    public ArrayList<Object> good;
    GoodsInitialPanel tab;
	MainFrame parent;
	public addGoodsInitial(MainFrame pa,final GoodsInitialPanel tab) {
		controller = new GoodsClassController();
		parent=pa;
		this.tab=tab;
		pnl = this.getContentPane();
		pnl.setLayout(null);
		service = new GoodsController();
		if (service.showGoods() != null)
			Refresh(service.showGoods());

		pnl.setBackground(Color.white);
		// ------------classTree------------------------------------------
		// tree = new JTree();
		createGoodsClass(getTreeData());
		tree.setBorder(BorderFactory.createLineBorder(Color.gray));
		tree.setBounds(dialogWidth * 2 / 100, dialogHeight * 5 / 100,
				dialogWidth * 18 / 100, dialogHeight * 85 / 100);
		pnl.add(tree);
		// -----------goodsTbl-------------------------------------------
		gtm = new GoodsTblModel();
		goodsTbl = new JTable(gtm);
		goodsTbl.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < goodsTbl.getColumnCount(); i++) {
			goodsTbl.getColumn(goodsTbl.getColumnName(i)).setCellRenderer(tcr);
		}
		jspLeft = new JScrollPane(goodsTbl);
		jspLeft.setBounds(dialogWidth * 20 / 100, dialogHeight * 5 / 100,
				dialogWidth * 35 / 100, dialogHeight * 85 / 100);
		pnl.add(jspLeft);
		// ---------chosenTbl------------------------------------------
		JLabel chosenLbl = new JLabel("当前已选商品");
		chosenLbl.setFont(new Font("微软雅黑", Font.BOLD, 14));
		chosenLbl.setBounds(dialogWidth * 72 / 100, dialogHeight * 5 / 100,
				dialogWidth * 30 / 100, dialogHeight * 5 / 100);
		pnl.add(chosenLbl);
		ctm = new ChosenTblModel();
		chosenTbl = new JTable(ctm);
		chosenTbl.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		for (int i = 0; i < chosenTbl.getColumnCount(); i++) {
			chosenTbl.getColumn(chosenTbl.getColumnName(i))
					.setCellRenderer(tcr);
		}

		jspRight = new JScrollPane(chosenTbl);
		jspRight.setBounds(dialogWidth * 60 / 100, dialogHeight * 10 / 100,
				dialogWidth * 35 / 100, dialogHeight * 60 / 100);
		pnl.add(jspRight);
		// -------addBtn-----------------------------------------------
		addBtn = new JButton(">");
		addBtn.setFont(new Font("宋体", Font.BOLD, 15));
		addBtn.setFocusPainted(false);
		addBtn.setBorderPainted(false);
		addBtn.setBackground(Color.white);
		addBtn.setBounds(dialogWidth * 55 / 100, dialogHeight * 20 / 100,
				dialogWidth * 5 / 100, dialogHeight * 5 / 100);
		addBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// System.out.println(selected.get(0).get(1));
				int[] row = goodsTbl.getSelectedRows();
				if (row.length > 0) {
					for (int i = 0; i < row.length; i++) {
						int exist = FindInRight(leftTblMessage.get(row[i]).get(
								0));
						if (exist < 0) {
							ctm.addRow(leftTblMessage.get(row[i]));
						} else
							JOptionPane.showMessageDialog(null, "该商品已选择！",
									"提示", JOptionPane.WARNING_MESSAGE);

					}
					chosenTbl.revalidate();
				} else
					JOptionPane.showMessageDialog(null, "请选择商品！", "提示",
							JOptionPane.WARNING_MESSAGE);
				;

			}
		});
		pnl.add(addBtn);
		// -------delBtn-----------------------------------------------
		delBtn = new JButton("<");
		delBtn.setFont(new Font("宋体", Font.BOLD, 15));
		delBtn.setFocusPainted(false);
		delBtn.setBorderPainted(false);
		delBtn.setBackground(Color.white);
		delBtn.setBounds(dialogWidth * 55 / 100, dialogHeight * 35 / 100,
				dialogWidth * 5 / 100, dialogHeight * 5 / 100);
		// 只能一行一行删除？
		delBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (chosenTbl.getSelectedRow() >= 0) {
					ctm.removeRow(chosenTbl.getSelectedRow());
					chosenTbl.revalidate();
				} else
					JOptionPane.showMessageDialog(null, "请选择商品！", "提示",
							JOptionPane.WARNING_MESSAGE);

			}

		});
		pnl.add(delBtn);
		// -------submitBtn----------------------------------------------
		submitBtn = new JButton("完 成");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.setBounds(dialogWidth * 67 / 100, dialogHeight * 78 / 100,
				dialogWidth * 8 / 100, dialogHeight * 5 / 100);
		add=new AddListener();
		submitBtn.addActionListener(add);
		pnl.add(submitBtn);
		// ----------exitBtn------------------------------------------------
		exitBtn = new JButton("放 弃");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitBtn.setBounds(dialogWidth * 80 / 100, dialogHeight * 78 / 100,
				dialogWidth * 8 / 100, dialogHeight * 5 / 100);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGoodsInitial.this.dispose();
			}
		});
		pnl.add(exitBtn);
		//
		this.setTitle("选择商品");
		this.setBounds((screenWidth - dialogWidth) / 2,
				(screenHeight - dialogHeight) / 2, dialogWidth, dialogHeight);

		this.setResizable(false);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	// tree
	private void createGoodsClass(ArrayList<GoodsClassVO> list) {
		GoodsClassNode root = createTreeRoot(list);
		DefaultMutableTreeNode Troot = createGoodsClassNode(root);
		tree = new JTree(Troot);
		treeModel = (DefaultTreeModel) tree.getModel();
		tree.setEditable(true);
		tree.addMouseListener(new MouseHandle());
		// treeModel.addTreeModelListener(this);
		expandTree(tree);
		treeJsp = new JScrollPane(tree);
		treeJsp.setBorder(null);
		treeJsp.setLocation(0, 0);
		treeJsp.setSize(110, 400);
	}

	private DefaultMutableTreeNode createGoodsClassNode(GoodsClassNode root) {
		DefaultMutableTreeNode Troot = null;
		if (root != null)
			Troot = new DefaultMutableTreeNode(root.getName());
		else
			return null;
		for (int i = 0; i < root.children.size(); i++) {
			DefaultMutableTreeNode Tnode = createGoodsClassNode(root.children
					.get(i));
			Troot.add(Tnode);
		}
		return Troot;
	}

	private GoodsClassNode createTreeRoot(ArrayList<GoodsClassVO> list) {
		GoodsClassNode root = new GoodsClassNode("灯具", "根");
		makeTree(root, list);
		return root;
	}

	public ArrayList<GoodsClassVO> getTreeData() {
		ArrayList<GoodsClassVO> result = new ArrayList<GoodsClassVO>();
		StockGoodsClassBLService controller = new GoodsClassController();
		result = controller.show();
		return result;
	}

	public void makeTree(GoodsClassNode root, ArrayList<GoodsClassVO> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUpClassName().equals(root.getName())) {
				GoodsClassNode child = new GoodsClassNode(
						list.get(i).getName(), list.get(i).getUpClassName());
				root.children.add(child);
				list.remove(i);
				i--;
			}
		}

		for (int i = 0; i < root.children.size(); i++) {
			makeTree(root.children.get(i), list);
		}

	}

	// 点击分类名显示该分类下的商品
	class MouseHandle extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			try {
				JTree tree = (JTree) e.getSource();
				int rowLocation = tree.getRowForLocation(e.getX(), e.getY());
				TreePath treepath = tree.getPathForRow(rowLocation);
				TreeNode treenode = (TreeNode) treepath.getLastPathComponent();
				nodeName = treenode.toString();
			} catch (NullPointerException ne) {
			}

			StockGoodsBLService goodsController = new GoodsController();
			ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
			if (!nodeName.equals("灯具")) {
				list = goodsController.showGoodsByClass(nodeName);
			} else {
				list = goodsController.showGoods();
			}
			Refresh(list);
			goodsTbl.revalidate();
		}
	}

	// end_tree

	class GoodsTblModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "商品编号", "商品名称", "型号", "单价", "进价" };

		public int getRowCount() {
			return leftTblMessage.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public Object getValueAt(int row, int col) {
			return leftTblMessage.get(row).get(col);
		}

		public String getColumnName(int column) {
			return head[column];
		}

		public void addRow(ArrayList<String> v) {
			leftTblMessage.add(v);
		}

		public void removeRow(int row) {
			leftTblMessage.remove(row);
		}
	}

	public void Refresh(ArrayList<GoodsVO> VO) {
		leftTblMessage = new ArrayList<ArrayList<String>>();
		for (GoodsVO vo : VO) {
			ArrayList<String> line = new ArrayList<String>();
			line.add(vo.getGoodsID());
			line.add(vo.getName());
			line.add(vo.getSize());
			line.add(Double.toString(vo.getPrice()));
			line.add(Double.toString(vo.getPurchasePrice()));
			leftTblMessage.add(line);
		}
	}

	class ChosenTblModel extends AbstractTableModel {
		/**
		 * 
		 */
		// 需要更多 吗 检测库存数量何时？？
		private static final long serialVersionUID = 1L;
		String head[] = { "商品编号", "商品名称", "型号", "单价", "进价" };

		public int getRowCount() {
			return rightTblMessage.size();
		}

		public void addRow(ArrayList<String> v) {
			rightTblMessage.add(v);
		}

		public void removeRow(int row) {
			rightTblMessage.remove(row);
		}

		public int getColumnCount() {
			return head.length;
		}

		public Object getValueAt(int row, int col) {
			return rightTblMessage.get(row).get(col);
		}

		public String getColumnName(int column) {
			return head[column];
		}
	}

	public ArrayList<ArrayList<String>> getGoods() {
		return rightTblMessage;
	}

	public int FindInRight(String id) {
		for (int i = 0; i < rightTblMessage.size(); i++) {
			if (id.equals(rightTblMessage.get(i).get(0)))
				return i;
		}
		return -1;
	}

	public void expandTree(JTree tree) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		expandAll(tree, new TreePath(root), true);
	}

	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		// Traverse children
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}

		// Expansion or collapse must be done bottom-up
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}
	
	class AddListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (rightTblMessage.size() > 0) {
				ArrayList<Object> good = new ArrayList<Object>();
				try {
					for (int i = 0; i < rightTblMessage.size(); i++) {
						String id = rightTblMessage.get(i).get(0);

						good.add(service.findByID(id));
					}

				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				tab.RefreshCTable(good);
				parent.setRightComponent(tab.subparent);
				tab.subparent.setFocus(0);
				//father.repaint();
			}

			addGoodsInitial.this.dispose();
		}
	}
}