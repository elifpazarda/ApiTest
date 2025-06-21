package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Feature("Fake Rest API")
public class FakeRestApiTest extends BaseTest {

    @Test
    @Story("Get all activities")
    @Description("Verify that the GET request to fetch all activities returns successfully with valid data")
    @Severity(SeverityLevel.NORMAL)
    public void testGetAllActivities() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
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
    }
}
