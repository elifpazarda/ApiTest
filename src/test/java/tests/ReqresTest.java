package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresTest extends BaseTest {

    @Test
    public void testGetUserById() {
        test = extent.createTest("GET /api/users/2 - Kullanıcı bilgisi getirme testi");

        given().
                when().
                get("https://reqres.in/api/users/2").
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("data.id", equalTo(2)).
                body("data.first_name", equalTo("Janet"));

        test.pass("GET isteği başarılı şekilde doğrulandı.");
    }
    @Test
    public void testCreateUser() {
        test = extent.createTest("POST /api/users - Yeni kullanıcı oluşturma testi");

        String requestBody = """
            {
                "name": "Elif",
                "job": "QA Engineer"
            }
            """;

        given().
                header("Content-Type", "application/json").
                body(requestBody).
                when().
                post("https://reqres.in/api/users").
                then().
                assertThat().
                statusCode(201).
                body("name", equalTo("Elif")).
                body("job", equalTo("QA Engineer")).
                body("id", notNullValue()).
                body("createdAt", notNullValue());

        test.pass("POST isteği ile kullanıcı başarılı şekilde oluşturuldu.");
    }
    @Test
    public void testUpdateUser() {
        test = extent.createTest("PUT /api/users/2 - Kullanıcı güncelleme testi");

        String requestBody = """
        {
            "name": "Elif",
            "job": "Senior QA Engineer"
        }
        """;

        given().
                header("Content-Type", "application/json").
                body(requestBody).
                when().
                put("https://reqres.in/api/users/2").
                then().
                assertThat().
                statusCode(200).
                body("name", equalTo("Elif")).
                body("job", equalTo("Senior QA Engineer")).
                body("updatedAt", notNullValue());

        test.pass("PUT isteği ile kullanıcı başarılı şekilde güncellendi.");
    }
    @Test
    public void testDeleteUser() {
        test = extent.createTest("DELETE /api/users/2 - Kullanıcı silme testi");

        given().
                when().
                delete("https://reqres.in/api/users/2").
                then().
                assertThat().
                statusCode(204); // No Content

        test.pass("DELETE isteği ile kullanıcı başarılı şekilde silindi.");
    }


}
