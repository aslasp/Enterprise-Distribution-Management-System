package Data.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Data.memberdata.Member;
import Data.serutility.JXCFile;
import Data.stockdata.goods.Goods;
import po.AccountPO;
import po.BeginInfoPO;
import po.GoodsPO;
import po.MemberPO;
import dataservice.financedataservice.initdataservice.FinanceInitDataService;

public class Init extends UnicastRemoteObject implements FinanceInitDataService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int num=0;
	public Init() throws RemoteException{
		super();
	}
	public int initInfo(String time,BeginInfoPO po) throws RemoteException{
		JXCFile.init(time);
		ArrayList<MemberPO> member=po.getMember();
		if(member!=null)
		try {
			Member m=new Member();
			for(MemberPO p:member){
				m.add(p);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<GoodsPO> goods=po.getGoods();
		if(goods!=null){
			Goods g;
			try {
				g = new Goods();
				for(GoodsPO p:goods){
					g.addGoods(p);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArrayList<AccountPO> account=po.getAccount();
		if(account!=null){
			try {
				Account a=new Account();
				for(AccountPO p:account){
					a.addAccount(p);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		add(po);
		
		return 0;
	}
	
	public BeginInfoPO getInfo(String time) throws RemoteException{
		ArrayList<BeginInfoPO> buffer=showAll();
		if(buffer==null)
			return null;
		for(BeginInfoPO po:buffer){
			if(po.getTime().equals(time)){
				return po;
			}
		}
		
		return null;
	}
	
	public ArrayList<BeginInfoPO> showAll() throws RemoteException{
		JXCFile file=new JXCFile("src/main/java/begininfo.ser");
		ArrayList<Object> a=file.read();
		if(a==null)
			return null;
		
		ArrayList<BeginInfoPO> buffer=new ArrayList<BeginInfoPO>();
		for(Object b:a){
			BeginInfoPO c=(BeginInfoPO)b;
			buffer.add(c);
		}
		
		  return buffer;
	}
	
	public void add(BeginInfoPO po){
		JXCFile file=new JXCFile("src/main/java/begininfo.ser");
		file.write(po);
	}
	public String getCurrentTime() throws RemoteException {		
		return JXCFile.getCurrentTime();
	}
	
	public String getRecentStockTime(){
		try {
			ArrayList<BeginInfoPO> po=showAll();
			if(po==null)
				return "2014-12-21";
			else{
				BeginInfoPO pp=po.get(po.size()-1);
				String recent=pp.getTime();
				return recent;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "2014-12-21";
	}
	
	public void setTime(String s) throws RemoteException{
		JXCFile.setTime(s);
	}
	
	public void reset(){
		JXCFile.reset();
	}
	
	
}
