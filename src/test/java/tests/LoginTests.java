package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LoginTests extends TestBase {
    @Test
    void loginWithUiTest() {
        // authorize
        // qaguru@qa.guru qaguru@qa.guru1
        open("/login");
        $("#Email").val("qaguru@qa.guru");
        $("#Password").val("qaguru@qa.guru1").pressEnter();

        // verify successful authorization
         $(".account").shouldHave(text("qaguru@qa.guru"));
    }

    @Test
    void loginWithCookieTest() {
        // authorize
        // qaguru@qa.guru qaguru@qa.guru1
        Map<String, String> cookiesMap =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                        .contentType(ContentType.URLENC)
                        .formParam("Email", "qaguru@qa.guru")
                        .formParam("Password", "qaguru@qa.guru1")
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(302)
                        .log().body()
                        .extract().cookies();

        // verify successful authorization
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookiesMap.get("Nop.customer")));
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookiesMap.get("NOPCOMMERCE.AUTH")));
        getWebDriver().manage().addCookie(new Cookie("ARRAffinity", cookiesMap.get("ARRAffinity")));

        open("");
        $(".account").shouldHave(text("qaguru@qa.guru"));
    }

}
