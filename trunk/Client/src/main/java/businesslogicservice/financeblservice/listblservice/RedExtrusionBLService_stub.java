package businesslogicservice.financeblservice.listblservice;

import vo.ReceiptVO;

public class RedExtrusionBLService_stub implements RedExtrusionBLService{
	public int createRedExtrusion(ReceiptVO vo) {
		System.out.println("Create red extrusion success!");
		return 0;
	}

	public ReceiptVO createRedExtrusionAndCopy(ReceiptVO vo) {
		System.out.println("Create red extrusion and copy success!");
		return new ReceiptVO(null, null, null, 0, 0);
	}
}
