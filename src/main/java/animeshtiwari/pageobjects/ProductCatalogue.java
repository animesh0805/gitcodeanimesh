package animeshtiwari.pageobjects;

import animeshtiwari.abstractcomponents.Absctractcomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends Absctractcomponent {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
       super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // List<WebElement> products = driver.findElements(By.xpath("//*[contains(@class,'mb-3')]"));
    @FindBy(xpath = "//*[contains(@class,'mb-3')]")
    List<WebElement> products;
    By productsBy=By.xpath("//*[contains(@class,'mb-3')]");
    By toastmessage= By.cssSelector("#toast-container");
    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }


    public WebElement getProductByName(String productname){
        WebElement prod =getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
//.card-body button:last-of-type
return prod;

    }
   // driver.findElement(By.cssSelector(".ng-animating")))
    @FindBy(css=".ng-animating")
    WebElement spinner;
    By addproductocart= By.cssSelector(".card-body button:last-of-type");;

    public void addProductToCart(String productname){
       WebElement prod= getProductByName(productname);
       prod.findElement(addproductocart).click();
       waitForElementToAppear(toastmessage);
       waitforElementToDisappear(spinner);
    }
}