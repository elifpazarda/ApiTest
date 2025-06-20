package base;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final ThreadLocal<ByteArrayOutputStream> requestStream = new ThreadLocal<>();
    private static final ThreadLocal<ByteArrayOutputStream> responseStream = new ThreadLocal<>();
    private static final ThreadLocal<PrintStream> requestCapture = new ThreadLocal<>();
    private static final ThreadLocal<PrintStream> responseCapture = new ThreadLocal<>();
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeSuite
    public void setUp() {
        logger.info("Test suite başlatılıyor...");
        
        String environment = ConfigReader.get("environment");
        String baseUrl = ConfigReader.get("baseUrl." + environment);

        logger.info("Seçilen ortam: {}", environment);
        logger.info("Base URL yüklendi: {}", baseUrl);

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType("application/json")
                .setAccept("application/json")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectResponseTime(org.hamcrest.Matchers.lessThan(5000L))
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @BeforeMethod
    public void setUpMethod() {
        setupRequestResponseLogging();
    }

    private void setupRequestResponseLogging() {
        requestStream.set(new ByteArrayOutputStream());
        responseStream.set(new ByteArrayOutputStream());
        requestCapture.set(new PrintStream(requestStream.get(), true));
        responseCapture.set(new PrintStream(responseStream.get(), true));

        RestAssured.filters(
                new RequestLoggingFilter(requestCapture.get()),
                new ResponseLoggingFilter(responseCapture.get())
        );
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                logger.error("Test başarısız: {}", result.getName());
                Allure.addAttachment("Error", result.getThrowable().getMessage());
            }
            if (requestStream.get() != null) {
                Allure.addAttachment("API Request", requestStream.get().toString());
            }
            if (responseStream.get() != null) {
                Allure.addAttachment("API Response", responseStream.get().toString());
            }
            if (requestStream.get() != null) {
                requestStream.get().reset();
            }
            if (responseStream.get() != null) {
                responseStream.get().reset();
            }

            logger.info("'{}' testi tamamlandı. Sonuç: {}",
                    result.getName(),
                    result.getStatus() == ITestResult.SUCCESS ? "BAŞARILI" : "BAŞARISIZ"
            );
        } finally {
            requestStream.remove();
            responseStream.remove();
            requestCapture.remove();
            responseCapture.remove();
        }
    }
}
