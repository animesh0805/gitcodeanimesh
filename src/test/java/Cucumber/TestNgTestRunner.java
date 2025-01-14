package Cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber",glue = "animeshtiwari.stepdefinitions",monochrome=true,plugin = {"html:target/Cucumber.html"})
public class TestNgTestRunner extends AbstractTestNGCucumberTests {
}
