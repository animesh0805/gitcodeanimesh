package animeshtiwari.testcomponents;

import animeshtiwari.resources.ExtentreporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listners extends BaseTest implements ITestListener {
    ExtentReports extent =ExtentreporterNG.getReportObject();
    ExtentTest test;
//To solve the problem of cocurrancy
    ThreadLocal<ExtentTest> extenttest= new ThreadLocal<>();

    @Override
    public  void onTestStart(ITestResult results){

        test=extent.createTest(results.getMethod().getMethodName());
        extenttest.set(test);
    }
    @Override
    public  void onTestSuccess(ITestResult results){
        extenttest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extenttest.get().fail(result.getThrowable());
        //For providing life to the driver,
        try {
            driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }
        //Exception e is main class of all the exception so we surrounded with it ,so now driver is assigned at run time
        catch (Exception e) {
          e.printStackTrace();
        }

        String filepath= null;
        try {
            filepath=getscreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extenttest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
