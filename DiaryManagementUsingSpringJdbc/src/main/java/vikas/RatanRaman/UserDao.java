package vikas.RatanRaman;

import java.sql.ResultSet;
import java.sql.SQLException;
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

	
	void insert()
	{
		
		Scanner sc=new Scanner(System.in);
		context=new ClassPathXmlApplicationContext("spring.xml");
		User user=context.getBean(User.class);
		System.out.print("\n\t Enter the User name : => ");
		user.setUserName(sc.nextLine());
		System.out.print("\n\t Enter the user Password :=> ");
		user.setUserPassword(sc.nextLine());
		System.out.print("\n\t Enter the user Type : => ");
		user.setUserType(sc.nextLine());
		System.out.print("\n\t Enter the Email : => ");
		user.setUserEmail(sc.nextLine());
		String query="Insert into user(userName,userPassword,userEmail,userType)"
				+ "values('"+user.getUserName()+"', '"+user.getUserPassword()+"' , '"
				+user.getUserEmail()+"' , '"+user.getUserType()+"')";
		if(temp.update(query)>0)
			System.out.println("\n\n User Created Succesfully ");
		else
			System.out.println("\n\t Error in User Creation");
			
	}
	public boolean login(ApplicationContext context)
	{
		System.out.println("\n\n\n\t===========================Welcome to Login Module=================================");
		Scanner sc=new Scanner(System.in);
		System.out.print("\n\n\t\t Enter the User Name =>  ");
		String userName=sc.nextLine();
		System.out.print("\n\n\t\t Enter the Password => ");
		String userPassword=sc.nextLine();
		String query="Select *from user where userName='"+userName+"' and  userPassword='"+
		userPassword+"'";
		
		UserLogin user=context.getBean(UserLogin.class);
		
		return temp.query(query,new ResultSetExtractor<Boolean>() {

			@Override
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next())
				{
					user.setUserName(userName);
					user.setUserType(rs.getString(5));
					return true;
				}else
					return false;
			}
			
		});
		
	}
	public void menu(ApplicationContext context)
	{
		System.out.print("\n\n\t\t=======================User Management Module==================");
		
		
	}
	
}
