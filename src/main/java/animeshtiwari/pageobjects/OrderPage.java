package animeshtiwari.pageobjects;

import animeshtiwari.abstractcomponents.Absctractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends Absctractcomponent {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tr//td[2]")
    List<WebElement> productNames;


    //List<WebElement> cartproducts = driver.findElements(By.xpath("//*[@class='cartSection']/ h3"));
    //We will use anymatch as it hellps to get the boolean value instead of folter which returs the filtered item
    public Boolean VerifyOrderDisplay(String productname) {

    Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
    return match;
}



}