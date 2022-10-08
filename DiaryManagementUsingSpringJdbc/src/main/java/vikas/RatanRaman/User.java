package vikas.RatanRaman;

public class User {
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userType;
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public void display(int a)
	{
		System.out.print("\n\n\t ============="+a+" => User Record ===============");
		System.out.print("\n\n\t user Id        :"+userId);
		System.out.print("\n\n\t User Name      :"+userName);
		System.out.print("\n\n\t User password  :"+userPassword);
		System.out.print("\n\n\t User Type      :"+userType);
		System.out.print("\n\n\t User Email     :"+userEmail);
	}
	
}
