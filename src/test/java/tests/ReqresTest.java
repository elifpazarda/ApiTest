package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Feature("Reqres API")
@Epic("User Management")
public class ReqresTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ReqresTest.class);

    @DataProvider(name = "userIds")
    public Object[][] provideUserIds() {
        return new Object[][]{
                {2}, {5}, {10}
        };
    }

    @Test(dataProvider = "userIds")
    @Story("Get user by ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get isteği ile her userId için kullanıcı doğrulanır.")
    public void testGetUserById(int userId) {
        logger.info("GET isteği gönderiliyor: /api/users/" + userId);

        given()
                .when()
                .get("/api/users/" + userId)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("data.id", equalTo(userId));

        logger.info("GET userId=" + userId + " başarılı doğrulandı.");
    }

    @Test
    @Story("Create user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Yeni kullanıcı POST isteği ile başarıyla oluşturulmalı.")
    public void testCreateUser() throws Exception {
        String requestBody = Files.readString(Paths.get("src/test/resources/testdata/createUser.json"));

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("Elif"))
                .body("job", equalTo("QA Engineer"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());

        logger.info("POST isteği ile kullanıcı başarıyla oluşturuldu.");
    }

    @Test
    @Story("Update user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Var olan kullanıcı PUT isteği ile güncellenir.")
    public void testUpdateUser() {
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
                .body("job", equalTo("Ceramic Artist"))
                .body("updatedAt", notNullValue());

        logger.info("PUT isteği ile kullanıcı başarıyla güncellendi.");
    }

    @Test
    @Story("Delete user")
    @Severity(SeverityLevel.MINOR)
    @Description("Var olan kullanıcı DELETE isteği ile silinir.")
    public void testDeleteUser() {
        given()
                .when()
                .delete("/api/users/2")
                .then()
                .assertThat()
                .statusCode(204);
        logger.info("DELETE isteği ile kullanıcı başarıyla silindi.");
    }
}
