package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test
            //(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().body(payload.AddBook(isbn, aisle)).when().post("/Library/Addbook.php").then().assertThat().statusCode(200)
                .extract().response().asString();

JsonPath js = ReUsableMethods.rawToJSON(response);
 String id = js.get("ID");



    }


    @Test
            //(DataProvider = "BooksData")
    public void deleteBook(String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";
        RestAssured.useRelaxedHTTPSValidation();

        String res =  given().body(payload.deleteBook(isbn, aisle)).when().post("/Library/DeleteBook.php").then().assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println("Response starts " + res + " ends");

    }


    @DataProvider(name="BooksData")
    public Object[][] getData(){
return new Object [][] {{"azima", "12589"}, {"azima234", "12589"}};

    }


}
