package Presentation.uihelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import vo.ReceiptVO;

public class MySort {
	
	public static ArrayList<ReceiptVO> sort(ArrayList<ReceiptVO> list){
		if(list==null) return null;
		ReceiptVO vlist[]=new ReceiptVO[list.size()];
		for(int i=0;i<list.size();i++)
			vlist[i]=list.get(i);
		Arrays.sort(vlist,new MyComparator());
		list.clear();
		for(ReceiptVO v:vlist)
			list.add(v);
		return list;
		
	}
	
	

}

class MyComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		ReceiptVO v1=(ReceiptVO)o1;
		ReceiptVO v2=(ReceiptVO)o2;
		String date1=v1.getId().split("-")[1];
		String date2=v2.getId().split("-")[1];
		return date1.compareTo(date2);
	}
	
}
