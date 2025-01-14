package animeshtiwari.tests;

import animeshtiwari.pageobjects.*;
import animeshtiwari.testcomponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
   String productName = "ZARA COAT 3";

    @Test(dataProvider ="getData",groups={"Purchase"})
    public void submitorder(HashMap<String, String> input) throws IOException {

        //we are returning landing page object in test case from base class beacuse we have to catch in our test case
        ProductCatalogue productCatalogue = landingpage.loginapplication(input.get("email"),input.get("password") );
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));

        //filter is used to apply one more codition likke if else
        CartsPage cartPage = productCatalogue.goTocart();
        ;
        Boolean match = cartPage.verifproductDisplay(input.get("productName"));
        //All assertion goes under test cases,validation does not goes under page object as it helps to pass or fail the test case
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectcountry("India");

        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        String confirmessage = confirmationPage.getConfirmationmessage();

        Assert.assertTrue(confirmessage.equalsIgnoreCase("Thankyou for the order."));

    }

    @Test(dependsOnMethods = {"submitorder"})
    public void OrderHistoryTest() throws InterruptedException {
        ProductCatalogue productCatalogue = landingpage.loginapplication("animash9961@gmail.com", "Manoj@0805");
        OrderPage orderPage = productCatalogue.goToOrderspage();
        orderPage.VerifyOrderDisplay(productName);
    }
    @DataProvider
    public Object getData() throws IOException {
        String Filepath=System.getProperty("user.dir")+"\\src\\test\\java\\animeshtiwari\\data\\PurchaseOrder.json";
       List<HashMap<String, String>> data= getJsonDataToMap(Filepath);

        return  new Object[][]{{data.get(0)},{data.get(1)}};
    }




//        HashMap<String,String> map = new HashMap<>();
//        map.put("email","animash9961@gmail.com");
//        map.put("password","Manoj@0805");
//        map.put("product","ZARA COAT 3");
//        HashMap<String,String> map1 = new HashMap<>();
//        map1.put("email","animesh9961@gmail.com");
//        map1.put("password","Rahul123");
//        map1.put("product","ADIDAS ORIGINAL");
//
//        return new Object[][]{{map},{map1}};
//    }
}
