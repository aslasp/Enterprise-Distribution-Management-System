package Presentation.mainui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import po.UserPO.UserJob;
import vo.UserVO;
import Presentation.userui.UserSet;
import businesslogic.userbl.User;
import businesslogicservice.userblservice.UserBLService;

//左侧菜单panel 头像Pane 构造
public class headPane extends JPanel{
	 JLabel head,info,name,job;
	 static JLabel grade;
	 int width,height;
	 JFrame rame;
	 Border border;
	 Color[] theme=new Color[2];
	 static UserVO user;
	public headPane( MainFrame frame,JPanel parent,UserVO user,int i){
		//--------------userInfo Pane构造
		rame=frame;
		theme=frame.getTheme();
		Color color=theme[0];
		this.setBackground(color);
		width=parent.getWidth();
		height=parent.getHeight();
		this.setLayout(new FlowLayout());
		name=new JLabel(user.getName());
		grade=new JLabel("业绩点："+user.getGrades());
		this.user=user;
		switch(user.getJob()){
		case MANAGER:
			head=new JLabel(new ImageIcon("img/promotion/head.png"));
			job=new JLabel("总经理");	
			break;		
		case FINANCE:
		case FINANCEMANAGER:
			head=new JLabel(new ImageIcon("img/finance/xxh.png"));
			job=new JLabel("财务人员");	
			break;
		case STOCK:
			head=new JLabel(new ImageIcon("img/stock/ysk.png"));
			job=new JLabel("库存人员");	
			break;
		case ADMINSTRATOR:
			head=new JLabel(new ImageIcon("img/login/admin.png"));
			job=new JLabel("系统管理员");
			break;
		case SALE:
		case SALEMANAGER:
			head=new JLabel(new ImageIcon("img/sales/wn.png"));
			job=new JLabel("销售人员");
			break;		
		}
		if(user.getJob()==UserJob.FINANCEMANAGER)
			job.setText("财务经理");
		else if(user.getJob()==UserJob.SALEMANAGER)
			job.setText("销售经理");
		border=BorderFactory.createLineBorder(new Color(0,0,0,0f));
		head.setBorder(border);
		name.setForeground(Color.WHITE);
		job.setForeground(Color.WHITE);
		grade.setForeground(Color.white);
		head.setSize(width/3, height/5);
		head.setLocation(0, 0);
		head.setToolTipText("修改头像");
		head.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		head.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				head.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
			public void mouseExited(MouseEvent e){
				head.setBorder(border);
			}
		});
		info=new JLabel(new ImageIcon("img/promotion/infoicon.png"));
		info.setToolTipText("用户设置");
		ImageIcon icon=(ImageIcon) info.getIcon();
		info.setSize(icon.getIconWidth(),icon.getIconHeight());
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JPanel userInfo=new JPanel();
		userInfo.setBackground(this.getBackground());
		userInfo.setLayout(new GridLayout(3,1));
		userInfo.add(name);userInfo.add(job);userInfo.add(grade);
		userInfo.setSize(width/2, height/5);
		this.add(head);
		this.add(info);
		if(i==0){
		this.add(userInfo);}
		this.setSize(width,height/5);
		this.setVisible(true);
		info.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				 
				UserSet set=new UserSet(rame,headPane.this);
				set.show(info,e.getX(),e.getY());
				//System.out.println("我被点击了");
				
			}
		});
	
	}
	
	public static void  RefreshGrades() throws Exception{
		UserBLService service=new User();
		double gra=service.showUser(user.getID()).getGrades();
		grade.setText("业绩点："+gra);
	}
}
