package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Feature("Reqres API")
@Epic("User Management")
public class ReqresTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ReqresTest.class);
    private static final String BASE_URI = "https://reqres.in";

    @DataProvider(name = "userIds")
    public Object[][] provideUserIds() {
        return new Object[][]{
                {1}, {2}
        };
    }

    @Test(dataProvider = "userIds")
    @Story("Get user by ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get isteği ile her userId için kullanıcı doğrulanır.")
    public void testGetUserById(int userId) {
        RestAssured.baseURI = BASE_URI;
        logger.info("GET /api/users/" + userId);

        given()
                .when()
                .get("/api/users/" + userId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", equalTo(userId));

        logger.info("Kullanıcı başarıyla doğrulandı: userId=" + userId);
    }

    @Test
    @Story("Create user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Yeni kullanıcı POST isteği ile oluşturulur.")
    public void testCreateUser() {
        RestAssured.baseURI = BASE_URI;
        String requestBody = """
                {
                    "name": "Elif",
                    "job": "QA Engineer"
                }
                """;

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("Elif"))
                .body("job", equalTo("QA Engineer"));

        logger.info("Kullanıcı başarıyla oluşturuldu.");
    }

    @Test
    @Story("Update user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Var olan kullanıcı PUT isteği ile güncellenir.")
    public void testUpdateUser() {
        RestAssured.baseURI = BASE_URI;
        String requestBody = """
                {
                    "name": "Elif",
                    "job": "Ceramic Artist"
                }
                """;

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/api/users/2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Elif"))
                .body("job", equalTo("Ceramic Artist"));

        logger.info("Kullanıcı başarıyla güncellendi.");
    }

    @Test
    @Story("Delete user")
    @Severity(SeverityLevel.MINOR)
    @Description("Var olan kullanıcı DELETE isteği ile silinir.")
    public void testDeleteUser() {
        RestAssured.baseURI = BASE_URI;

        given()
                .when()
                .delete("/api/users/2")
                .then()
                .assertThat()
                .statusCode(204);

        logger.info("Kullanıcı başarıyla silindi.");
    }
}
