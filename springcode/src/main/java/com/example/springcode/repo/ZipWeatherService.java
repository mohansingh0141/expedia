package com.example.springcode.repo;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springcode.domain.WeatherResponse;

@Service
public class ZipWeatherService {
	
	
	@Value("http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/")
	private String url;
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
	
	public WeatherResponse getWeather(int zipcode){
		

		RestTemplate restTemplate = new RestTemplate();	

		WeatherResponse serverResponse=null;
		
		try {

		
		HashMap<String,Object> dataMap = restTemplate.getForObject(url+zipcode+".json",HashMap.class);		
		
		HashMap<String, Object> currentObservation = (HashMap<String, Object>) dataMap.get("current_observation");
		HashMap<String, Object> locationData=null;
		if(currentObservation!=null){
			locationData = (HashMap<String, Object>) currentObservation.get("observation_location");
		}
		
		
		if(dataMap.get("error")==null&&locationData!=null){
			WeatherResponse data = new WeatherResponse();
			data.setZipcode(new Integer(zipcode).toString());
			data.setCity((String)locationData.get("city"));
			data.setState((String)locationData.get("state"));
			data.setTemp((Double)currentObservation.get("temp_f"));
			return data;
		}
		else{
			return null;
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return serverResponse;
		
	}

}
