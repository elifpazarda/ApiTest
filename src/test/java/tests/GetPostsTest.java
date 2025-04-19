package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetPostsTest extends BaseTest {

    @Test
    public void testGetPosts() {
        try {
            test = extent.createTest("GET /posts - Basit GET istegi testi");

            test.log(Status.INFO, "GET istegi baslatildi: https://jsonplaceholder.typicode.com/posts");

            given().
                    when().
                    get("https://jsonplaceholder.typicode.com/posts").
                    then().
                    assertThat().
                    statusCode(200).
                    contentType("application/json; charset=utf-8").
                    body("[0].userId", notNullValue());

            test.pass("GET istegi basarili bir sekilde dogrulandi.");

        } catch (Exception e) {
            if (test != null) {
                test.fail("Test sirasinda bir hata olustu: " + e.getMessage());
            } else {
                System.out.println("Extent test nesnesi olusturulamadi: " + e.getMessage());
            }
        }
    }
}
