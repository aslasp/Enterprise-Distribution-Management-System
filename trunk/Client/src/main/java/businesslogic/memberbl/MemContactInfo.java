package businesslogic.memberbl;

public class MemContactInfo {
	private String tel;
	private String address;
	private String postcode;
	private String EMail;
	

	public MemContactInfo(String tel, String address, String postcode,
			String eMail) {
		this.tel = tel;
		this.address = address;
		this.postcode = postcode;
		EMail = eMail;
		
	}

	public String getTel() {
		return tel;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getEMail() {
		return EMail;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	
	
	
	
}
