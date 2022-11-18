package appTest;

import files.payload;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

public class complexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.coursePrice());
        //Print no of count of courses
        int count = js.getInt("courses.size()");
        System.out.println(count);
        System.out.println(js.getInt("dashboard.purchaseAmount"));
        System.out.println(js.getString("courses[0].title"));


        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            String coursePrice = js.get("courses[" + i + "].price").toString();
            System.out.println(courseTitles);
            System.out.println(coursePrice);

        }
        for (int j = 0; j < count; j++) {
            String courseTitles = js.get("courses[" + j + "].title");
            if (courseTitles.equalsIgnoreCase("RPA")) {
                int RPACopies = js.get("courses[" + j + "].copies");
                System.out.println(RPACopies);
                break;
            }

            //sumValidation.sumOfCourse();


        }
    }}

