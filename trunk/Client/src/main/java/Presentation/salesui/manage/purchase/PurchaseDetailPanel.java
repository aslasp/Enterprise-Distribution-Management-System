package Presentation.salesui.manage.purchase;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.CommodityVO;
import vo.PurchaseVO;
import Presentation.mainui.MainFrame;
import businesslogic.salesbl.SalesController;
import businesslogic.userbl.User;
import businesslogicservice.salesblservice.viewInfoService;
import businesslogicservice.userblservice.UserViewService;

public class PurchaseDetailPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame  parent;
	PurchaseVO vo;
	viewInfoService service;
	public AddPurchasePanel p;
	JLabel member;
	public PurchaseDetailPanel(String id,MainFrame father) throws Exception{
		p=new AddPurchasePanel(father);
		parent=father;
		service=new SalesController();
		vo=service.PFindByID(id);
		p.title.setText("查看进货单详情");
		p.IDLbl.setText("编号："+vo.getId());
		UserViewService user=new User();
		p.userLbl.setText("操作员:"+user.getName(vo.getUser()));
		p.JHSLbl.setText("进货商："+vo.getMemberName());
		p.p1.remove(p.JHSBox);
		p.stockFld.setText(vo.getStockid());
		p.stockFld.setEditable(false);;
		p.remarkFld.setText(vo.getInfo());
		p.remarkFld.setEditable(false);
		p.totalLbl.setText("总计："+vo.getTotalInAll()+"元");
		ArrayList<CommodityVO> clist=vo.getPurchaseList();
		ArrayList<Object> plist=new ArrayList<Object>();
		for(int i=0;i<clist.size();i++)
			plist.add(clist.get(i));   
		p.RefreshCTable(plist);  
		if(vo.getHurry()==0)
			p.hurryBox.setSelected(true);
		p.hurryBox.setEnabled(false);
		p.btnPnl.remove(p.addGoodsBtn);
		p.btnPnl.remove(p.delGoodsBtn);
		p.btnPnl.remove(p.submitBtn);
		p.exitBtn.setText("返回");
		p.table.setEnabled(false);
		if(vo.getHurry()==0)
			p.hurryBox.setSelected(true);
		p.hurryBox.setEnabled(false);
		
			
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.CENTER);
		this.repaint();		
	}
	public void useToReceipt() {
		// TODO Auto-generated method stub
	
		p.btnPnl.remove(p.exitBtn);
		
		
	}

}
