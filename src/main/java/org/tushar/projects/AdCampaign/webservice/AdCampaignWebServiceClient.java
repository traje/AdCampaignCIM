package org.tushar.projects.AdCampaign.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class AdCampaignWebServiceClient {
	private final static Logger logger = Logger.getLogger(AdCampaignWebServiceClient.class);
	private HttpClient client = null;
	private HttpPost post = null;
	private HttpGet request = null;
	private HttpResponse response = null;
	private BufferedReader reader = null;

	private String WEB_SERVICE_URL_CREATE = "http://localhost:8080/AdCampaign/ad/create";
	private String WEB_SERVICE_URL_ALL = "http://localhost:8080/AdCampaign/ad/all";
	private String WEB_SERVICE_URL_FIND_ACTIVE = "http://localhost:8080/AdCampaign/ad/findActive/100";
	private String WEB_SERVICE_URL_FIND = "http://localhost:8080/AdCampaign/ad/find/100";
	private String WEB_SERVICE_URL_DELETE = "http://localhost:8080/AdCampaign/ad/deleteAd/100";
	private String WEB_SERVICE_URL_UPDATE = "http://localhost:8080/AdCampaign/ad/updateAd";

	private void init() {
		client = HttpClientBuilder.create().build();
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {
		// TODO Auto-generated method stub
		AdCampaignWebServiceClient tester = new AdCampaignWebServiceClient();
		tester.init();
		//tester.testAll();
		tester.testCreate();
		tester.testUpdate();
		//tester.testAll();		
		//tester.testFindActive();
		//tester.testFind();
		//tester.testDelete();
	}

	private void testAll() throws ClientProtocolException, IOException{
		logger.debug("entered testAll");
		try {
			request = new HttpGet(WEB_SERVICE_URL_ALL);
			response = client.execute(request);
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			logger.debug("Out put of testAll ======================= :");
			while ((line = reader.readLine()) != null) {
				logger.debug(line);
			}
			logger.debug(": ====================end Out put of testAll");
		} finally {
			reader.close();
		}
	}
	
	private void testFindActive() throws ClientProtocolException, IOException{
		logger.debug("entered testFindActive");
		try {
			request = new HttpGet(WEB_SERVICE_URL_FIND_ACTIVE);
			response = client.execute(request);
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			logger.debug("Out put of testFindActive ======================= :");
			while ((line = reader.readLine()) != null) {
				logger.debug(line);
			}
			logger.debug(": ====================end Out put of testFindActive");
		} finally {
			reader.close();
		}
	}

	private void testFind() throws ClientProtocolException, IOException{
		logger.debug("entered testFind");
		try {
			request = new HttpGet(WEB_SERVICE_URL_FIND);
			response = client.execute(request);
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			logger.debug("Out put of testFind ======================= :");
			while ((line = reader.readLine()) != null) {
				logger.debug(line);
			}
			logger.debug(": ====================end Out put of testFind");
		} finally {
			reader.close();
		}
	}	
	
	private void testCreate() throws ClientProtocolException, IOException, JSONException{
		logger.debug("entered testCreate");
		try {
			post = new HttpPost(WEB_SERVICE_URL_CREATE);
			JSONObject json = new JSONObject();
			json.put("partner_id", "100");
			json.put("duration", "60");
			json.put("ad_content", "First Advertisement");
			StringEntity input = new StringEntity( json.toString());
			input.setContentType("application/json");
			post.setEntity(input);			
			response = client.execute(post);
			
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			logger.debug("Out put of testCreate ======================= :");
			while ((line = reader.readLine()) != null) {
				logger.debug(line);
			}
			logger.debug(": ====================end Out put of testCreate");
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			reader.close();			
		}		
	}
	
	private void testUpdate() throws ClientProtocolException, IOException, JSONException{
		logger.debug("entered testUpdate");
		try {
			post = new HttpPost(WEB_SERVICE_URL_UPDATE);
			JSONObject json = new JSONObject();
			json.put("partner_id", "100");
			json.put("duration", "70");
			json.put("ad_content", "Updated Advertisement");
			StringEntity input = new StringEntity( json.toString());
			input.setContentType("application/json");
			post.setEntity(input);			
			response = client.execute(post);
			
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			logger.debug("Out put of testUpdate ======================= :");
			while ((line = reader.readLine()) != null) {
				logger.debug(line);
			}
			logger.debug(": ====================end Out put of testUpdate");
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			reader.close();			
		}		
	}	
		
	
	private void testDelete() throws ClientProtocolException, IOException, JSONException{
		logger.debug("entered testCreate");
		try {
			HttpDelete delete = new HttpDelete(WEB_SERVICE_URL_DELETE);			
			response = client.execute(delete);
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			logger.debug("Out put of testCreate ======================= :");
			while ((line = reader.readLine()) != null) {
				logger.debug(line);
			}
			logger.debug(": ====================end Out put of testCreate");
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			reader.close();			
		}		
	}	
	
}
