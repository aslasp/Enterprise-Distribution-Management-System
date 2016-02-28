package dataservice.financedataservice.listdataservice;

import po.ReceiptPO;

public class RedExtrusionDataService_stub implements RedExtrusionDataService{

	public int createRedExtrusion(ReceiptPO po) {
		System.out.println("Create red extrusion success!");
		return 0;
	}

	public ReceiptPO createRedExtrusionAndCopy(ReceiptPO po) {
		System.out.println("Create red extrusion and copy success!");
		return new ReceiptPO();
	}

}
