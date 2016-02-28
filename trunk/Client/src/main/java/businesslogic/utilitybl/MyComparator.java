package businesslogic.utilitybl;

import java.util.Comparator;

import po.ReceiptPO;

public class MyComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		ReceiptPO p1=(ReceiptPO)o1;
		ReceiptPO p2=(ReceiptPO)o2;
		return p1.getDate().compareTo(p2.getDate());
		
		
	}
	

}
