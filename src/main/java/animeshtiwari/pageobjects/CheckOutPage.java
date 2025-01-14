package animeshtiwari.pageobjects;

import animeshtiwari.abstractcomponents.Absctractcomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends Absctractcomponent {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

   @FindBy(xpath="//*[@placeholder=\"Select Country\"]")
    WebElement country;
    @FindBy(xpath = "//*[contains(@class,'ta-item')]/following-sibling::button[1]")
WebElement selectcountry;
    @FindBy(css=".action__submit ")
    WebElement submit;
   By results= By.cssSelector(".ta-results");
    public void selectcountry(String countryname){
        Actions a = new Actions(driver);
        //a.sendKeys(driver.findElement(By.xpath("//*[@
        a.sendKeys(country, countryname).build().perform();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    waitForElementToAppear(results);
    //driver.findElement(By.xpath("(//*[contains(@class,'ta-item')])[2]")).click();
selectcountry.click();
    }
    public ConfirmationPage submitOrder(){
        Actions a = new Actions(driver);
        a.moveToElement(submit).click().build().perform();

   return new ConfirmationPage(driver);
    }

}