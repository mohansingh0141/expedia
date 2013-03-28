package com.example.springcode.mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springcode.domain.Error;
import com.example.springcode.domain.ServerResponse;
import com.example.springcode.repo.ZipUtilityService;

@Controller
@RequestMapping("/")
public class APIController {
	
	@Resource(name="zipUtilityService")
	private ZipUtilityService zipUtilityService;

	@RequestMapping("home")
	public String home(HttpServletRequest request,HttpServletResponse response){
		
		return "index";
		
	}
	
	@RequestMapping("getWeather")
	public String getWeather(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		
		String zipcode = request.getParameter("zipcode");
		
		if(zipUtilityService.isValidZip(zipcode)){
			ServerResponse serverResponse = zipUtilityService.getWeather(Integer.parseInt(zipcode));
			
			if(serverResponse!=null){
				modelMap.put("data", serverResponse);
			}
			else{
				Error error = new Error();
				error.setMsg(zipcode+" : Zipcode Not Found");
				modelMap.put("error", error);
			}
		}
		else{
			Error error = new Error();
			error.setMsg(zipcode+" : Invalid zip code format");
			modelMap.put("error", error);
		}	
		
		return "index";
		
	}
	
}
