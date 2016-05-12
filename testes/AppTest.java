
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

public class AppTest {
  private WebDriver driver;
  private WebElement we; 
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private boolean existe = false;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new HtmlUnitDriver();
    baseUrl = "http://143.107.58.177:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testeEntraURL() throws Exception {
//	we = null;
//	existe = false;
//    driver.get(baseUrl + "/bigode-dono/");
//    we = driver.findElement(By.cssSelector("button.btn.btn-pago"));
//    if(we != null)
//    	existe = true;
//    assertTrue(existe);
  }
  /*testes que serao adaptados com a conexao, funcionando quando existir algo a pagar
  @Test
  public void testeAbreFechaPagar() throws Exception{
	driver.get(baseUrl + "/bigode-dono/");
    driver.findElement(By.cssSelector("div.article.pago-master-class > div.item.row > div.col-xs-9 > p.source")).click();
    driver.findElement(By.cssSelector("div.article.pago-master-class > div.item.row > div.col-xs-9 > p.source")).click();
  }
  
  @Test
  public void clicaPago() throws Exception{
	driver.get(baseUrl + "/bigode-dono/");
    driver.findElement(By.cssSelector("button.btn.btn-pago")).click();
  }
  
  @Test
  public void testeAbreFechaEntregar() throws Exception{
	driver.get(baseUrl + "/bigode-dono/");
    driver.findElement(By.cssSelector("div.article.entregue-master-class > div.item.row > div.col-xs-9 > p.source")).click();
    driver.findElement(By.cssSelector("div.article.entregue-master-class > div.item.row > div.col-xs-9 > p.source")).click();
  }
  
  @Test
  public void clicaEntregue() throws Exception{
	driver.get(baseUrl + "/bigode-dono/");
    driver.findElement(By.cssSelector("button.btn.btn-entregue")).click();
  }*/

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
