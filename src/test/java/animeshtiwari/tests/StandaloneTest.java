package animeshtiwari.tests;

import animeshtiwari.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        LandingPage landingpage= new LandingPage(driver);
        String productname = "ZARA COAT 3";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("animash9961@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Manoj@0805");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(@class,'mb-3')]"))));
        List<WebElement> products = driver.findElements(By.xpath("//*[contains(@class,'mb-3')]"));
        //filter is used to apply one more codition likke if else
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
//.card-body button:last-of-type
        prod.findElement(By.xpath("//*[@class='card-body']//button[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.xpath("//*[contains(@routerlink,'cart')]")).click();
        List<WebElement> cartproducts = driver.findElements(By.xpath("//*[@class='cartSection']/ h3"));
//We will use anymatch as it hellps to get the boolean value instead of folter which returs the filtered item
        Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//*[@placeholder=\"Select Country\"]")),"india").build().perform();
        //a.sendKeys(driver.findElement(By.xpath("//*[contains(@placeholder,\"Select Country\")])")), "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//*[contains(@class,'ta-item')])[2]")).click();
        a.moveToElement(driver.findElement(By.cssSelector(".action__submit "))).click().build().perform();
        String Confirmessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertTrue( Confirmessage.equalsIgnoreCase("Thankyou for the order."));
       driver.close();
    }
}
