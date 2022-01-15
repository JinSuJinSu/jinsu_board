package vo;

public class UserVO {
	
	private String userID;
	private String name;
	private String password;
	private String phone;
	private String email;
	
	public UserVO() {
		
	}
	
	public UserVO(String userID) {
		this.userID = userID;
	}
	
	public UserVO(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}
	
	public UserVO(String userID, String name, String password, String phone, String email) {
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}



	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
