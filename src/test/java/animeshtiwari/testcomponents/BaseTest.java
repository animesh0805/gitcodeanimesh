package animeshtiwari.testcomponents;

import animeshtiwari.pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingpage;

    public WebDriver initializedriver() throws IOException {

        //if an file has .properties extension JAVA has excellent feature to read it
        Properties prop = new Properties();
        //.load takes file as input stream using java we can convert file to input stream object
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\animeshtiwari\\resources\\GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");
        //prop.getProperty("browser");

        if (browserName.contains("chrome")) {
           // ChromeOptions options= new ChromeOptions();
            WebDriverManager.chromedriver().setup();
//            if(browserName.contains("headless")){
//                options.addArguments("headless");
//            }
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
//firefox
        } else if (browserName.equalsIgnoreCase("edge")) {
            //ede
            System.setProperty("webdriver.edge.driver","C:\\Selenium\\edgedriver\\msedgedriver.exe");
            driver=new EdgeDriver();
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }
    public String getscreenshot(String testcasename,WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File src= ts.getScreenshotAs(OutputType.FILE );
        File file=new File(System.getProperty("user.dir")+" //reports//"+testcasename+".png");

        FileUtils.copyFile(src,file);
        return System.getProperty("user.dir")+"//reports//"+testcasename+".png";

    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchAppplication() throws IOException {
        driver = initializedriver();
        landingpage = new LandingPage(driver);
        landingpage.goTo();
        return landingpage;
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException {
        //read json to string
        File fis = new File(Filepath);
        String jsoncontent= FileUtils.readFileToString(fis, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
}
