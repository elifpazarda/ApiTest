package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Feature("Rest Countries API")
public class RestCountriesTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(RestCountriesTest.class);

    @Test
    @Story("Get Turkish speaking countries")
    @Description("Verify that countries where Turkish is spoken can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    public void testTurkishLanguageCountries() {
        logger.info("Türkçe konuşulan ülkeler için GET isteği başlatılıyor");

        given()
                .when()
                .get("/lang/tur")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].region", not(emptyOrNullString()))
                .body("[0].capital[0]", not(emptyOrNullString()));

        logger.info("GET isteği başarıyla tamamlandı - Türkçe konuşulan ülkeler alındı");
        logger.debug("Doğrulama kriterleri: İlk ülkenin bölgesi ve başkenti dolu");
    }
}
