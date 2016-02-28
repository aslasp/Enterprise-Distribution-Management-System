package Data.datafactory;

import java.util.Comparator;

import po.ReceiptPO;

public	class SequenceOfReceiptPO implements Comparator<ReceiptPO> {
      public int compare(ReceiptPO a1, ReceiptPO a2) { 
             if(a1.getDate().equals(a2.getDate())){
    	        return a1.getType().compareTo(a2.getType());
             }
             else{
    	        return a1.getDate().compareTo(a2.getDate());
             }
      
      }
}