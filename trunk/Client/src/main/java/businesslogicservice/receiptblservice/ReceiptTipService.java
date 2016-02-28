package businesslogicservice.receiptblservice;

import java.util.ArrayList;

import vo.ReceiptMessageVO;
public interface ReceiptTipService {
	//总经理
	public ArrayList<ReceiptMessageVO> getToApprove();
	//销售
	public ArrayList<ReceiptMessageVO> getSaleApproved();
	//财务
	public ArrayList<ReceiptMessageVO> getFinanceApproved();
	//库存？
	public ArrayList<ReceiptMessageVO> getStockApproved();
	
	public void addMessage(ReceiptMessageVO vo);
	public void deleteessage(ReceiptMessageVO vo);
	//public ArrayList<ReceiptVO> get();

}
