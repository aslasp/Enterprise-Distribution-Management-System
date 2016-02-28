package Presentation.mainui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

//过滤XLS文件
public class XLSFilter extends FileFilter{
	 public boolean accept(File f){ 
		 if(f.isDirectory())
			 return true;
		 String[] filePostfix={"xls"};
		 for(String str:filePostfix){
			 if(getExtension(f).equals(str))
				 return true;
		 }
		 return false;	
	}

	 public String getDescription(){
		 return "Excel文档(*.xls)";
	 }
	 
	 
	 public static String getExtension(File f){
		 String ext="";
		 String s=f.getName();
		 int i=s.lastIndexOf('.');
		 if(i>0&&i<s.length()-1){
			 ext=s.substring(i+1).toLowerCase();
		 }
		 return ext;
	 }
}
