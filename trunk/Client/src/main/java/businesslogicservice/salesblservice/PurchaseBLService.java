package businesslogicservice.salesblservice;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;

public interface PurchaseBLService {
	
	public String getNewID(ReceiptType type);
	
	public PurchaseVO PFindByID(String id);
	public PurchaseReturnVO PRFindByID(String id);
	public int addPurchase(PurchaseVO vo);
	public int addPurchaseReturn(PurchaseReturnVO vo);
	
	public int modifyPurchase(PurchaseVO vo);
	public int modifyPurchaseReturn(PurchaseReturnVO vo);
	
	public ArrayList<PurchaseVO> showPurchase();
	public ArrayList<PurchaseReturnVO> showPurchaseReturn();
	
	public ArrayList<PurchaseVO> findPurchase(String message,String type);
	public ArrayList<PurchaseReturnVO> findPurchaseReturn(String message,String type);
}
