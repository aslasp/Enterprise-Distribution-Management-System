package Data.serutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class JXCFile {
	String name;

	public JXCFile(String s) {
		name = s;
	}

	public ArrayList<Object> read() {
		ArrayList<Object> ls = new ArrayList<Object>();

		try {
			FileInputStream fin = new FileInputStream(name);
			if (fin.available() <= 0) {
				fin.close();
				return null;
				
			} else {
				ObjectInputStream in = new ObjectInputStream(fin);
				while (fin.available() > 0) {
					ls.add(in.readObject());
				}
				in.close();

				return ls;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(name+"文件不存在");
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void write(ArrayList<Object> ls) {

		try {
			boolean isexist = false;
			File file = new File(name);
			if (file.exists())
				isexist = true;
			FileOutputStream fo = new FileOutputStream(name, true);
			long pos = 0;

			ObjectOutputStream os;
			os = new ObjectOutputStream(fo);
			if (isexist) {
				pos = fo.getChannel().position() - 4;
				fo.getChannel().truncate(pos);
			}

			for (Object o : ls) {
				os.writeObject(o);
			}
			os.close();
			
			//当删除所有数据后，直接将这个文件删除、、、yan
			if(ls.size()==0){
				File f=new File(name);
				f.delete();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//修改的情况下，需要删除原来的数据，重新写入，所以新建一个writeM给modify方法——yan
	public void writeM(ArrayList<Object> ls){
		File ff=new File(name);
		ff.delete();
		try {
			boolean isexist = false;
			File file = new File(name);
			if (file.exists())
				isexist = true;
			FileOutputStream fo = new FileOutputStream(name);
			long pos = 0;

			ObjectOutputStream os;
			os = new ObjectOutputStream(fo);
			if (isexist) {
				pos = fo.getChannel().position() - 4;
				fo.getChannel().truncate(pos);
			}

			for (Object o : ls) {
				os.writeObject(o);
			}
			os.close();
			
			//当删除所有数据后，直接将这个文件删除、、、yan
			if(ls.size()==0){
				File f=new File(name);
				f.delete();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 	public void write(Object o) {

		try {
			boolean isexist = false;
			File file = new File(name);
			if (file.exists())
				isexist = true;
			FileOutputStream fo = new FileOutputStream(name, true);
			long pos = 0;

			ObjectOutputStream os;
			os = new ObjectOutputStream(fo);
			if (isexist) {
				pos = fo.getChannel().position() - 4;
				fo.getChannel().truncate(pos);
			}
			os.writeObject(o);

			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
