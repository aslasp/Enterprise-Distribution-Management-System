package po;

import java.io.Serializable;

public class LogPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private  String userID;
	private  String userName;
	private String info;
	private double addGrades;
	public LogPO(String date,String userID, String userName, String info, double addGrades) {
		this.date=date;
		this.userID = userID;
		this.userName = userName;
		this.info = info;
		this.addGrades = addGrades;
	}
	public String getDate(){
		return date;
	}
	public String getUserID() {
		return userID;
	}
	public String getUserName() {
		return userName;
	}
	public String getInfo() {
		return info;
	}
	public double getAddGrades() {
		return addGrades;
	}
	
}
