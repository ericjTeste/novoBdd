package steps;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	
	public static WebDriver driver; // static garante que é o mesmo navegador para todo o projeto
	
	//EXECUÇÃO NO GITHUB
	@Before
	public void configurarNavegador() {
	    if (driver == null) {
	        WebDriverManager.chromedriver().setup();
	        
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless"); // Essencial para rodar no GitHub Actions
	        options.addArguments("--no-sandbox"); // Evita erros de permissão no Linux
	        options.addArguments("--disable-dev-shm-usage"); // Evita falta de memória em containers
	        options.addArguments("--window-size=1920,1080"); // Define um tamanho de tela padrão

	        driver = new ChromeDriver(options);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    }
	}
	
	public void finalizarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reseta a variável para o próximo cenário
        }
    }


/* EXECUÇÃO LOCAL
	    public static WebDriver driver; // static garante que é o mesmo navegador para todo o projeto
	    
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--headless"); // Adicione esta linha para o GitHub Actions
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");

	    driver = new ChromeDriver(options);

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
	*/
}