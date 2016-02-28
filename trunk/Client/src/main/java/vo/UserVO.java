package vo;

import po.UserPO.UserJob;

public class UserVO {
	private String name;
	private String ID;
	private String password;
	private UserJob job;
	private double grades;

	public UserVO(String name, String ID, String password, UserJob job,double grades) {
		this.name = name;
		this.ID = ID;
		this.password = password;
		this.job=job;
		this.grades=grades;
	}
	public void setPassWord(String p){
		this.password=p;
	}
	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public String getPassword() {
		return password;
	}

	public UserJob getJob() {
		return job;
	}
	
	public double getGrades(){
		return grades;
	}
	
	public void setGrades(double grades){
		this.grades=grades;
		
	}
}
