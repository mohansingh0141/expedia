import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcode.domain.ServerResponse;
import com.example.springcode.repo.ZipUtilityService;

public class ZipUtilityServiceTest {
	
	
	private ZipUtilityService zipUtilityService;
	
	@Before
	public void setup(){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
		zipUtilityService = (ZipUtilityService) context.getBean("zipUtilityService");
		
	}
	
	@Test
	public void validZip(){
		
		Assert.assertTrue(zipUtilityService.isValidZip("94117"));
		
	}
	
	@Test
	public void invalidZip(){
		Assert.assertFalse(zipUtilityService.isValidZip("9456788"));
		Assert.assertFalse(zipUtilityService.isValidZip("945w78s"));
	}
	
	@Test
	public void weatherInfoIfZipValid(){
		ServerResponse response = zipUtilityService.getWeather(94117);
		Assert.assertEquals("Cole Valley, San Francisco", response.getCity());
		Assert.assertEquals("California", response.getState());
	}
	
	@Test
	public void weatherInfoIfZipInvalid(){
	
		Assert.assertNull(zipUtilityService.getWeather(987654));
	}

}
