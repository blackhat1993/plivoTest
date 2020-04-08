package plivo.automation;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {
	
	private Properties prop;

	public ReadProperty() throws IOException {
		
		prop = new Properties();
		//InputStream ip = new InputStream("C:\\Users\\CHROME\\eclipse-workspace\\ApiAutomationFramework\\src\\test\\java\\plivo\\automation\\config.properties");
		InputStream ip = this.getClass().getResourceAsStream("config.properties");
		prop.load(ip);
	}
	
	public String getToken() {
		
		return prop.getProperty("token");
	}
	
	public String getChannelName() {
		
		return prop.getProperty("channel.name");
	}
	
	public String getChannelNewName() {
		
		return prop.getProperty("channel.name.new");
	}
	
	public String getBaseUri() {
		
		return prop.getProperty("baseUri");
	}

}
