package steps;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.CourierCreate;
import pojos.CourierLogin;

import static constans.BaseUri.BASE_URL;
import static io.restassured.RestAssured.given;

public class StepsForCourierApi {

    public static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .setBasePath("api/v1/courier")
                    .setContentType(ContentType.JSON)
                    .build();

    @Step("Создание нового курьера")
    public static Response createCourier(CourierCreate body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when().post();

    }

    @Step("Авторизация курьера")
    public static Response loginCourier(CourierLogin body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when().post("/login");

    }

}
