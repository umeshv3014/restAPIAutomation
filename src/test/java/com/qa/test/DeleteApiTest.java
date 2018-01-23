package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class DeleteApiTest extends TestBase {
	TestBase testBase;
	String servicUrl;
	String servicUrluserUpdate;
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
		servicUrluserUpdate = prop.getProperty("serviceUrlUserUpdate");
		url = servicUrl + servicUrluserUpdate;
	}
	
	@Test
	public void deleteAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
	//	jackson api marshaling and unmarshaling
		ObjectMapper objectMapper = new ObjectMapper();
		Users users = new Users("mukesh", "leader", "500");
		
		// object to jason String marshaling
		String userJsonString = objectMapper.writeValueAsString(users);
		System.out.println(userJsonString);
		closeableHttpResponse = restClient.delete(url, userJsonString, headerMap);//call the api
		
		//validate the responce
		//Responcecode
	    int responceCode =  closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(responceCode, testBase.RESPONSE_STATUS_CODE_204);
		
	}
}
