package appTest;

import POJO.GetCourse;
import POJO.api;
import POJO.webAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuth {

    public static void main(String[] args) throws InterruptedException {

        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

    //    WebDriver driver = new ChromeDriver();
       // driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
         //       driver.findElement(By.cssSelector("input[type='email']")).sendKeys("qatesting.tx786@gmail.com");
           //     driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
             //   Thread.sleep(4000);
              //  driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Ak123456$");
               // driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
               // Thread.sleep(4000);
              //  String url = driver.getCurrentUrl();
                String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AfgeXvv95fjAWevds-zdeo0DXGr_B3X2LEcBXVJR3bBa7n2df5QDnlJRqNLuO5TPbzI-Nw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=1&prompt=none";

String partialCode = url.split("code=")[1];
String code = partialCode.split("&scope")[0];
        System.out.println(code);


        String accessTokenRs =
        given().urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(accessTokenRs);
        String accessToken = js.getString("access_token");

//String response = given().queryParam("access_token", accessToken)
//                .when().log().all()
//        .get("https://rahulshettyacademy.com/getCourse.php").asString();
//        System.out.println(response);
//
        //Serialization - POJO
        GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        List<api> apiCourses = gc.getCourses().getApi();
        for(int i=0; i<apiCourses.size(); i++){
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        ArrayList<String> a = new ArrayList<String>();

        List<webAutomation> webAutomation = gc.getCourses().getWebAutomation();

        for(int i=0; i<webAutomation.size(); i++){
            a.add (webAutomation.get(i).getCourseTitle());

            }

List<String> expectedList = Arrays.asList(courseTitles);
       Assert.assertTrue(a.equals(expectedList));

    }
}
