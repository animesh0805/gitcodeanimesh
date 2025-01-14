package animeshtiwari.stepdefinitions;

import animeshtiwari.pageobjects.*;
import animeshtiwari.testcomponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitions extends BaseTest {
    public LandingPage landingpage;
    public ProductCatalogue productCatalogue;
    public CartsPage cartPage;
    public ConfirmationPage confirmationPage;
    public CheckOutPage checkOutPage;

    @Given("I landed on ecommerce page")
    public void I_landed_on_ecommerce_page() throws IOException {
        landingpage = launchAppplication();
    }

    @Given("^Loged in with username (.+) and password (.+)$")
    public void loged_in_with_username(String username, String password) {
        productCatalogue = landingpage.loginapplication(username, password);

    }

    @When("^I add the product (.+) to Cart$")
    public void I_add_product_to_cart(String productname) {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productname);
        cartPage = productCatalogue.goTocart();
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkour_products(String productname) {
        Boolean match = cartPage.verifproductDisplay(productname);
        Assert.assertTrue(match);
        checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectcountry("India");

        confirmationPage = checkOutPage.submitOrder();
    }


    @Then("{string} message is displayed on confirmantion page")
    public void messageIsDisplayedOnConfirmantionPage(String message) {
        String confirmessage = confirmationPage.getConfirmationmessage();

        Assert.assertTrue(confirmessage.equalsIgnoreCase("Thankyou for the order."));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String message) {
        Assert.assertEquals(message, landingpage.getErrormessage());
        driver.close();
    }
}
