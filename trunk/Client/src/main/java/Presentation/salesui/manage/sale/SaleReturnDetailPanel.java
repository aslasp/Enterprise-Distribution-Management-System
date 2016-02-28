package Presentation.salesui.manage.sale;

import vo.SaleReturnVO;
import businesslogic.salesbl.SalesController;
import businesslogic.userbl.User;
import businesslogicservice.salesblservice.viewInfoService;
import businesslogicservice.userblservice.UserViewService;
import Presentation.mainui.MainFrame;

public class SaleReturnDetailPanel extends AddSaleReturnPanel{
	
	viewInfoService service;
	SaleReturnVO vo;
	public SaleReturnDetailPanel(String id,MainFrame frame) throws Exception{
		super(frame);
		service=new SalesController();
		title.setText("查看销售退货单详情");
		vo=service.SRFindByID(id);
		IDLbl.setText("编号："+vo.getId());
		memberLbl.setText("客户:"+vo.getMemberName());
		clerkLbl.setText("业务员:"+vo.getClerk());
		id=vo.getId();
		if(vo.getHurry()==0)
			hurryBox.setSelected(true);
		hurryBox.setEnabled(false);
		stockLbl.setText("仓库"+vo.getStockid());
		UserViewService user=new User();
		userLbl.setText("操作员:"+user.getName(vo.getUser()));
		totalFinDiscountLbl.setText("退货总额："+vo.getTotal()[2]+"(折让后)");
		RefreshCTable(vo.getSaleReturnList());
		remarkLbl.setText("备注:"+vo.getInfo());
		p2.remove(remarkFld);
		btnPnl.remove(submitBtn);		
	}
	
	
	public void useToReceipt() {
		// TODO Auto-generated method stub
	
		btnPnl.remove(exitBtn);
		
		
	}
	

}
