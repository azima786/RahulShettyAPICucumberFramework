package appTest;

import io.restassured.RestAssured;
import org.junit.BeforeClass;


public class BaseTest {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.basePath = "/maps/api/place";

    }

}
