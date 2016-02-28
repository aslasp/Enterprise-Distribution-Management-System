package Presentation.mainui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import po.UserPO.UserJob;
import vo.UserVO;
import Presentation.financeui.LeftLongPanel;
import Presentation.promotionui.ProLeftPanel;
import Presentation.salesui.SalesLeftPanel;
import Presentation.stockui.StockLeftPanel;
import Presentation.userui.UserLeftPanel;
/*
 * Author:huang
 * lastModify:12-31 by jin
 * 
 * */
public class MainFrame extends JFrame implements  ActionListener {
	
	private static final long serialVersionUID = 1L;
	int xOld, yOld;
	JSplitPane jsp;
	JPanel jp1, jp2, welcomePanel;
	JLabel jlb;
	String type;
	UserVO user;
	JLabel timeNow;
	JPanel function;
	Color[] color = new Color[2];
	JLabel messageBtn;
	MessageWindow mw;
	message m;
	boolean messageWinExist=false;
	MoveListener move;
	public MainFrame(UserVO myuser) throws Exception {
		
		user = myuser;
		
		//===========信箱线程处理================
		MessageWindow.getInstance(this).setUser(user.getJob());
		m=new message(MessageWindow.getInstance(MainFrame.this));
		m.startThread();
		MessageWindow.getInstance(this).setParent(this);

		
		JPanel welcomePanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			// 给panel加上图片
			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("img/mainFrame/new year.jpg");
				Image img = icon.getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(),
						icon.getImageObserver());
			}
		};
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp1, jp2);
		jsp.setOneTouchExpandable(true);

		this.setRightComponent(welcomePanel, 0);

		
		this.add(jsp);
		this.setTitle("进销存系统");
		this.setIconImage(new ImageIcon("qq.PNG").getImage());
		this.setSize(1100, 600);
		this.setLocation(150, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("img/icon.png").getImage());
		
		jsp.setDividerSize(0);
		setDividerLocation("long");
		jsp.setDividerSize(0);
		
		//设置主题色
		UserJob job = user.getJob();
		switch (job) {
		case MANAGER:
			type = "manager";
			setColor();
			this.setLeftComponent(new ProLeftPanel(this));
			break;
		case FINANCE:
		case FINANCEMANAGER:
			type = "finance";
			setColor();
			this.setLeftComponent(new LeftLongPanel(this));
			break;
		case ADMINSTRATOR:
			type = "adminstrator";
			setColor();
			this.setLeftComponent(new UserLeftPanel(this));
			break;
		case STOCK:
			type = "stock";
			setColor();
			this.setLeftComponent(new StockLeftPanel(this));
			break;
		case SALE:
		case SALEMANAGER:
			type = "sales";
			setColor();
			this.setLeftComponent(new SalesLeftPanel(this));
			break;
		}
		
		// 处理窗口拖动事件
		jsp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		move=new MoveListener();
		jsp.addMouseMotionListener(move);
	
	}
	class MoveListener extends MouseMotionAdapter{
		public void mouseDragged(MouseEvent e) {
			int xOnScreen = e.getXOnScreen();
			int yOnScreen = e.getYOnScreen();
			int xx = xOnScreen - xOld;
			int yy = yOnScreen - yOld;
			MainFrame.this.setLocation(xx, yy);
		}
	}
	
	//左右比例
	public void setDividerLocation(String s) {
		if (s.equals("long"))
			jsp.setDividerLocation(0.225);
		else
			jsp.setDividerLocation(0.150);
	}	
	
	
	public void setLeftComponent(JPanel a) {
		jsp.setLeftComponent(a);
	}

	//欢迎界面调用
	public void setRightComponent(JPanel a, int i) {
		a.setLayout(new BorderLayout());
		getFunction(i);
		a.add(function, BorderLayout.NORTH);
		jsp.setRightComponent(a);
	}
	
	//给其他界面调用，使界面跳转
	public void setRightComponent(JPanel a) {
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		getFunction(1);
		right.add(function, BorderLayout.NORTH);
		right.add(a, BorderLayout.CENTER);
		right.add(getFootPanel(), BorderLayout.SOUTH);
		jsp.setRightComponent(right);
	}

	
	//顶部功能键
	public void getFunction(int i) {
		JPanel button = new functionPane(this);
		button.setBackground(Color.WHITE);
		JPanel pane = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.RIGHT);
		pane.setLayout(flow);
		pane.add(button);
		if (i == 0){
			button.setOpaque(false);
			pane.setOpaque(false);
		} else {
			button.setBackground(Color.white);
			pane.setBackground(Color.WHITE);
		}
		function = pane;
	}

	public JPanel getFootPanel() {
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		JPanel jp3 = new JPanel();
		jp3.setBackground(Color.white);
		jp3.setLayout(gbl);
		Timer t = new Timer(1000, this);// 每隔一秒触发ActionEvent事件
		t.start();// 启动计时器
		timeNow = new JLabel(Calendar.getInstance().getTime().toLocaleString());
		jp3.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		JPanel timePnl=new JPanel();
		timePnl.setBackground(Color.white);
		timePnl.add(timeNow);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=1;
		gbc.weighty=0.1;
		gbl.setConstraints(timePnl, gbc);
		jp3.add(timePnl);
		
		//-----------信箱----------
		JPanel messageBtnPnl=new JPanel();
		messageBtnPnl.setBackground(Color.white);
		ImageIcon icon=new ImageIcon("img/message_w.png");
		messageBtn=new JLabel(icon);
		messageBtnPnl.add(messageBtn);
		messageBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		messageBtn.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
			if(messageWinExist==false){
				mw=MessageWindow.getInstance(MainFrame.this,e.getXOnScreen(),e.getYOnScreen());
				mw.setVisible(true);
				messageWinExist=true;
				}
				else{
					mw.setVisible(false);
					messageWinExist=false;
					mw.clear();
				}
			}
		});
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.weightx=0.1;
		gbc.weighty=0.1;
		gbl.setConstraints(messageBtnPnl, gbc);
		jp3.add(messageBtnPnl);
		return jp3;
	}

	public void setColor() {
		if (type.equals("finance")) {
			color[0] = new Color(242, 125, 5);
			color[1] = new Color(222, 105, 5);
		} else if (type.equals("sales")) {
			color[0] = new Color(47, 73, 136);
			color[1] = new Color(27, 53, 116);
		} else if (type.equals("stock")) {
			color[0] = new Color(51, 125, 86);
			color[1] = new Color(31, 105, 66);
		} else if (type.equals("manager")) {
			color[0] = new Color(115, 46, 126);
			color[1] = new Color(95, 26, 106);
		} else {
			color[0] = new Color(61, 49, 35);
			color[1] = new Color(220, 177, 131);
		}
	}

	public void actionPerformed(ActionEvent e) {
		this.timeNow.setText(Calendar.getInstance().getTime().toLocaleString());

	}

	//主题色
	public Color[] getTheme() {
		return this.color;
	}
	//信箱键
	public JLabel  getMessage(){
		return this.messageBtn;
	}
	public message getMessageThread(){
		return this.m;
	}
	public UserVO getUser() {
		return this.user;	
	}
	public void setUser(UserVO vo) {
		this.user = vo;
	}
	public  MouseMotionAdapter getMove(){
		return this.move;
	}
}
