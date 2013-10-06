package com.djangodemo.gcmclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class NetworkHelper {
	private static final String TAG = "NetSync";
    			
	private static HttpPost GCMPost(String url){
		HttpPost httppost = new HttpPost(url);
	    httppost.setHeader("Accept", "application/json");
	    httppost.setHeader("User-Agent", "AndroidApp");
	    return httppost;
	}
	
	public static Boolean registeruser(String registrationID, Context context){
		HttpClient httpclient = new DefaultHttpClient();
		
	    HttpPost httppost = GCMPost(MagicValues.server+"/gcmhttp/registeruser");
	    
	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("reg_id", registrationID));
	    
	    try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity responseEntity = response.getEntity();
	        //Decode reply
	        if(responseEntity!=null){
	        	String reply = EntityUtils.toString(responseEntity);
	        	Log.d(TAG,reply);
	        	JSONObject json = new JSONObject(reply);
	        	
	        	String status = json.getString(MagicValues.JSON_KEY_STATUS);
	        	if(status.equalsIgnoreCase(MagicValues.JSON_SUCCESS)){
		        	return true;	
	        	} else {
	        		return false;
	        	}
	        }
	        	        
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Log.d("Error", "Stacktrace", e);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.d("Error", "Stacktrace", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("Error", "Stacktrace", e);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("Error", "Stacktrace", e);
		}
	    
        return false;
	}
}
