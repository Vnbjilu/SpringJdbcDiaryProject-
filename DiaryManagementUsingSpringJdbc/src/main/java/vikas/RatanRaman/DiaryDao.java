package vikas.RatanRaman;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class DiaryDao {
	private JdbcTemplate temp;

	Scanner sc;
	ApplicationContext context;

	public void setTemp(JdbcTemplate temp) {
		this.temp = temp;
	}

	public void insert() {
		String query = "";
		context = new ClassPathXmlApplicationContext("spring.xml");
		Diary dd = context.getBean(Diary.class);
		sc = new Scanner(System.in);
		System.out.print("Enter the Person Name");
		dd.setPersonName(sc.nextLine());
		System.out.print("Enter the Email id of Person ");
		dd.setPersonEmail(sc.nextLine());
		System.out.print("Enter the Contact of person ");
		dd.setPersonContact(sc.nextLine());
		System.out.println("Enter the address of person ");
		dd.setPersonAddress(sc.nextLine());
		query = "Insert into person(personName,personEmail,personContact,personAddress)" + " values('"
				+ dd.getPersonName() + "' , '" + dd.getPersonEmail() + "' , '" + dd.getPersonContact() + "', '"
				+ dd.getPersonAddress() + "')";

		if (temp.update(query) > 0)
			System.out.println("\n\n\tThanku Record Created And Stored Successfully");
		else
			System.out.println("\n\n\t Error in Processing");
	}

	public void update() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Diary dd = context.getBean(Diary.class);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Id of the Person");
		dd.setDid(Integer.parseInt(sc.nextLine()));
		System.out.print("Enter the Name of the person");
		dd.setPersonName(sc.nextLine());
		System.out.print("Enter the email id ");
		dd.setPersonEmail(sc.nextLine());
		System.out.print("Enter the contact of the person ");
		dd.setPersonContact(sc.nextLine());
		System.out.println("Enter the address of the person");
		dd.setPersonAddress(sc.nextLine());
		String query = "Update person set personName='" + dd.getPersonName() + "' , personEmail='" + dd.getPersonEmail()
				+ "' , personContact='" + dd.getPersonContact() + "' , personAddress='" + dd.getPersonAddress()
				+ "' where personId=" + dd.getDid();
		// System.out.println(query);
		if (temp.update(query) > 0)
			System.out.println("\n\n\t Thanku Record Updated Succesfully ");
		else
			System.out.println("\n\t No Such Record found that id=" + dd.getDid());

	}

	public void display() {
		String query = "Select *from person";
		ArrayList<Diary> diary = new ArrayList<>();
		context = new ClassPathXmlApplicationContext("spring.xml");
		temp.query(query, new ResultSetExtractor<List<Diary>>() {

			@Override
			public List<Diary> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				while (rs.next()) {
					Diary dd = context.getBean(Diary.class);
					dd.setDid(rs.getInt(1));
					dd.setPersonName(rs.getString(2));
					dd.setPersonEmail(rs.getString(3));
					dd.setPersonContact(rs.getString(4));
					dd.setPersonAddress(rs.getString(5));
					diary.add(dd);

				}
				return null;
			}

		});
		int cn = 1;
		for (Diary tmp : diary) {
			tmp.display(cn++);
		}
	}

	public void delete() {
		int id = 0;
		sc = new Scanner(System.in);
		System.out.print("\n\n\t Enter the Person Id that record you want to delete ");
		id = sc.nextInt();
		String query = "delete from person where personId=" + id;
		if (temp.update(query) > 0)
			System.out.println("\n\n Record is deleted Successfully ");
		else
			System.out.println("\n\t Sorry !! Record is not Found of Id=" + id);

	}

	public void diarymenu(ApplicationContext context) {
		UserLogin user = context.getBean(UserLogin.class);
		int i = 1;
		while (i != 5) {
			System.out.print("\n\n\t\t=======================Diary Management Module==================");
			System.out.println("\n\n\t ========================User Details==========================");
			System.out.println("\n\t User Name : " + user.getUserName());
			System.out.println("\n\t User Type : " + user.getUserType());
			System.out.println("======================================================================");
			System.out.print("\n\n\t\t 1-> for Create Person Record");
			System.out.print("\n\n\t\t 2-> for Display All Record ");
			System.out.print("\n\t\t\t 3-> for Record Updation");
			System.out.print("\n\t\t\t 4-> for Record Deletion");
			System.out.print("\n\n\t\t 5-> for Main Menu");
			System.out.print("\n\n\n\t\t Enter the choice ");
			i = sc.nextInt();
			switch (i) {
			case 1:
				insert();
				break;
			case 2:
				display();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			case 5:
				break;
			default:
				System.out.println("\n\n\t Sorry ! Wrong Option ");
				System.out.println("\n\n\t Try!!!! Again");

			}
			System.out.print("\n Type ok And press Enter to continue...........=> ");
			String tmp = sc.nextLine();
		}
	}
}
