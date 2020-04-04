package channel.tests;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import plivo.automation.Channel;
import plivo.automation.Uri;

public class PlivoTest extends BaseTest {

	protected String channelName;
	protected String channelId;
	protected String newChannelName;
	
	
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
	
	@Test(priority=1)
	public void testJoinNewlyCreatedChannel() {
		
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
	
	@Test(priority=2)
	public void testRenameChannel() {
		
		newChannelName = rp.getChannelNewName();
		String renameUri = utility.generateUri(baseUri, Uri.CHANNEL_RENAME.toString());
		
		JSONObject body = new JSONObject();
		body.put(Channel.CHANNEL.toString(), channelId);
		body.put(Channel.NAME.toString(), newChannelName);
		body.put(Channel.VALIDATE.toString(), true);
		
		Response resp  = utility.firePostRequest(renameUri, accessToken, headers, null, body);
		
		assertNull(resp.jsonPath().getString("error"), resp.jsonPath().getString("error"));
		assertTrue(resp.jsonPath().getBoolean("ok"));

		String chName = resp.jsonPath().getString("channel.name");
		String chId = resp.jsonPath().getString("channel.id");
		
		assertEquals(chName, newChannelName, "renaming the channel name is unsuccessfull");
		assertEquals(chId, channelId, "The channle name renamed is different");
		
		System.out.println("successfully renamed the channel with name "+chName+" and id "+chId);
		
	}
	
	@Test(priority=3)
	public void verifyRenamedChannel() {
		
		String listUri = utility.generateUri(baseUri, Uri.CHANNEL_LIST.toString());
		
		Response resp = utility.fireGetRequest(listUri, accessToken, headers, null);
		
		assertNull(resp.jsonPath().getString("error"), resp.jsonPath().getString("error"));
		assertTrue(resp.jsonPath().getBoolean("ok"));
		
		List<String> channelNames = new ArrayList<String>();		
		channelNames = resp.jsonPath().getList("channels.name");
		int i=0;
		boolean flag = false;
		
		for (String cname: channelNames) {
			
			if (cname.equals(newChannelName)) {
				flag = true;
				break;
			}
			i++;
		}
		
		assertTrue(flag, "Channel could not be renamed successfully!!!");
		assertEquals(resp.jsonPath().getList("channels.id").get(i), channelId, "channel "+channelName+" with Id "+channelId+ " could not be renamed to "+newChannelName);
		
		System.out.println("After renaming channel "+channelName+" to "+newChannelName+" it is present in the list");
		
	}
	
	@Test(priority=4)
	public void testArchiveChannel() {
		
		String archiveUri = utility.generateUri(baseUri, Uri.CHANNEL_ARCHIVE.toString());
		
		JSONObject body = new JSONObject();
		body.put(Channel.CHANNEL.toString(), channelId);
		
		Response resp  = utility.firePostRequest(archiveUri, accessToken, headers, null, body);
		
		assertNull(resp.jsonPath().getString("error"), resp.jsonPath().getString("error"));
		assertTrue(resp.jsonPath().getBoolean("ok"));
		
		System.out.println("Successfully archived the channel "+channelName);
	}
	
	
	@Test(priority=5)
	public void verifyArchivedChannel() {
		
		String isArchiveUri = utility.generateUri(baseUri, Uri.CHANNEL_INFO.toString());
		
		HashMap<String, Object> query = new HashMap<String, Object>();
		query.put(Channel.CHANNEL.toString(), channelId);
		
		Response resp  = utility.fireGetRequest(isArchiveUri, accessToken, headers, query);
		
		assertNull(resp.jsonPath().getString("error"), resp.jsonPath().getString("error"));
		assertTrue(resp.jsonPath().getBoolean("ok"));
		
		assertTrue(resp.jsonPath().getBoolean("channel.is_archived"), "Channel is not archeived");
		
		System.out.println("channel "+newChannelName+" is archeived");
	}
	
}
