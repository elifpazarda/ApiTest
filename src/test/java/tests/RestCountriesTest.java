package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Feature("Rest Countries API")
@Epic("Country Data")
public class RestCountriesTest extends BaseTest {

    private static final String BASE_URI = "https://restcountries.com/v3.1";

    @Test
    @Story("Get Turkish speaking countries")
    @Description("Verify that countries where Turkish is spoken can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    public void testTurkishLanguageCountries() {
        RestAssured.baseURI = BASE_URI;
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
    }
}
