package dataservice.financedataservice.listdataservice;

import po.ReceiptPO;

public interface RedExtrusionDataService {
	public int createRedExtrusion(ReceiptPO vo);
    public int createRedExtrusionAndCopy(ReceiptPO vo);
}
