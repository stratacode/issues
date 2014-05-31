
public class Application extends WebApplication
{
	public Application(){}

	public Class<? extends Page> getHomePage(){
		return HomePage.class;
	}

 	@Override
    	protected void init() {
        	super.init();
                /*
        	IResourceSettings resourceSettings = getResourceSettings();
        	resourceSettings.addResourceFolder("WEB-INF/html"); 
        	resourceSettings.setResourceStreamLocator(new PathStripperLocator());
                */
       }
}
