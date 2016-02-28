package Data.stockdata.goodsClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeDemo extends JFrame {
	DefaultMutableTreeNode root;
	/* DefaultMutableTreeNode是树数据结构中的通用节点 */
	DefaultMutableTreeNode child;
	DefaultMutableTreeNode chosen;
	JTree tree;
	DefaultTreeModel model; // 使用 TreeNodes 的简单树数据模型.

	TreeDemo() {
		Container contentPane = this.getContentPane();
		JPanel jPanel1 = new JPanel(new BorderLayout());
		root = new DefaultMutableTreeNode("根");
		tree = getTree();
		jPanel1.add(new JScrollPane(tree), BorderLayout.CENTER);
		model = (DefaultTreeModel) tree.getModel();

		JButton jButton1 = new JButton("使节点可编辑");
		// 选中节点
		// jButton1.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e){
		// tree.setEditable(true);
		// tree.getCellEditor().addCellEditorListener(new CellEditorAction());
		// }
		// });
		// 使节点可编辑
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.setEditable(true);
				tree.startEditingAtPath(tree.getSelectionPath());
			}
		});

		jButton1.setBackground(Color.blue);
		jButton1.setForeground(Color.white);
		JPanel jPanel2 = new JPanel();
		jPanel2.add(jButton1);
		jPanel1.add(jPanel2, BorderLayout.SOUTH);
		contentPane.add(jPanel1);
		this.setTitle("JtreeDemo");
		this.setSize(300, 500);
		this.setLocation(500, 150);
		this.setVisible(true);
	}

	class Branch {
		DefaultMutableTreeNode r;

		public Branch(String[] data) {
			r = new DefaultMutableTreeNode(data[0]);
			for (int i = 1; i < data.length; i++) {
				r.add(new DefaultMutableTreeNode(data[i]));
			}
		}

		public DefaultMutableTreeNode node() {
			return r;
		}
	}

	//从逻辑层取得要显示的树
	public JTree getTree() {
		GoodsClass gc;
		JTree tree = null;
		try {
			gc = new GoodsClass();
//			tree = gc.show();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return tree;
	}

	public static void main(String args[]) {
		TreeDemo test = new TreeDemo();
	}
}
