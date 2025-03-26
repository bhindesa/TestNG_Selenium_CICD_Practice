package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

	public WebDriver driver;
	
	
	public WebDriver getDriver () throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config/browserConfig.properties");
		prop.load(file);
		
		
		String browser  = System.getProperty("browser") != null ? System.getProperty("browser"):  prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("firefox")) {
			
			try {
				driver = new FirefoxDriver();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.manage().window().setSize( new Dimension(1440, 900));
			
		} else if(browser.contains("chrome")) {

			String chromeBinaryFilePath = "/opt/chrome-testing/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing";
			String chromeDriverTestingPath = "/usr/local/bin/chromedriver";
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setBinary(chromeBinaryFilePath);
			if(browser.contains("headless")) {
				chromeOptions.addArguments("headless");
			}
			
			System.setProperty("webdriver.chrome.driver", chromeDriverTestingPath);
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().setSize( new Dimension(1440, 900));
		} 
		
		return driver;
	}
	
	public static String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot sc = (TakesScreenshot) driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		
		String newFilePath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		
		File file = new File(newFilePath);
		
		FileUtils.copyFile(source, file);
		
		return newFilePath;
		
	}
	
}
