package businesslogicservice.financeblservice.listblservice;

import java.util.ArrayList;

import vo.PaymentVO;

public interface PaymentBLService {
	public int createPayment(PaymentVO vo);
	public ArrayList<PaymentVO> getPayment();
	public String getNewID();
	public PaymentVO findByID(String s);
	public int modify(PaymentVO vo);
}
