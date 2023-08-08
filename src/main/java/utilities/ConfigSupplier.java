package utilities;

import java.util.Properties;

//
// ***************************************************************************************************************************************************
// the enum is designed based on the principles of Singleton design pattern to get the global variables.
// the instance is loaded only once and used for the entire execution which is inherently thread safe & serializable 
// ***************************************************************************************************************************************************
//
public enum ConfigSupplier 
{
INSTANCE;
	private final Properties properties;
	
	ConfigSupplier()
	{
		properties = PropertyUtils.loadGlobalProperties("/src/test/resources/config/config.properties");
	}
	
	// Static getter
    public static ConfigSupplier getInstance()
    {
        return INSTANCE;
    }
 
    public String getApplicationURL(String application) 
   	{
       	  String prop = properties.getProperty(application);
             if(prop != null) return prop;
             else throw new RuntimeException("property " + application+ " is not specified in the config.properties file");		
   	}
    public String getTimeOut()
    {	   	  
 	   String prop = properties.getProperty("timeout");
        if(prop != null) return prop;
        else throw new RuntimeException("The timeout property is not specified in the config.properties file");
    }
   public String getCurrentBrowser()
   {	   	  
	   String prop = properties.getProperty("browser");
       if(prop != null) return prop;
       else throw new RuntimeException("The browser property is not specified in the config.properties file");
   }
}
