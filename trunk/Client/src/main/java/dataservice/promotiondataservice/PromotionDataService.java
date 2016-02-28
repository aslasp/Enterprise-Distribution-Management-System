package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.PromotionPO;
import po.PromotionPO.PromotionType;

public interface PromotionDataService extends Remote{
	
		public int Add(PromotionPO po);
		public PromotionPO find(String id,PromotionType type);
		public int Delete(String id,PromotionType type);;
		public int Modify(PromotionPO po);
		public ArrayList<PromotionPO>showAll();
		public ArrayList<PromotionPO>show(PromotionType type);
		//促销有不同类型存储在不同的ser文件里  所以data层需要判断po的PromotionType  做不同的转换
		//存入 不同的文件   to小小黄


	
}
