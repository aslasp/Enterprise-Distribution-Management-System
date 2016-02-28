package Presentation.salesui.manage.sale;

import java.util.ArrayList;

import javax.swing.JFrame;

import po.UserPO.UserJob;
import vo.SaleVO;
import vo.UserVO;
import businesslogic.salesbl.SalesController;
import businesslogic.userbl.User;
import businesslogicservice.salesblservice.viewInfoService;
import businesslogicservice.userblservice.UserViewService;
import Presentation.mainui.MainFrame;

public class SaleDetailPanel extends AddSalePanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	viewInfoService service;
	public SaleDetailPanel(String ID,MainFrame frame) throws Exception {
		super(frame);
		title.setText("查看销售单详情");
		service=new SalesController();
		SaleVO vo=service.SFindByID(ID);
		IDLbl.setText("编号:"+vo.getId());
		memberLbl.setText("客户:"+vo.getMemberName());
		p1.remove(XSSBox);
		clerkLbl.setText("业务员:"+vo.getClerk());
		p1.remove(clerkFld);
		stockLbl.setText("仓库:"+vo.getStockid());
		p1.remove(stockFld);
		remarkLbl.setText("备注:"+vo.getInfo());
		UserViewService user=new User();
		userLbl.setText("操作员:"+user.getName(vo.getUser()));
		p2.remove(remarkFld);
		if(vo.getHurry()==0)
			hurryBox.setSelected(true);
		hurryBox.setEnabled(false);
		discountLbl.setText("折让："+vo.getDiscount()[2]);
		p2.remove(discountMoneyFld);
		couponBtn.setText("代金券抵消:"+(vo.getTotalValue()-vo.getToPay()));
		couponBtn.setEnabled(false);
		totalOriginLbl.setText("原初总价:"+vo.getTotalOrigin());
		totalProDiscountLbl.setText("折让金额:"+vo.getDiscount()[3]);
		totalFinDiscountLbl.setText("折后总价:"+vo.getTotalValue());
		totalToPayLbl.setText("客户应付:"+vo.getToPay());
		ArrayList<Object> list=new ArrayList<Object>();
		list.addAll(vo.getSalesList());
		RefreshCTable(list);
		table.setEnabled(false);
		btnPnl.remove(addGoodsBtn);
		btnPnl.remove(delGoodsBtn);
		btnPnl.remove(submitBtn);
		
		
		
	}
	
	public void useToReceipt() {
		// TODO Auto-generated method stub
	
		btnPnl.remove(exitBtn);
		
		
	}
	

}
