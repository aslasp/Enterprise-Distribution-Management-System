package Presentation.financeui.initial;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import businesslogic.memberbl.Member;
import businesslogicservice.memberblservice.MemberBLService;
import po.MemberPO.MemberType;
import vo.MemberVO;
import Presentation.mainui.MainFrame;
import Presentation.mainui.MyTableCellRenderer;

public class MemberInitialPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	MemberModel mm;
	ArrayList<ArrayList<String>> memberC = new ArrayList<ArrayList<String>>();
	ArrayList<MemberVO> member=new ArrayList<MemberVO>();
	JTable memberTable;
	JScrollPane jsp;
	JButton addBtn,delBtn;
	JComboBox<String> memberBox;
	AddInitialPanel subparent; 
	MainFrame parent;
	ArrayList<Integer> haveSelected=new ArrayList<Integer>();
	JPanel btnPnl;
	public MemberInitialPanel(MainFrame frame){
		parent=frame;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 40, 5, 40);
		this.setBackground(Color.white);
		this.setLayout(gbl);
		c.fill=GridBagConstraints.BOTH;
		//--------表格-------------
		mm=new MemberModel();
		memberTable=new JTable(mm);
		memberTable.getTableHeader().setReorderingAllowed(false);
		// table 渲染器，设置文字内容居中显示，设置背景色等
				DefaultTableCellRenderer tcr = new MyTableCellRenderer();
				for (int i = 0; i < memberTable.getColumnCount(); i++) {
					memberTable.getColumn(memberTable.getColumnName(i)).setCellRenderer(
							tcr);
				}
		jsp=new JScrollPane(memberTable);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		gbl.setConstraints(jsp, c);
		this.add(jsp);
		//------客户增删处-----------
		btnPnl=new JPanel();
		btnPnl.setBackground(Color.white);
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 0.1;
		gbl.setConstraints(btnPnl, c);
		this.add(btnPnl);
		
		//------------------------------------
		// ---------------------------
				ArrayList<String> st = new ArrayList<String>();
				MemberBLService min = null;
			    try {
						min = new Member();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}

				
				ArrayList<MemberVO> member = min.showMembers();
				if (member == null) {
					String memberText[] = { "当前无客户可选" };
					memberBox = new JComboBox<String>(memberText);
					memberBox.setEditable(false);
				} else {
					for (MemberVO vo : member) {
						st.add(vo.getName());
					}
					String memberText[] = new String[st.size()+1];
					memberText[0]="请选择客户";
					for (int i = 0; i < st.size(); i++) {
						memberText[i+1] = st.get(i);
					}
					memberBox = new JComboBox<String>(memberText);
				}
				// ---------------------------
		//------------------------------------
		//小小黄加监听 id调用哪个本pane的getNewID
//		String memberBoxText[]={"选择客户"};
//		memberBox=new JComboBox<String>(memberBoxText);
		memberBox.setBackground(Color.white);
		memberBox.setFont(font);
		btnPnl.add(memberBox);
		//
		//
		addBtn = new JButton("添加客户");
		addBtn.setFont(font);
		addBtn.setBackground(new Color(204, 242, 239));
		addBtn.setFocusPainted(false);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(memberBox.getSelectedIndex()==0)
				    parent.setRightComponent(new addMemberInitial(parent,MemberInitialPanel.this));
				else{
					int index=memberBox.getSelectedIndex();
					boolean isIn=false;
					for(Integer inte:haveSelected){
						if(index==inte){
							isIn=true;
						}
					}
					if(isIn==true){
						 JOptionPane.showMessageDialog(null,"您已选择了该客户!","提示",JOptionPane.WARNING_MESSAGE);
					}
					else{
					haveSelected.add(index);
					MemberBLService min = null;
					try {
							min = new Member();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
					}

					
					ArrayList<MemberVO> member = min.showMembers();
					MemberVO vv=member.get(index-1);
					vv.setMemberID(getNewID(vv.getmType()));
					RefreshTable(vv);
					memberTable.revalidate();
				}
				}
			}
		});
		btnPnl.add(addBtn);
		delBtn = new JButton("删除选中");
		delBtn.setFont(font);
		delBtn.setBackground(new Color(204, 242, 239));
		delBtn.setFocusPainted(false);
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int allselected[]=memberTable.getSelectedRows();
				if(allselected.length<=0)
					JOptionPane.showMessageDialog(null,"请选择客户!","提示",JOptionPane.WARNING_MESSAGE);
				else{
					for(int j=allselected.length-1;j>=0;j--){
					int selected=allselected[j];
					haveSelected.remove(selected);
					for(int i=selected+1;i<memberC.size();i++){
						if(mm.getValueAt(selected, 2).equals(mm.getValueAt(i, 2))){
							String mtype=memberC.get(i).get(2);
							String type;
							if(mtype.equals("进货商"))
								type="JHS";
							else
								type="XSS";
							double d=Double.parseDouble(memberC.get(i).get(0).substring(4))-1;
							NumberFormat nf = NumberFormat.getInstance();
						     nf.setMinimumIntegerDigits(7); 
						     nf.setGroupingUsed(false);
							 memberC.get(i).set(0,type+"-"+nf.format(d));
						}
					}
					mm.removeRow(selected);
					memberTable.revalidate();					
				}
				}
			}
		});
		btnPnl.add(delBtn);
	}
	class MemberModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String head[]={"客户编号","客户名","分类","电话","应收","应付"};
		public int getRowCount() {
			return memberC.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public String getValueAt(int row, int col) {
			return memberC.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}
		public void addRow(ArrayList<String> v) {
			memberC.add(v);
		}

		public void removeRow(int row) {
			memberC.remove(row);
			member.remove(row);
		}
		
	}
	public void RefreshTable(MemberVO vo){
		ArrayList<String> line=new ArrayList<String>();
		line.add(vo.getMemberID());
		line.add(vo.getName());
		if(vo.getmType()==MemberType.JHS)
			line.add("进货商");
		else line.add("销售商");
		line.add(vo.getTel());
		line.add(vo.getToReceive()+"");
		line.add(vo.getToPay()+"");
		memberC.add(line);
		member.add(vo);
	
	}
	
	public ArrayList<MemberVO> getMember(){
		if(member.size()==0)
			return null;
		return member;		
	}
	
	public String getNewID(MemberType type){
		String cop="销售商";
		if(type==MemberType.JHS)
			cop="进货商";
			
		for(int i=memberC.size()-1;i>=0;i--){
			String mtype=memberC.get(i).get(2);
			if(mtype.equals(cop))
			{ double d=Double.parseDouble(memberC.get(i).get(0).substring(4))+1;
			NumberFormat nf = NumberFormat.getInstance();
		     nf.setMinimumIntegerDigits(7); 
		     nf.setGroupingUsed(false);
		     return type.toString()+"-"+nf.format(d);
		     }
			
		}
		return type.toString()+"-0000001";
		
	}
	public void setParent(AddInitialPanel pane){
		this.subparent=pane;
	}
}

