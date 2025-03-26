package test;


import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import base.BaseTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Test1 extends BaseTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException {
		BaseTest base = new BaseTest();
			driver = base.getDriver();
	}
	
	@AfterClass
	public void tearDown() throws IOException {
		driver.quit();;
	}
	
	
	@Test(dataProvider = "getUserData", enabled=false)
	public void shouldAnswerWithTrue(HashMap<String, String> userData) {
		
		System.out.println(userData);
		String uFName = userData.get("firstname")  != null  ? (String) userData.get("firstname") : "None" ;
		String uLName = userData.get("lastname") != null ? (String) userData.get("lastname") : "No Last Name";
		String gender =  userData.get("gender") != null ?  (String) userData.get("gender") : null;
		
		System.out.print("uName: " + uFName + " ,");
		System.out.print("password : " + uLName + " ,");
		System.out.print("gender: " + gender + " |");
		System.out.println();

	}

	@DataProvider
	public Object[][] getUserData() throws IOException {

//		Map<String, ?> user1 = Map.of("firstname", "Sarb", "lastname", "bhinder", "marks", 99, "age", 25, "gender", "male");
//		Map<String, ?> user2 = Map.of("firstname", "Trump", "lastname", "Donald", "marks", 69, "age", 52);
//		Map<String, ?> user3 = Map.of("firstname", "Sam", "lastname", "Dhiilon", "age", 52);

		String jsonContent = FileUtils.readFileToString(new File( System.getProperty("user.dir") + "/src/test/java/testData/User.json"), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});

		return new Object[][] {
			{data.get(0)},
			{data.get(1)},
			{data.get(2)}
		};

	}
	
	
	@Parameters({"browser", "myName"})
	@Test(enabled=false)
	public void paraTest(@Optional("no browser") String browser, @Optional("no name")  String name) {
		
		System.out.println("Test Para run");
		System.out.println(browser);
		System.out.println(name);

		Assert.assertTrue(false);
	}
	
	@Test(priority = 1)
	public void getGoogleTitle() {
		
		driver.get("https://www.google.com");
		
		Assert.assertTrue(false);
			
	}
	
	@Test(priority = 2)
	public void getFacebookTitle() {
		
		driver.get("https://www.facebook.com");
		
		Assert.assertTrue(true);
		
		
	}
	
	@Test(priority = 3)
	public void getGmailTitle() {
		
		driver.get("https://www.gmail.com");
		
		Assert.assertTrue(true);
		
	}
	
	@Test(priority = 5)
	public void getUdemyTitle() {
		
		driver.get("https://www.Udemy.com");
		
		
		Assert.assertTrue(true);
		driver.quit();
		
	}
	
//	@Test(priority = 4, retryAnalyzer = RetryListener.class)
	@Test(priority = 4)
	public void getCanadaTitle() {
		
		driver.get("https://www.Canada.ca");
		
		String title = driver.getTitle();
		boolean gotTitle = title != null ? true : false;
		Assert.assertFalse(gotTitle);
			
		
	}
	
}
