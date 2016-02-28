package dataservice.financedataservice.BSLdataservice;

import po.BSLPO;

public interface FinanceBSLDataService {
	public BSLPO viewBSL();
	public int export(BSLPO a);
}
