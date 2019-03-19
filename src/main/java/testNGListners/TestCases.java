package testNGListners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;








@Listeners(testNGListners.ListnerTest.class)


public class TestCases {
	public static String TCName = "My New TC";
	
	//builds a new report using the html template 
    ExtentHtmlReporter htmlReporter;
    
    static ExtentReports extent;
    //helps to generate the logs in test report.
    static ExtentTest test;
    
    
    @Parameters({ "OS", "browser" })
    @BeforeTest
    public void startReport(String OS, String browser) {
    	// initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
        System.out.println(System.getProperty("user.dir") +"/test-output/testReport.html");
        
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
         
        //To add system or environment info by using the setSystemInfo method.
        extent.setSystemInfo("OS", OS);
        extent.setSystemInfo("Browser", browser);
        
        //configuration items to change the look and feel
        //add content, manage tests etc
        //htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

	
	
	

    

    @Test(retryAnalyzer = Retry.class)
    public static void FirstMethod()
    {
    	TestCaseMethods.FirstMethod();

       /* System.out.println("In First Methd");
        
        Assert.assertTrue(true);
        test = extent.createTest("Test Case 1", "PASSED test case");
        
        test.info("Started executing testcase1");
        test.log(Status.PASS, "This TC PASSEED");
        
        String Filepath = "C://jba//workspaces//default//TestNGListners//test-output";
        String Filepath1 = Filepath.replace("//", "/");
        test.info(Filepath1);*/
        
        
        
    }

    @Test(retryAnalyzer = Retry.class)
    public static void secondMethod()
    {
    	TestCaseMethods.secondMethod();
        /*System.out.println("Inside second method");
        test = extent.createTest("Test Case 1", "Failed test case");

        Assert.assertTrue(false);
        test.log(Status.FAIL, "This TC failed");*/
    }
    
    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
    
    @AfterTest
    public void tearDown() {
    	//to write or update test information to reporter
        extent.flush();
        
       
        	try {
				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "C://jba//workspaces//default//TestNGListners//test-output//testReport.html"});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        
       
    }



}
