package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Constants;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase {
	TestBase testBase;
	String servicUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	String lastName;
	String id;
	String avatar;
	String firstName;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		servicUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		url = servicUrl + apiUrl;

	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
	//	jackson api marshaling and unmarshalling
		ObjectMapper objectMapper = new ObjectMapper();
		Users users = new Users("mukesh", "leader");
		
	//	object to jsone
		//objectMapper.writeValue(new File("/restAPIAutomation/src/main/java/com/qa/data/users.jason"), user);
		
		// object to jason String marshalling
		String userJsonString = objectMapper.writeValueAsString(users);
		System.out.println(userJsonString);
		closeableHttpResponse = restClient.post(url, userJsonString, headerMap);//call the api
		
		//validate the responce
		//Responcecode
	   int responceCode =  closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(responceCode, Constants.RESPONSE_STATUS_CODE_201);
		
		//2. JsonString
		String responceString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject jsonObject = new JSONObject(responceString);
		System.out.println("the responce Json from API is  " + jsonObject);
		//Json to java object unmarshalling
		Users userObj =	objectMapper.readValue(responceString, Users.class);
		System.out.println(userObj);
		Assert.assertTrue(users.getName().equals(userObj.getName()));
		Assert.assertTrue(users.getJob().equals(userObj.getJob()));
		System.out.println(userObj.getId());
		System.out.println(userObj.getCreatedAt());
		
	}
}
