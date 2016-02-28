package businesslogic.salesbl;

import java.util.ArrayList;

import po.CommodityPO;
import vo.CommodityVO;

public class Commodity {
	//String id, String name, String type, double price,
	//double last_bid, int num, double total, double cost, String tip)
	public static CommodityVO poToVO(CommodityPO po){
		CommodityVO vo=new CommodityVO(po.getID(),po.getName(),po.getType(),
				po.getPrice(),po.getLast_bid(),po.getNum(),po.getTotal(),po.getCost(),
				po.getTip());
		return vo;
	}
	
	//String id, String name, String type, double price,
//	double last_bid, int num, double total, double cost, String tip)
	public static CommodityPO voToPO(CommodityVO vo){
		CommodityPO po=new CommodityPO(vo.getID(),vo.getName(),vo.getType(),vo.getPrice(),
				vo.getLast_bid(), vo.getNum(),vo.getTotal(),vo.getCost(),vo.getTip());
		return po;
	}
	
	public static ArrayList<CommodityPO> voTPo(ArrayList<CommodityVO> vo){
		ArrayList<CommodityPO> List=new ArrayList<CommodityPO>();
	
		for(int i=0;i<vo.size();i++)
			List.add(voToPO(vo.get(i)));
		return List;
	}
	
	public static ArrayList<CommodityVO> poTVo(ArrayList<CommodityPO> po){
		ArrayList<CommodityVO> List=new ArrayList<CommodityVO>();
		
		for(int i=0;i<po.size();i++)
			List.add(poToVO(po.get(i)));
		return List;
	}
	
	
	//红冲
	public static ArrayList<CommodityPO>  getRedList(ArrayList<CommodityPO> list){
		ArrayList<CommodityPO> result=new ArrayList<CommodityPO>();
		for(int i=0;i<list.size();i++){
			CommodityPO c=list.get(i);
			CommodityPO com=new CommodityPO(c.getID(),c.getName(),
					c.getType(),c.getPrice(),c.getLast_bid(),-c.getNum(),-c.getTotal(),
					-c.getCost(),c.getTip());
			result.add(com);
		}
		return result;
	}
	
}	

