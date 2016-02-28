package businesslogicservice.receiptblservice;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;
import vo.ReceiptVO;

public interface ReceiptBLService {
	
	//public int Modify(String id);
	public int Batch(String[] id,int status);
	
	public int Approve(String id,int status);
	public ArrayList<ReceiptVO> ToApprove();
	public ArrayList<ReceiptVO> ToApprove(ReceiptType type);
	public ArrayList<ReceiptVO> Approved();
	public ArrayList<ReceiptVO> Approved(ReceiptType type);

 }
