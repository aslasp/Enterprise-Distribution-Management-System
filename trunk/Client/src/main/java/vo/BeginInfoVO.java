package vo;

import java.util.ArrayList;

public class BeginInfoVO {
		//商品，客户，账户初始信息
	    ArrayList<GoodsVO> goods;
	    ArrayList<MemberVO> member;
	    ArrayList<AccountVO> account;
	    String time;
	    String userID;//套账创建者
	    
	    public BeginInfoVO(){
	    	time=null;
	    	goods=null;
	    	member=null;
	    	account=null;
	    	userID=null;
	    }
	    
	    public BeginInfoVO(String itime,ArrayList<GoodsVO> a,ArrayList<MemberVO> b,ArrayList<AccountVO> c,String user){
            time=itime;
	    	goods=a;
	    	member=b;
	    	account=c;
	    	userID=user;
	    }
	    
	    public ArrayList<GoodsVO> getGoods(){
	    	return goods;
	    }
	    
	    public ArrayList<MemberVO> getMember(){
	    	return member;
	    }
	    
	    public ArrayList<AccountVO> getAccount(){
	    	return account;
	    }
	    
	    public String getTime(){
	    	return time;
	    }
	    
	    public String getUserID(){
	    	return userID;
	    }


}
