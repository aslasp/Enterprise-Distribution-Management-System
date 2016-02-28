package Presentation.salesui.manage.purchase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import po.ReceiptPO.ReceiptType;
import vo.CommodityVO;
import vo.LogVO;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import Presentation.mainui.ChooseGoodsFatherPane;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import Presentation.salesui.manage.PurchaseMgrPanel;
import businesslogic.userbl.User;
import businesslogicservice.userblservice.UserBLService;
//进货退货只能改变 退货价格？？
public class AddPurchaseReturnPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame parent;
	AddPurchasePanel p;
	PurchaseVO pvo;
	String pid;
	UserBLService user;
	purReturnListener returnlisten;
	public AddPurchaseReturnPanel(MainFrame frame,String id) throws Exception {
		user=new User();
		parent=frame;
		this.setLayout(new BorderLayout());
		p=new AddPurchasePanel(parent);
		this.add(p,BorderLayout.CENTER);
		p.title.setText("创建进货退货单");
		pvo=p.service.PFindByID(id);
		pid=p.service.getNewID(ReceiptType.PURCHASERETURN);
		p.IDLbl.setText("编号："+pid);
		p.JHSLbl.setText("进货商："+pvo.getMemberName());
		p.p1.remove(p.JHSBox);
		p.userLbl.setText("操作员："+user.showUser(pvo.getUser()).getName());
		p.stockLbl.setText("仓库："+pvo.getStockid());
		p.p2.remove(p.stockFld);
		p.table=new JTable(p.ctm){public boolean isCellEditable(int row,int column){
			if(column==6||column==3)
				return true;
			else return false;
		}};
		p.stockFld.setEnabled(false);
		p.remarkFld.setText(pvo.getInfo());
		p.totalMoney=pvo.getTotalInAll();
		p.totalLbl.setText("总计："+p.totalMoney+"元");
		ArrayList<CommodityVO> clist=pvo.getPurchaseList();
		ArrayList<Object> plist=new ArrayList<Object>();
		for(int i=0;i<clist.size();i++)
			plist.add(clist.get(i));
		p.RefreshCTable(plist);
		p.submitBtn.removeActionListener(p.psl);
		returnlisten=new purReturnListener();
		p.submitBtn.addActionListener(returnlisten);
		p.btnPnl.remove(p.addGoodsBtn);
		p.btnPnl.remove(p.delGoodsBtn);
			
		

	}
	
	
	class purReturnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			ArrayList<CommodityVO> cmlist = new ArrayList<CommodityVO>();
			for (int j = 0; j < p.table.getRowCount(); j++) {
				ArrayList<String> line = p.cmContent.get(j);
				double cost = Double.parseDouble(line.get(4)) * p.last_bid.get(j);
				CommodityVO cv = new CommodityVO(line.get(0), line.get(1),
						line.get(2), Double.parseDouble(line.get(4)),
						p.last_bid.get(j), Integer.parseInt(line.get(3)),
						Double.parseDouble(line.get(5)), cost, line.get(6));
				cmlist.add(cv);
			}
			int hurry = 1;
			if (p.hurryBox.isSelected())
				hurry = 0;
			
		
			String mem = pvo.getMemberName();
			PurchaseReturnVO vo = new PurchaseReturnVO(pid, mem, pvo.getMemberID(),
					 parent.getUser().getID(), 0,p.remarkFld.getText(),hurry,cmlist,
					 p.totalMoney, pvo.getStockid(),pvo.getId());
			int result = p.service.addPurchaseReturn(vo);
			if (result == 0) {
				JOptionPane.showMessageDialog(null, "进货退货单创建成功");
				PurchaseMgrPanel pmg;
				try {
					pmg = new PurchaseMgrPanel(parent);

					parent.setRightComponent(pmg);
					pmg.RefreshPanel();
					log.addLog(new LogVO(log.getdate(), parent.getUser()
							.getID(), parent.getUser().getName(), "创建一笔进货退货单", 4));
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
		
	}
	


