package com.obsqura.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
	public static Properties getProperties() throws IOException {
		
		//properties class
				Properties prop =new Properties();
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuration\\GlobalData.properties");
				prop.load(fis);
				return prop;
	}

}
