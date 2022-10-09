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

public class UserDao {
	private JdbcTemplate temp;
	private ApplicationContext context;

	public void setTemp(JdbcTemplate temp) {
		this.temp = temp;
	}

	void insert() {

		Scanner sc = new Scanner(System.in);
		context = new ClassPathXmlApplicationContext("spring.xml");
		User user = context.getBean(User.class);
		System.out.print("\n\t Enter the User name : => ");
		user.setUserName(sc.nextLine());
		System.out.print("\n\t Enter the user Password :=> ");
		user.setUserPassword(sc.nextLine());
		System.out.print("\n\t Enter the user Type : => ");
		user.setUserType(sc.nextLine());
		System.out.print("\n\t Enter the Email : => ");
		user.setUserEmail(sc.nextLine());
		String query = "Insert into user(userName,userPassword,userEmail,userType)" + "values('" + user.getUserName()
				+ "', '" + user.getUserPassword() + "' , '" + user.getUserEmail() + "' , '" + user.getUserType() + "')";
		if (temp.update(query) > 0)
			System.out.println("\n\n User Created Succesfully ");
		else
			System.out.println("\n\t Error in User Creation");

	}

	public boolean login(ApplicationContext context) {
		System.out
				.println("\n\n\n\t===========================Welcome to Login Module=================================");
		Scanner sc = new Scanner(System.in);
		System.out.print("\n\n\t\t Enter the User Name =>  ");
		String userName = sc.nextLine();
		System.out.print("\n\n\t\t Enter the Password => ");
		String userPassword = sc.nextLine();
		String query = "Select *from user where userName='" + userName + "' and  userPassword='" + userPassword + "'";

		UserLogin user = context.getBean(UserLogin.class);

		return temp.query(query, new ResultSetExtractor<Boolean>() {

			@Override
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					user.setUserName(userName);
					user.setUserType(rs.getString(5));
					return true;
				} else
					return false;
			}

		});

	}

	public void menu(ApplicationContext context) {
		UserLogin user = context.getBean(UserLogin.class);
		Scanner sc = new Scanner(System.in);
		int i = 1;
		while (i != 5) {
			System.out.print("\n\n\t\t=======================User Management Module==================");
			System.out.println("\n\n\t ========================User Details==========================");
			System.out.println("\n\t User Name : " + user.getUserName());
			System.out.println("\n\t User Type : " + user.getUserType());
			System.out.println("======================================================================");
			System.out.print("\n\n\t\t 1-> for Create User");
			System.out.print("\n\n\t\t 2-> for Display All User ");
			System.out.print("\n\t\t\t 3-> for User Updation");
			System.out.print("\n\t\t\t 4-> for User Deletion");
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

	private void delete() {
		int id=0;
		Scanner sc=new Scanner(System.in);
		System.out.print("\n\n\t Enter the User Id that record you want to delete ");
		id=sc.nextInt();
		String query="delete from user where userId="+id;
		if(temp.update(query)>0)
			System.out.println("\n\n Record is deleted Successfully ");
		else
			System.out.println("\n\t Sorry !! Record is not Found of Id="+id);
		

	}

	private void update() {
		Scanner sc=new Scanner(System.in);
		context=new ClassPathXmlApplicationContext("spring.xml");
		User user=context.getBean(User.class);
		System.out.println("\n\t Enter the user id :=> ");
		user.setUserId(sc.nextInt());
		System.out.print("\n\t Enter the User name : => ");
		user.setUserName(sc.nextLine());
		System.out.print("\n\t Enter the user Password :=> ");
		user.setUserPassword(sc.nextLine());
		System.out.print("\n\t Enter the user Type : => ");
		user.setUserType(sc.nextLine());
		System.out.print("\n\t Enter the Email : => ");
		user.setUserEmail(sc.nextLine());
		String query="Update user set userName='"+user.getUserName()
					   +"' , userPassword='"+user.getUserPassword()+
					   "' , userEmail='"+user.getUserEmail()+
					   "' , userType='"+user.getUserType()+"' where userId="+user.getUserId();
		if(temp.update(query)>0)
			System.out.println("\n\n\t Record is Updated Succesfully ");
		else
			System.out.println("\n\n Record is not Found for Userid = "+user.getUserId());
					 
	}

	private void display() {
		String query = "select *from user";
		List<User> user = new ArrayList<>();
		temp.query(query, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
				while (rs.next()) {
					User us = context.getBean(User.class);
					us.setUserId(rs.getInt(1));
					us.setUserName(rs.getString(2));
					us.setUserPassword(rs.getString(3));
					us.setUserEmail(rs.getString(4));
					us.setUserType(rs.getString(5));
					user.add(us);
				}
				return null;
			}

		});
		int cn = 1;
		for (User e : user) {

			e.display(cn++);
		}
	}

}
