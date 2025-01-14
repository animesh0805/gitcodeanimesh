package animeshtiwari.pageobjects;

import animeshtiwari.abstractcomponents.Absctractcomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends Absctractcomponent {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //String Confirmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
@FindBy(css=".hero-primary")
    WebElement confirmationMessage;
public String getConfirmationmessage(){
   return confirmationMessage.getText();
}

}