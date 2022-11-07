package tests;

import generatosdata.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import pojos.CourierCreate;
import pojos.CourierLogin;
import steps.StepsForCourierApi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginApiTests {
    @Test
    @DisplayName("Првоерка логина курьера в системе")
    public void checkLoginCourierReturnsIdCourier() {
        CourierCreate request = CourierGenerator.getNewCourier();
        Response response = StepsForCourierApi.createCourier(request);
        response.then().statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = StepsForCourierApi.loginCourier(new CourierLogin(request.getLogin(), request.getPassword()));
        loginResponse.then().statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Нельзя авторизоваться без заполненного логина")
    public void checkLoginCourierWithoutLogin() {
        CourierCreate request = CourierGenerator.getNewCourier();
        request.setLogin(null);

        Response loginResponse = StepsForCourierApi.loginCourier(new CourierLogin(request.getLogin(), request.getPassword()));
        loginResponse.then().statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Невозможно авторизоваться c несуществующим логином и паролем")
    public void loginCourierWithWrongCredentialsReturns404() {
        Response loginResponse = StepsForCourierApi.loginCourier(new CourierLogin(
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)));

        loginResponse.then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}
