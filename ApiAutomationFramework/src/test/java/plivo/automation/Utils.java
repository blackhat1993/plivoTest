package plivo.automation;

import java.util.HashMap;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Utils {

	public String generateUri(String base, String extra) {
		
		return base+extra;
	}
	
	public Response fireGetRequest(String uri, String token, HashMap<String, String> headers, HashMap<String, Object> query) {
		
		RestAssured.baseURI = uri;		
		System.out.println("Request URL is "+RestAssured.baseURI);
		
		Response resp;
		
		if (query == null)
			resp = RestAssured.given().auth().oauth2(token).headers(headers).get();
		
		else {
		
			for (HashMap.Entry<String,Object> entry : query.entrySet())  
				System.out.println("key is "+entry.getKey()+" , Value is "+entry.getValue());
		
			resp = RestAssured.given().auth().oauth2(token).queryParams(query).headers(headers).get();
		
		}
		
		System.out.println("Response code is "+ resp.getStatusCode());
		System.out.println("Response is "+ resp.asString());
		
		return resp;
		
	}
	
public Response firePostRequest(String uri, String token, HashMap<String, String> headers, HashMap<String, Object> query, JSONObject body) {
		
		RestAssured.baseURI = uri;
		System.out.println("Request URL is "+RestAssured.baseURI);
		
		Response resp;
		
		if (query == null)
			resp = RestAssured.given().auth().oauth2(token).headers(headers).body(body.toJSONString()).post();
		
		else {
			
			for (HashMap.Entry<String,Object> entry : query.entrySet())  
				System.out.println("key is "+entry.getKey()+" , Value is "+entry.getValue());
			resp = RestAssured.given().auth().oauth2(token).queryParams(query).headers(headers).body(body.toJSONString()).post();
		}
		
		System.out.println("Response code is "+ resp.getStatusCode());
		System.out.println("Response is "+ resp.asString());
		
		return resp;
		
	}
}
