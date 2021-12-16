package praktikum.client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.data.Order;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {


    private static final String ORDER_PATH = "api/v1/orders";

    @Step
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();


    }

    @Step
    public ValidatableResponse cancel(int track) {
        return given()
                .spec(getBaseSpec())
                .body(track)
                .when()
                .put(ORDER_PATH + "/cancel")
                .then();


    }

    @Step
    public ValidatableResponse getOrdersList(List<Object> orders) {
        return given()
                .spec(getBaseSpec())
                .when()
                .queryParam("courierId")
                .get(ORDER_PATH)
                .then()
                .log().body();




    }

}

