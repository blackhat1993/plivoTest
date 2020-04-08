package channel.tests;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import plivo.automation.Channel;
import plivo.automation.Uri;

public class ArchiveChannelTest extends RenameChannelTest  {
	
	
	@Test()
	public void testArchiveChannel() {
		
		String archiveUri = utility.generateUri(baseUri, Uri.CHANNEL_ARCHIVE.toString());
		
		JSONObject body = new JSONObject();
		body.put(Channel.CHANNEL.toString(), channelId);
		
		Response resp  = utility.firePostRequest(archiveUri, accessToken, headers, null, body);
		
		assertNull(resp.jsonPath().getString("error"), resp.jsonPath().getString("error"));
		assertTrue(resp.jsonPath().getBoolean("ok"));
		
		System.out.println("Successfully archived the channel "+channelName);
		
		String isArchiveUri = utility.generateUri(baseUri, Uri.CHANNEL_INFO.toString());
		
		HashMap<String, Object> query = new HashMap<String, Object>();
		query.put(Channel.CHANNEL.toString(), channelId);
		
		resp  = utility.fireGetRequest(isArchiveUri, accessToken, headers, query);
		
		assertNull(resp.jsonPath().getString("error"), resp.jsonPath().getString("error"));
		assertTrue(resp.jsonPath().getBoolean("ok"));
		
		assertTrue(resp.jsonPath().getBoolean("channel.is_archived"), "Channel is not archeived");
		
		System.out.println("channel "+newChannelName+" is archeived");
		
		
	}
}
