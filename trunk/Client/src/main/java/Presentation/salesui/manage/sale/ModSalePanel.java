package Presentation.salesui.manage.sale;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import po.UserPO.UserJob;
import businesslogic.salesbl.SalesController;
import businesslogic.userbl.User;
import businesslogicservice.salesblservice.SalesBLService;
import businesslogicservice.userblservice.UserViewService;
import vo.LogVO;
import vo.SaleVO;
import vo.UserVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import Presentation.salesui.manage.SaleMgrPanel;

public class ModSalePanel extends JPanel implements ActionListener{
	MainFrame parent;
	AddSalePanel p;
	SaleVO vo;
	SalesBLService service;
	boolean isRed=false;
	public  ModSalePanel(String id,MainFrame father) throws Exception{
		parent=father;
		service=new SalesController();
		vo=service.SFindByID(id);
		
		p=new AddSalePanel(father);
		p.id=id;
		p.UserID=vo.getUser();
		p.proid=vo.getProid();
		p.memid=vo.getMemberID();
		p.UserID=vo.getUser();
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.CENTER);
		p.setDiscount(vo.getDiscount());
		p.setTotal(vo.getTotal());
		p.title.setText("修改销售单");
		p.IDLbl.setText("编号:"+vo.getId());
		p.memberLbl.setText("销售商:"+vo.getMemberName());
		p.mem=vo.getMemberName();
		p.memid=vo.getMemberID();
		p.p1.remove(p.XSSBox);
		p.clerkLbl.setText("业务员:"+vo.getClerk());
		p.p1.remove(p.clerkFld);
		p.stockFld.setText(vo.getStockid());
		p.discountMoneyFld.setText(vo.getDiscount()[2]+"");
		p.stockFld.setEditable(false);
		p.remarkFld.setText(vo.getInfo());
		if(vo.getHurry()==0)
			p.hurryBox.setSelected(true);
		p.hurryBox.setEnabled(false);
		UserViewService user=new User();
		p.userLbl.setText("操作员:"+user.getName(vo.getUser()));
		p.couponBtn.setText("代金券抵消"+(vo.getTotalValue()-vo.getToPay())+"元");
		p.couponBtn.setEnabled(false);
		p.btnPnl.remove(p.addGoodsBtn);
		p.btnPnl.remove(p.delGoodsBtn);
		p.totalOriginLbl.setText("原初总价:"+vo.getTotalOrigin()+"元");
		p.totalProDiscountLbl.setText("折让金额:"+(vo.getTotalOrigin()-vo.getTotalValue())+"元");
		p.totalFinDiscountLbl.setText("折后总价:"+vo.getTotalValue()+"元");
		p.totalToPayLbl.setText("客户应付:"+vo.getToPay()+"元");
		ArrayList<Object> list=new ArrayList<Object>();
		list.addAll(vo.getSalesList());
		p.RefreshCTable(list);
		ActionListener[] listen=p.submitBtn.getActionListeners();
		
		p.submitBtn.removeActionListener(p);
		p.exitBtn.removeActionListener(p);
		
		p.exitBtn.removeActionListener(p);;

		//12.20带监听
	}
	
	public void UseToModify(ActionListener ok,boolean isRed){
		p.submitBtn.addActionListener(ok);
		p.exitBtn.addActionListener(ok);
		p.submitBtn.addActionListener(this);
		
			if(isRed)
			{	p.title.setText("制定销售单");this.isRed=true;}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
			p.getSale();
			String tip="修改";
			int result=0;
			if(isRed){
				tip="制定";result=service.addSale(p.sale);
			}
			result = service.modifySale(p.sale);
			if (result == 0) {
				log.addLog(new LogVO(log.getdate(),parent.getUser()
					.getID(), parent.getUser().getName(), tip+"一笔销售单", 3));
				headPane.RefreshGrades();
			} else
				JOptionPane.showMessageDialog(null, "销售单"+tip+"失败", "提示",
					JOptionPane.WARNING_MESSAGE);

	
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
/*	public static void main(String[] args) throws Exception {
		 JFrame test=	new JFrame();
		 MainFrame frame=new MainFrame(new UserVO("JL-00001","","",UserJob.SALE,1));
		 test.add(new ModSalePanel( frame,"XSD-20141221-00001"));
		 test.setSize(500,400);
		 test.setVisible(true);
		 test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}*/

}

