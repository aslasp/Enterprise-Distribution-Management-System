package businesslogic.utilitybl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class getServer {
	private static String host=null;
	
	public static String getServer() throws IOException {
		if(host==null){
			BufferedReader br=new BufferedReader(new FileReader("Port.txt"));
			String str=null;
			String port=null;
			while((str=br.readLine())!=null){
				port=str;
			}
			br.close();
			String ip=null;
			br=new BufferedReader(new FileReader("IP.txt"));
			str=null;
			while((str=br.readLine())!=null){
				ip=str;
			}
			br.close();
			host=ip+":"+port;
			return host;
		}else{
			return host;
		}
	}
	

}
