package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {

    protected static ExtentReports extent;
    protected static ExtentTest test;
// @BeforeSuite ve @AfterSuite, test grubu (suite) başlamadan/bitince çalışır
    @BeforeSuite
    public void setUpReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    // Her test metodu başlamadan önce otomatik olarak test başlığı oluşturur
    @BeforeMethod
    public void createExtentTest(Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterSuite
    public void tearDownReport() {
        try {
            if (extent != null) {
                extent.flush();
            }
            utils.PdfReportUtil.convertHtmlToPdf(
                    "test-output/extent-report.html",
                    "test-output/pdf-reports/extent-report.pdf"
            );

        } catch (Exception e) {
            System.out.println("Extent raporu olusturulurken hata: " + e.getMessage());
        }
    }
}
