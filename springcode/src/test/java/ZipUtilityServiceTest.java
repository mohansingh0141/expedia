import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcode.domain.WeatherResponse;
import com.example.springcode.repo.ZipWeatherService;
import com.example.springcode.validator.ZipCodeValidator;


public class ZipUtilityServiceTest {
	
	
	private ZipWeatherService zipWeatherService;
	
	
	@Before
	public void setup(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
		zipWeatherService = (ZipWeatherService) context.getBean("zipWeatherService");
		
	}
	
	@Test
	public void validZip(){
		
		Assert.assertTrue(ZipCodeValidator.isValidZip("94117"));
		
	}
	
	@Test
	public void invalidZip(){
		Assert.assertFalse(ZipCodeValidator.isValidZip("9456788"));
		Assert.assertFalse(ZipCodeValidator.isValidZip("945w78s"));
	}
	
	@Test
	public void weatherInfoIfZipValid(){
		WeatherResponse response = zipWeatherService.getWeather(94117);
		Assert.assertEquals("Cole Valley, San Francisco", response.getCity());
		Assert.assertEquals("California", response.getState());
	}
	
	@Test
	public void weatherInfoIfZipInvalid(){
	
		Assert.assertNull(zipWeatherService.getWeather(987654));
	}

}
