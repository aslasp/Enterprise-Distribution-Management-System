package businesslogicservice.salesblservice;

import vo.PurchaseReturnVO;
import vo.PurchaseVO;
import vo.SaleReturnVO;
import vo.SaleVO;

public interface viewInfoService {
	
	public PurchaseVO PFindByID(String id);
	public PurchaseReturnVO PRFindByID(String id);
	public SaleReturnVO SRFindByID(String id);
	public SaleVO SFindByID(String id);

}
