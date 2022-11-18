package appTest;

import files.ReUsableMethods;
import files.payload;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class MyFirstTest extends BaseTest {

    @Test
    @DisplayName("Add place to the database")
    public void AddPlace() {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("key", "qaclick123");

        String addResponse =
                given()
                        .queryParams(params)
                        .when()
                        .body(payload.AddPlace())
                        .post("/add/json")
                        .then()
                        .statusCode(200)
                       .extract().response().asString();

        System.out.println(addResponse);
        JsonPath path = new JsonPath(addResponse);
        String place_id = path.get("place_id");
        System.out.println(place_id);

    String newAddress = "70 winter walk, USA";
        given().log().all()
                    .queryParams(params).body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                    .when().put("/update/json")
                    .then().assertThat().log().all().statusCode(200)
                    .body("msg",  equalTo("Address successfully updated"));

        String getPlaceResponse = given().log().all()
                .queryParams(params)
                .queryParam("place_id", place_id)
                .when().get("/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJSON(getPlaceResponse);
        String address = js.get("address");
        Assert.assertEquals(address, newAddress);

    }

    @Test
    @DisplayName("Add place to the database")
    public void AddPlaceUsingJson() throws IOException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("key", "qaclick123");

        String addResponse =
                given()
                        .queryParams(params)
                        .when()
                        .body(new String (Files.readAllBytes(Paths.get("C:\\Users\\azima.keshwani\\Desktop\\RestAssured-Tejasvi-Udemy\\src\\test\\java\\files\\addplace.json"))))
                        .post("/add/json")
                        .then()
                        .statusCode(200)
                        .extract().response().asString();

        System.out.println(addResponse);
        JsonPath path = new JsonPath(addResponse);
        String place_id = path.get("place_id");
        System.out.println(place_id);

        String newAddress = "70 winter walk, USA";
        given().log().all()
                .queryParams(params).body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg",  equalTo("Address successfully updated"));

        String getPlaceResponse = given().log().all()
                .queryParams(params)
                .queryParam("place_id", place_id)
                .when().get("/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJSON(getPlaceResponse);
        String address = js.get("address");
        Assert.assertEquals(address, newAddress);

    }





}