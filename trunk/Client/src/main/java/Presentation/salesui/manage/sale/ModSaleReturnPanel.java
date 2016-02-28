package Presentation.salesui.manage.sale;

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
import vo.SaleReturnVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;

public class ModSaleReturnPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SaleReturnVO vo;
	AddSaleReturnPanel p;
	MainFrame parent;
	boolean isRed=false;
	public ModSaleReturnPanel(String Id,MainFrame frame) throws Exception {
		p=new AddSaleReturnPanel(frame);
		parent=frame;
		p.title.setText("修改销售退货单");
		vo=p.service.SRFindByID(Id);
		p.IDLbl.setText("编号:"+vo.getId());
	
		p.memberLbl.setText("销售商:"+vo.getMemberName());
		p.stockLbl.setText("仓库:"+vo.getStockid());
		p.clerkLbl.setText("业务员："+vo.getClerk());
		UserViewService user=new User();
		p.userLbl.setText("操作员:"+user.getName(vo.getUser()));
		p.totalFinDiscountLbl.setText("退货总额："+vo.getTotal()[2]+"元");
		if(vo.getHurry()==0)
			p.hurryBox.setSelected(true);
		p.setEnabled(false);
		p.remarkFld.setText(vo.getInfo());
		p.submitBtn.removeActionListener(p);
		p.exitBtn.removeActionListener(p);
		p.RefreshCTable(vo.getSaleReturnList());
		this.setLayout(new BorderLayout());
		this.add(p, BorderLayout.CENTER);
		//12.20带监听
	}
	
	
	public void UseToModify(ActionListener ok,boolean isRed){
		p.submitBtn.addActionListener(ok);
		p.exitBtn.addActionListener(ok);
		p.submitBtn.addActionListener(this);
		if(isRed)
		{	p.title.setText("制定销售退货单");this.isRed=true;}
		
	}


	@Override
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
		try {
		double[] total=vo.getTotal();
		total[2]=p.totalMoney;
		SaleReturnVO v=new SaleReturnVO(vo.getId(),vo.getMemberName(),vo.getMemberID(),
				vo.getUser(),vo.getStatus(),p.remarkFld.getText(),vo.getHurry(),total,
				vo.getDiscount(),vo.getClerk(),cmlist,vo.getStockid());
		String tip="修改";
		int result=0;
		if(isRed){
			tip="制定";result=p.service.addSaleReturn(v);
		}
		result=p.service.modifySaleReturn(v);
		if(result==0){
			log.addLog(new LogVO(log.getdate(),parent.getUser()
					.getID(), parent.getUser().getName(), tip+"一笔销售退货单", 3));
			
					headPane.RefreshGrades();
				
			} else
				JOptionPane.showMessageDialog(null, "销售退货单"+tip+"失败", "提示",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
