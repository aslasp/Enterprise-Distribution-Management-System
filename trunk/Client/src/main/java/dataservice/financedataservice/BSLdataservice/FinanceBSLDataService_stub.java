package dataservice.financedataservice.BSLdataservice;

import po.BSLPO;
public class FinanceBSLDataService_stub implements FinanceBSLDataService{
	
	public BSLPO viewBSL() {
		System.out.println("View businessstatementlist success!");
		return new BSLPO();
	}

	public int export(BSLPO a) {
		System.out.println("Export businessstatementlist success!");
		return 0;
	}
	
}
