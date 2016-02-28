package businesslogic.memberbl;

import java.rmi.Naming;
import java.text.NumberFormat;
import java.util.ArrayList;

import po.MemberPO;
import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import vo.MemberVO;
import businesslogic.utilitybl.getServer;
import businesslogicservice.memberblservice.MemberBLService;
import businesslogicservice.memberblservice.MemberViewService;
import dataservice.memberdataservice.MemberDataService;

public class Member implements MemberBLService,MemberViewService{
	 MemBaseInfo bInfo;
	 MemAccountInfo aInfo;
	 private MemContactInfo cInfo;
	 private MemberDataService service;
	
	
	public Member() throws Exception{
		String host=getServer.getServer();
		String url="rmi://"+host+"/memberService";
	
		service=(MemberDataService)Naming.lookup(url);
	
		
	}
	
	public int addMember(MemberVO vo) {
		// TODO Auto-generated method stub
		MemberPO po=voToPo(vo);
		
		return  service.add(po);
		
	}

	public int deleteMember(String memID) {
		// TODO Auto-generated method stub
		return service.delete(memID);
	}

	public int modifyMember(MemberVO vo) {
		// TODO Auto-generated method stub
		MemberPO po=voToPo(vo);
		return service.modify(po);
	}
	
	
	public ArrayList<MemberVO> findMember(String message) {
		// TODO Auto-generated method stu
		ArrayList<MemberPO> po=service.find(message);
		if(po==null) return null;
		ArrayList<MemberVO> vo=new ArrayList<MemberVO>();
		for(int i=0;i<po.size();i++){
			vo.add(poToVo(po.get(i)));
		}
		return vo;
	}
	
	public MemberVO findById(String ID) {
		// TODO Auto-generated method stub
		return poToVo(service.findByID(ID));
	}

	public ArrayList<MemberVO> showMembers() {
		// TODO Auto-generated method stub
		ArrayList<MemberPO> po=service.showAll();
		if(po==null)
			return null;
		ArrayList<MemberVO> vo=new ArrayList<MemberVO>();
		for(int i=0;i<po.size();i++)
			vo.add(poToVo(po.get(i)));
		return vo;
	}
	
	public ArrayList<MemberVO> show(MemberType type) {
		ArrayList<MemberPO> po=service.show(type);
		if(po==null)
			return null;
		
		return poToVo(po);
	}
	public int checkToReceive(String id,double m){
		MemberPO p=service.findByID(id);
		double toReceive=p.getToReceive();
		if(toReceive+m>p.getMaxOwe())
			return 1;
		return 0;
	}
	
	public int isToReceive(String id,double m){
		MemberVO v=findById(id);
		if((v.getToReceive()+m)<0){
			return 1;
		}
		return 0;
	}
	
	public int isToPay(String id,double m){
		MemberVO v=findById(id);
		if((v.getToPay()+m)<0){
			return 1;
		}
		return 0;
	}
	
	public int changeToReceive(String id,double m){
		return service.changeToReceive(id, m);
	}
	public int changeToPay(String id,double m){
		return service.changeToPay(id, m);
	}


	
	public void updatePoints(String id,double price){		
		MemberPO po=service.findByID(id);
		po.setPoints((price/100)+po.getPoints());
		po=updateLevel(po);
		service.modify(po);	
	}
	
	//表驱动
	public MemberPO updateLevel(MemberPO po){
		MemberLevel[]  level=new MemberLevel[]{MemberLevel.ONE,MemberLevel.TWO,
				MemberLevel.THREE,MemberLevel.FOUR,MemberLevel.FIVE};
		double[] spoint=new double[]{0,5000,15000,30000,50000};
		double p=po.getPoints();
		for(int i=spoint.length-1;i>=0;i--){
			if(p>spoint[i]&&(i==spoint.length-1?true:p<=spoint[i+1]))
				{po.setmLevel(level[i]);break;}
		}
		return po;	
	}

	
	
	public String getNewID(MemberType type) {
		// TODO Auto-generated method stub
		ArrayList<MemberPO> po=service.showAll();
		String lastID=null;
		if(po==null) lastID="0000001";
		else{
		for(int i=0;i<po.size();i++){
			if(po.get(i).getmType()==type)
				{lastID=po.get(i).getMemberID();}
		}
		if(lastID!=null)
		{	
			double d=Double.parseDouble(lastID.substring(4,11))+1;
		     NumberFormat nf = NumberFormat.getInstance();
		     nf.setMinimumIntegerDigits(7); 
		     nf.setGroupingUsed(false);
		     lastID=nf.format(d);
		}
		
		else lastID="0000001";}
		if(type==MemberType.JHS)
			return "JHS-"+lastID;
		else 
			return "XSS-"+lastID;
	}



	public int getSaleNum() {
		// TODO Auto-generated method stub
		int num=0;
		ArrayList<MemberPO> po=service.showAll();
		for(MemberPO p:po)
			if(p.getmType()==MemberType.XSS)
				num++;
		return num;
	}


	public int getPurchaseNum() {
		// TODO Auto-generated method stub
		int num=0;
		ArrayList<MemberPO> po=service.showAll();
		for(MemberPO p:po)
			if(p.getmType()==MemberType.JHS)
				num++;
		return num;
	}

	@Override
	public String[] getAllMemberName() {
		// TODO Auto-generated method stub
		ArrayList<MemberPO> mempo=service.showAll();
		if(mempo==null) return null;
		else{
			String memName[]=new String[mempo.size()+1];
			memName[0]="全部";
			for(int i=0;i<mempo.size();i++)
				memName[i+1]=mempo.get(i).getName();
			return memName;
		}
		
	}


	public MemberPO voToPo(MemberVO vo){
		MemberPO po=new MemberPO(vo.getMemberID(),vo.getmType(),vo.getmLevel(),vo.getName(),
				vo.getTel(),vo.getAddress(),vo.getPostcode(),vo.getEMail(),vo.getDefaultClerk()
				,vo.getMaxOwe(),vo.getToReceive(),vo.getToPay(),vo.getPoints());
		
		return po;
	}
	
	public ArrayList<MemberPO> voToPo(ArrayList<MemberVO> vo){
		ArrayList<MemberPO> po=new ArrayList<MemberPO>();
		for(int i=0;i<vo.size();i++){
			MemberPO p=voToPo(vo.get(i));
			po.add(p);
		}
		return po;
	}
	
	public MemberVO poToVo(MemberPO po){
	   if(po!=null){
	   bInfo=new MemBaseInfo(po.getmType(),po.getmLevel(),po.getMemberID(),po.getName(),po.getPoints(),po.getDefaultClerk());
	   aInfo=new MemAccountInfo(po.getMaxOwe(),po.getToReceive(),po.getToPay());
	   cInfo=new MemContactInfo(po.getTel(),po.getAddress(),po.getPostcode(),po.getEMail());
	   MemberVO vo=new MemberVO(bInfo,aInfo,cInfo);
	   return vo;}
	   else return null;
		
	}
	
	public ArrayList<MemberVO> poToVo(ArrayList<MemberPO> po){
		ArrayList<MemberVO> vo=new ArrayList<MemberVO>();
		for(int i=0;i<po.size();i++){
			MemberVO v=poToVo(po.get(i));
			vo.add(v);
		}
		return vo;
	}
	

}
