package vikas.RatanRaman;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		DiaryDao dd = context.getBean(DiaryDao.class);
		// dd.insert();
		// dd.delete();
		firstMenu();
		UserDao ud = context.getBean(UserDao.class);
		if (ud.login(context)) {
			UserLogin user = context.getBean(UserLogin.class);
			if (user.getUserType().equalsIgnoreCase("admin"))
				mainAdminMenu(context);
			else
				mainMenu(context);
		}
	}

	public static void firstMenu() {
		System.out.print("\n\t==================== Welcome to Diary Managment Program ========================");
		System.out.print("\n\t Programmer Details: Vikas Srivastava ");

	}

	public static void mainAdminMenu(ApplicationContext context) {
		int c = 1;
		UserLogin user = context.getBean(UserLogin.class);
		System.out.println("\n\n\t ======================Welcome to Main Portal=================================");
		System.out.println("\n\n\t ========================User Details===========================================");
		System.out.println("\n\t User Name : " + user.getUserName());
		System.out.println("\n\t User Type : " + user.getUserType());
		System.out.println("=================================Main Menu======================");

		System.out.print("\n\t " + c++ + "-> User Management ");
		System.out.print("\n\t " + c++ + "-> Diary Management ");
		System.out.print("\n\t " + c++ + "-> Logout ");
		System.out.print("\n\t " + c++ + "-> Exit");
	}

	public static void mainMenu(ApplicationContext context) {
		int c = 1;
		Scanner sc=new Scanner(System.in);
		UserLogin user = context.getBean(UserLogin.class);
		int mn = 1;
		while (mn != 3) {
			System.out.println("\n\n\t ======================Welcome to Main Portal=================================");
			System.out
					.println("\n\n\t ========================User Details===========================================");
			System.out.println("\n\t User Name : " + user.getUserName());
			System.out.println("\n\t User Type : " + user.getUserType());
			System.out.println("=================================Main Menu======================");

			System.out.print("\n\t " + c++ + "-> Diary Management ");
			System.out.print("\n\t " + c++ + "-> Logout ");
			System.out.print("\n\t " + c++ + "-> Exit");
			System.out.print("\n\n\\t\t Enter the choice => ");
			mn=sc.nextInt();
		}
	}
}
