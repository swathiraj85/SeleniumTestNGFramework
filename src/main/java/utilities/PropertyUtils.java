package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//***************************************************************************************************************************************************
//  The configuration class to load the value of global properties
//****************************************************************************************************************************************************

public class PropertyUtils {

	public static Properties loadGlobalProperties(String file_path) {
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + file_path);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException("failed to load properties file "+ file_path);
		}

		// load a properties file
		try {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("failed to load properties file "+ file_path);
		}
		return properties;
	}
}
