package po;

import java.io.Serializable;


public class BeginInfoPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    GoodsPO goods;
	    MemberPO member;
	    AccountPO account;
	    String time;
	    
	    public BeginInfoPO(){
	    	goods=null;
	    	member=null;
	    	account=null;
	    }
	    
	    public BeginInfoPO(GoodsPO a,MemberPO b,AccountPO c){
	    	goods=a;
	    	member=b;
	    	account=c;
	    }
	    
	    public GoodsPO getGoods(BeginInfoPO vo){
	    	return goods;
	    }
	    
	    public MemberPO getMember(BeginInfoPO vo){
	    	return member;
	    }
	    
	    public AccountPO getAccount(AccountPO vo){
	    	return account;
	    }


}
