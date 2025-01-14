package animeshtiwari.pageobjects;

import animeshtiwari.abstractcomponents.Absctractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartsPage extends Absctractcomponent {
    WebDriver driver;

    public CartsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='cartSection']/ h3")
    List<WebElement> cartproducts;


    //List<WebElement> cartproducts = driver.findElements(By.xpath("//*[@class='cartSection']/ h3"));
    //We will use anymatch as it hellps to get the boolean value instead of folter which returs the filtered item
    public Boolean verifproductDisplay(String productname) {

    Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
    return match;
}
@FindBy(css=".totalRow button")
WebElement checkoutButton;
public CheckOutPage goToCheckout(){
    checkoutButton.click();
    return new CheckOutPage(driver);
    // driver.findElement(By.cssSelector(".totalRow button")).click();
}



}