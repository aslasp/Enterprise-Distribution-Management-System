package Presentation.stockui.giftmanage;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import po.MemberPO.MemberType;
import vo.CommodityVO;
import vo.GiftVO;
import vo.GoodsVO;
import vo.LogVO;
import vo.MemberVO;
import Presentation.mainui.ChooseGoodsFatherPane;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import Presentation.stockui.ChooseGoodsDialog;
import businesslogic.memberbl.Member;
import businesslogic.stockbl.gift.GiftCommodityListModel;
import businesslogic.stockbl.gift.GiftController;
import businesslogicservice.memberblservice.MemberBLService;
import businesslogicservice.stockblservice.giftblservice.GiftBLService;

public class CreateGiftPanel extends ChooseGoodsFatherPane implements
		ActionListener {

	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	JLabel title;
	JScrollPane jsp;
	JTable table;
	GiftCommodityListModel gcm;
	JComboBox<String> memberBox;
	JButton submitBtn, addBtn, delBtn, exitBtn;
	public ArrayList<CommodityVO> commodityList = new ArrayList<CommodityVO>();
	ArrayList<String> userList;

	public CreateGiftPanel(MainFrame myFather) {
		parent = myFather;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		// -----------title------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		title = new JLabel("创建库存赠送单");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 2;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0.1;
		gbl.setConstraints(titlePnl, cons);
		this.add(titlePnl);
		// ------------------------------------
		cons.fill = GridBagConstraints.BOTH;
		JPanel memberPnl = new JPanel();
		memberPnl.setBackground(Color.white);
		JLabel memberLbl = new JLabel("客户：");
		memberLbl.setFont(font);
		memberPnl.add(memberLbl);

		// 给下拉框加入内容
		ArrayList<MemberVO> member = new ArrayList<MemberVO>();
		try {
			MemberBLService memberController = new Member();
			member = memberController.show(MemberType.XSS);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		userList = new ArrayList<String>();
		userList.add("请选择用户");
		for (int i = 0; i < member.size(); i++) {
			userList.add(member.get(i).getMemberID() + " "
					+ member.get(i).getName());
		}
		String boxText[] = (String[]) userList
				.toArray(new String[member.size()]);

		memberBox = new JComboBox<String>(boxText);
		memberBox.setBackground(Color.white);
		memberBox.setFont(font);
		memberPnl.add(memberBox);
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0.1;
		gbl.setConstraints(memberPnl, cons);
		this.add(memberPnl);
		// ---------table--------------------------
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		jsp = new JScrollPane(table);
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 5;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		gbl.setConstraints(jsp, cons);
		this.add(jsp);
		// -------buttons-----------------
		JPanel btnPnl = new JPanel();
		btnPnl.setBackground(Color.white);
		cons.gridx = 0;
		cons.gridy = 9;
		cons.gridheight = 2;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0.1;
		gbl.setConstraints(btnPnl, cons);
		this.add(btnPnl);
		//
		addBtn = new JButton("添加商品");
		addBtn.setFont(font);
		addBtn.setBackground(Color.white);
		addBtn.setFocusPainted(false);
		addBtn.addActionListener(this);
		btnPnl.add(addBtn);
		delBtn = new JButton("删除商品");
		delBtn.setFont(font);
		delBtn.setBackground(Color.white);
		delBtn.setFocusPainted(false);
		delBtn.addActionListener(this);
		btnPnl.add(delBtn);
		submitBtn = new JButton("确定");
		submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.addActionListener(this);
		btnPnl.add(submitBtn);
		exitBtn = new JButton("取消");
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		exitBtn.addActionListener(this);
		btnPnl.add(exitBtn);
	}

	public void RefreshCTable(ArrayList<Object> VO) {
		if (VO.get(0) instanceof GoodsVO) {
			for (int i = 0; i < VO.size(); i++) {
				GoodsVO goodsVO = (GoodsVO) VO.get(i);
				int exist = findExistLine(goodsVO.getGoodsID());
				if (exist < 0) {// 赠品不存在，添加赠品对象
					CommodityVO commodityVO = new CommodityVO(
							goodsVO.getGoodsID(), goodsVO.getName(),
							goodsVO.getSize(), goodsVO.getPrice(),
							goodsVO.getLastPurchasePrice(), 1, 0, 0, "");
					commodityList.add(commodityVO);
				} else {// 赠品存在，修改赠品数量
					int num = commodityList.get(exist).getNum();
					num++;
					CommodityVO commodityVO = new CommodityVO(
							goodsVO.getGoodsID(), goodsVO.getName(),
							goodsVO.getSize(), goodsVO.getPrice(),
							goodsVO.getLastPurchasePrice(), num, 0, 0, "");
					commodityList.set(exist, commodityVO);
				}
			}
		}
	}

	// 查找当前想加入的赠品是否已经存在，存在返回行号，否则返回-1
	public int findExistLine(String id) {
		for (int i = 0; i < commodityList.size(); i++) {
			if (id.equals(commodityList.get(i).getID()))
				return i;
		}
		return -1;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitBtn) {
			parent.setRightComponent(new GiftPanel(parent));
		} else if (e.getSource() == addBtn) {
			ChooseGoodsDialog cgd = new ChooseGoodsDialog(CreateGiftPanel.this);
			cgd.dispose();
			gcm = new GiftCommodityListModel(commodityList);
			table.setModel(gcm);
			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}

		} else if (e.getSource() == delBtn) {
			int rownum = table.getSelectedRow();
			if (rownum == -1) {
				JOptionPane.showMessageDialog(null, "           请选择一行赠品", null,
						JOptionPane.WARNING_MESSAGE);
			} else {
				gcm.removeRow(rownum);
				table.revalidate();
				// 清除选择的行，否则下次不进行选择就删除会数组越界
				table.clearSelection();
			}
		} else if (e.getSource() == submitBtn) {
			String memberData = memberBox.getSelectedItem().toString();
			// 要先判断有木有选择客户信息
			if (memberData.equals("请选择用户")) {
				JOptionPane.showMessageDialog(null, "请选择赠送客户！", null,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 判断是否选择了商品,赠送数量默认为1
			if (gcm == null || gcm.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "           请选择赠品哈~", null,
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// 修改赠品数量后，需要重新更新commodityList
			int rowCount = gcm.getRowCount();
			ArrayList<CommodityVO> recordList = new ArrayList<CommodityVO>();

			for (int i = 0; i < rowCount; i++) {
				CommodityVO oldVO = commodityList.get(i);
				int num = 0;
				try {
					num = Integer.parseInt((String) gcm.getValueAt(i, 3));
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "      请注意你的输入是否合法噢~ ",
							null, JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (num < 0) {
					JOptionPane.showMessageDialog(null, "       请确定赠品数量合法噢~",
							null, JOptionPane.WARNING_MESSAGE);
					return;
				}

				double cost = oldVO.getPrice() * num;

				CommodityVO vo = new CommodityVO(oldVO.getID(),
						oldVO.getName(), oldVO.getType(), oldVO.getPrice(),
						oldVO.getLast_bid(), num, cost, cost, oldVO.getTip());
				recordList.add(vo);
			}

			String data[] = memberData.split(" ");
			String ID = data[0];
			String name = data[1];
			String user = parent.getUser().getID();
			GiftVO vo = new GiftVO("", name, ID, user, 0, 0, "", recordList);
			GiftBLService giftService = null;
			try {
				giftService = new GiftController();
			} catch (Exception e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}

			giftService.addGift(vo);
			log.addLog(new LogVO(log.getdate(), parent.getUser().getID(),
					parent.getUser().getName(), "创建一笔库存赠送单", 5));
			try {
				headPane.RefreshGrades();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			parent.setRightComponent(new GiftPanel(parent));
		}
	}
}
