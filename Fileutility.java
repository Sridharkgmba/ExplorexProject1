package Genericutility;

import java.io.FileInputStream;
import java.util.Properties;

public class Fileutility {
	FileInputStream fis;
	Properties pobj;

	public String readdataproperty(String key) throws Throwable {

		FileInputStream fis = new FileInputStream(Ipathconstant.Filepath);
		Properties pobj = new Properties();
		pobj.load(fis);
		String value = pobj.getProperty(key);
		return value;

	}

}
