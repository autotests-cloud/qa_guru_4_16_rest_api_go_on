package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CartTests extends TestBase{

    @Test
    void successLoginWithDataInFileTest() {
        Response response =
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("ARRAffinity=1fa9158750fcf7cee1728ac683a12594fe016bf3b1c0544237f51a4ffe2ef5ea; __utmc=78382081; __utmz=78382081.1616511077.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __RequestVerificationToken=1Ggj4MH1zaXe6_bAGo8oioIroK05NOUaOe14WhljCzw4J1QQJ534oqE_akH8FJqEeeP0Zd2Jpg9Wkcx1HkpBplq5dQnk7l9apiSN0CDLjPU1; ASP.NET_SessionId=1aj4jddt2se0pfra3vzngk4f; __utma=78382081.1412259254.1616511077.1616511077.1616515719.2; Nop.customer=9345ab71-54a6-468e-a89a-20fd34d3cc66; __utmt=1; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=16&RecentlyViewedProductIds=52&RecentlyViewedProductIds=72; __atuvc=8%7C12; __atuvs=605a25f95ad95eba006; __utmb=78382081.30.10.1616515719")
                .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&product_attribute_16_3_6=18&product_attribute_16_4_7=44&product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
                .when()
                .post("/addproducttocart/details/16/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", is(true))
                .extract().response();

        System.out.println(response);
    }
}
