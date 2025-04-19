package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static ExtentReports extent;
    protected static ExtentTest test;
//“Suite”, birden fazla test sınıfını veya testi bir araya getiren test paketi anlamına gelir.
//Yani bir grup testin ortak çalıştığı bir koleksiyon. {GPT}
    @BeforeSuite
    public void setUpReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void tearDownReport() {
        try {
            if (extent != null) {
                extent.flush();
            }
        } catch (Exception e) {
            System.out.println("Extent raporu olusturulurken hata: " + e.getMessage());
        }
    }
}
