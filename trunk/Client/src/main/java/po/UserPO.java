package po;

import java.io.Serializable;



public class UserPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String ID;
	private String password;
	private UserJob job;
	private double grades;//业绩点

	public UserPO(String name, String ID, String password, UserJob job,double grades) {
		this.name = name;
		this.ID = ID;
		this.password = password;
		this.job=job;
		this.grades=grades;
	}

	public String getName() {
		return name;
	}

	/*public void setName(String name) {
		this.name = name;
	}*/

	public String getID() {
		return ID;
	}

	/*public void setID(String iD) {
		ID = iD;
	}*/

	public String getPassword() {
		return password;
	}

	/*public void setPassword(String password) {
		this.password = password;
	}*/

	public UserJob getJob() {
		return job;
	}

	/*public void setJob(UserType type) {
		this.type = type;
	}*/
	
	public double getGrades(){
		return grades;
	}
	/*public void setGrades(double g){
		this.grades+=g;
	}*/
	
	public enum UserJob implements Serializable{
		MANAGER,FINANCE,FINANCEMANAGER,SALE,STOCK,ADMINSTRATOR,SALEMANAGER

	}

}
