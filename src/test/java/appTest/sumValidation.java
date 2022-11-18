package appTest;

import files.payload;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

public class sumValidation {

    @Test
    public void sumOfCourse(){

        JsonPath js = new JsonPath(payload.coursePrice());
        int count = js.getInt("courses.size()");
        int sum =0;
        for (int i = 0; i < count; i++) {
            int price = js.get("courses[" + i + "].price");
            int copies = js.get("courses[" + i + "].copies");
            int totalPrice = price * copies;
            sum+=totalPrice;

        }

        int TotalAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, TotalAmount);
    }
    }

