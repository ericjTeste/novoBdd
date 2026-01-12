package steps;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {



	    public static WebDriver driver; // static garante que é o mesmo navegador para todo o projeto

	    @Before
	    public void configurarNavegador() {
	        if (driver == null) {
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();
	            driver.manage().window().maximize();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        }
	    }

	    @After(order = 1)
	    public void finalizarNavegador() {
	        if (driver != null) {
	            driver.quit();
	            driver = null; // Reseta para o próximo teste
	        }
	    }
	
}