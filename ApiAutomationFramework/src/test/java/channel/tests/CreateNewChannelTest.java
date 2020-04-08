package channel.tests;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import plivo.automation.Channel;
import plivo.automation.ReadProperty;
import plivo.automation.Uri;

public class CreateNewChannelTest extends BaseTest {

	protected String channelName;
	protected String channelId;
	
	
	@Test
	public void testCreateNewChannel() {
		
		String nameUri = utility.generateUri(baseUri, Uri.CHANNEL_CREATE.toString());
		
		JSONObject body = new JSONObject();
		body.put(Channel.NAME.toString(), rp.getChannelName());
		body.put(Channel.VALIDATE.toString(), true);
		
		
		Response resp  = utility.firePostRequest(nameUri, accessToken, headers, null, body);
		
		assertNull(resp.jsonPath().getString("error"), resp.jsonPath().getString("detail"));
		assertTrue(resp.jsonPath().getBoolean("ok"));
		
		channelName = resp.jsonPath().getString("channel.name");
		channelId = resp.jsonPath().getString("channel.id");
		
		assertEquals(channelName, rp.getChannelName(), "the channel name created is different!!!1");
		assertNotNull(channelId);
		
		System.out.println("channel created with name "+channelName+" and id "+channelId);
		
		
	}
}
