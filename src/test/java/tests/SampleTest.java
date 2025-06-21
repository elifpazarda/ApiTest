package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Feature("Sample Tests")
@Epic("API Testing")
public class SampleTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SampleTest.class);

    @Test
    @Story("Sample Test")
    @Description("This is a sample test to verify TestNG configuration")
    @Severity(SeverityLevel.TRIVIAL)
    public void testSample() {
        logger.info("🔹 Sample test başlatıldı.");

        assert "API".startsWith("A");

        logger.info("Sample test başarıyla tamamlandı.");
    }
}
