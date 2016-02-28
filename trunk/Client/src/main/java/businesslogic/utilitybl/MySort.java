package businesslogic.utilitybl;

import java.util.ArrayList;
import java.util.Arrays;

import po.ReceiptPO;

public class MySort {
	public static ArrayList<ReceiptPO> sort(ArrayList<ReceiptPO> list){
		if(list==null) return null;
		ReceiptPO[] plist=new ReceiptPO[list.size()];
		for(int i=0;i<list.size();i++)
			plist[i]=list.get(i);
		Arrays.sort(plist,new MyComparator());
		list.clear();
		for(ReceiptPO p:plist)
			list.add(p);
		return list;
}
	
	public static ArrayList<ReceiptPO> hurrySort(ArrayList<ReceiptPO> list){
		if(list==null) return null;
		ArrayList<ReceiptPO> hurry=new ArrayList<ReceiptPO>();
		for(int i=0;i<list.size();i++)
			if(list.get(i).getHurry()==0){
				hurry.add(list.get(i));list.remove(i);
			}
		if(hurry.size()!=0){
			sort(hurry);
			sort(list);
		}else{
			sort(list);
		}
		hurry.addAll(list);
		return hurry;
	}

}
