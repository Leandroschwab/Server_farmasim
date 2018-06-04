package _functions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("src/main/resources/config.properties");
		props.load(file);
		return props;
	}
	
	public static String getProps(String Props) throws IOException{
		Properties prop = getProp();
		String valor = prop.getProperty(Props);
		return valor;
	}
}
