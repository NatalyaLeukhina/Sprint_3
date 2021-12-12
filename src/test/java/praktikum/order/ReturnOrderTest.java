package praktikum.order;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.OrderClient;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;


public class ReturnOrderTest {

    private OrderClient orderClient;
    private List<Object> orders;
    private int statusCode;

    @Before
    public void setUp() {
        orderClient = new OrderClient();

    }

    @Test
    @DisplayName("Check order list")
    @Description("Return order list")
    public void returnOrderList() {

        ValidatableResponse response = orderClient.getOrdersList(orders);
        statusCode = response.extract().statusCode();
        orders = response.extract().body().jsonPath().getList("orders");

        assertThat("Status code is incorrect", statusCode, equalTo(200));
        assertFalse(orders.isEmpty());

    }
}


