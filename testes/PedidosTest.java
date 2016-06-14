
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

public class PedidosTest extends AbstractTests {
  private WebElement wElement; 
  private boolean acceptNextAlert = true;
  private boolean existe = false;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
	public void setUp() throws Exception {
		driver.get(BASE_URL);
	}
  
  @Test
  public void testeAbreFechaPedido() throws Exception{
	  List<WebElement> pedidos = driver.findElements(By.cssSelector("div.item.row"));
	  List<WebElement> minimizas = driver.findElements(By.cssSelector("a.minimiza"));
	  int i = 0;
	  
	  //assertEquals(pedidos.size(), minimizas.size());
	  
	  for (WebElement elemento: pedidos){
		  elemento.click();
		  WebElement mnz = minimizas.get(i);
		  Thread.sleep(1000);
		  assertTrue(mnz.isDisplayed());
		  elemento.click();
		  Thread.sleep(1000);
		  assertTrue(!mnz.isDisplayed());
		  i++;
	  }
  }
  
}
