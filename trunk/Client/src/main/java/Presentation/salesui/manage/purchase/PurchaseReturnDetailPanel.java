package Presentation.salesui.manage.purchase;

import java.util.ArrayList;

import vo.PurchaseReturnVO;
import businesslogic.salesbl.SalesController;
import businesslogic.userbl.User;
import businesslogicservice.salesblservice.viewInfoService;
import businesslogicservice.userblservice.UserViewService;
import Presentation.mainui.MainFrame;

public class PurchaseReturnDetailPanel extends AddPurchasePanel{
	/**
	 * 
	 */
	viewInfoService service;
	PurchaseReturnVO vo;
	private static final long serialVersionUID = 1L;
	public PurchaseReturnDetailPanel(String id,MainFrame frame) throws Exception {
		super(frame);
		service=new SalesController();
		title.setText("查看进货退货单详情");
		vo=service.PRFindByID(id);
		IDLbl.setText("编号"+vo.getId());
		JHSLbl.setText("进货商:"+vo.getMemberName());
		p1.remove(JHSBox);
		UserViewService se=new User();
		userLbl.setText("操作员:"+se.getName(vo.getUser()));
		stockLbl.setText("仓库:"+vo.getStockid());
		p2.remove(stockFld);
		remarkLbl.setText("备注:"+vo.getInfo());
		p2.remove(remarkFld);
		totalLbl.setText("总计:"+vo.getTotalInAll()+"元");
		if(vo.getHurry()==0)
			hurryBox.setSelected(true);
		hurryBox.setEnabled(false);
		ArrayList<Object> list=new ArrayList<Object>();
		list.addAll(vo.getPurchaseReturnList());
		table.setEnabled(false);
		RefreshCTable(list);
		if(vo.getHurry()==0)
			hurryBox.setSelected(true);
		hurryBox.setEnabled(false);
		btnPnl.remove(addGoodsBtn);
		btnPnl.remove(delGoodsBtn);
		btnPnl.remove(submitBtn);
		exitBtn.setText("返回");
		hurryBox.setEnabled(false);
		stockFld.setEditable(false);
		remarkFld.setEditable(false);
		JHSBox.setEditable(false);
		
	}
	
	public void useToReceipt() {
		// TODO Auto-generated method stub
	
		btnPnl.remove(exitBtn);
		
		
	}
}
