package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification respec;
    Response response;
    TestDataBuild data = new TestDataBuild();
static String place_id;


    @Given("Add place payload with {string}, {string}, {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }
    @When("user calls {string} with {string} HTTP request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        respec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST")){
            response= res.when().post(resourceAPI.getResource());
        }
        else if (method.equalsIgnoreCase("GET")){
            response= res.when().get(resourceAPI.getResource());
        }

    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
       assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
       place_id = getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParams("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response,"name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Given("DeletePlace payload")
    public void delete_place_payload()  throws IOException {
res =   given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

}
