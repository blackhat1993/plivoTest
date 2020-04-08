package channel.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import plivo.automation.Channel;
import plivo.automation.Uri;

public class RenameChannelTest extends JoinNewCreatedChannelTest {

	protected String newChannelName;
	
	@Test
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
		
		String listUri = utility.generateUri(baseUri, Uri.CHANNEL_LIST.toString());
		
		resp = utility.fireGetRequest(listUri, accessToken, headers, null);
		
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
}
