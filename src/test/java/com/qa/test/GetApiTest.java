package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Constants;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase {
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

	@Test(priority = 1)
	public void getApiTestWithoutHeader() throws ClientProtocolException,
			IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);

		// status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("status code " + statusCode);

		Assert.assertEquals(statusCode, Constants.RESPONSE_STATUS_CODE_200,
				"status code is not " + Constants.RESPONSE_STATUS_CODE_200);

		// JSON String
		String responceString = EntityUtils.toString(
				closeableHttpResponse.getEntity(), "UTF-8"); // json will be
																// saved in
																// string

		JSONObject responceJson = new JSONObject(responceString);
		System.out.println("responce JSON fron API  " + responceJson);

		// Single value assertion
		// perPageValue
		String perPageValue = TestUtil.getValueByJpath(responceJson,
				"/per_page");
		System.out.println(perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);

		// totalValue
		String totalValue = TestUtil.getValueByJpath(responceJson, "/total");
		System.out.println(totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// get the value for jsonarray
		String lastName = TestUtil.getValueByJpath(responceJson,
				"/data[0]/last_name");
		Assert.assertEquals(lastName, "Bluth");
		String id = TestUtil.getValueByJpath(responceJson, "/data[0]/id");
		Assert.assertEquals(Integer.parseInt(id), 1);
		String avatar = TestUtil.getValueByJpath(responceJson,
				"/data[0]/avatar");
		Assert.assertEquals(avatar,
				"https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		String firstName = TestUtil.getValueByJpath(responceJson,
				"/data[0]/first_name");
		Assert.assertEquals(firstName, "George");
		// System.out.println(lastName + id + avatar + firstName);

		// get the value for jsonarray
		lastName = TestUtil.getValueByJpath(responceJson, "/data[1]/last_name");
		Assert.assertEquals(lastName, "Weaver");
		id = TestUtil.getValueByJpath(responceJson, "/data[1]/id");
		Assert.assertEquals(Integer.parseInt(id), 2);
		avatar = TestUtil.getValueByJpath(responceJson, "/data[1]/avatar");
		Assert.assertEquals(avatar,
				"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
		firstName = TestUtil.getValueByJpath(responceJson,
				"/data[1]/first_name");
		Assert.assertEquals(firstName, "Janet");
		// System.out.println(lastName + id + avatar + firstName);

		// get the value for jsonarray
		lastName = TestUtil.getValueByJpath(responceJson, "/data[2]/last_name");
		Assert.assertEquals(lastName, "Wong");
		id = TestUtil.getValueByJpath(responceJson, "/data[2]/id");
		Assert.assertEquals(Integer.parseInt(id), 3);
		avatar = TestUtil.getValueByJpath(responceJson, "/data[2]/avatar");
		Assert.assertEquals(avatar,
				"https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg");
		firstName = TestUtil.getValueByJpath(responceJson,
				"/data[2]/first_name");
		Assert.assertEquals(firstName, "Emma");
		// System.out.println(lastName + id + avatar + firstName);

		// All headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeader = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeader.put(header.getName(), header.getValue());
		}
		System.out.println("header array  " + allHeader);

	}

	@Test(priority = 2)
	public void getApiTestWithHeader() throws ClientProtocolException,
			IOException {

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("userName", "tes");
		// headerMap.put("password", "test123");
		// headerMap.put("user", "amazon");
		// headerMap.put("authToken", "test123");
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url, headerMap);

		// status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("status code " + statusCode);

		Assert.assertEquals(statusCode, Constants.RESPONSE_STATUS_CODE_200,
				"status code is not " + Constants.RESPONSE_STATUS_CODE_200);

		// JSON String
		String responceString = EntityUtils.toString(
				closeableHttpResponse.getEntity(), "UTF-8"); // json will be
																// saved in
																// string

		JSONObject responceJson = new JSONObject(responceString);
		System.out.println("responce JSON fron API  " + responceJson);

		// Single value assertion
		// perPageValue
		String perPageValue = TestUtil.getValueByJpath(responceJson,
				"/per_page");
		System.out.println(perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);

		// totalValue
		String totalValue = TestUtil.getValueByJpath(responceJson, "/total");
		System.out.println(totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// get the value for jsonarray
		String lastName = TestUtil.getValueByJpath(responceJson,
				"/data[0]/last_name");
		Assert.assertEquals(lastName, "Bluth");
		String id = TestUtil.getValueByJpath(responceJson, "/data[0]/id");
		Assert.assertEquals(Integer.parseInt(id), 1);
		String avatar = TestUtil.getValueByJpath(responceJson,
				"/data[0]/avatar");
		Assert.assertEquals(avatar,
				"https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		String firstName = TestUtil.getValueByJpath(responceJson,
				"/data[0]/first_name");
		Assert.assertEquals(firstName, "George");
		// System.out.println(lastName + id + avatar + firstName);

		// get the value for jsonarray
		lastName = TestUtil.getValueByJpath(responceJson, "/data[1]/last_name");
		Assert.assertEquals(lastName, "Weaver");
		id = TestUtil.getValueByJpath(responceJson, "/data[1]/id");
		Assert.assertEquals(Integer.parseInt(id), 2);
		avatar = TestUtil.getValueByJpath(responceJson, "/data[1]/avatar");
		Assert.assertEquals(avatar,
				"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
		firstName = TestUtil.getValueByJpath(responceJson,
				"/data[1]/first_name");
		Assert.assertEquals(firstName, "Janet");
		// System.out.println(lastName + id + avatar + firstName);

		// get the value for jsonarray
		lastName = TestUtil.getValueByJpath(responceJson, "/data[2]/last_name");
		Assert.assertEquals(lastName, "Wong");
		id = TestUtil.getValueByJpath(responceJson, "/data[2]/id");
		Assert.assertEquals(Integer.parseInt(id), 3);
		avatar = TestUtil.getValueByJpath(responceJson, "/data[2]/avatar");
		Assert.assertEquals(avatar,
				"https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg");
		firstName = TestUtil.getValueByJpath(responceJson,
				"/data[2]/first_name");
		Assert.assertEquals(firstName, "Emma");
		// System.out.println(lastName + id + avatar + firstName);

		// All headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeader = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeader.put(header.getName(), header.getValue());
		}
		System.out.println("header array  " + allHeader);

	}
}
