package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	CloseableHttpClient httpClient;
	HttpGet httpget;
	CloseableHttpResponse closeableHttpResponse;
	HttpPost httpPost;
	HttpPut httpPut;
	HttpDelete httpDelete;
	HttpPatch httpPatch;

	// 1. GET Method without Headers:
	public CloseableHttpResponse get(String url)
			throws ClientProtocolException, IOException {
		httpClient = HttpClients.createDefault(); // create connection return
													// one CloseableHttpClient
													// object
		httpget = new HttpGet(url);// create get connection with url
		closeableHttpResponse = httpClient.execute(httpget);// hit the url //
															// the complete
															// responce status
															// header
		return closeableHttpResponse;
	}

	// 2. GET Method with Headers:
	public CloseableHttpResponse get(String url,
			HashMap<String, String> headerMpa) throws ClientProtocolException,
			IOException {
		httpClient = HttpClients.createDefault(); // create connection return
													// one CloseableHttpClient
													// object
		httpget = new HttpGet(url);// create get connection with url
		for (Map.Entry<String, String> entry : headerMpa.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient
				.execute(httpget);// hit the url // the complete responce status
									// header
		return closeableHttpResponse;
	}

	// 3. POST Method:
	public CloseableHttpResponse post(String url, String entityString,
			HashMap<String, String> headerMap) throws ClientProtocolException,
			IOException {
		httpClient = HttpClients.createDefault();
		httpPost = new HttpPost(url);// post request
		httpPost.setEntity(new StringEntity(entityString));// fine payload

		// for header
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		closeableHttpResponse = httpClient.execute(httpPost);// hit the url //
																// the complete
																// responce
																// status header
		return closeableHttpResponse;

	}

	// put Method

	public CloseableHttpResponse put(String url, String entityString,
			HashMap<String, String> headerMap) throws ClientProtocolException,
			IOException {
		httpClient = HttpClients.createDefault();
		httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(entityString));
		// for header
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient
				.execute(httpPut);// hit the url // the complete responce status
									// header
		return closeableHttpResponse;
	}

	// delete method
	public CloseableHttpResponse delete(String url, String entityString,
			HashMap<String, String> headerMap) throws ClientProtocolException,
			IOException {
		httpClient = HttpClients.createDefault();
		httpDelete = new HttpDelete(url);
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpDelete.addHeader(entry.getKey(), entry.getValue());
		}
		closeableHttpResponse = httpClient.execute(httpDelete);// hit the url //
																// the complete
																// responce
																// status header
		return closeableHttpResponse;
	}

	 public CloseableHttpResponse patch(String url, String entityString,
				HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
	  httpClient = HttpClients.createDefault();
	  httpPatch = new HttpPatch(url);
	  httpPatch.setEntity(new StringEntity(entityString));
	  for (Map.Entry<String, String> entry : headerMap.entrySet()) {
		  httpPatch.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient
				.execute(httpPatch);// hit the url // the complete responce status
									// header
		return closeableHttpResponse;
	 }
}
