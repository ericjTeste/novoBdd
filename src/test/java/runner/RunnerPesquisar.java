package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/feature2", // Caminho das features
    glue = "steps2",                           // Nome do pacote onde está a classe Steps.java
    plugin = {"pretty", "html:target/report.html"},
    monochrome = true
)

public class RunnerPesquisar {

}
