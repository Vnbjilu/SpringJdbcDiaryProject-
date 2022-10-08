package vikas.RatanRaman;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
     ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
     DiaryDao dd=context.getBean(DiaryDao.class);
     //dd.insert();
     //dd.delete();
     
     
    }
}
