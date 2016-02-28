package Presentation.mainui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import vo.LogVO;
import vo.UserVO;
import Presentation.uihelper.UIhelper;
import businesslogic.userbl.User;
import businesslogicservice.userblservice.UserBLService;

/*
 * Author:wang
 * lastModify:12-1 By jin
 * 
 * */
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	String id, key;
	JTextField idField;
	JPasswordField passwordField;
	JLabel label;
	int screenHeight, screenWidth, frameHeight, frameWidth;
	int xOld, yOld;

	public LoginFrame() throws Exception {
		
		// 获得与分辨率匹配的大小
		screenHeight = UIhelper.getScreenHeight();
		screenWidth = UIhelper.getScreenWidth();
		frameHeight = 480;
		frameWidth = 300;
		this.setSize(frameWidth, frameHeight);
		this.setLocation((screenWidth - frameWidth) / 2,
				(screenHeight - frameHeight) / 2);
		this.setTitle("欢迎使用进销存系统");
		// 设置图标
		this.setIconImage(new ImageIcon("img/icon.png").getImage());

		// ----------覆盖整个窗口的MainPanel---------------------------------------------
		JPanel mainPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			// 给panel加上图片
			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("img/Login/login.jpg");
				Image img = icon.getImage();
				g.drawImage(img, 0, 0, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
			}
		};

		
		//-------- 关闭按钮----------
		final ImageIcon close = new ImageIcon("img/Login/closeButton.png");
		final ImageIcon close1 = new ImageIcon("img/Login/closeButton1.png");
		final JLabel closeButton = new JLabel(close);
		closeButton.setSize(close.getIconWidth(), close.getIconHeight());
		closeButton.setLocation(getWidth() - 20, 0);
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//-------- 最小化按钮----------
		final ImageIcon min = new ImageIcon("img/Login/minButton.png");
		final ImageIcon min1 = new ImageIcon("img/Login/minButton1.png");
		final JLabel minButton = new JLabel(min);
		minButton.setSize(min.getIconWidth(), min.getIconHeight());
		minButton.setLocation(getWidth() - 45, 0);
		minButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		class buttonListener extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == minButton)
					LoginFrame.this.setExtendedState(JFrame.ICONIFIED);
				else
					System.exit(0);
			}
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == minButton)
					minButton.setIcon(min1);
				else
					closeButton.setIcon(close1);
			}
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == minButton)
					minButton.setIcon(min);
				else
					closeButton.setIcon(close);
			}
		}
		buttonListener bl = new buttonListener();
		minButton.addMouseListener(bl);
		closeButton.addMouseListener(bl);

		mainPanel.add(minButton);
		mainPanel.add(closeButton);

		// 设置mainPanel的大小和位置：同frame一样大小，覆盖整个frame
		mainPanel.setSize(frameWidth, frameHeight);
		mainPanel.setLocation(0, 0);

		// --------------头像---------------
		ImageIcon icon = new ImageIcon("img/Login/tou.png");
		icon.setImage(icon.getImage().getScaledInstance(180, 180,
				Image.SCALE_DEFAULT));
		label = new JLabel();
		label.setLocation(55, 50);
		label.setIcon(icon);
		label.setSize(icon.getIconWidth(), icon.getIconHeight());
		
		// ------------在mainPanel上添加组件------------------------------------------------
		// 设置用户名文本域
		idField = new JTextField();
		idField.setSize(frameWidth * 64 / 100, frameHeight / 16);
		idField.setLocation(frameWidth * 26 / 100, frameHeight * 55 / 100);
		idField.setVisible(true);

		//实时检测id
		idField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				try {
					UserBLService service = new User();
					UserVO v = service.showUser(idField.getText());
					if (v != null) {
						switch (v.getJob()) {
						case MANAGER:
							label.setIcon(new ImageIcon("img/Login/head_p.png"));
							break;
						case FINANCE:
						case FINANCEMANAGER:
							label.setIcon(new ImageIcon("img/Login/head_f.png"));
							break;
						case SALE:
						case SALEMANAGER:
							label.setIcon(new ImageIcon("img/Login/head_s.png"));
							break;
						case STOCK:
							label.setIcon(new ImageIcon("img/Login/head_k.png"));
							break;
						default:
							label.setIcon(new ImageIcon("img/Login/head_a.png"));
						}
					} else {
						label.setIcon(new ImageIcon("img/Login/tou.png"));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "您的网络未连接！", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
		idField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					passwordField.requestFocus();
			}
		});

		// 设置密码域
		passwordField = new JPasswordField();
		passwordField.setSize(frameWidth * 64 / 100, frameHeight / 16);
		passwordField
				.setLocation(frameWidth * 26 / 100, frameHeight * 65 / 100);
		passwordField.setVisible(true);
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP)
					idField.requestFocus();
			}
		});

		// 设置登录按钮
		JButton loginButton = new JButton("登录");
		Font buttonFont = new Font("登录", Font.PLAIN, 13);
		loginButton.setFont(buttonFont);
		loginButton.setSize(frameWidth / 5, frameHeight * 6 / 100);
		loginButton.setLocation((frameWidth - frameWidth / 5) / 2,
				frameHeight * 80 / 100);
		loginButton.addActionListener(new LoginListener());
		loginButton.setVisible(true);

		// 设置用户名标签
		JLabel idLabel = new JLabel("工号");
		Font idLabelFont = new Font("工号", Font.BOLD, 14);
		idLabel.setFont(idLabelFont);
		idLabel.setSize(frameWidth / 5, frameHeight / 11);
		idLabel.setLocation(frameWidth * 8 / 100, frameHeight * 54 / 100);
		idLabel.setVisible(true);
		
		// 设置了密码标签
		JLabel passwordLabel = new JLabel("密码");
		Font passwordLabelFont = new Font("密码", Font.BOLD, 14);
		passwordLabel.setFont(passwordLabelFont);
		passwordLabel.setSize(frameWidth / 5, frameHeight / 11);
		passwordLabel.setLocation(frameWidth * 8 / 100, frameHeight * 64 / 100);
		passwordLabel.setVisible(true);
		JPanel portraitPanel = new JPanel();

		mainPanel.setVisible(true);
		mainPanel.setLayout(null);
	

		mainPanel.add(label);
		mainPanel.add(idLabel);
		mainPanel.add(passwordLabel);
		mainPanel.add(portraitPanel);
		mainPanel.add(loginButton);
		mainPanel.add(idField);
		mainPanel.add(passwordField);

		this.setUndecorated(true);
		this.add(mainPanel);
		this.repaint();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getRootPane().setDefaultButton(loginButton);
		// 处理窗口拖动事件
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
				LoginFrame.this.setLocation(xx, yy);
			}
		});

	}



	// 下面的类是按钮的事件监听器  11-17 By jin
	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			UserBLService service;
			try {
				service = new User();
				
				String ID = idField.getText();
				String passWord = new String(passwordField.getPassword());
				int result = service.login(ID, passWord);
				switch (result) {
				case 0:
					//成功登陆
					UserVO user = service.showUser(ID);
					MainFrame frame = new MainFrame(user);
					frame.setDividerLocation("long");
					//操作日志
					log.addLog(new LogVO(log.getdate(), user.getID(), user
							.getName(), "登陆进销存系统", 2));
					headPane.RefreshGrades();
					LoginFrame.this.dispose();
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "该用户不存在!", "提示",
							JOptionPane.WARNING_MESSAGE);
					idField.setText("");
					passwordField.setText("");
					break;
				default:
					JOptionPane.showMessageDialog(null, "密码错误", "提示",
							JOptionPane.WARNING_MESSAGE);
					passwordField.setText("");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "您的网络未连接！", "提示",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		//选择ip和port
		new IPFrame();
	}

}
