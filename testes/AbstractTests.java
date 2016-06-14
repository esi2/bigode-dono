import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public abstract class AbstractTests {
	
	protected static final String BASE_URL = "http://143.107.58.177:8080/bigode-dono/";
	protected static WebDriver driver;
	
	static {
		Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.SEVERE);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		driver = new FirefoxDriver();
		driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	
	protected int randomInt(int min, int max) {
		Random random = new Random();
		return (random.nextInt((max - min) + 1) + min);
	}
	
	protected boolean isElementPresent(By by) {
	    try {
	    	driver.findElement(by);
	    	return true;
	    } 
	    catch (Exception e) {
	    	return false;
	    }
	}

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
