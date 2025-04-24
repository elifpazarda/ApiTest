package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ReqresTest extends BaseTest {

    // DataProvider – userId listesi
    @DataProvider(name = "userIds")
    public Object[][] provideUserIds() {
        return new Object[][] {
                {2}, {5}, {10}
        };
    }

    // GET test – her userId için ayrı çalışır
    @Test(dataProvider = "userIds")
    public void testGetUserById(int userId) {
        given().
                when().
                get("https://reqres.in/api/users/" + userId).
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("data.id", equalTo(userId));

        test.pass("GET isteği userId=" + userId + " için başarıyla doğrulandı.");
    }


    @Test
    public void testCreateUser() throws Exception {
        // JSON veriyi dosyadan oku
        String requestBody = Files.readString(Paths.get("src/test/resources/testdata/createUser.json"));

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
        String requestBody = """
        {
            "name": "Elif",
            "job": "Ceramic Artist"
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
                body("job", equalTo("Ceramic Artist")).
                body("updatedAt", notNullValue());

        test.pass("PUT isteği ile kullanıcı başarılı şekilde güncellendi.");
    }

    @Test
    public void testDeleteUser() {
        given().
                when().
                delete("https://reqres.in/api/users/2").
                then().
                assertThat().
                statusCode(204); // No Content

        test.pass("DELETE isteği ile kullanıcı başarılı şekilde silindi.");
    }
}
