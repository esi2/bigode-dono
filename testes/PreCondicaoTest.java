
import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

public class PreCondicaoTest extends AbstractTests {
  private WebElement wElement; 
  private boolean acceptNextAlert = true;
  private boolean existe = false;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
	public void setUp() throws Exception {
		driver.get(BASE_URL);
	}

  @Test  
  public void testeEntraURL() throws Exception {
	wElement = null;
	wElement = driver.findElement(By.cssSelector("div.row.igBigode"));
    assertTrue(wElement != null);
  } 
  
}
