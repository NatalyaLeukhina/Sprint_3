package praktikum.courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.CourierClient;
import praktikum.data.Courier;
import praktikum.data.CourierCredentials;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class CourierAuthorizationTest {

    private CourierClient courierClient;
    private ValidatableResponse courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        CourierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check courier authorization")
    @Description("Authorize the courier")
    public void checkCourierCanBeLogInTest() {
        Courier courier = Courier.getRandom();
        courierClient.create(courier);

        courierId = courierClient.login(CourierCredentials.from(courier));

        int statusCode = courierId.extract().statusCode();
        Integer isCourierLogIn = courierId.extract().path("id");


        assertThat("Status code is incorrect", statusCode, equalTo(200));

        assertThat("Courier ID is incorrect", isCourierLogIn, notNullValue());
        }
}
