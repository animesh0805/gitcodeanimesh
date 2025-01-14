package animeshtiwari.pageobjects;

import animeshtiwari.abstractcomponents.Absctractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Absctractcomponent {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //WebElement Useremail=driver.findElement(By.id("userEmail")).sendKeys("animash9961@gmail.com");
@FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(id="userPassword")
    WebElement passwordEle;
    @FindBy(id="login")
    WebElement submit;
    @FindBy(xpath = "//*[contains(@class,'flyInOut')]")
    WebElement errorMessage;
    public ProductCatalogue loginapplication(String email, String password){
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();

        //This action takes us to product catalogue page so we will create Object for it here only
        ProductCatalogue productCatalogue =new ProductCatalogue(driver);
        return productCatalogue;

    }
    public String getErrormessage(){
        waitForWebElementToAppear(errorMessage);
        return  errorMessage.getText();

    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }


}
