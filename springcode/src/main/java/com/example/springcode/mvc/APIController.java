package com.example.springcode.mvc;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springcode.domain.Error;
import com.example.springcode.domain.WeatherResponse;
import com.example.springcode.domain.ZipCode;
import com.example.springcode.repo.ZipWeatherService;
import com.example.springcode.validator.ZipCodeValidator;

@Controller
@RequestMapping("/")
public class APIController {
	
	@Resource(name="zipWeatherService")
	private ZipWeatherService zipWeatherService;

	
	 @InitBinder("zipObj")
	    protected void initBinder(WebDataBinder binder) {
	        binder.setValidator(new ZipCodeValidator());
	  }
	
	@RequestMapping("")
	public String root(ZipCode zipCode,ModelMap modelMap){
		modelMap.put("zipObj", zipCode);
		return "index";
	}
	
	
	@RequestMapping("home")
	public String home(ZipCode zipCode,ModelMap modelMap){
		modelMap.put("zipObj", zipCode);
		return "index";
	}


	
	@RequestMapping("getWeather")
	public String getWeather(@ModelAttribute("zipObj") @Valid ZipCode zipObj,BindingResult bindingResult ,ModelMap modelMap){
		
				
		if(bindingResult.hasErrors()){
			return "index";
		}
		else{
			WeatherResponse weatherResponse = zipWeatherService.getWeather(Integer.parseInt(zipObj.getZipValue()));
			
			if(weatherResponse!=null){
				modelMap.put("data", weatherResponse);
			}
			else{
				Error error = new Error();
				error.setMsg(zipObj.getZipValue()+" : Zipcode Not Found");
				modelMap.put("error", error);
			}
			//modelMap.put("zipObj", zipObj);
		}		
		
		return "index";
		
	}
	
}
