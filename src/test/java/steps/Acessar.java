package steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Acessar {

	//private WebDriver driver;
	
	private WebDriver driver = Hooks.driver;

	@Dado("que estou no site da Amazon")
	public void que_estou_no_site_da_amazon() {

		//WebDriverManager.chromedriver().setup();
	    //this.driver = new ChromeDriver();
	    //this.driver.manage().window().maximize();
	    //this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    this.driver.get("https://www.amazon.com.br");
		
		

	  
	}

	@Dado("estou na tela inicial")
	public void estou_na_tela_inicial() {
		// Verifica se o logo da Amazon está presente para confirmar que carregou

		driver.findElement(By.xpath("//a[@aria-label='Amazon.com.br']"));

	}

	@Quando("aciono no icone da amazon")
	public void aciono_no_icone_da_amazon() {

		driver.findElement(By.id("nav-logo-sprites")).click();

	}

	@Entao("o sistema atualiza pagina")
	public void o_sistema_atualiza_pagina() {

		// Verifica se ainda estamos no site ou se o título contém "Amazon"
		/////assert (driver.getTitle().contains("Amazon"));
//		driver.quit(); // Fecha o navegador ao terminar
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
