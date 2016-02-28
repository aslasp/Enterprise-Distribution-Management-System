package businesslogic.promotionbl;

import java.util.ArrayList;

import po.CommodityPO;
import po.PackPO;
import vo.CommodityVO;
import vo.PackVO;
import businesslogic.salesbl.Commodity;

public class pack {
	
	static Commodity com=new Commodity();
	public static PackVO poToVo(PackPO po){
		ArrayList<CommodityPO> clist=po.getCombine();
		ArrayList<CommodityVO> vlist=com.poTVo(clist);
		PackVO vo=new PackVO(po.getTotalValue(),po.getPackValue(),vlist);
		return vo;
	}
	
	
	public static PackPO voToPo(PackVO vo){
		ArrayList<CommodityVO> clist=vo.getCombine();
		ArrayList<CommodityPO> vlist=com.voTPo(clist);
		PackPO po=new PackPO(vo.getTotalValue(),vo.getPackValue(),vlist);
		return po;
	}

}
