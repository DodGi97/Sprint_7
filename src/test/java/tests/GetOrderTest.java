package tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.StepsForOrderApi;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class GetOrderTest {
    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderRequestReturnsNotEmptyList() {
        Response ordersList = StepsForOrderApi.getOrderList();
        ordersList.then()
                .statusCode(200)
                .and()
                .body("orders", hasSize(greaterThan(0)));
    }
}
