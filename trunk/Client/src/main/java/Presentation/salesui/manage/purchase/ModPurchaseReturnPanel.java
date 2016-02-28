package Presentation.salesui.manage.purchase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import businesslogic.userbl.User;
import businesslogicservice.userblservice.UserViewService;
import vo.CommodityVO;
import vo.LogVO;
import vo.PurchaseReturnVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;

public class ModPurchaseReturnPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PurchaseReturnVO vo;
	AddPurchasePanel p;
	MainFrame parent;
	boolean isRed=false;
	public ModPurchaseReturnPanel( String id,MainFrame frame) throws Exception  {
		p=new AddPurchasePanel(frame);
		parent=frame;
		vo=p.service.PRFindByID(id);
		p.title.setText("修改进货退货单");
		p.IDLbl.setText("编号："+vo.getId());
		p.JHSLbl.setText("进货商:"+vo.getMemberName());
		p.p1.remove(p.JHSBox);
		p.id=vo.getId();
		UserViewService user=new User();
		p.userLbl.setText("操作员:"+user.getName(vo.getUser()));
		p.stockLbl.setText("仓库:"+vo.getStockid());
		p.p2.remove(p.stockFld);
		if(vo.getHurry()==0)
		 p.hurryBox.setSelected(true);
		p.hurryBox.setEnabled(false);;
		p.remarkFld.setText(vo.getInfo());
		p.totalLbl.setText("总计："+vo.getTotalInAll()+"元");
		ArrayList<CommodityVO> clist=vo.getPurchaseReturnList();
		ArrayList<Object> plist=new ArrayList<Object>();
		plist.addAll(clist);
		p.RefreshCTable(plist);
		p.submitBtn.removeActionListener(p.psl);
		p.exitBtn.removeActionListener(p.elisten);
		this.setLayout(new BorderLayout());
		this.add(p, BorderLayout.CENTER);
		
	}
	
	public void UseToModify(ActionListener ok,boolean isRed){
		p.submitBtn.addActionListener(ok);
		p.exitBtn.addActionListener(ok);
		p.submitBtn.addActionListener(this);
		if(isRed)
		{	p.title.setText("制定进货退货单");this.isRed=true;}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> cmlist = new ArrayList<CommodityVO>();
		for (int j = 0; j <p. table.getRowCount(); j++) {
			ArrayList<String> line = p.cmContent.get(j);
			double cost = Double.parseDouble(line.get(4)) * p.last_bid.get(j);
			CommodityVO cv = new CommodityVO(line.get(0), line.get(1),
					line.get(2), Double.parseDouble(line.get(4)),
					p.last_bid.get(j), Integer.parseInt(line.get(3)),
					Double.parseDouble(line.get(5)), cost, line.get(6));
			cmlist.add(cv);
		}
		PurchaseReturnVO v=new PurchaseReturnVO(vo.getId(),vo.getMemberName(),
				vo.getMemberID(),vo.getUser(),vo.getStatus(),p.remarkFld.getText(),
				vo.getHurry(),cmlist,p.totalMoney,vo.getStockid(),vo.getPurid());
		String tip="修改";
		int result=0;
		if(isRed)
		{
			result=p.service.addPurchaseReturn(v);tip="制定";
		}
		result=p.service.modifyPurchaseReturn(v);
		if (result == 0) {
			log.addLog(new LogVO(log.getdate(),parent.getUser()
				.getID(), parent.getUser().getName(), tip+"一笔进货退货单", 3));
			try {
				headPane.RefreshGrades();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else
			JOptionPane.showMessageDialog(null, "进货退货单"+tip+"失败", "提示",
				JOptionPane.WARNING_MESSAGE);
		
	}

}
