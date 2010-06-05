import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExample
{
	public static void main( String[] args ) throws Exception
	{
		ApplicationContext applicationContextExample = new ClassPathXmlApplicationContext( "applicationContextExample.xml" );
		ApplicationContextService applicationContextService = (ApplicationContextService) applicationContextExample.getBean( "applicationContextService" );
		applicationContextService.printExampleClassProperty();
	}
}