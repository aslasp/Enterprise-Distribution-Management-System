package Presentation.promotionui.DiscountPromotion;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import vo.GoodsClassVO;
import vo.GoodsVO;
import Presentation.mainui.ChooseGoodsFatherPane;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.stockui.goodsmanage.GoodsClassNode;
import Presentation.uihelper.UIhelper;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;

public class DiscountDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	JTextField discountFld;
	//
	ArrayList<ArrayList<String>> selected = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> rightTblMessage = new ArrayList<ArrayList<String>>();
	ChosenTblModel ctm;
	GoodsTblModel gtm;
	ArrayList<ArrayList<String>> leftTblMessage = new ArrayList<ArrayList<String>>();
	ChooseGoodsFatherPane father;
	//
	StockGoodsBLService service;
	StockGoodsClassBLService controller;
	JButton submitBtn, exitBtn, addBtn, delBtn;
	JTree tree;
	JScrollPane jspLeft, jspRight;
	JTable goodsTbl, chosenTbl;
	Container pnl;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int dialogWidth = screenWidth * 2 / 3;
	int dialogHeight = screenHeight * 2 / 3;

	//
	JScrollPane treeJsp = null;
	DefaultTreeModel treeModel = null;
	String nodeName = null;// 原有节点名称
	GoodsClassNode newNode = null;

	public DiscountDialog(ChooseGoodsFatherPane myFather) {
		controller = new GoodsClassController();
		father = myFather;
		service = new GoodsController();
		pnl = this.getContentPane();
		pnl.setLayout(null);
		if (service.showGoods() != null)
			Refresh(service.showGoods());
		pnl.setBackground(Color.white);
		// ------------classTree------------------------------------------
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
		// ------------discount-------------------------------------------
		JLabel discountLbl1 = new JLabel("以上商品打");
		discountLbl1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		discountLbl1.setBounds(dialogWidth * 60 / 100, dialogHeight * 73 / 100,
				dialogWidth * 15 / 100, dialogHeight * 5 / 100);
		pnl.add(discountLbl1);
		discountFld = new JTextField();
		discountFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		discountFld.setBounds(dialogWidth * 68 / 100, dialogHeight * 73 / 100,
				dialogWidth * 7 / 100, dialogHeight * 5 / 100);
		pnl.add(discountFld);
		JLabel discountLbl = new JLabel("折。");
		discountLbl.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		discountLbl.setBounds(dialogWidth * 75 / 100, dialogHeight * 73 / 100,
				dialogWidth * 10 / 100, dialogHeight * 5 / 100);
		pnl.add(discountLbl);
		// -------submitBtn----------------------------------------------
		submitBtn = new JButton("完 成");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.setBounds(dialogWidth * 67 / 100, dialogHeight * 84 / 100,
				dialogWidth * 8 / 100, dialogHeight * 5 / 100);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double discount = 0;
				try {
					discount = Double.parseDouble(discountFld.getText()) * 0.1;
					DecimalFormat df = new DecimalFormat("#.##");
					if(discount<=0||discount>=10){
						JOptionPane.showMessageDialog(null, "折扣需在0-10折之间哦!",
								"提示", JOptionPane.WARNING_MESSAGE);
					}else{
						String v = df.format(discount);
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
							father.parent.setRightComponent(father);
							father.RefreshCTable(good, Double.parseDouble(v));
						}

					DiscountDialog.this.dispose();
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "请输入合法数值,e.g 9折!",
							"提示", JOptionPane.WARNING_MESSAGE);

				}

			}
		});
		pnl.add(submitBtn);
		// ----------exitBtn------------------------------------------------
		exitBtn = new JButton("放 弃");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitBtn.setBounds(dialogWidth * 80 / 100, dialogHeight * 84 / 100,
				dialogWidth * 8 / 100, dialogHeight * 5 / 100);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiscountDialog.this.dispose();
			}
		});
		pnl.add(exitBtn);
		//
		this.setTitle("选择打折商品");
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
		String head[] = { "商品编号", "商品名", "型号", "售价" };

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

	class ChosenTblModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[] = { "商品编号", "商品名", "型号" };

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

	public void Refresh(ArrayList<GoodsVO> VO) {
		leftTblMessage = new ArrayList<ArrayList<String>>();
		for (GoodsVO vo : VO) {
			ArrayList<String> line = new ArrayList<String>();
			line.add(vo.getGoodsID());
			line.add(vo.getName());
			line.add(vo.getSize());
			line.add(Double.toString(vo.getPrice()));
			leftTblMessage.add(line);
		}
	}

	public int FindInRight(String id) {
		for (int i = 0; i < rightTblMessage.size(); i++) {
			if (id.equals(rightTblMessage.get(i).get(0)))
				return i;
		}
		return -1;
	}
}
