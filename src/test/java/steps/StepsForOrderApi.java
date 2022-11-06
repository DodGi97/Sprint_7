package steps;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.OrderCreate;

import static constans.BaseUri.BASE_URL;
import static io.restassured.RestAssured.given;

public class StepsForOrderApi {
    public static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .setBasePath("api/v1/orders")
                    .setContentType(ContentType.JSON)
                    .build();

    @Step("Создать заказ")
    public static Response createOrder(OrderCreate request) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(request)
                .when()
                .post();

    }

    @Step("Получить список заказов")
    public static Response getOrderList() {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .when()
                .get();
    }
}
