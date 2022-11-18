package appTest;

import POJO.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Ecommerce {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        Login login = new Login();
        login.setUserEmail("ak123@gmail.com");
       login.setUserPassword("Abc123456$");

RequestSpecification reqLogin= given().relaxedHTTPSValidation().spec(req).body(login);
 LoginResponse loginResponse = reqLogin.log().all().when().post("api/ecom/auth/login")
         .then().spec(res).extract().response().as(LoginResponse.class);

        System.out.println("Token: " + loginResponse.getToken());
        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();
        System.out.println(loginResponse.getUserId());

        //Add product
//        RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
//                .addHeader("authorization", token).build();
//
//        RequestSpecification reqAddProduct= given().log().all().spec(addProductBaseReq)
//                .param("productName", "Laptop")
//                .param("productAddedBy", userId)
//                .param("productCategory", "fashion")
//                .param("productSubCategory", "computer")
//                .param("productPrice", "150000")
//                .param("productDescription", "Lenux Laptop")
//                .param("productFor", "men");
//               // .param("productImage", new File("\"C:\\Users\\azima.keshwani\\Desktop\\me.jpg\""));
//
//        String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
//                .then().log().all().extract().response().asString();
//
//        JsonPath js = new JsonPath(addProductResponse);
//        String productId = js.get("productId");
//        System.out.println(productId);
//        RequestSpecification addProductBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
//                .addHeader("authorization", token)
//                .build();
//
//        RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
//                .param("productAddedBy", userId)
//                .param("productCategory", "fashion")
//                .param("productSubCategory", "shirts")
//                .param("productPrice", "11500")
//                .param("productDescription", "Lenova")
//                .param("productFor", "men");
//               // .multiPart("productImage",new File("//users//rahulshetty//documents//laptop.png"));
//
//        String addProductResponse =reqAddProduct.when().post("/api/ecom/product/add-product").
//                then().log().all().extract().response().asString();
//        JsonPath js = new JsonPath(addProductResponse);
//        String productId =js.get("productId");

        //Create Order
        RequestSpecification createProductReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token)
                .setContentType(ContentType.JSON)
                .build();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCountry("India");
        orderDetail.setProductOrderedId("6375221bd7778f579732fcb4");

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        orderDetailList.add(orderDetail);

        Orders orders = new Orders();
        orders.setOrders(orderDetailList);

RequestSpecification createOrderReq = given().log().all().spec(createProductReq).body(orders);

String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(responseAddOrder);

        //ViewOrder
//        RequestSpecification viewOrder=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
//                .addHeader("authorization", token)
//                .build();
//
//        RequestSpecification viewOrderReq = given().log().all().spec(viewOrder);
//
//        viewOrder viewOrder1 = viewOrderReq.log().all().when().post("/api/ecom/order/get-orders-details?id=6374fff7d7778f579732ceb0")
//                .then().spec(res).extract().response().as(viewOrder.class);
//
//        System.out.println(viewOrder1.data);

    //Delete Product
        RequestSpecification deleteOrder=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token)
                .build();

        String productId = "6375427ed7778f57973313f7";
        RequestSpecification deleteOrderReq = given().pathParams("productId", productId).log().all().spec(deleteOrder);

        String deleteOrderRes =  deleteOrderReq.when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response().asString();

       JsonPath js = new JsonPath(deleteOrderRes);
       String message = js.get("message");
        Assert.assertEquals("Product Deleted Successfully", message);
    }
}
