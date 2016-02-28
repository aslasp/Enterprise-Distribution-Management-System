package Presentation.stockui.goodsmanage;

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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import vo.GoodsClassVO;
import vo.GoodsVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.stockui.StockMessage;
import businesslogic.stockbl.goods.GoodsController;
import businesslogic.stockbl.goods.GoodsModel;
import businesslogic.stockbl.goodsClass.GoodsClassController;
import businesslogicservice.stockblservice.goodsblservice.StockGoodsBLService;
import businesslogicservice.stockblservice.goodsclassblservice.StockGoodsClassBLService;
/*
 * Author:yan
 * lastModify:12-30 by jin
 * description:商品及分类管理主界面
 * */
public class GoodsPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyButton addGoodsBtn, delGoodsBtn, modGoodsBtn, searchBtn, refreshBtn;
	JButton addGCBtn, delGCBtn, modGCBtn;
	JTextField searchFld;
	JTable goodsTable;
	GoodsModel goodsModel;
	JScrollPane jspTable = null;
	Color stockColor = new Color(51, 125, 86);
	String keyWord;

	JScrollPane treeJsp = null;
	JTree tree = null;
	StockGoodsClassBLService controller;
	StockGoodsBLService goodsController;
	DefaultTreeModel treeModel = null;
	String nodeName = null;// 原有节点名称

	GoodsClassNode newNode = null;
	DefaultMutableTreeNode addNode = null;
	public DefaultMutableTreeNode updateNode = null;

	static MainFrame parent;

	public GoodsPanel(MainFrame frame) {
		parent = frame;
		controller = new GoodsClassController();
		goodsController = new GoodsController();

		this.setBackground(Color.white);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 3, 3, 3);
		// -----------upButtonPnl--------------------------------------------------------
		JPanel btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		addGoodsBtn = new MyButton("添加商品", new ImageIcon("img/stock/add.png"));
		addGoodsBtn.addActionListener(new AddGoodsBtnListener());
		btnPnl.add(addGoodsBtn);
		//
		delGoodsBtn = new MyButton("删除商品",
				new ImageIcon("img/stock/delete.png"));
		delGoodsBtn.addActionListener(new DelGoodsBtnListener());
		btnPnl.add(delGoodsBtn);
		//
		modGoodsBtn = new MyButton("修改商品信息", new ImageIcon(
				"img/stock/modify.png"));
		modGoodsBtn.addActionListener(new ModGoodsBtnListener());
		btnPnl.add(modGoodsBtn);
		// --------------------------
		refreshBtn = new MyButton("刷新", new ImageIcon("img/stock/refresh.png"));
		refreshBtn.addActionListener(new Refresh());
		btnPnl.add(refreshBtn);
		// 搜索框
		searchFld = new JTextField(6);
		searchFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchFld.getDocument().addDocumentListener(new SearchFldListener());
		btnPnl.add(searchFld);
		// 查找按钮
		searchBtn = new MyButton(new ImageIcon("img/stock/find.png"));
		searchBtn.addActionListener(new SearchBtnListener());
		btnPnl.add(searchBtn);
		// -------------------------------------
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);

		// ---------goodsClassTree-------------------
		createGoodsClass(getTreeData());
		tree.setBackground(Color.white);
		tree.setBorder(BorderFactory.createLineBorder(Color.gray));
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3, 3, 3, 3);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 5;
		c.gridwidth = 2;
		c.weightx = 0.05;
		c.weighty = 50;
		gbl.setConstraints(tree, c);
		this.add(tree);

		// ---------------------button---------------------
		JPanel downPnl = new JPanel();
		downPnl.setLayout(new GridLayout(1, 3));
		// ------------------------------------------------
		addGCBtn = new JButton("添加分类");
		addGCBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		addGCBtn.setForeground(stockColor);
		addGCBtn.setBackground(Color.white);
		addGCBtn.setFocusPainted(false);
		addGCBtn.addActionListener(this);
		downPnl.add(addGCBtn);
		// ------------------------------------------------
		delGCBtn = new JButton("删除分类");
		delGCBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		delGCBtn.setForeground(stockColor);
		delGCBtn.setBackground(Color.white);
		delGCBtn.setFocusPainted(false);
		delGCBtn.addActionListener(this);
		downPnl.add(delGCBtn);
		// ------------------------------------------------
		modGCBtn = new JButton("修改分类");
		modGCBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		modGCBtn.setForeground(stockColor);
		modGCBtn.setBackground(Color.white);
		modGCBtn.setFocusPainted(false);
		modGCBtn.addActionListener(this);
		downPnl.add(modGCBtn);
		c.insets = new Insets(1, 1, 1, 1);
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 0.05;
		c.weighty = 0.05;
		gbl.setConstraints(downPnl, c);
		this.add(downPnl);

		// ----------goodsTable------------------
		goodsModel = new GoodsModel();
		goodsTable = new JTable(goodsModel);
		goodsTable.setBackground(Color.white);
		goodsTable.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < goodsTable.getColumnCount(); i++) {
			goodsTable.getColumn(goodsTable.getColumnName(i)).setCellRenderer(
					tcr);
		}

		jspTable = new JScrollPane(goodsTable);
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 7;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 0.6;
		c.weighty = 55;
		c.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(jspTable, c);
		this.add(jspTable);
	}

	class AddGoodsBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TreePath parentPath = tree.getSelectionPath();

			if (parentPath == null) {
				JOptionPane.showMessageDialog(null, "         请选择分类", null,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 取得商品分类
			DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parentPath
					.getLastPathComponent();

			// 判断商品分类下是否有子分类——只有叶节点才可以添加商品
			if (!parentNode.isLeaf()) {
				JOptionPane.showMessageDialog(null, "只有叶节点才能添加商品噢~", null,
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			String GoodsClass = parentNode.toString();
			parent.setRightComponent(new AddGoodsPanel(parent, GoodsClass));

			// 重新再获得数据模型,刷新界面
			tree.clearSelection();
			goodsModel = new GoodsModel();
			goodsTable.setModel(goodsModel);
			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			for (int i = 0; i < goodsTable.getColumnCount(); i++) {
				goodsTable.getColumn(goodsTable.getColumnName(i))
						.setCellRenderer(tcr);
			}
		}

	}

	class DelGoodsBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int rownum = goodsTable.getSelectedRow();
			if (rownum == -1) {
				JOptionPane.showMessageDialog(null, "           请选择一行商品", null,
						JOptionPane.WARNING_MESSAGE);
			} else {
				String id = (String) goodsModel.getValueAt(rownum, 0);
				GoodsVO vo = null;
				try {
					vo = goodsController.findByID(id);
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}

				new DelGoodsDialog(vo);

				// 重新再获得数据模型,刷新界面
				tree.clearSelection();
				goodsModel = new GoodsModel();
				goodsTable.setModel(goodsModel);
				// table 渲染器，设置文字内容居中显示，设置背景色等
				DefaultTableCellRenderer tcr = new MyTableCellRenderer();
				for (int i = 0; i < goodsTable.getColumnCount(); i++) {
					goodsTable.getColumn(goodsTable.getColumnName(i))
							.setCellRenderer(tcr);
				}
			}
		}

	}

	class ModGoodsBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int rownum = goodsTable.getSelectedRow();
			if (rownum == -1) {
				JOptionPane.showMessageDialog(null, "           请选择一行商品", null,
						JOptionPane.WARNING_MESSAGE);
			} else {
				String id = (String) goodsModel.getValueAt(rownum, 0);
				GoodsVO vo = null;
				try {
					vo = goodsController.findByID(id);
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				parent.setRightComponent(new ModGoodsPanel(parent, vo));

				// 重新再获得数据模型,刷新界面
				tree.clearSelection();
				goodsModel = new GoodsModel();
				goodsTable.setModel(goodsModel);
				// table 渲染器，设置文字内容居中显示，设置背景色等
				DefaultTableCellRenderer tcr = new MyTableCellRenderer();
				for (int i = 0; i < goodsTable.getColumnCount(); i++) {
					goodsTable.getColumn(goodsTable.getColumnName(i))
							.setCellRenderer(tcr);
				}
			}
		}

	}

	class SearchFldListener implements DocumentListener {

		public void insertUpdate(DocumentEvent e) {
			keyWord = searchFld.getText();
		}

		public void removeUpdate(DocumentEvent e) {
			keyWord = searchFld.getText();
		}

		public void changedUpdate(DocumentEvent e) {
			keyWord = searchFld.getText();
		}

	}

	class SearchBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ArrayList<GoodsVO> list = goodsController.findGoods(keyWord);

			goodsModel = new GoodsModel(list);
			goodsTable.setModel(goodsModel);
			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			for (int i = 0; i < goodsTable.getColumnCount(); i++) {
				goodsTable.getColumn(goodsTable.getColumnName(i))
						.setCellRenderer(tcr);
			}
		}

	}

	// tree---------------------------------------------------------------------
	private void createGoodsClass(ArrayList<GoodsClassVO> list) {
		GoodsClassNode root = createTreeRoot(list);
		DefaultMutableTreeNode Troot = createGoodsClassNode(root);

		tree = new JTree(Troot);
		treeModel = (DefaultTreeModel) tree.getModel();
		tree.setEditable(false);
		tree.addMouseListener(new MouseHandle());

		expandTree(tree);

		// 渲染器，用于调整树背景色及文字背景色
		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) tree
				.getCellRenderer();
		cellRenderer.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cellRenderer.setBackgroundSelectionColor(new Color(135, 206, 235));
		cellRenderer.setTextNonSelectionColor(Color.black);
		cellRenderer.setTextSelectionColor(Color.blue);

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

	// end_yan-----------------------------------------------------------

	// 关于分类的增加删除的监听
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("添加分类")) {
			DefaultMutableTreeNode parentNode = null;
			TreePath parentPath = tree.getSelectionPath();

			if (parentPath == null) {
				JOptionPane.showMessageDialog(null, "       请选择父节点", null,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 取得新节点的父节点
			parentNode = (DefaultMutableTreeNode) (parentPath
					.getLastPathComponent());
			new AddGoodsClassDialog(this, parentNode.toString());
			DefaultMutableTreeNode newNode = addNode;
			if (newNode != null) {
				newNode.setAllowsChildren(true);

				GoodsClassVO vo = new GoodsClassVO(newNode.toString(),
						parentNode.toString());
				int result = controller.addGoodsClass(vo);
				if (result == 0) {
					// 由DefaultTreeModel的insertNodeInto（）方法增加新节点
					treeModel.insertNodeInto(newNode, parentNode,
							parentNode.getChildCount());

					// tree的scrollPathToVisible()方法在使Tree会自动展开文件夹以便显示所加入的新节点。若没加这行则加入的新节点
					// 会被 包在文件夹中，你必须自行展开文件夹才看得到。
					tree.scrollPathToVisible(new TreePath(newNode.getPath()));
					expandTree(tree);// 还是自己写的方法
				} else {
					String stringResult = StockMessage.getStringResult(result);
					JOptionPane.showMessageDialog(null, stringResult, null,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (e.getActionCommand().equals("删除分类")) {
			TreePath treepath = tree.getSelectionPath();
			if (treepath != null) {
				// 下面两行取得选取节点的父节点.
				DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) treepath
						.getLastPathComponent();
				TreeNode parent = (TreeNode) selectionNode.getParent();
				if (parent != null) {
					GoodsClassVO vo = new GoodsClassVO(
							selectionNode.toString(), parent.toString());
					int result = controller.deleteGoodsClass(vo);
					if (result == 0) {
						// 由DefaultTreeModel的removeNodeFromParent()方法删除节点，包含它的子节点。
						treeModel.removeNodeFromParent(selectionNode);
						treeModel.reload();
					} else {
						String stringResult = StockMessage
								.getStringResult(result);
						JOptionPane.showMessageDialog(null, stringResult, null,
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		} else if (e.getActionCommand().equals("修改分类")) {
			TreePath treePath = tree.getSelectionPath();
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath
					.getLastPathComponent();
			GoodsClassVO oldVO = controller.showGoodsClassInfo(node.toString());
			new ModifyGoodsClassDialog(this, node.toString());
			if (updateNode != null) {
				GoodsClassVO newVO = new GoodsClassVO(updateNode.toString(),
						oldVO.getUpClassName());
				int result = controller.modifyGoodsClass(oldVO, newVO);

				if (result != 0) {
					String stringResult = StockMessage.getStringResult(result);
					JOptionPane.showMessageDialog(null, stringResult, null,
							JOptionPane.WARNING_MESSAGE);
				} else {
					node.setUserObject(updateNode.toString());
					treeModel.reload();
				}
			}
		}

		expandTree(tree);
	}

	// 点击分类节点显示节点下的商品信息的监听
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
			if (nodeName != null) {
				if (!nodeName.equals("灯具")) {
					list = goodsController.showGoodsByClass(nodeName);
				} else {
					list = goodsController.showGoods();
				}
			}
			goodsModel = new GoodsModel(list);
			goodsTable.setModel(goodsModel);

			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			for (int i = 0; i < goodsTable.getColumnCount(); i++) {
				goodsTable.getColumn(goodsTable.getColumnName(i))
						.setCellRenderer(tcr);
			}
		}
	}

	class AddGoodsClassDialog extends JDialog implements ActionListener {
		private static final long serialVersionUID = 1L;
		JLabel fatherClassjl = null;
		JLabel classjl = null;
		JTextField fatherClassjtf = null;
		JTextField classjtf = null;
		JButton okjb = null;
		JButton canceljb = null;

		GoodsPanel father = null;

		public AddGoodsClassDialog(GoodsPanel goodsPanel, String parent) {
			this.father = goodsPanel;

			Font font = new Font("楷体", Font.BOLD, 16);
			fatherClassjl = new JLabel("父分类");
			fatherClassjl.setFont(font);
			fatherClassjl.setBounds(15, 20, 60, 30);
			this.add(fatherClassjl);

			classjl = new JLabel("分类名");
			classjl.setFont(font);
			classjl.setBounds(15, 60, 60, 30);
			this.add(classjl);

			fatherClassjtf = new JTextField();
			fatherClassjtf.setFont(font);
			fatherClassjtf.setText(parent);
			fatherClassjtf.setEditable(false);
			fatherClassjtf.setBounds(85, 20, 180, 30);
			this.add(fatherClassjtf);

			classjtf = new JTextField();
			classjtf.setFont(font);
			classjtf.setBounds(85, 60, 180, 30);
			this.add(classjtf);

			okjb = new JButton("确定");
			okjb.setBounds(50, 110, 60, 30);
			okjb.addActionListener(this);
			okjb.setVisible(true);
			this.add(okjb);

			canceljb = new JButton("取消");
			canceljb.setBounds(170, 110, 60, 30);
			canceljb.addActionListener(this);
			canceljb.setVisible(true);
			this.add(canceljb);

			this.setSize(300, 200);
			this.setLayout(null);
			this.setTitle("添加商品分类");
			this.setResizable(false);
			this.setLocation(goodsPanel.getX() + goodsPanel.getWidth() / 2,
					goodsPanel.getY() + goodsPanel.getHeight() / 3);
			this.setModal(true);
			this.setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == okjb) {
				if (fatherClassjtf.getText().trim().equals("")
						|| classjtf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "        请填写全部信息！",
							"Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				father.newNode = new GoodsClassNode(classjtf.getText().trim(),
						fatherClassjtf.getText().trim().toString());
				father.addNode = new DefaultMutableTreeNode(classjtf.getText()
						.trim());
				this.dispose();
			} else if (e.getSource() == canceljb) {
				father.addNode = null;
				this.dispose();
			}
		}

	}

	class ModifyGoodsClassDialog extends JDialog implements ActionListener {
		private static final long serialVersionUID = 1L;
		JLabel oldClassjl = null;
		JLabel classjl = null;
		JTextField oldClassjtf = null;
		JTextField classjtf = null;
		JButton okjb = null;
		JButton canceljb = null;

		GoodsPanel father = null;

		public ModifyGoodsClassDialog(GoodsPanel father, String parent) {
			this.father = father;

			Font font = new Font("楷体", Font.BOLD, 16);
			oldClassjl = new JLabel("原类名");
			oldClassjl.setFont(font);
			oldClassjl.setBounds(15, 20, 60, 30);
			this.add(oldClassjl);

			classjl = new JLabel("新类名");
			classjl.setFont(font);
			classjl.setBounds(15, 60, 60, 30);
			this.add(classjl);

			oldClassjtf = new JTextField();
			oldClassjtf.setFont(font);
			oldClassjtf.setText(parent);
			oldClassjtf.setEditable(false);
			oldClassjtf.setBounds(85, 20, 180, 30);
			this.add(oldClassjtf);

			classjtf = new JTextField();
			classjtf.setFont(font);
			classjtf.setBounds(85, 60, 180, 30);
			this.add(classjtf);

			okjb = new JButton("确定");
			okjb.setBounds(50, 110, 60, 30);
			okjb.addActionListener(this);
			okjb.setVisible(true);
			this.add(okjb);

			canceljb = new JButton("取消");
			canceljb.setBounds(170, 110, 60, 30);
			canceljb.addActionListener(this);
			canceljb.setVisible(true);
			this.add(canceljb);

			this.setSize(300, 200);
			this.setLayout(null);
			this.setTitle("修改商品分类");
			this.setResizable(false);
			this.setLocation(father.getX() + father.getWidth() / 2,
					father.getY() + father.getHeight() / 3);
			this.setModal(true);
			this.setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == okjb) {
				if (oldClassjtf.getText().trim().equals("")
						|| classjtf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "        请填写全部信息！",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				father.updateNode = new DefaultMutableTreeNode(classjtf
						.getText().trim());
				this.dispose();
			} else if (e.getSource() == canceljb) {
				father.updateNode = null;
				this.dispose();
			}
		}

	}

	class MyButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		MyButton(String text, Icon icon) {
			super(text, icon);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			this.setForeground(new Color(51, 125, 86));
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

	// end_yan-----------------------------------------------------------

	class Refresh implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			tree.clearSelection();
			goodsModel = new GoodsModel();
			goodsTable.setModel(goodsModel);
			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			for (int i = 0; i < goodsTable.getColumnCount(); i++) {
				goodsTable.getColumn(goodsTable.getColumnName(i))
						.setCellRenderer(tcr);
			}
		}

	}

	public void expandTree(JTree tree) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		expandAll(tree, new TreePath(root), true);
	}
	//展开树
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

}