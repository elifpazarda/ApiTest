package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestCountriesTest extends BaseTest {

    @Test
    public void testTurkishLanguageCountries() {
        given().
                when().
                get("https://restcountries.com/v3.1/lang/tur").
                then().
                assertThat().
                statusCode(200).
                body("[0].region", not(emptyOrNullString())).
                body("[0].capital[0]", not(emptyOrNullString()));

        test.pass("Türkçe konuşulan ülkeler başarıyla doğrulandı.");
    }
}
