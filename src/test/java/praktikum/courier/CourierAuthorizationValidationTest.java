package praktikum.courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.client.CourierClient;
import praktikum.data.Courier;
import praktikum.data.CourierCredentials;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(Parameterized.class)
    public class CourierAuthorizationValidationTest {
        public Courier courier;
        public CourierClient courierClient;
        public int expectedStatus;
        public String expectedMessage;
        public ValidatableResponse courierId;


        public CourierAuthorizationValidationTest(Courier courier, int expectedStatus, String expectedMessage) {
            this.courier = courier;
            this.expectedStatus = expectedStatus;
            this.expectedMessage = expectedMessage;

        }

        @Parameterized.Parameters

        public static Object[][] getTestData() {
            return new Object[][] {
                    {Courier.getWithLoginOnly(), 400, "Недостаточно данных для входа"},
                    {Courier.getWithPasswordOnly(), 400, "Недостаточно данных для входа"},
                    {Courier.getWithPasswordAndLogin(), 404, "Учетная запись не найдена"}
            };

        }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

        @After
        public void tearDown() {
            CourierClient.delete(courierId);
        }

        @Test
        @DisplayName("Check courier authorization without filling in the required fields")
        @Description("Courier can not be log in with only login, with only password, with a non-existent username and password")
        public void  invalidRequestIsNotLogIn() {

            courierId = courierClient.login(CourierCredentials.from(courier));
            String message = courierId.extract().path("message");
            int status = courierId.extract().statusCode();

            assertThat("Status code is incorrect", status, equalTo(expectedStatus));
            assertThat("Message is incorrect", message, equalTo(expectedMessage));

        }

    }

