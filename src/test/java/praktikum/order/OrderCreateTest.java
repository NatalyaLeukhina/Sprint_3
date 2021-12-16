package praktikum.order;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.client.OrderClient;
import praktikum.data.Order;
import praktikum.data.ScooterColor;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private OrderClient orderClient;
    Order order;
    private List<ScooterColor> color;
    private int expectedStatus;
    private int track;

    public OrderCreateTest(List<ScooterColor> color, int expectedStatus) {
        this.color = color;
        this.expectedStatus =expectedStatus;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {List.of(ScooterColor.BLACK), 201},
                {List.of(ScooterColor.GREY), 201},
                {null, 201},
                {List.of(ScooterColor.BLACK, ScooterColor.GREY), 201}
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
        order = Order.getOrder().setColor(color);
    }

    @After
    public void tearDown() {
        orderClient.cancel(track);

    }

        @Test
        @DisplayName("Check order can be created")
        @Description("Creating order")
        public void orderCanBeCreated() {

            ValidatableResponse response = orderClient.create(order);
            int statusCode = response.extract().statusCode();
            track = response.extract().path("track");


            assertThat("Status code is incorrect", statusCode, equalTo(expectedStatus));
            assertThat("Order is not created", track, is(not(0)));


        }

    }

