package animeshtiwari.abstractcomponents;

import animeshtiwari.pageobjects.CartsPage;
import animeshtiwari.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Absctractcomponent {
    WebDriver driver;
    WebDriverWait wait;
    public Absctractcomponent(WebDriver driver) {
    this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForElementToAppear(By findBy) {
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}
    public void waitForWebElementToAppear(WebElement errormessage) {
        wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(errormessage));
    }
//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
 public void waitforElementToDisappear(WebElement ele){
     wait= new WebDriverWait(driver, Duration.ofSeconds(5));

     wait.until(ExpectedConditions.invisibilityOf(ele));

 }
 //header is common
    @FindBy(xpath = "//*[contains(@routerlink,'cart')]")
            WebElement cartheader;
    @FindBy(css="[routerlink*='myorders']")
    WebElement orderheader;
     //driver.findElement(By.xpath("//*[contains(@routerlink,'cart')]")).click();
    public CartsPage goTocart(){
        cartheader.click();
        CartsPage cartPage = new CartsPage(driver);
        return cartPage;


    }
    public OrderPage goToOrderspage(){
        orderheader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;


    }



}
