package channel.tests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import plivo.automation.ReadProperty;
import plivo.automation.Utils;

public class BaseTest {
	
	protected ReadProperty rp;
	protected String accessToken;
	protected Utils utility;
	protected String baseUri;
	protected HashMap<String, String> headers;
	
	@BeforeClass
	public void setUp() throws IOException {
	
		rp = new ReadProperty();
		utility  = new Utils();
		
		baseUri = rp.getBaseUri();
		setHeaders();
	}
	
	@Test
	public void testAccessToken() {
		
		accessToken = rp.getToken();
		assertNotNull(accessToken, "Access Token is null, please enter a valid Token");
		
	}
	
	public void setHeaders() {
		
		headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
	}

}
