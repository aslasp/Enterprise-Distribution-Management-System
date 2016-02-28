package Presentation.salesui.manage.purchase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vo.CommodityVO;
import vo.LogVO;
import vo.PurchaseVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import businesslogic.salesbl.SalesController;
import businesslogic.userbl.User;
import businesslogicservice.salesblservice.PurchaseBLService;
import businesslogicservice.userblservice.UserViewService;

public class ModPurchasePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame  parent;
	PurchaseVO vo;
	PurchaseBLService service;
	AddPurchasePanel p;
	boolean isRed=false;
	public ModPurchasePanel(String id,MainFrame father) throws Exception{
		parent=father;
		service=new SalesController();
		vo=service.PFindByID(id);
		p=new AddPurchasePanel(father);
		p.id=vo.getId();
		p.memid=vo.getMemberID();
		p.title.setText("修改进货单");
		p.IDLbl.setText("编号："+vo.getId());
		p.JHSLbl.setText("进货商："+vo.getMemberName());
		p.p1.remove(p.JHSBox);
		p.stockFld.setText(vo.getStockid());
		p.stockFld.setEditable(false);
		p.remarkFld.setText(vo.getInfo());
		p.totalLbl.setText("总计："+vo.getTotalInAll()+"元");
		ArrayList<CommodityVO> clist=vo.getPurchaseList();
		ArrayList<Object> plist=new ArrayList<Object>();
		for(int i=0;i<clist.size();i++)
			plist.add(clist.get(i));
		p.RefreshCTable(plist);
		if(vo.getHurry()==0)
			p.hurryBox.setSelected(true);
		p.hurryBox.setEnabled(false);
		UserViewService user=new User();
		p.userLbl.setText("操作员:"+user.getName(vo.getUser()));
		p.btnPnl.remove(p.addGoodsBtn);
		p.btnPnl.remove(p.delGoodsBtn);
		p.submitBtn.removeActionListener(p.psl);
		p.exitBtn.removeActionListener(p.elisten);
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.CENTER);
		
		
		
		
	}
	
	public void UseToModify(ActionListener ok,boolean isRed){
		p.submitBtn.addActionListener(ok);
		p.exitBtn.addActionListener(ok);
		p.submitBtn.addActionListener(this);
		if(isRed)
		{	p.title.setText("制定进货单");this.isRed=true;}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
			PurchaseVO vo=p.getPurchase();
			int result=0;
			String tip="修改";
			if(isRed)
				{result=service.addPurchase(vo);tip="制定";}
			else result = service.modifyPurchase(vo);
			if (result == 0) {
				log.addLog(new LogVO(log.getdate(),parent.getUser()
						.getID(), parent.getUser().getName(), tip+"一笔进货单", 3));
				headPane.RefreshGrades();
			} else
				JOptionPane.showMessageDialog(null, "进货单"+tip+"失败", "提示",
				JOptionPane.WARNING_MESSAGE);


		}catch(Exception err){
			err.printStackTrace();
		}
	}
}
