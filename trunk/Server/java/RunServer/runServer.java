package RunServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import Data.memberdata.Member;
import Data.stockdata.goods.Goods;
import Data.stockdata.goodsClass.GoodsClass;
import Data.userdata.User;


//rmi 启动 服务注册  11-17 By jin
public class runServer {
	public static void main(String[] args) {
		/**
		 * 加载安全机制
		 */
		System.setSecurityManager(new SecurityManager());

		try {

			// 客户端启用的端口号为1099 yan 11-18
			

			LocateRegistry.createRegistry(1099);// 客户端启用的端口号为1099 yan 11-18

			System.out.println("已启动服务器");
			User user = new User();
			Member member=new Member();
			GoodsClass gc=new GoodsClass();
			Goods g=new Goods();
			Naming.bind("memberService",member);
			Naming.bind("userService", user);	
			Naming.rebind("goodsClassService", gc);
			Naming.rebind("goodsService", g);
			
			 
			

		} catch (Exception e) {
			System.out.println("错误" + e);
		}
	}

}
