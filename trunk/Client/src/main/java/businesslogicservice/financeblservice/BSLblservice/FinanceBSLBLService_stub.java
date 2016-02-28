package businesslogicservice.financeblservice.BSLblservice;

import vo.BSLVO;

public class FinanceBSLBLService_stub implements FinanceBSLBLService{

	public BSLVO viewBSL() {
	    System.out.println("View businessstatementlist success!");
		return new BSLVO();
	}

	public int export(BSLVO a) {
		System.out.println("Export businessstatementlist success!");
		return 0;
	}
}
