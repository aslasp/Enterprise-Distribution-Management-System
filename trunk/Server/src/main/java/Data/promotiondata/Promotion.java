package Data.promotiondata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;

import Data.datafactory.SequenceOfPromotionPO;
import Data.serutility.JXCFile;
import po.CouponPO;
import po.DiscountProPO;
import po.GiftCouponProPO;
import po.GiftGoodProPO;
import po.PackProPO;
import po.PromotionPO;
import po.MemberPO.MemberLevel;
import po.PromotionPO.PromotionType;
import dataservice.promotiondataservice.PromotionDataService;
/*
 * //促销有不同类型存储在不同的ser文件里  所以data层需要判断po的PromotionType  做不同的转换
			//存入 不同的文件   to小小黄
 */
public class Promotion extends UnicastRemoteObject implements PromotionDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;
	public Promotion() throws RemoteException {
		super();
		
	}
	
	public int Add(PromotionPO po) throws RemoteException {
		if(po.getType()==PromotionType.DISCOUNT){
			file=new JXCFile("discount.ser");
			file.write(po);
			return 0;
		}
		else if(po.getType()==PromotionType.GIFTCOUPON){
			file=new JXCFile("giftcoupon.ser");
			file.write(po);
			return 0;
		}
		else if(po.getType()==PromotionType.GIFTGOODS){
			file=new JXCFile("giftgoods.ser");
			file.write(po);
			return 0;
		}
		else if(po.getType()==PromotionType.PACK){
			file=new JXCFile("pack.ser");
			file.write(po);
			return 0;
		}
		return 1;
	}

	public PromotionPO find(String id,PromotionType type) throws RemoteException {
		if(type==PromotionType.PACK){
			ArrayList<PackProPO> al=getPackPro();
			if(al!=null){
			   for(PackProPO po:al){
				  if(po.getID().equals(id)){
				    	return po;
				  }
			   }	
			}
		}
		else if(type==PromotionType.GIFTCOUPON){
			ArrayList<GiftCouponProPO> al=getGiftCouponPro();
			if(al!=null){
			  for(GiftCouponProPO po:al){
				  if(po.getID().equals(id)){
					  return po;
				  }
			  }
			}
		}
		else if(type==PromotionType.DISCOUNT){
			ArrayList<DiscountProPO> al=getDiscountPro();
			if(al!=null){
			  for(DiscountProPO po:al){
			 	  if(po.getID().equals(id)){
					  return po;
				  }
			  }	
			}
		}
		else if(type==PromotionType.GIFTGOODS){
			ArrayList<GiftGoodProPO> al=getGiftGoodPro();
			if(al!=null){
			  for(GiftGoodProPO po:al){
				  if(po.getID().equals(id)){
					  return po;
				  }
			  }	
		    }
		}
		return null;
	}

	public int Modify(PromotionPO po) throws RemoteException {
		ArrayList<Object> all=new ArrayList<Object>();
		if(po.getType()==PromotionType.PACK){
			PackProPO it=(PackProPO)po;
			ArrayList<PackProPO> al=getPackPro();
			if(al==null)
				return 1;
			for(int i=0;i<al.size();i++){
				PackProPO p=al.get(i);
				if(p.getID().equals(it.getID())){
					p.setStartDate(it.getStartDate());
					p.setEndDate(it.getEndDate());
					p.setLevel(it.getLevel());
					p.setPackList(it.getPackList());
					p.setPackValue(it.getPackValue());
					p.setIsMatch(it.IsMatch());
					break;
				}
			}
			for(int i=0;i<al.size();i++)
				all.add(al.get(i));
			file=new JXCFile("pack.ser");
			file.writeM(all);
		}
		else if(po.getType()==PromotionType.DISCOUNT){
			DiscountProPO it=(DiscountProPO)po;
			ArrayList<DiscountProPO> al=getDiscountPro();
			if(al==null)
				return 1;
			
			for(int i=0;i<al.size();i++){
				DiscountProPO p=al.get(i);
				if(p.getID().equals(it.getID())){
					p.setStartDate(it.getStartDate());
					p.setEndDate(it.getEndDate());
					p.setLevel(it.getLevel());
					p.setDiscount(it.getCountList(),it.getGoodsList());
					p.setIsMatch(it.IsMatch());
					break;
				}
			}
			for(int i=0;i<al.size();i++)
				all.add(al.get(i));
			file=new JXCFile("discount.ser");
			file.writeM(all);
		}
		else if(po.getType()==PromotionType.GIFTCOUPON){
			GiftCouponProPO it=(GiftCouponProPO)po;
			ArrayList<GiftCouponProPO> al=getGiftCouponPro();
			if(al==null)
				return 1;
			for(int i=0;i<al.size();i++){
				GiftCouponProPO p=al.get(i);
				if(p.getID().equals(it.getID())){
					p.setStartDate(it.getStartDate());
					p.setEndDate(it.getEndDate());
					p.setLevel(it.getLevel());
					p.setCouponList(it.getCouponList());
					p.setTotalValue(it.getTotalValue());
					p.setIsMatch(it.IsMatch());
					break;
				}
			}
			for(int i=0;i<al.size();i++)
				all.add(al.get(i));
			file=new JXCFile("giftcoupon.ser");
			file.writeM(all);
		}
		else if(po.getType()==PromotionType.GIFTGOODS){
			GiftGoodProPO it=(GiftGoodProPO)po;
			ArrayList<GiftGoodProPO> al=getGiftGoodPro();
			if(al==null)
				return 1;
			for(int i=0;i<al.size();i++){
				GiftGoodProPO p=al.get(i);
				if(p.getID().equals(it.getID())){
					p.setStartDate(it.getStartDate());
					p.setEndDate(it.getEndDate());
					p.setLevel(it.getLevel());
					p.setGiftList(it.getGiftList());
					p.setTotalValue(it.getTotalValue());
					p.setIsMatch(it.IsMatch());
					break;
				}
			}
			for(int i=0;i<al.size();i++)
				all.add(al.get(i));
			file=new JXCFile("giftgoods.ser");
			file.writeM(all);
		}
		else{
			return 1;
		}
		return 0;
	}

	public ArrayList<PromotionPO> showAll() throws RemoteException {
		ArrayList<PromotionPO> all=new ArrayList<PromotionPO>();
		ArrayList<PackProPO> a1=getPackPro();
		if(a1!=null){
		    for(PackProPO po:a1){
			  all.add(po);
		    }
		}
		ArrayList<GiftCouponProPO> a2=getGiftCouponPro();
		if(a2!=null){
		  for(GiftCouponProPO po:a2){
			  all.add(po);
		  }
		}
		
		ArrayList<DiscountProPO> a3=getDiscountPro();
		if(a3!=null){
		  for(DiscountProPO po:a3){
			  all.add(po);
		  }
		}
		
		
		
		ArrayList<GiftGoodProPO> a4=getGiftGoodPro();
		if(a4!=null){
		  for(GiftGoodProPO po:a4){
			  all.add(po);
		  }
		}
		
		if(all.size()==0)
			return null;
		
		  Collections.sort(all,new SequenceOfPromotionPO());
		  
		  return all;
	}

	public ArrayList<PromotionPO> show(PromotionType type) throws RemoteException{
		ArrayList<PromotionPO> result=new ArrayList<PromotionPO>();
		if(type==PromotionType.PACK){
			ArrayList<PackProPO> p=getPackPro();
			if(p==null)
				return null;
			for(PackProPO po:p){
				result.add(po);
			}
		}
		else if(type==PromotionType.DISCOUNT){
			ArrayList<DiscountProPO> p=getDiscountPro();
			if(p==null)
				return null;
			for(DiscountProPO po:p){
				result.add(po);
			}
		}
		else if(type==PromotionType.GIFTCOUPON){
			ArrayList<GiftCouponProPO> p=getGiftCouponPro();
			if(p==null)
				return null;
			for(GiftCouponProPO po:p){
				result.add(po);
			}
		}
		else if(type==PromotionType.GIFTGOODS){
			ArrayList<GiftGoodProPO> p=getGiftGoodPro();
			if(p==null)
				return null;
			for(GiftGoodProPO po:p){
				result.add(po);
			}
		}
		else{
			return null;
		}
		
		return result;
	}
	
	public ArrayList<PackProPO> getPackPro() throws RemoteException{
		file=new JXCFile("pack.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<PackProPO> buffer=new ArrayList<PackProPO>();
		for(Object b:a){
			PackProPO po=(PackProPO)b;
			buffer.add(po);
		}
		
		return buffer;
	}
	
	
	//有可能因为ArrayList里嵌套ArrayList的原因
	public ArrayList<GiftCouponProPO> getGiftCouponPro() throws RemoteException{
		file=new JXCFile("giftcoupon.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<GiftCouponProPO> buffer=new ArrayList<GiftCouponProPO>();
		for(Object b:a){
			
			GiftCouponProPO po=(GiftCouponProPO)b;
			buffer.add(po);
		}
		
		return buffer;
	}
	
	public ArrayList<DiscountProPO> getDiscountPro() throws RemoteException{
		file=new JXCFile("discount.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<DiscountProPO> buffer=new ArrayList<DiscountProPO>();
		for(Object b:a){
			DiscountProPO po=(DiscountProPO)b;
			buffer.add(po);
		}
		
		return buffer;
	}
	
	public ArrayList<GiftGoodProPO> getGiftGoodPro() throws RemoteException{
		file=new JXCFile("giftgoods.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<GiftGoodProPO> buffer=new ArrayList<GiftGoodProPO>();
		for(Object b:a){
			GiftGoodProPO po=(GiftGoodProPO)b;
			buffer.add(po);
		}
		
		return buffer;
	}

	public int Delete(String id, PromotionType type) throws RemoteException {
		ArrayList<Object> all=new ArrayList<Object>();
		if(type==PromotionType.PACK){
			ArrayList<PackProPO> al=getPackPro();
			if(al==null)
				return 1;
			for(int i=0;i<al.size();i++){
				PackProPO p=al.get(i);
				if(p.getID().equals(id)){
					al.remove(i);
					break;
				}
			}
			for(int i=0;i<al.size();i++)
				all.add(al.get(i));
			file=new JXCFile("pack.ser");
			file.writeM(all);;
		}
		else if(type==PromotionType.DISCOUNT){
			ArrayList<DiscountProPO> al=getDiscountPro();
			if(al==null)
				return 1;
			
			for(int i=0;i<al.size();i++){
				DiscountProPO p=al.get(i);
				if(p.getID().equals(id)){
					al.remove(i);
					break;
				}
			}
			for(int i=0;i<al.size();i++)
				all.add(al.get(i));
			file=new JXCFile("discount.ser");
			file.writeM(all);
		}
		else if(type==PromotionType.GIFTCOUPON){
			ArrayList<GiftCouponProPO> al=getGiftCouponPro();
			if(al==null)
				return 1;
			for(int i=0;i<al.size();i++){
				GiftCouponProPO p=al.get(i);
				if(p.getID().equals(id)){
					al.remove(i);
					break;
				}
			}
			for(int i=0;i<al.size();i++)
				all.add(al.get(i));
			file=new JXCFile("giftcoupon.ser");
			file.writeM(all);
		}
		else if(type==PromotionType.GIFTGOODS){
			ArrayList<GiftGoodProPO> al=getGiftGoodPro();
			if(al==null)
				return 1;
			for(int i=0;i<al.size();i++){
				GiftGoodProPO p=al.get(i);
				if(p.getID().equals(id)){
				    al.remove(i);
					break;
				}
			}
			for(int i=0;i<al.size();i++)
				all.add(al.get(i));
			file=new JXCFile("giftgoods.ser");
			file.writeM(all);
		}
		else{
			return 1;
		}
		return 0;
	}
	/*
	public static void main(String[] args)throws Exception{
		
		Promotion p=new Promotion();
		/*ArrayList<GiftCouponProPO> t;
		
	t=p.getGiftCouponPro();
	//p.show(PromotionType.GIFTCOUPON);
	p.Delete("DJQ-20141207-001", PromotionType.GIFTCOUPON);
	for(GiftCouponProPO po:t){
			System.out.println(po.getStartDate()+po.getTotalValue());
		}
		System.out.println("---------------------------------------");

		ArrayList<CouponPO> cp=new ArrayList<CouponPO>();
		int i=p.Add(new GiftCouponProPO("a","b","c",MemberLevel.ONE,cp,100));
		System.out.println(i);

		System.out.println(p.Add(new GiftCouponProPO("DJQ-20141205-001","b","c",MemberLevel.ONE,new ArrayList<CouponPO>(),100)));
		ArrayList<CouponPO> pp=new ArrayList<CouponPO>();
		CouponPO ppp=new CouponPO("1111",100,false);
		pp.add(ppp);
		System.out.println(p.Add(new GiftCouponProPO("DJQ-20141205-001","b","c",MemberLevel.ONE,pp,100)));
		System.out.println(p.Add(new GiftCouponProPO("DJQ-20141206-002","b","c",MemberLevel.ONE,pp,100)));
		int result=p.Delete("DJQ-20141206-002",PromotionType.GIFTCOUPON);
		System.out.println(result);

		t=p.getGiftCouponPro();
		for(GiftCouponProPO po:t){
			System.out.println(po.getStartDate()+po.getTotalValue());
		}
	}
	 PromotionPO po=p.find("DJQ-20141230-001",PromotionType.GIFTCOUPON);
	 System.out.println(po.IsMatch());
	 if(po!=null){
		 po.setIsMatch(true);
		 p.Modify(po);
	 }
	po=p.find("DJQ-20141230-001",PromotionType.GIFTCOUPON);
	 System.out.println(po.IsMatch());
	 
	}*/
	
	public static void main(String[] args){
			try {
				Promotion p=new Promotion();
				ArrayList<GiftGoodProPO> g=p.getGiftGoodPro();
				GiftGoodProPO pp=g.get(1);
				System.out.println(pp.getTotalValue());
				pp.setTotalValue(101);
				p.Modify(pp);
				ArrayList<GiftGoodProPO> g1=p.getGiftGoodPro();
				GiftGoodProPO pp1=g1.get(1);
				System.out.println(pp1.getTotalValue());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
//	public static void main(String[] args){
//		try {
//			Promotion p=new Promotion();
//			ArrayList<DiscountProPO> g=p.getDiscountPro();
//			DiscountProPO pp=g.get(0);
//			System.out.println(pp.getLevel());
//			pp.setLevel(MemberLevel.FIVE);;
//			p.Modify(pp);
//			ArrayList<DiscountProPO> g1=p.getDiscountPro();
//			DiscountProPO pp1=g1.get(0);
//			System.out.println(pp1.getLevel());
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}
}
