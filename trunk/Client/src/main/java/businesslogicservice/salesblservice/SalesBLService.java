package businesslogicservice.salesblservice;
import java.util.ArrayList;

import po.ReceiptPO.ReceiptType;
import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import vo.SaleReturnVO;
import vo.SaleVO;


public interface SalesBLService {
	public String getNewID(ReceiptType type);

	public int addSale(SaleVO vo);
	public int addSaleReturn(SaleReturnVO vo);
	public SaleVO SFindByID(String id);
	public SaleReturnVO SRFindByID(String id);
	public double getPrivilege(String MemberID);
	
	public int modifySale(SaleVO vo);
	public int modifySaleReturn(SaleReturnVO vo);
	public ArrayList<SaleVO> showSale();
	public ArrayList<SaleReturnVO> showSaleReturn();
	
	//按类型查找
	public ArrayList<SaleVO> findSale(String message,String type);
	public ArrayList<SaleReturnVO> findSaleReturn(String message,String type);
}
