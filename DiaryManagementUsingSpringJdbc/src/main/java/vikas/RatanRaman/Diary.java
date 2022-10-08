package vikas.RatanRaman;

public class Diary {
	private int Did;
	private String personName;
	private String personEmail;
	private String personContact;
	private String personAddress;
	public int getDid() {
		return Did;
	}
	public void setDid(int did) {
		Did = did;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPersonContact() {
		return personContact;
	}
	public void setPersonContact(String personContact) {
		this.personContact = personContact;
	}
	public String getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}
	public void display(int a)
	{
		System.out.println("\n\n\t================="+a+" => Record Details==============");
		System.out.println("\n\n\t Person id        :"+Did);
		System.out.println("\n\n\t Person Name      :"+personName);
		System.out.println("\n\n\t Person Email     :"+personEmail);
		System.out.println("\n\n\t Person Contact   :"+personContact);
		System.out.println("\n\n\t Person Address   :"+personAddress);
		System.out.println("\n\n\t====================End or Record ======================");
		
	}
	

}
