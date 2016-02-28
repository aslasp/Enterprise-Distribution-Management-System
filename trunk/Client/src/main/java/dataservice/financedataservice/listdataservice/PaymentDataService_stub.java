package dataservice.financedataservice.listdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentPO;

public class PaymentDataService_stub implements PaymentDataService{
	public int createPayment(PaymentPO po) {
		System.out.println("Create payment success!");
		return 0;
	}

	public ArrayList<PaymentPO> getPayment() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNum() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaymentPO findByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(PaymentPO po) {
		// TODO Auto-generated method stub
		return 0;
	}
}
