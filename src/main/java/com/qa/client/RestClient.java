package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	//1. GET Method without Headers:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); //create connection return one CloseableHttpClient object
		HttpGet httpget = new HttpGet(url);//create get connection with url
		CloseableHttpResponse closeableHttpResponse =  httpClient.execute(httpget);//hit the url // the complete responce  status header 
		return closeableHttpResponse;
	}
	
	//2. GET Method with Headers:
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMpa) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); //create connection return one CloseableHttpClient object
		HttpGet httpget = new HttpGet(url);//create get connection with url
		for(Map.Entry<String, String> entry : headerMpa.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse =  httpClient.execute(httpget);//hit the url // the complete responce  status header 
		return closeableHttpResponse;
	}
	
	//3. POST Method:
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);//post request
		httpPost.setEntity(new StringEntity(entityString));//fine payload
		
		//for header
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse =  httpClient.execute(httpPost);//hit the url // the complete responce  status header 
		return closeableHttpResponse;
		
	}
	
	//put Method
	
	public CloseableHttpResponse put(String url, String entityString,HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(entityString));
		//for header
				for (Map.Entry<String, String> entry : headerMap.entrySet()) {
					httpPut.addHeader(entry.getKey(), entry.getValue());
				}
				CloseableHttpResponse closeableHttpResponse =  httpClient.execute(httpPut);//hit the url // the complete responce  status header 
				return closeableHttpResponse;
	}
	
	//delete method
	public CloseableHttpResponse delete(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpDelete.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse =  httpClient.execute(httpDelete);//hit the url // the complete responce  status header 
		return closeableHttpResponse;
	}
	
//	public void patch(String url){
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpPatch httpPatch = new HttpPatch(url);
//	}
}
