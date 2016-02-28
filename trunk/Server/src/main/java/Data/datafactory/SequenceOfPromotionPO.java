package Data.datafactory;

import java.util.Comparator;

import po.PromotionPO;



public	class SequenceOfPromotionPO implements Comparator<PromotionPO> {
      public int compare(PromotionPO a1, PromotionPO a2) { 
      
           if(a1.getDate().equals(a2.getDate())){
    	      return a1.getType().compareTo(a2.getType());
           }
           else{
    	      return a1.getDate().compareTo(a2.getDate());
           }
      
      }
}
