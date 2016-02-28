package businesslogicservice.receiptblservice;

import java.util.ArrayList;

import vo.ReceiptVO;


//经营历程表

public interface ReceiptListService {
	public ArrayList<ReceiptVO> View();
	public ArrayList<ReceiptVO> getSale();
	public ArrayList<ReceiptVO> AccurateFind(String[] message);
	public ReceiptVO RedAndCopy(String id);
	public int Red(String id);//红冲单据 返回红冲成功与否
}
