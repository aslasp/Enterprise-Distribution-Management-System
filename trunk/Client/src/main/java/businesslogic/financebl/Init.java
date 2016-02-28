package businesslogic.financebl;

import java.rmi.Naming;
import java.util.ArrayList;

import po.AccountPO;
import po.BeginInfoPO;
import po.GoodsPO;
import po.MemberPO;
import dataservice.financedataservice.initdataservice.FinanceInitDataService;
import vo.AccountVO;
import vo.BeginInfoVO;
import vo.GoodsVO;
import vo.MemberVO;
import businesslogic.memberbl.MemAccountInfo;
import businesslogic.memberbl.MemBaseInfo;
import businesslogic.memberbl.MemContactInfo;
import businesslogic.utilitybl.getServer;
import businesslogicservice.financeblservice.initblservice.FinanceInitBLService;

public class Init implements FinanceInitBLService{

	FinanceInitDataService service;
	 
	 public Init()throws Exception{
		 //   	System.setSecurityManager(new SecurityManager());
		        String host=getServer.getServer();
		 		String url="rmi://"+host+"/initService";
		 		
		 		service=(FinanceInitDataService)Naming.lookup(url);
		     }
	
	@Override
	public int initInfo(String time, BeginInfoVO vo) {
		BeginInfoPO po=voToPo(vo);
		return service.initInfo(time, po);
	}

	@Override
	public BeginInfoVO getInfo(String time) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BeginInfoPO voToPo(BeginInfoVO vo){
		if(vo==null)
			return null;
		
		ArrayList<GoodsVO> goods=vo.getGoods();
		ArrayList<GoodsPO> a;
		if(goods==null)
			a=null;
		else{
			a=new ArrayList<GoodsPO>();
			for(GoodsVO g:goods){
				GoodsPO p=new GoodsPO(g.getGoodsID(),g.getName(),g.getSize(),g.getNumInStock(),g.getPurchasePrice(),g.getPrice(),g.getLastPurchasePrice(),g.getLastPrice(),g.getGoodsClass(),g.getManufactureDate(),g.getMinNumInstock());
                a.add(p);
			}
		}
		
		ArrayList<MemberVO> member=vo.getMember();
		ArrayList<MemberPO> b;
		if(member==null)
			b=null;
		else{
			b=new ArrayList<MemberPO>();
			for(MemberVO m:member){
				MemberPO p=new MemberPO(m.getMemberID(),m.getmType(),m.getmLevel(),m.getName(),m.getTel(),m.getAddress(),m.getPostcode(),m.getEMail(),m.getDefaultClerk(),m.getMaxOwe(),m.getToReceive(),m.getToPay(),m.getPoints());
                b.add(p);
			}
		}
		
		ArrayList<AccountVO> account=vo.getAccount();
		ArrayList<AccountPO> c;
		if(account==null)
			c=null;
		else{
			c=new ArrayList<AccountPO>();
			for(AccountVO m:account){
				AccountPO p=new AccountPO(m.getName(),m.getMoney());
                c.add(p);
			}
		}
		
	
		BeginInfoPO po=new BeginInfoPO(vo.getTime(),a,b,c,vo.getUserID());
		return po;
	}
	
	public ArrayList<BeginInfoPO> voToPo(ArrayList<BeginInfoVO> vo){
		if(vo==null)
			return null;
		ArrayList<BeginInfoPO> po=new ArrayList<BeginInfoPO>();
		for(BeginInfoVO v:vo){
			BeginInfoPO p=voToPo(v);
			po.add(p);
		}
		
		return po;
	}
	
	public BeginInfoVO poToVo(BeginInfoPO po){
		if(po==null)
			return null;
		
		ArrayList<GoodsPO> goods=po.getGoods();
		ArrayList<GoodsVO> a;
		if(goods==null)
			a=null;
		else{
			a=new ArrayList<GoodsVO>();
			for(GoodsPO g:goods){
				GoodsVO v=new GoodsVO(g.getGoodsID(),g.getName(),g.getSize(),g.getNumInStock(),g.getPurchasePrice(),g.getPrice(),g.getLastPurchasePrice(),g.getLastPrice(),g.getGoodsClassName(),g.getManufactureDate(),g.getMinNumInStock());
                a.add(v);
			}
		}
		
		ArrayList<MemberPO> member=po.getMember();
		ArrayList<MemberVO> b;
		if(member==null)
			b=null;
		else{
			b=new ArrayList<MemberVO>();
			for(MemberPO m:member){
				MemBaseInfo mbi=new MemBaseInfo(m.getmType(),m.getmLevel(),m.getMemberID(),m.getName(), m.getPoints(),m.getDefaultClerk());
				MemAccountInfo mai=new MemAccountInfo(m.getMaxOwe(),m.getToReceive(),m.getToPay());
				MemContactInfo mci=new MemContactInfo(m.getTel(),m.getAddress(),m.getPostcode(),m.getEMail());
				MemberVO v=new MemberVO(mbi,mai,mci);
                b.add(v);
			}
		}
		
		ArrayList<AccountPO> account=po.getAccount();
		ArrayList<AccountVO> c;
		if(account==null)
			c=null;
		else{
			c=new ArrayList<AccountVO>();
			for(AccountPO m:account){
				AccountVO v=new AccountVO(m.getName(),m.getMoney());
                c.add(v);
			}
		}
		
	
		BeginInfoVO vo=new BeginInfoVO(po.getTime(),a,b,c,po.getUserID());
		return vo;
	}

	public ArrayList<BeginInfoVO> poToVo(ArrayList<BeginInfoPO> po){
		if(po==null)
			return null;
		ArrayList<BeginInfoVO> vo=new ArrayList<BeginInfoVO>();
		for(BeginInfoPO p:po){
			BeginInfoVO v=poToVo(p);
			vo.add(v);
		}
		
		return vo;
	}
	
	
	@Override
	public String getCurrentTime() {
		return service.getCurrentTime();
	}

	@Override
	public ArrayList<BeginInfoVO> showAll() {
		ArrayList<BeginInfoPO> po=service.showAll();
		return poToVo(po);
	}

	@Override
	public void setTime(String s) {
		service.setTime(s);
	}

	@Override
	public void reset() {
		service.reset();
	}

}
