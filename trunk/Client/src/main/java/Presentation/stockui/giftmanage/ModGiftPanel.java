package Presentation.stockui.giftmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import vo.GiftVO;
import vo.LogVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import businesslogic.stockbl.gift.GiftCommodityListModel;
import businesslogic.stockbl.gift.GiftController;
import businesslogic.stockbl.gift.GiftReceipt;
import businesslogicservice.stockblservice.giftblservice.GiftBLService;

public class ModGiftPanel extends CreateGiftPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GiftVO vo;
	GiftBLService controller;
	boolean isRed = false;

	public ModGiftPanel(String ID, MainFrame myFather) {
		super(myFather);
		try {
			controller = new GiftController();
			vo = controller.findByID(ID);
			title.setText("修改库存赠送单");
			this.remove(addBtn);
			this.remove(delBtn);
			submitBtn.removeActionListener(this);
			exitBtn.removeActionListener(this);

			// 设置下拉框显示的是选中的客户
			String member = vo.getMemberID() + " " + vo.getMemberName();
			memberBox.setSelectedItem(member);

			// 设置表格显示赠送的商品
			commodityList = vo.getGiftList();
			gcm = new GiftCommodityListModel(vo.getGiftList());
			table.setModel(gcm);
			// table 渲染器，设置文字内容居中显示，设置背景色等
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public void UseToModify(ActionListener ok, boolean isRed) {
		submitBtn.addActionListener(ok);
		exitBtn.addActionListener(ok);
		submitBtn.addActionListener(new SubmitActionListener());

		if (isRed) {
			title.setText("制定库存赠送单");
			this.isRed = true;
		}
	}

	class SubmitActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			String memberData = memberBox.getSelectedItem().toString();
			// 要先判断有木有选择客户信息
			if (memberData.equals("请选择用户")) {
				JOptionPane.showMessageDialog(null, "请选择赠送客户！", null,
						JOptionPane.WARNING_MESSAGE);
				return;
			}

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
			GiftReceipt receipt = new GiftReceipt(vo.getId(), ID, name,
					vo.getUser(), ReceiptType.GIFT, vo.getHurry(),
					vo.getStatus(), vo.getInfo(), recordList);

			if (receipt.Modify(vo.getId()) == 0) {
				log.addLog(new LogVO(log.getdate(), parent.getUser().getID(),
						parent.getUser().getName(), "创建一笔库存赠送单", 5));
				try {
					headPane.RefreshGrades();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "          修改失败", "提示",
						JOptionPane.WARNING_MESSAGE);
			}
		}

	}
}
