package appTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

    public static void main(String[] args) {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

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
        RequestSpecification response = given().spec(req)
                .body(p);

              Response response1= response.when().post("/maps/api/place/add/json")
                .then().spec(res).extract().response();

        String responseString = response1.asString();
        System.out.println(responseString);
    }
}
