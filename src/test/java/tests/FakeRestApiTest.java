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

@Feature("Fake Rest API")
public class FakeRestApiTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FakeRestApiTest.class);

    @Test
    @Story("Get all activities")
    @Description("Verify that the GET request to fetch all activities returns successfully with valid data")
    @Severity(SeverityLevel.NORMAL)
    public void testGetAllActivities() {
        logger.info("Tüm aktiviteler için GET isteği başlatılıyor");

        given()
                .when()
                .get("/Activities")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(10))
                .body("[0].title", not(emptyOrNullString()))
                .body("[1].title", not(emptyOrNullString()));

        logger.info("GET isteği başarıyla tamamlandı - Tüm aktiviteler alındı");
        logger.debug("Doğrulama kriterleri: En az 10 aktivite ve ilk iki aktivitenin başlıkları dolu");
    }
}
