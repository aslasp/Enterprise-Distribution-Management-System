package businesslogicservice.financeblservice.listblservice;

import java.util.ArrayList;

import vo.PaymentVO;

public class PaymentBLService_stub implements PaymentBLService{
	public int createPayment(PaymentVO vo) {
		System.out.println("Create payment success!");
		return 0;
	}

	public ArrayList<PaymentVO> getPayment() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNewID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentVO findByID(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(PaymentVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
