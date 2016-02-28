package Presentation.memberui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import po.MemberPO.MemberLevel;
import po.MemberPO.MemberType;
import vo.LogVO;
import vo.MemberVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.headPane;
import Presentation.mainui.log;
import businesslogic.memberbl.MemAccountInfo;
import businesslogic.memberbl.MemBaseInfo;
import businesslogic.memberbl.MemContactInfo;
import businesslogic.memberbl.Member;
import businesslogicservice.memberblservice.MemberBLService;

//未加输入检测
public class AddMemberPanel extends JPanel {
	/**
	 * !!!!已写监听
	 */

	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	MainFrame parent;
	public String ID;
	public typeListener typel;
	MemberBLService service;
	public MemberType mtype;
	public JButton submitBtn, cancelBtn;
	public JComboBox<String> typeCbox;

	JTextField nameFld, phoneFld, addressFld, postcodeFld, EMailFld,
			defaultClerkFld;
	JLabel title;
	public JLabel IDLbl;
	JLabel typeLbl;
	JLabel nameLbl;
	JLabel phoneLbl;
	JLabel addressLbl;
	JLabel postcodeLbl;
	JLabel EMailLbl;
	JLabel defaultClerkLbl;
	//String nameText, phoneText, addressText, postcodeText, EMailText,
			//clerkText;
	JPanel left;
	JPanel typePnl;
	public AddListener add;
	public AddMemberPanel(MainFrame frame) throws Exception {
		parent = frame;
		service = new Member();
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(10, 60, 10, 60);
		this.setBackground(Color.white);
		// -------------title-----------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		titlePnl.setLayout(new GridLayout(1, 1));
		 title = new JLabel("添加客户");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		titlePnl.add(title);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(titlePnl, c);
		this.add(titlePnl);
		// -----------------------------------
		JPanel mPnl = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		mPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(mPnl, c);
		this.add(mPnl);
		mPnl.setLayout(new GridLayout(1, 2));
		left = new JPanel();
		JPanel right = new JPanel();
		left.setBackground(Color.white);
		right.setBackground(Color.white);
		mPnl.add(left);
		mPnl.add(right);
		left.setLayout(new GridLayout(6, 1));
		right.setLayout(new GridLayout(6, 1));
		// ---------ID------------------
		JPanel IDPnl = new JPanel();
		IDPnl.setBackground(Color.white);
		IDLbl = new JLabel();
		IDLbl.setFont(font);
		// 这里要有一个方法来创建String，使得编号自动生成
		String text = "编号：_____________";
		IDLbl.setText(text);
		IDPnl.add(IDLbl);
		left.add(IDPnl);
		// -------type--------------------
		typePnl = new JPanel();
		typePnl.setBackground(Color.white);
		left.add(typePnl);
		typeLbl = new JLabel("类型");
		typeLbl.setFont(font);
		typePnl.add(typeLbl);
		String[] type = { "请选择类型", "进货商", "销售商" };
		typeCbox = new JComboBox<String>(type);
		typeCbox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		typeCbox.setBackground(Color.white);
		typel=new typeListener();
		typeCbox.addItemListener(typel);
		
		typePnl.add(typeCbox);
		// ------------name-------------
		JPanel namePnl = new JPanel();
		namePnl.setBackground(Color.white);
		left.add(namePnl);
		nameLbl = new JLabel("姓名 ");
		nameLbl.setFont(font);
		namePnl.add(nameLbl);
		nameFld = new JTextField(6);
		nameFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		namePnl.add(nameFld);
		characterlisten character=new characterlisten();
		nameFld.addFocusListener(character);
			
		// -----------phone-------------
		JPanel phonePnl = new JPanel();
		phonePnl.setBackground(Color.white);
		phoneLbl = new JLabel("电话 ");
		phoneLbl.setFont(font);
		phonePnl.add(phoneLbl);
		phoneFld = new JTextField(8);
		phoneFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		phoneFld.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent e){
				if(!phoneFld.getText().equals("")){
				String regex ="^(((\\d{3})|\\d{3}-)?\\d{8})|\\d{11}$";
				if(!phoneFld.getText().matches(regex)){
					JOptionPane.showMessageDialog(null, "电话格式不正确，请重新输入","错误提示",
							JOptionPane.WARNING_MESSAGE);
					phoneFld.setText("");
				}
				}
			}
			});
		phonePnl.add(phoneFld);
		left.add(phonePnl);
		// ------------EMail----------
		JPanel eMailPnl = new JPanel();
		eMailPnl.setBackground(Color.white);
		EMailLbl = new JLabel("电子邮箱 ");
		EMailLbl.setFont(font);
		eMailPnl.add(EMailLbl);
		EMailFld = new JTextField(8);
		EMailFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		eMailPnl.add(EMailFld);
		EMailFld.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent e){
				if(!EMailFld.getText().equals("")){
				String regex = "[\\w_]+@[\\w]+[.][\\w]+([.][\\w]+)*";
				if(!EMailFld.getText().matches(regex)){
					JOptionPane.showMessageDialog(null, "Email格式不正确，请重新输入","错误提示",
							JOptionPane.WARNING_MESSAGE);
					EMailFld.setText("");
				}
				}
			}
			
		
		});
		left.add(eMailPnl);
		// ----------address-----------
		JPanel addPnl = new JPanel();
		addPnl.setBackground(Color.white);
		addressLbl = new JLabel("地址 ");
		addressLbl.setFont(font);
		addPnl.add(addressLbl);
		addressFld = new JTextField(10);
		addressFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		addPnl.add(addressFld);
		right.add(addPnl);
		// --------postcode---------
		JPanel pcPnl = new JPanel();
		pcPnl.setBackground(Color.white);
		postcodeLbl = new JLabel("邮编 ");
		postcodeLbl.setFont(font);
		pcPnl.add(postcodeLbl);
		postcodeFld = new JTextField(6);
		postcodeFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		postcodeFld.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent e){
				if(!postcodeFld.getText().equals("")){
				String regex = "[\\d]{6}";
				if(!postcodeFld.getText().matches(regex)){
					JOptionPane.showMessageDialog(null, "邮编格式不正确，请重新输入","错误提示",
							JOptionPane.WARNING_MESSAGE);
					postcodeFld.setText("");
				}
				}
			}
			
		
		});
		pcPnl.add(postcodeFld);
		right.add(pcPnl);
		// -------defaultClerk----------
		JPanel cPnl = new JPanel();
		cPnl.setBackground(Color.white);
		defaultClerkLbl = new JLabel("默认业务员 ");
		defaultClerkLbl.setFont(font);
		cPnl.add(defaultClerkLbl);
		defaultClerkFld = new JTextField(6);
		defaultClerkFld.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cPnl.add(defaultClerkFld);
		defaultClerkFld.addFocusListener(character);
		right.add(cPnl);
		// ----------submitBtn---------------
		JPanel btnPnl=new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy =7;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		submitBtn = new JButton("确  定");
		submitBtn.setFont(font);
		submitBtn.setBackground(new Color(166, 210, 121));
		submitBtn.setFocusPainted(false);
		btnPnl.add(submitBtn);
		btnPnl.add(new JLabel("          "));
		cancelBtn = new JButton("取 消");
		cancelBtn.setFont(font);
		cancelBtn.setFocusPainted(false);
		cancelBtn.setBackground(new Color(251, 147, 121));
		btnPnl.add(cancelBtn);
		add=new AddListener();
		cancelBtn.addActionListener(add);
		
		
		submitBtn.addActionListener(add);
		
		// ------------------------------------------------------------------

	}
	
	class AddListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==submitBtn){
			if (ID == null || ID.equals("")) {
				JOptionPane.showMessageDialog(null, "请选择用户类型，并输入信息！", "提示",
						JOptionPane.CLOSED_OPTION);
			} else {
				boolean isValid=!(nameFld.getText().equals("")||defaultClerkFld.getText().equals("")||
						phoneFld.getText().equals("")||EMailFld.getText().equals("")||postcodeFld.getText().equals("")
						||addressFld.getText().equals("")); 
				if(isValid){
					MemberVO vo=getMemberVO();
					int result = service.addMember(vo);
			
					if (result == 0) {
						JOptionPane.showMessageDialog(null, "添加客户成功！", "提示",
								JOptionPane.CLOSED_OPTION);
						log.addLog(new LogVO(log.getdate(),parent.getUser().getID(),parent.getUser().getName(),
							"添加了一个新客户"+nameFld.getText(),3));
						try {
						headPane.RefreshGrades();
						} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					
					} else 
						JOptionPane.showMessageDialog(null, "添加客户失败！", "提示",
							JOptionPane.WARNING_MESSAGE);
				
					Update();
				}else
					JOptionPane.showMessageDialog(null, "信息输入不完整！", "提示",
							JOptionPane.WARNING_MESSAGE);
					
			}
			}else if(e.getSource()==cancelBtn)
				Update();
			
		}
		
	}
	
	public MemberVO getMemberVO(){
		MemBaseInfo bInfo = new MemBaseInfo(mtype, MemberLevel.ONE,
				ID, nameFld.getText(), 0, defaultClerkFld.getText());
		MemContactInfo cInfo = new MemContactInfo(phoneFld
				.getText(), addressFld.getText(), postcodeFld
				.getText(), EMailFld.getText());
		MemAccountInfo aInfo = new MemAccountInfo(1000000, 0, 0);

		MemberVO vo = new MemberVO(bInfo, aInfo, cInfo);
		return vo;
	}
	
	class typeListener implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String t = typeCbox.getSelectedItem().toString();
			if (t.equals("进货商"))
				mtype = MemberType.JHS;
			else
				mtype = MemberType.XSS;
			

			ID = service.getNewID(mtype);
			IDLbl.setText("编号：" + ID);
		}
		
	}
	
	
	
	public void Update() {
		MemberMgrPanel mgr = new MemberMgrPanel(parent);
		parent.setRightComponent(mgr);
		if (service.showMembers() != null)
			mgr.RefreshMemberTable(service.showMembers());
	}
	
	class characterlisten extends FocusAdapter{
		public void focusLost(FocusEvent e){
			String tip="业务员";
			if(e.getSource()==nameFld)
				 tip="客户姓名";
			JTextField Fld=(JTextField)e.getSource();
			if(!Fld.getText().equals("")){
				String regex = "[\u4e00-\u9fffa-zA-Z]+";
				if(!Fld.getText().matches(regex)){
					JOptionPane.showMessageDialog(null, tip+"输入不正确，请重新输入","错误提示",
							JOptionPane.WARNING_MESSAGE);
					Fld.setText("");
				}
			}
			
		}
	}
	
	
}
