public class ApplicationContextService implements IApplicationContextService
{
	private String exampleClassProperty;

	public ApplicationContextService()
	{
	}
	
	public ApplicationContextService( String exampleClassProperty )
	{
		this.exampleClassProperty = exampleClassProperty;
	}
	public void printExampleClassProperty()
	{
		System.out.println( exampleClassProperty );
	}
	public void setExampleClassProperty( String exampleClassProperty )
	{
		this.exampleClassProperty = exampleClassProperty;
	}
}
