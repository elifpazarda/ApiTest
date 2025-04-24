package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FakeRestApiTest extends BaseTest {

    @Test
    public void testGetAllActivities() {
        given().
                when().
                get("https://fakerestapi.azurewebsites.net/api/v1/Activities").
                then().
                assertThat().
                statusCode(200).
                body("size()", greaterThanOrEqualTo(10)).  // Aktivite sayısı en az 10 olsun
                body("[0].title", not(emptyOrNullString())). // İlk aktivitenin başlığı boş olmasın
                body("[1].title", not(emptyOrNullString())); // İkinci aktivite de dolu olsun

        test.pass("Tüm aktiviteler başarıyla alındı ve başlıklar doğrulandı.");
    }
}
