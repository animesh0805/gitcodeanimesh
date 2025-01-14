package animeshtiwari.tests;

import animeshtiwari.pageobjects.CartsPage;
import animeshtiwari.pageobjects.ProductCatalogue;
import animeshtiwari.testcomponents.BaseTest;
import animeshtiwari.testcomponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorvalidationTest extends BaseTest {
    @Test(groups = {"Errorhandling"},retryAnalyzer = Retry.class)
    public void errorValidation() {
        String productname = "ZARA COAT 3";
        //we are returning landing page object in test case from base class beacuse we have to catch in our test case
        landingpage.loginapplication("animash9961@gmail.com", "Manoj@085");
        Assert.assertEquals("Incorrect email or password.", landingpage.getErrormessage());
    }

    @Test
    public void Producterrorvalidation() {
        String productname = "ZARA COAT 3";
        //we are returning landing page object in test case from base class beacuse we have to catch in our test case
        ProductCatalogue productCatalogue = landingpage.loginapplication("animash9961@gmail.com", "Manoj@0805");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productname);

        //filter is used to apply one more codition likke if else
        CartsPage cartPage = productCatalogue.goTocart();
        ;
        Boolean match = cartPage.verifproductDisplay("ZARA COAT 33");
        //All assertion goes under test cases,validation does not goes under page object as it helps to pass or fail the test case
        Assert.assertFalse(match);

    }
}
