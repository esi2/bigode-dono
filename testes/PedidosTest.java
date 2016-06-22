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
  public void testRecebimentoPedido() throws Exception{
	  apagaPedidos();
	  inserePedido();
	  List <WebElement> pedidoFeito = driver.findElements(By.cssSelector("div.item.row"));
	  assertTrue(pedidoFeito.size() > 0);
	  WebElement textoPedido = driver.findElement(By.cssSelector("strong"));
	  assertTrue(textoPedido.getText().equals("A mesa 1 fez um pedido!"));
  }
  
  @Test
  public void testeAbreFechaPedido() throws Exception{
	  List<WebElement> pedidos = driver.findElements(By.cssSelector("div.item.row"));
	  List<WebElement> minimizas = driver.findElements(By.cssSelector(".minimiza"));
	  int i = 0;
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
  
  /*
  @Test
  public void marcarProdutoEntregue()throws Exception{
	  apagaPedidos();
	  inserePedido();
	  Thread.sleep(2000);
	  WebElement pedido = driver.findElement(By.xpath("//div[@id='todos-pedidos']/div/div/div/p"));
	  pedido.click();
	  Thread.sleep(1000);
	  List<WebElement> botaoCheck = driver.findElements(By.xpath("(//input[@name='demo'])"));
	  int indice = 0;
	  int numclicks = 0;
	  while(indice < botaoCheck.size()){
	      if(indice == 0)
	    	  driver.findElement(By.name("demo")).click();
		  else
			  botaoCheck.get(indice).click();
	      numclicks++;
		  Thread.sleep(1000);
		  driver.switchTo().alert().accept();
		  if(isAlertPresent() && indice != (botaoCheck.size()-1))
			  Assert.fail("Alerta de baixa de pedido exibida antes de checar todos os produtos no pedido");
		  if(!isAlertPresent() && indice == (botaoCheck.size()-1))
			  Assert.fail("Nao foi exibido alerta de baixa de pedido");
		  indice++;
		  botaoCheck = driver.findElements(By.xpath("(//input[@name='demo'])"));
	  }
	  driver.switchTo().alert().accept();
	  Thread.sleep(1000);
	  botaoCheck = driver.findElements(By.xpath("//input[@name='demo']"));
	  assertTrue(botaoCheck.size() == 0);/*
  }*/
  
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
