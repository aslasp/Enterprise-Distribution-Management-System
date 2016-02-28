package Presentation.mainui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Presentation.uihelper.UIhelper;

//输入服务器IP和port
public class IPFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	int screenWidth = UIhelper.getScreenWidth();
	int screenHeight = UIhelper.getScreenHeight();
	int frameWidth = 410;
	int frameHeight = 270;
	int xOld,yOld;
	// ------------------------
	Font font = new Font("微软雅黑", Font.PLAIN, 14);
	JButton submitBtn, exitBtn;
	JComboBox<String> IPBox, portBox;
	ArrayList<String> port,IP;

	public IPFrame() {
		this.setTitle("进销存系统");
		this.setIconImage(UIhelper.getImage("img/icon.png"));
		this.setBounds((screenWidth - frameWidth) / 2,
				(screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		this.setLayout(new GridLayout(1, 1));
		// -------------------
		JPanel pnl = new JPanel(){
			private static final long serialVersionUID = 1L;
			// 给panel加上图片
			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("img/net.png");
				Image img = icon.getImage();
				g.drawImage(img, 0, 0, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
			}
		};
		this.add(pnl);
		//--------------------
		pnl.setLayout(new GridLayout(4, 1));
		JPanel titlePnl = new JPanel();
		titlePnl.setOpaque(false);
		pnl.add(titlePnl);
		JLabel title=new JLabel("欢迎使用进销存系统");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		title.setForeground(Color.white);
		titlePnl.add(title);
		//----------------------
		JPanel top = new JPanel();
		top.setOpaque(false);
		pnl.add(top);
		JPanel mid = new JPanel();
		mid.setOpaque(false);
		pnl.add(mid);
		JPanel bottom = new JPanel();
		bottom.setOpaque(false);
		pnl.add(bottom);
		// -------IP-----------
		JLabel IPLbl = new JLabel("请输入IP：");
		IPLbl.setForeground(Color.white);
		IPLbl.setFont(font);
		top.add(IPLbl);
		IPBox = new JComboBox<String>();
		IPBox.setFont(font);
		IPBox.setBackground(Color.white);
		IPBox.setEditable(true);
		top.add(IPBox);
		// -------Port-------------
		JLabel portLbl = new JLabel("端口：");
		portLbl.setFont(font);
		portLbl.setForeground(Color.white);
		mid.add(portLbl);
		portBox =  new JComboBox<String>();
		portBox.setFont(font);
		portBox.setBackground(Color.white);
		portBox.setEditable(true);
		mid.add(portBox);
		init();
		// --------buttons---------
		submitBtn = new JButton("确定");
		submitBtn.setFont(font);
		submitBtn.setFocusPainted(false);
		submitBtn.setBackground(new Color(166, 210, 121));
		bottom.add(submitBtn);
		submitBtn.addActionListener(this);
		this.getRootPane().setDefaultButton(submitBtn);	
		
		bottom.add(new JLabel());
		exitBtn = new JButton("取消");
		exitBtn.setFont(font);
		exitBtn.setFocusPainted(false);
		exitBtn.setBackground(new Color(251, 147, 121));
		bottom.add(exitBtn);
		exitBtn.addActionListener(this); 
	
		// -------------------
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
		//处理窗口拖动事件
		  this.addMouseListener(new MouseAdapter() {  
	            public void mousePressed(MouseEvent e) {  
	                xOld = e.getX();  
	                yOld = e.getY();  
	            }  
	        });  
	        this.addMouseMotionListener(new MouseMotionAdapter() {  
	            @Override  
	            public void mouseDragged(MouseEvent e) {  
	                int xOnScreen = e.getXOnScreen();  
	                int yOnScreen = e.getYOnScreen();  
	                int xx = xOnScreen - xOld;  
	                int yy = yOnScreen - yOld;  
	                IPFrame.this.setLocation(xx, yy);  
	            }  
	        });  

		

	}

	//获取本地历史记录
	public void init(){
		port=new ArrayList<String>();
		IP=new ArrayList<String>();
		try {
			//初始化Port
			BufferedReader br=new BufferedReader(new FileReader("Port.txt"));
			String str=null;
				while((str=br.readLine())!=null){
					port.add(str);
				}
			br.close();
			for(int i=port.size()-1;i>=0;i--)
				portBox.addItem(port.get(i));
			portBox.setSelectedIndex(0);
			
			//初始化IP
			br=new BufferedReader(new FileReader("IP.txt"));
			str=null;
			while((str=br.readLine())!=null){
				IP.add(str);
			}
			br.close();
			for(int i=IP.size()-1;i>=0;i--)
				IPBox.addItem(IP.get(i));
			IPBox.setSelectedIndex(0);
		} catch (IOException e) {
				// TODO Auto-generated catch block
			portBox.setToolTipText("请输入端口号!");
			IPBox.setToolTipText("请输入服务器IP地址！");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==exitBtn){
			this.dispose();
		}else if(e.getSource()==submitBtn){
			this.dispose();
			//存储历史记录
			String txt=IPBox.getSelectedItem().toString();
			String file="IP.txt";
			write(txt,file,IP);
			txt=portBox.getSelectedItem().toString();
			file="Port.txt";
			write(txt,file,port);
			try {
				new LoginFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
	}

	public void write(String txt,String file,ArrayList<String> set){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			for(int i=0;i<set.size();i++)
				if(set.get(i).equals(txt)){
					set.remove(i);
					break;
			}
			set.add(txt);
			int more=set.size()-10;
			if(more>0){
				while(more>0){
					set.remove(0);
					more--;
				}
			}
			for(int i=0;i<set.size();i++)
				bw.write(set.get(i)+"\r\n");
			bw.close();
		
		} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		
	}
	
	
	

}
