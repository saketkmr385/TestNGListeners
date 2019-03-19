package testNGListners;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestCaseMethods extends TestCases {
	
	@Test(retryAnalyzer = Retry.class)
    public static void FirstMethod()
    {

        System.out.println("In First Methd");
        
        Assert.assertTrue(true);
        test = extent.createTest("Test Case 1", "PASSED test case");
        
        test.info("Started executing testcase1");
        test.log(Status.PASS, "This TC PASSEED");
        test.log(Status.INFO, "Executing this TC");
        
        String Filepath = "C://jba//workspaces//default//TestNGListners//test-output";
        String Filepath1 = Filepath.replace("//", "/");
        test.info(Filepath1);
        
        /*String url = "http://extentreports.com";
        String name = "extent";
        Markup m = MarkupHelper.createExternalLink(url, name);

        test.pass(m);
        // or
        test.log(Status.PASS, m);*/
        
        
        
    }

    @Test(retryAnalyzer = Retry.class)
    public static void secondMethod()
    {
        System.out.println("Inside second method");
        test = extent.createTest("Test Case 1", "Failed test case");

        Assert.assertTrue(false);
        test.log(Status.FAIL, "This TC failed");
    }
	

}
