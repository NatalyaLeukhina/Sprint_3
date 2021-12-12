package praktikum.courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.client.CourierClient;
import praktikum.data.Courier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



@RunWith(Parameterized.class)
public class CourierCreateRequestValidationTest {
    public Courier courier;
    public int expectedStatus;
    public String expectedMessage;
    public ValidatableResponse courierId;

    public CourierCreateRequestValidationTest(Courier courier, int expectedStatus, String expectedMessage) {
        this.courier = courier;
        this.expectedStatus = expectedStatus;
        this.expectedMessage = expectedMessage;

    }

    @Parameterized.Parameters

    public static Object[][] getTestData() {
        return new Object[][] {
                {Courier.getWithLoginOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithPasswordOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithPasswordAndLogin(), 201, null}
    };

    }
    @After
    public void tearDown() {
        CourierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check creating new courier without login or password or first name")
    @Description("Creating new courier with only login, with only password, with login and password without first name")
    public void  invalidRequestIsNotAllowedWithLoginOnly() {

        ValidatableResponse response = new CourierClient().create(courier);

        String message = response.extract().path("message");
        int statusCode = response.extract().statusCode();

        assertThat("Status code is incorrect", statusCode, equalTo(expectedStatus));
        assertThat("Message is incorrect", message, equalTo(expectedMessage));

    }

}
