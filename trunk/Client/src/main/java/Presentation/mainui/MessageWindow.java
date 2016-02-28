package Presentation.mainui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;

import po.UserPO.UserJob;
import vo.ReceiptMessageVO;
import Presentation.uihelper.UIhelper;
import businesslogic.receiptbl.ReceiptMessage;
import businesslogicservice.receiptblservice.ReceiptTipService;

//消息提示
public class MessageWindow extends JWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int screenWidth = UIhelper.getScreenWidth();
	static int screenHeight = UIhelper.getScreenHeight();
	static int winWidth = screenWidth * 28 / 100;
	static int winHeight = screenHeight * 3 / 5;
	JList<String> list;
	JScrollPane jsp;
	UserJob type;
	Vector<String> message;
	MainFrame parent;
	boolean tag=false;
	int typetag;
	static MessageWindow instance = null;
	//单例
	private MessageWindow(MainFrame frame) {
		super(frame);
		parent=frame;
		type = frame.getUser().getJob();

		this.setBounds(20, 20, winWidth, winHeight);
		JPanel pnl = new JPanel();
		pnl.setBounds(0, 0, winWidth, winHeight);
		pnl.setBackground(Color.white);
		// ----------------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);
		pnl.setLayout(gbl);
		// ----------------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		JLabel title = new JLabel("消息中心");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		titlePnl.add(title);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 0.08;
		gbl.setConstraints(titlePnl, gbc);
		pnl.add(titlePnl);
		// ----------------------
		gbc.fill = GridBagConstraints.BOTH;
		JPanel mPnl = new JPanel();
		mPnl.setBackground(Color.white);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 6;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(mPnl, gbc);
		pnl.add(mPnl);
		mPnl.setLayout(new GridLayout(1, 1));
		// -----JList-------------
		message = new Vector<String>();
		tag=false;
		message.add("您暂时没有新消息!");
		list = new JList<String>(message);
		list.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		jsp = new JScrollPane(list);
		mPnl.add(jsp);
		// -----------------------
		this.add(pnl);
		this.setVisible(false);
		
			
	
	}

	
	public void getNewMessage(){
		try {
			ReceiptTipService service = new ReceiptMessage();
			ArrayList<ReceiptMessageVO> vo = null;
			switch (type) {
			case SALE:
				typetag=1;
				vo = service.getSaleApproved();
				break;
			case MANAGER:
				typetag=0;
				vo = service.getToApprove();
				break;
			case FINANCE:
				typetag=2;
				vo = service.getFinanceApproved();
				break;
			case STOCK:
				typetag=3;
				vo = service.getStockApproved();
				break;

			}
			if (vo != null) {
				tag=true;
				message.clear();
				if(parent.getMessage()!=null)
				{	
					parent.getMessage().setIcon(new ImageIcon("img/newMessage.gif"));
				}
				for (int i = 0; i < vo.size(); i++)
					message.add(vo.get(i).getInfo());
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static MessageWindow getInstance(MainFrame frame,int x,int y) {
		if (instance == null)
			instance = new MessageWindow(frame);
		instance.setLocation((x-winWidth),(y-winHeight));
		return instance;
	}
	
	public static MessageWindow getInstance(MainFrame frame){
		if (instance == null)
			instance = new MessageWindow(frame);
		return instance;
	}
	
	public void setUser(UserJob type){
		this.type=type;
	}
	public void setParent(MainFrame frame){
		this.parent=frame;
	}
	
	
	public void clear(){
			if(tag){
				ReceiptTipService service;
				try {
					service = new ReceiptMessage();
					for(int i=0;i<message.size();i++){
						service.deleteessage(new ReceiptMessageVO(typetag,message.get(i).toString()));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			message.clear();
			tag=false;
			message.add("你暂时没有新消息！");
			parent.getMessage().setIcon(new ImageIcon("img/message_w.png"));
		
	}

}
