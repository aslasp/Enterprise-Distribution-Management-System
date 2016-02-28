package po;

import java.io.Serializable;
import java.util.ArrayList;


public class BeginInfoPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    ArrayList<GoodsPO> goods;
	    ArrayList<MemberPO> member;
	    ArrayList<AccountPO> account;
	    String time;
	    String userID;
	    
	    public BeginInfoPO(){
	    	time=null;
	    	goods=null;
	    	member=null;
	    	account=null;
	    	userID=null;
	    }
	    
	    public BeginInfoPO(String itime,ArrayList<GoodsPO> a,ArrayList<MemberPO> b,ArrayList<AccountPO> c,String user){
	    	time=itime;
	    	goods=a;
	    	member=b;
	    	account=c;
	    	userID=user;
	    }
	    
	    public String getTime(){
	    	return time;
	    }
	    
	    public ArrayList<GoodsPO> getGoods(){
	    	return goods;
	    }
	    
	    public ArrayList<MemberPO> getMember(){
	    	return member;
	    }
	    
	    public ArrayList<AccountPO> getAccount(){
	    	return account;
	    }
	    
	    public String getUserID(){
	    	return userID;
	    }


}
