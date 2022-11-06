package tests;

import generatosdata.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CourierCreate;
import steps.StepsForCourierApi;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateApiTests {

    @Test
    @DisplayName("Создание курьера через API")
    public void checkCreateCourierReturns201AndTrue() {
        CourierCreate request = CourierGenerator.getNewCourier();
        Response response = StepsForCourierApi.createCourier(request);
        response.then().statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void createTwoIdenticalCouriersReturns409() {
        CourierCreate request = CourierGenerator.getNewCourier();
        Response response = StepsForCourierApi.createCourier(request);
        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response errorResponse = StepsForCourierApi.createCourier(request);
        errorResponse.then()
                .statusCode(409)
                .and()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Нельзя создать курьера без обязательных полей")
    public void createCourierWithoutPasswordReturns400() {
        CourierCreate request = CourierGenerator.getNewCourier();
        request.setPassword(null);

        Response response = StepsForCourierApi.createCourier(request);
        response.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}

