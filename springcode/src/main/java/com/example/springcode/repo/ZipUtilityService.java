package com.example.springcode.repo;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import com.example.springcode.domain.ServerResponse;

@Service
public class ZipUtilityService {
	
	Pattern pattern;
	
	public ZipUtilityService(){
		pattern = Pattern.compile("^\\d{5}$");
	}
	
	public boolean isValidZip(String zipcode){
		
		try{
			
			Matcher matcher = pattern.matcher(zipcode);
			return matcher.matches();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ServerResponse getWeather(int zipcode){
		

		HttpClient client = new DefaultHttpClient();
		HttpResponse response=null;

		ServerResponse serverResponse=null;
		
		try {

		HttpGet get = new HttpGet("http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/"+zipcode+".json");
		get.addHeader("accept", "application/json");
		response = client.execute(get);			 
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		HashMap<String,Object> dataMap = mapper.readValue(response.getEntity().getContent(),new TypeReference<HashMap<String,Object>>(){});
		
		HashMap<String, Object> currentObservation = (HashMap<String, Object>) dataMap.get("current_observation");
		HashMap<String, Object> locationData=null;
		if(currentObservation!=null){
			locationData = (HashMap<String, Object>) currentObservation.get("observation_location");
		}
		
		
		if(dataMap.get("error")==null&&locationData!=null){
			ServerResponse data = new ServerResponse();
			data.setZipcode(new Integer(zipcode).toString());
			data.setCity((String)locationData.get("city"));
			data.setState((String)locationData.get("state"));
			data.setTemp((Double)currentObservation.get("temp_f"));
			return data;
		}
		else{
			return null;
		}
		
		}catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 		
		
		return serverResponse;
		
	}

}
