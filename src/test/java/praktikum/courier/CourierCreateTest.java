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
import static org.junit.Assert.*;

public class CourierCreateTest {

    private Courier courier;
    private CourierClient courierClient;
    private ValidatableResponse courierId;


    @Before
    public void setUp() {
        courier = Courier.getRandom();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        CourierClient.delete(courierId);
    }



    @Test
    @DisplayName("Check creating new courier")
    @Description("Creating new courier")
    public void checkCourierCanBeCreatedTest() {
        Courier courier = Courier.getRandom();


        ValidatableResponse response = courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier));
        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");


        assertTrue("Courier is not created", isCourierCreated);
        assertThat("Status code is incorrect", statusCode, equalTo(201));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }

    @Test
    @DisplayName("Check duplicate courier can not be created")
    @Description("Creating a courier with the first name, login and password of an existing courier")
    public void duplicateCourierCannotBeCreated() {
        courierClient.create(courier);
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        String thisLoginIsUsed = "Этот логин уже используется";

        String expectedMessage = response.extract().path("message");

        assertThat("Ожидаемый текст ошибки: " + thisLoginIsUsed + ". Фактический: " + expectedMessage,
                expectedMessage, (equalTo(thisLoginIsUsed)));

        assertThat("Status code is incorrect", statusCode, equalTo(409));

    }


}
