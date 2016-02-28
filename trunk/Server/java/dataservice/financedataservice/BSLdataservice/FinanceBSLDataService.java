package dataservice.financedataservice.BSLdataservice;

import po.BSLPO;
import java.rmi.Remote;

public interface FinanceBSLDataService extends Remote{
	public BSLPO viewBSL();
	public int export(BSLPO a);
}
