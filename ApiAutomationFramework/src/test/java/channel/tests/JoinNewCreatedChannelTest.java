package channel.tests;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import plivo.automation.Channel;
import plivo.automation.Uri;

public class JoinNewCreatedChannelTest extends CreateNewChannelTest{

	
	@Test
	public void testJoinChannel() {
		
		String joinUri = utility.generateUri(baseUri, Uri.CHANNEL_JOIN.toString());
		
		JSONObject body = new JSONObject();
		body.put(Channel.NAME.toString(), channelName);
		body.put(Channel.VALIDATE.toString(), true);
		
		Response resp  = utility.firePostRequest(joinUri, accessToken, headers, null, body);
		
		assertNull(resp.jsonPath().getString("error"), resp.jsonPath().getString("error"));
		assertTrue(resp.jsonPath().getBoolean("ok"));

		String chName = resp.jsonPath().getString("channel.name");
		String chId = resp.jsonPath().getString("channel.id");
		
		assertEquals(chName, channelName, "joined channel name is different");
		assertEquals(chId, channelId, "joined channel id is different");
		
		System.out.println("joined channel with name "+chName+" and id "+chId);
		
		
	}
}
