package appTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializedTest {

    public static void main(String[] args) {

        googleMaps p = new googleMaps();
        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");
        p.setAddress("29, side layout, cohen 09");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);

        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
p.setLocation(l);

RestAssured.baseURI= "https://rahulshettyacademy.com";
        Response response = given().log().all().queryParams("key", "qaclick123")
                .body(p)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
        String res = response.asString();
        System.out.println(res);
    }
}
