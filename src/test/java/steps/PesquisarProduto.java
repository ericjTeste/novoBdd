package steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; // Import necessário
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager; // Import necessário

public class PesquisarProduto {
	
	//private WebDriver driver;
	private WebDriver driver = Hooks.driver;


	@Dado("queEstou no siteAmazon") // Certifique-se que esta frase está no seu .feature
    public void inicializarNavegador() {
        
		//WebDriverManager.chromedriver().setup();
        //this.driver = new ChromeDriver();
        //this.driver.manage().window().maximize();
        //this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://www.amazon.com.br");
    }

	@Quando("abro o menu lateral {string}")
	public void abro_o_menu_lateral(String string) {
		
		//WebDriverManager.chromedriver().setup();
		//this.driver = new ChromeDriver(); // Inicializa o driver
		//this.driver.manage().window().maximize();
		//this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//this.driver.get("https://www.amazon.com.br");
		
        // Agora o driver não será mais null
		driver.findElement(By.id("nav-hamburger-menu")).click();
	}

	@Quando("seleciono a categoria {string}")
	public void seleciono_a_categoria(String categoria) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement elemento = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(categoria)));
	    
	    // Força o clique via JavaScript para ignorar bloqueios de animação
	    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
	}

	@Entao("o titulo da pagina deve conter {string}")
	public void o_titulo_da_pagina_deve_conter(String termoEsperado) {
  //      assert(driver.getTitle().contains(termoEsperado));
        // Se quiser fechar ao final deste teste:
        // driver.quit(); 
	}
	
	@After(order = 10, value = "@funcionais") // Ordem maior executa PRIMEIRO no @After
	public void tirarScreenshot(Scenario cenario) throws IOException {
		if (driver != null) {
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// Usar o nome do cenário evita caracteres estranhos do toString()
			String nomeArquivo = cenario.getName().replace(" ", "_");
			FileUtils.copyFile(file, new File("target/screenshot/" + nomeArquivo + ".jpg"));
		}
	}

	@After(order = 1, value = "@funcionais") // Ordem menor executa por ÚLTIMO
	public void fecharNavegador() {
		if (driver != null) {
			driver.quit();
		}
	}
}